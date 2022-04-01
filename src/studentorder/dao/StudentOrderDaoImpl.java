package studentorder.dao;

import studentorder.config.Config;
import studentorder.domain.*;
import studentorder.exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentOrderDaoImpl implements StudentOrderDao {

    private static final String INSERT_ORDER =
            "INSERT INTO jc_student_order(" +
                    "student_order_id, student_order_status, student_order_date, " +
                    "h_sur_name, h_given_name, h_patronymic, h_date_of_birthday, " +
                    "h_passport_seria, h_passport_number, h_passport_date, " +
                    "h_passport_office_id, h_post_index, h_street_code, h_building, " +
                    "h_extension, h_apartment, h_university_id, h_student_number, " +
                    "w_sur_name, w_given_name, w_patronymic, w_date_of_birthday, " +
                    "w_passport_seria, w_passport_number, w_passport_date, " +
                    "w_passport_office_id, w_post_index, w_street_code, w_building, " +
                    "w_extension, w_apartment, w_university_id, w_student_number, " +
                    "certificate_id, register_office_id, marriage_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_CHILD =
            "INSERT INTO jc_student_child(" +
                    "student_order_id, c_sur_name, c_given_name, " +
                    "c_patronymic, c_date_of_birthday, c_certificate_number, " +
                    "c_certificate_date, c_register_office_id, c_post_index, " +
                    "c_street_code, c_building, c_extension, c_apartment)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ORDERS =
            "select so.*, ro.r_office_area_id, ro.r_office_name, " +
                    "po_h.p_office_area_id as h_p_office_area_id, " +
                    "po_h.p_office_name as h_p_office_name," +
                    "po_w.p_office_area_id as w_p_office_area_id, " +
                    "po_w.p_office_name as w_p_office_name " +
                    "from jc_student_order so " +
                    "inner join jc_register_office ro on ro.r_office_id = so.register_office_id " +
                    "inner join jc_passport_office po_h on po_h.p_office_id = so.h_passport_office_id " +
                    "inner join jc_passport_office po_w on po_w.p_office_id = so.w_passport_office_id " +
                    "where student_order_status = 0 " +
                    "order by student_order_date ";

    private static final String SELECT_CHILD =
            "select soc. *, ro.r_office_area_id, ro.r_office_name " +
                    "from jc_student_child soc " +
                    "inner join jc_register_office ro on r_office_id = soc.c_register_office_id " +
                    "where student_order_id in ";

    //TODO refactoring - make one method
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperties(Config.DB_URL),
                Config.getProperties(Config.DB_LOGIN),
                Config.getProperties(Config.DB_PASSWORD)
        );
    }

    @Override
    public Long saveStudentOrder(StudentOrder so) throws DaoException {
        long result = -1L;
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[]{"student_order_id"})) {

            con.setAutoCommit(false);//транзакция

            try {
                //Header
                stmt.setInt(1, StudentOrderStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                //Husband
                setParamsForAdult(stmt, 3, so.getHusband());
                // Wife
                setParamsForAdult(stmt, 18, so.getWife());
                //Marriage
                stmt.setString(33, so.getMarriageCertificateId());
                stmt.setLong(34, so.getMarriageOffice().getOfficeId());
                stmt.setDate(35, java.sql.Date.valueOf(so.getMarriageDate()));

                stmt.executeUpdate();//возвращает целое число, сколько было изменений

                ResultSet gkRs = stmt.getGeneratedKeys();
                if (gkRs.next()) {
                    result = gkRs.getLong(1);
                }
                gkRs.close();

                saveChildren(con, so, result);

                con.commit();
            } catch (SQLException e) {
                con.rollback();//все изменеия не проходят
                throw new DaoException(e);
            }

            saveChildren(con, so, result);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<StudentOrder> getStudentOrders() throws DaoException {
        List<StudentOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)) {
            stmt.setInt(1, StudentOrderStatus.START.ordinal());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                StudentOrder so = new StudentOrder();
                fillStudentOrder(rs, so);
                fillMarriage(rs, so);

                Adult husband = fillAdult(rs, "h_");
                Adult wife = fillAdult(rs, "w_");
                so.setHusband(husband);
                so.setWife(wife);

                result.add(so);
            }
            findChildren(con, result);

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private void findChildren(Connection con, List<StudentOrder> result) throws SQLException {
        String cl = "(" + result.stream().map(so -> String.valueOf(so.getStudentOrderId()))
                .collect(Collectors.joining(",")) + ")";
        Map<Long, StudentOrder> maps = result.stream()
                .collect(Collectors
                        .toMap(StudentOrder::getStudentOrderId, so -> so));
        try (PreparedStatement stmt = con.prepareStatement(SELECT_CHILD + cl)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Child ch = fillChild(rs);
                StudentOrder so = maps.get(rs.getLong("student_order_id"));
                so.addChild(ch);
            }
        }
    }

    private Child fillChild(ResultSet rs) throws SQLException {
        String surName = rs.getString("c_surName");
        String givenName = rs.getString("c_given_name");
        String patronymic = rs.getString("c_patronymic");
        LocalDate dateOfBirth = rs.getDate("c_date_of_birth").toLocalDate();

        Child child = new Child(surName, givenName, patronymic, dateOfBirth);
        child.setCertificateNumber(rs.getString("c_certificate_number"));
        child.setIssueDate(rs.getDate("c_certificate_date").toLocalDate());

        Long roId = rs.getLong("c_register_office_id");
        String roArea = rs.getString("r_office_area_id");
        String roName = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, roArea, roName);
        child.setIssueDepartment(ro);
        child.setAddress(fillAddress(rs, "c_"));
        return child;
    }

    private void saveChildren(Connection con, StudentOrder so, long soId) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement(INSERT_CHILD)) {

            for (Child child : so.getChildren()) {
                stmt.setLong(1, soId);
                setParamsForChild(stmt, child);
                stmt.addBatch();//добавление в пакет записей
            }
            stmt.executeBatch();//выгрузка из пакетов
        }
    }

    private void setParamsForChild(PreparedStatement stmt, Child child) throws SQLException {
        setParamsForPerson(stmt, 2, child);
        stmt.setString(6, child.getCertificateNumber());
        stmt.setDate(7, java.sql.Date.valueOf(child.getIssueDate()));
        stmt.setLong(8, child.getIssueDepartment().getOfficeId());
        setParamsForAddress(stmt, 9, child);

    }

    private void setParamsForAdult(PreparedStatement stmt, int start, Adult adult) throws SQLException {
        start = setParamsForPerson(stmt, start, adult);
        stmt.setString(start++, adult.getPassportSeria());
        stmt.setString(start++, adult.getPassportNumber());
        stmt.setDate(start++, Date.valueOf(adult.getIssueDate()));
        stmt.setLong(start++, adult.getIssueDepartment().getOfficeId());
        stmt.setLong(start++, adult.getUniversity().getUniversityId());
        stmt.setString(start++, adult.getStudentId());
        setParamsForAddress(stmt, start, adult);
    }

    private int setParamsForPerson(PreparedStatement stmt, int start, Person person) throws SQLException {
        stmt.setString(start++, person.getSurName());
        stmt.setString(start++, person.getName());
        stmt.setString(start++, person.getPatronymic());
        stmt.setDate(start, Date.valueOf(person.getDateOfBirthday()));
        return start;
    }

    private void setParamsForAddress(PreparedStatement stmt, int start, Person person) throws SQLException {
        Address h_address = person.getAddress();
        stmt.setString(start++, h_address.getPostCode());
        stmt.setLong(start++, h_address.getStreet().getStreet_code());
        stmt.setString(start++, h_address.getBuilding());
        stmt.setString(start++, h_address.getExtension());
        stmt.setString(start, h_address.getApartment());
    }

    private Adult fillAdult(ResultSet rs, String pref) throws SQLException {
        Adult adult = new Adult();
        adult.setSurName(rs.getString(pref + "sur_name"));
        adult.setName(rs.getString(pref + "given_name"));
        adult.setPatronymic(rs.getString(pref + "patronymic"));
        adult.setDateOfBirthday(rs.getDate(pref + "date_of_birthday").toLocalDate());
        adult.setPassportSeria(rs.getString(pref + "passport_seria"));
        adult.setPassportNumber(rs.getString(pref + "passport_number"));
        adult.setIssueDate(rs.getDate(pref + "passport_date").toLocalDate());
        adult.setIssueDepartment(fillPassportOffice(rs, pref));
        adult.setAddress(fillAddress(rs, pref));
        adult.setUniversity(fillUniversity(rs, pref));
        adult.setStudentId(rs.getString(pref + "student_number"));
        return adult;
    }

    private University fillUniversity(ResultSet rs, String pref) throws SQLException {
        return new University(
                rs.getLong(pref + "university_id"),
                rs.getString(pref + "university_name"));
    }

    private Street fillStreet(ResultSet rs, String pref) throws SQLException {
        long str = rs.getLong(pref + "street_code");
        String strName = rs.getString(pref + "street_name");
        return new Street(str, strName);
    }

    private PassportOffice fillPassportOffice(ResultSet rs, String pref) throws SQLException {
        Long poId = rs.getLong(pref + "passport_office_id");
        String poArea = rs.getString(pref + "p_office_area_id");
        String poName = rs.getString(pref + "p_office_name");
        return new PassportOffice(poId, poArea, poName);
    }

    private Address fillAddress(ResultSet rs, String pref) throws SQLException {
        Address adr = new Address();
        adr.setPostCode(rs.getString(pref + "post_index"));
        adr.setStreet(fillStreet(rs, pref));
        adr.setBuilding(rs.getString(pref + "building"));
        adr.setExtension(rs.getString(pref + "extension"));
        adr.setApartment(rs.getString(pref + "apartment"));
        return adr;
    }

    private void fillStudentOrder(ResultSet rs, StudentOrder so) throws SQLException {
        so.setStudentOrderId(rs.getLong("student_order_id"));
        so.setStudentOrderDate(rs.getTimestamp("student_order_date").toLocalDateTime());
        so.setStudentOrderStatus(StudentOrderStatus.fromValue(rs.getInt("student_order_status")));

    }

    private void fillMarriage(ResultSet rs, StudentOrder so) throws SQLException {
        so.setMarriageCertificateId(rs.getString("certificate_id"));
        so.setMarriageDate(rs.getDate("marriage_date").toLocalDate());
        Long roId = rs.getLong("register_office_id");
        String areaId = rs.getString("r_office_area_id");
        String name = rs.getString("r_office_name");
        RegisterOffice ro = new RegisterOffice(roId, areaId, name);
        so.setMarriageOffice(ro);
    }
}
