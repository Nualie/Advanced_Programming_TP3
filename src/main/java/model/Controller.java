/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tp2.DBActions;
import tp2.TextConstants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author JAA
 */
public class Controller extends HttpServlet {
//TODO analyze and challenge those variable declarations
    private DBActions dba;
    private InputStream input;
    private String dbUrl = "";
    private String dbUser = "";
    private String dbPwd = "";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Properties prop = new Properties();
        input = getServletContext().getResourceAsStream(TextConstants.DB_CONFIG_PATH);
        prop.load(input);

        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbPwd = prop.getProperty("dbPwd");

        if (request.getParameter("action") == null) {
            request.getRequestDispatcher(TextConstants.JSP_HOME_PAGE).forward(request, response);
        } else {
            dba = new DBActions(dbUrl, dbUser, dbPwd);

            ServletContext ctx = this.getServletContext();
            ServletConfig conf = this.getServletConfig();
            String loginCtx = ctx.getInitParameter("login");
            String pwdCtx = conf.getInitParameter("password");

            // if (dba.checkCredentials(userInput)) {
            if (loginCtx.equals(request.getParameter(TextConstants.FORM_LOGIN_FIELD)) &&
                    pwdCtx.equals(request.getParameter(TextConstants.FORM_PWD_FIELD))) {
                request.setAttribute("empList", dba.getEmployees());
                request.getRequestDispatcher(TextConstants.JSP_WELCOME_PAGE).forward(request, response);
            } else {
                request.setAttribute("errKey", TextConstants.ERROR_MESSAGE);
                request.getRequestDispatcher(TextConstants.JSP_HOME_PAGE).forward(request, response);
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
