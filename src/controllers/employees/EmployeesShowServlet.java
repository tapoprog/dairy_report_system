package controllers.employees;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;


@WebServlet("/employees/show")
public class EmployeesShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public EmployeesShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        Employee my = (Employee)request.getSession().getAttribute("login_employee");


        long follows_count = (long)em.createNamedQuery("getMyFollowsCount", Long.class)
                .setParameter("followEmployee", my)
                .setParameter("followerEmployee", e)
                .getSingleResult();

        em.close();

        request.setAttribute("employee", e);
        request.setAttribute("follows_count",follows_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/show.jsp");
        rd.forward(request, response);
    }

}
