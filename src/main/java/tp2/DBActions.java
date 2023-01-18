package tp2;

import java.sql.*;
import java.util.ArrayList;

import static tp2.TextConstants.QUERY_SELECT_ALL_EMPLOYEES;

/**
 *
 * @author JAA
 */
public class DBActions {
    private Connection conn;
    private Statement statement;
    private ResultSet queryResult;

    public DBActions(String dbUrl, String dbUser, String dbPwd) {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            System.out.println(sqle.getStackTrace());
        }

    }

    private Statement getStatement() {
        try {
            statement = conn.createStatement();
        } catch (SQLException sqle) {
            System.out.println(sqle.getErrorCode());
        }
        return statement;

    }

    private ResultSet getResultSet(String query) {
        statement = getStatement();
        try {
            queryResult = statement.executeQuery(query);
        } catch (SQLException sqle) {
            System.out.println(sqle.getErrorCode());
        }
        return queryResult;

    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> Employees = new ArrayList<>();
        queryResult = getResultSet(QUERY_SELECT_ALL_EMPLOYEES);
        try {
            while (queryResult.next()) {
                Employee oneSingleEmployee = new Employee();
                oneSingleEmployee.setFirstname(queryResult.getString("FIRSTNAME"));
                oneSingleEmployee.setLastName(queryResult.getString("LASTNAME"));
                oneSingleEmployee.setEmail(queryResult.getString("EMAIL"));
                oneSingleEmployee.setCity(queryResult.getString("CITY"));

                Employees.add(oneSingleEmployee);
            }
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return Employees;
    }
}
