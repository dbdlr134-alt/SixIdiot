package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TeacherDAO;
import model.TeacherSalesDTO; // 새로 만든 DTO import

// header.jsp의 '강사별매출통계' 링크와 연결
@WebServlet("/stats/sales.do")
public class TeacherSalesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TeacherSalesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. DAO를 통해 통계 데이터 조회
        TeacherDAO dao = TeacherDAO.getInstance();
        List<TeacherSalesDTO> salesList = dao.selectTeacherSales();
        
        // 2. request 객체에 목록 저장 ("salesList"라는 이름으로)
        request.setAttribute("salesList", salesList);
        
        // 3. /stats/sales.jsp로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/stats/sales.jsp");
        rd.forward(request, response);
    }
}