package studentorder.dao;

import studentorder.domain.Street;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImpl {

    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jc_student",
                "postgres", "1234"
        );
        return con;
    }

    public List<Street> findStreets(String pattern) throws Exception {
        List<Street> result = new LinkedList<>();
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        String sql = "select street_code, street_name \n" +
                "from jc_street\n" +
                "where upper(street_name)\n" +
                "like upper('%"+ pattern +"%');";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Street str = new Street(rs.getLong("street_code"),
                    rs.getString("street_name"));
            result.add(str);
        }
        return result;
    }
}
