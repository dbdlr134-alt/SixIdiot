package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member/edit.do")
public class MemberEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberEditServlet() { super(); }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. list.jsp에서 보낸 회원번호(no) 파라미터 받기
        String cNo = request.getParameter("no");
        
        // 2. DAO를 통해 해당 회원 정보 조회
        MemberDAO dao = MemberDAO.getInstance();
        MemberDTO member = dao.selectMemberByNo(cNo);
        
        // 3. request 객체에 DTO(member) 저장
        request.setAttribute("member", member);
        
        // 4. 수정 폼 페이지(/member/edit.jsp)로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/member/edit.jsp");
        rd.forward(request, response);
    }
}