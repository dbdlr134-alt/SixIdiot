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

// header.jsp의 '회원등록' 링크와 /member/add.jsp의 form action과 매핑
@WebServlet("/member_add.do")
public class MemberAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberAddServlet() {
        super();
    }

    /**
     * GET 요청 시: 회원 등록 폼(/member/add.jsp)으로 포워딩
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // "모든 jsp파일은 webapp안에 있어" 참고 -> /member/add.jsp
        RequestDispatcher rd = request.getRequestDispatcher("/member/add.jsp");
        rd.forward(request, response);
    }

    /**
     * POST 요청 시: 폼에서 전송된 데이터 처리
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 한글 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 폼 데이터(add.jsp)를 DTO 객체에 매핑
        MemberDTO dto = new MemberDTO();
        dto.setC_no(request.getParameter("c_no"));
        dto.setC_name(request.getParameter("c_name"));
        dto.setPhone(request.getParameter("phone"));
        dto.setAddress(request.getParameter("address"));
        dto.setRegist_date(request.getParameter("regist_date"));
        dto.setC_type(request.getParameter("c_type"));

        // 3. DAO를 통해 데이터베이스에 삽입
        MemberDAO dao = MemberDAO.getInstance();
        int rowCount = dao.insertMember(dto);

        // 4. 결과 처리 (성공 시 회원 목록 페이지로 리다이렉트)
        if (rowCount > 0) {
            // 등록 성공 시, 추후 만들 '회원조회' 서블릿으로 이동
            response.sendRedirect(request.getContextPath() + "/member/list.do");
        } else {
            // 등록 실패 시, 다시 등록 폼(GET)으로 이동
            response.sendRedirect(request.getContextPath() + "/member_add.do");
        }
    }
}