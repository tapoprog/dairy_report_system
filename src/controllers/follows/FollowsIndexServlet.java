package controllers.follows;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;


@WebServlet("/follows/index")
public class FollowsIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public FollowsIndexServlet() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("id")));

        int page;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        } catch(Exception e2) {
            page = 1;
        }
        List<Follow> follows = em.createNamedQuery("getMyAllFollows", Follow.class)
                    .setParameter("followEmployee",e)
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();

        long follows_count = (long)em.createNamedQuery("getMyFollowsCount", Long.class)
                                    .setParameter("followEmployee", e)
                                    .getSingleResult();

        em.close();

        request.setAttribute("employee",e);
        request.setAttribute("follows", follows);
        request.setAttribute("follows_count", follows_count);
        request.setAttribute("page", page);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/index.jsp");
        rd.forward(request, response);
    }

}
