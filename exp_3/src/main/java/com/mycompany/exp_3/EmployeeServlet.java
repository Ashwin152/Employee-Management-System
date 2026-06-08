package com.mycompany;

import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addEmployee")
public class EmployeeServlet extends HttpServlet {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("employeePU");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        double salary = Double.parseDouble(request.getParameter("salary"));

        EntityManager em = emf.createEntityManager();

        Employee emp = new Employee(name, email, salary);

        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();

        em.close();

        out.println("<h2>Employee Inserted Successfully</h2>");
        out.println("<a href='index.html'>Add Another Employee</a>");
    }
}