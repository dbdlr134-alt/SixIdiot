package servlet;

import java.io.IOException;
import java.util.List; // List 임포트
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.StatisticsDTO; // DTO 임포트
import model.VacResvDAO; // DAO 임포트

@WebServlet("/StatisticsServlet")
public class StatisticsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StatisticsServlet() {
        super();
    }

    // 메뉴 링크를 통한 요청이므로 doGet()에서 처리
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. DAO 호출
        VacResvDAO dao = VacResvDAO.getInstance();
        List<StatisticsDTO> statList = dao.getStatistics();
        
        // 2. 결과를 request에 속성으로 저장
        request.setAttribute("statList", statList);
        
        // 3. statistics.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("statistics.jsp");
        dispatcher.forward(request, response);
    }
}