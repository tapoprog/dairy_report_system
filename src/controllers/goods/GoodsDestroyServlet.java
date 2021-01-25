package controllers.goods;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Good;
import models.Report;
import utils.DBUtil;

@WebServlet("/goods/destroy")
public class GoodsDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GoodsDestroyServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Report r = em.find(Report.class, Integer.parseInt(request.getParameter("report_id")));

         // Integer型でパラメータを受け取る
            Integer g = Integer.valueOf(request.getParameter("good_id"));
            // それを使ってSQL実施、情報を取り出す
            Good a = em.find(Good.class,g);


            em.getTransaction().begin();
            em.remove(a);       // データ削除
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("good_id");

            response.sendRedirect(request.getContextPath() + "/reports/show?id="+ r.getId());
        }
    }

}
