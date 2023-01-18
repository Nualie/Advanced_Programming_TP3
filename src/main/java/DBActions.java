package com.apr2023.model;

import java.sql.*;
import java.util.ArrayList;

import static com.apr2023.utils.TextConstants.QUERY_SELECT_ALL_EMPLOYEES;

/**
 *
 * @author JAA
 */
public class DBActions {
    //TODO correct level of encapsulation ?DONE
    //TODO initialization not required ?DONE
    private Connection conn;
    private Statement statement;
    private ResultSet queryResult;

    public DBActions(String dbUrl, String dbUser, String dbPwd) {
        try {
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
        } catch (SQLException sqle) { //TODO why not just catch Exception e ? it's more specificDONE
                //TODO add error codeDONE
            System.out.println(sqle.getMessage());
            System.out.println(sqle.getStackTrace());
        }

    }

    private Statement getStatement() {
        try {
            statement = conn.createStatement(); // TODO DONE
                                                // createStatement is declared in an interface (Connection).
                                                // So why is this possible?
                                                //it's an instance
        } catch (SQLException sqle) { //TODO why not just catch Exception e ?DONE
            //TODO add error codeDONE
            System.out.println(sqle.getErrorCode());
        }
        return statement;

    }

    private ResultSet getResultSet(String query) {
        statement = getStatement();
        try {
            queryResult = statement.executeQuery(query);
        } catch (SQLException sqle) { //TODO why not just catch Exception e ?DONE
            //TODO add error codeDONE
            System.out.println(sqle.getErrorCode());
        }
        return queryResult;

    }

    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> Employees = new ArrayList<>();  //TODO local vs global variableDONE
        queryResult = getResultSet(QUERY_SELECT_ALL_EMPLOYEES);
        try {
            while (queryResult.next()) {
                Employee oneSingleEmployee = new Employee();
                oneSingleEmployee.setFirstname(queryResult.getString("FIRSTNAME"));
                oneSingleEmployee.setLastName(queryResult.getString("LASTNAME"));
                oneSingleEmployee.setEmail(queryResult.getString("EMAIL"));
                oneSingleEmployee.setCity(queryResult.getString("CITY"));
                //TODO add code to retrieve the last name, the email and the cityDONE

                Employees.add(oneSingleEmployee);
            }
        } catch (SQLException sqle) { //TODO why not just catch Exception e ?DONE
            //TODO add error codeDONE
            System.out.println(sqle.getMessage());
        }
        return Employees;
    }
}
