package controllers.follows;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;


@WebServlet("/follows/destroy")
public class FollowsDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public FollowsDestroyServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Employee e = em.find(Employee.class, Integer.parseInt(request.getParameter("employeeId")));

            // Integer型でパラメータを受け取る
            Integer f = Integer.valueOf(request.getParameter("follow_id"));
            // それを使ってSQL実施、情報を取り出す
            Follow a = em.find(Follow.class,f);

            em.getTransaction().begin();
            em.remove(a);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("follow_id");

            response.sendRedirect(request.getContextPath() + "/employees/show?id="+ e.getId());
        }
    }

}
