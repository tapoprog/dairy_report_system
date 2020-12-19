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

        List<Follow> follows = em.createNamedQuery("getAllFollows", Follow.class).getResultList();

        em.close();

        request.setAttribute("follows", follows);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/index.jsp");
        rd.forward(request, response);
    }

}
