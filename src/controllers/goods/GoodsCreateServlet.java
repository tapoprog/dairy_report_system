package controllers.goods;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Good;
import models.Report;
import utils.DBUtil;

@WebServlet("/goods/create")
public class GoodsCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GoodsCreateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Good g = new Good();

            g.setGoodEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Report a = em.find(Report.class, Integer.parseInt(request.getParameter("reportId")));
            Integer r = Integer.parseInt(request.getParameter("reportId"));
            g.setGoodReportId(r);

            em.getTransaction().begin();
            em.persist(g);
            em.getTransaction().commit();
            em.close();

            response.sendRedirect(request.getContextPath() + "/reports/show?id="+ a.getId());
        }
    }
}
