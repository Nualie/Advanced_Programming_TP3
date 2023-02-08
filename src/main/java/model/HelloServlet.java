package model;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "HelloServlet", value = "/HelloServlet")
public class HelloServlet extends HttpServlet {

    @EJB
    EmployeeSessionBean employeeSessionBean;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Collection<EmployeeEntity> employeeEntities = employeeSessionBean.getAllEmployees();
        for(EmployeeEntity employee : employeeEntities){
            System.out.println("Name of employee: "+employee.getLastName());
        }

    }

    public void init(){};




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}
