package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/list.do")
public class MemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberListServlet() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. DAO를 통해 전체 회원 목록 조회
        MemberDAO dao = MemberDAO.getInstance();
        List<MemberDTO> memberList = dao.selectAllMembers();
        
        // 2. request 객체에 목록 저장
        request.setAttribute("memberList", memberList);
        
        // 3. /member/list.jsp로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/member/list.jsp");
        rd.forward(request, response);
    }
}