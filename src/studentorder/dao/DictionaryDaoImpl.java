package studentorder.dao;

import studentorder.config.Config;
import studentorder.domain.PassportOffice;
import studentorder.domain.RegisterOffice;
import studentorder.domain.Street;
import studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl implements DictionaryDao {
    private static final String GET_PASSPORT=
            "select * from jc_passport_office where p_office_area_id = ?";
    private static final String GET_REGISTER =
            "select * from jc_register_office where r_office_area_id = ?";
    private static final String GET_STREET =
            "select * from jc_street where upper(street_name) like upper(?)";
    /*знак вопроса это параметр, который обрабатывается в
      PreparedStatement stmt = con.prepareStatement(GET_STREET)
      stmt.setString(1, pattern);
            */

    //соединение м базой данных
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                Config.getProperties(Config.DB_URL),
                Config.getProperties(Config.DB_LOGIN),
                Config.getProperties(Config.DB_PASSWORD)
        );
    }

    //поиск улиц удовлетворяющих условию PATTERN
    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"),
                        rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaID) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER)) {

            stmt.setString(1, areaID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RegisterOffice str = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaID) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)) {

            stmt.setString(1, areaID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PassportOffice str = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name"));
                result.add(str);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}
