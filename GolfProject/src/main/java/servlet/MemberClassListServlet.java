package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassDAO;
import model.MemberClassViewDTO; // 새로 만든 DTO import

// header.jsp의 '회원별 수강조회' 링크와 연결
@WebServlet("/member/classList.do")
public class MemberClassListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberClassListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. DAO를 통해 3개 테이블 JOIN 결과(View) 조회
        ClassDAO dao = ClassDAO.getInstance();
        List<MemberClassViewDTO> classList = dao.selectMemberClassView();
        
        // 2. request 객체에 목록 저장 ("classList"라는 이름으로)
        request.setAttribute("classList", classList);
        
        // 3. /member/classList.jsp로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/member/classList.jsp");
        rd.forward(request, response);
    }
}