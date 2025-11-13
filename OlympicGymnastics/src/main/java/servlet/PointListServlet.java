// servlet/PointListServlet.java
package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PointDAO;
import model.PointListDTO;

@WebServlet("/PointListServlet") // 점수 조회 전용 URL
public class PointListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PointListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. DAO를 통해 점수 목록 조회
        PointDAO dao = new PointDAO();
        List<PointListDTO> pointList = dao.getPointList();
        
        // 2. 조회된 목록을 request 객체에 "pointList"라는 이름으로 저장
        request.setAttribute("pointList", pointList);
        
        // 3. 'point_list.jsp'로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/point_list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}