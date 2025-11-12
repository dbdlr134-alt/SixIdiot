package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.MemberDAO;
import model.MemberDTO;

@WebServlet("/member_update.do")
public class MemberUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberUpdateServlet() { super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 한글 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 폼 데이터(edit.jsp)를 DTO에 매핑
        MemberDTO dto = new MemberDTO();
        dto.setC_no(request.getParameter("c_no")); // readonly
        dto.setC_name(request.getParameter("c_name")); // readonly (DAO에서 사용 안 함)
        dto.setPhone(request.getParameter("phone"));
        dto.setAddress(request.getParameter("address"));
        dto.setRegist_date(request.getParameter("regist_date"));
        dto.setC_type(request.getParameter("c_type"));

        // 3. DAO를 통해 DB 수정
        MemberDAO dao = MemberDAO.getInstance();
        dao.updateMember(dto);

        // 4. 수정 완료 후, 회원 조회 페이지(list.do)로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/member/list.do");
    }
}