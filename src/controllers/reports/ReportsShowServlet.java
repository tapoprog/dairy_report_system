package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;


@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public ReportsShowServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        request.setAttribute("_token", request.getSession().getId());

        Integer g = Integer.parseInt(request.getParameter("id"));
        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        Employee my = (Employee)request.getSession().getAttribute("login_employee");

        try{
            Good goods = (Good)em.createNamedQuery("getMyGood", Good.class)
                                       .setParameter("goodEmployee", my)
                                       .setParameter("goodReportId", g)
                                       .getSingleResult();

             request.setAttribute("goods",goods);
        }
        catch (NoResultException e){
            System.out.println("0件です");
        }

        em.close();

        request.setAttribute("report", r);
        request.setAttribute("login_employee", my);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }


}
