package servlet;

import java.io.IOException;
import java.io.PrintWriter; // 8-⑥ 알림창 요구사항
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ClassDAO;
import model.ClassDTO;
import model.MemberDAO;
import model.MemberDTO;
import model.TeacherDAO;
import model.TeacherDTO;

@WebServlet("/class_add.do")
public class ClassAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClassAddServlet() {
        super();
    }

    /**
     * GET 요청: 수강신청 폼(add.jsp)을 로드
     * (Dropdown 생성을 위해 회원목록과 강사목록을 DB에서 조회)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. (Req 8-①) 회원 목록 조회
        MemberDAO mDao = MemberDAO.getInstance();
        List<MemberDTO> memberList = mDao.selectAllMembers();
        
        // 2. (Req 8-③) 강사(강의) 목록 조회
        TeacherDAO tDao = TeacherDAO.getInstance();
        List<TeacherDTO> teacherList = tDao.selectAllTeachers();
        
        // 3. request 객체에 목록 저장
        request.setAttribute("memberList", memberList);
        request.setAttribute("teacherList", teacherList);
        
        // 4. /class/add.jsp로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/class/add.jsp");
        rd.forward(request, response);
    }

    /**
     * POST 요청: 폼 데이터 DB에 삽입 (수강신청 처리)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 한글 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 폼 데이터를 DTO에 매핑
        ClassDTO dto = new ClassDTO();
        dto.setRegist_month(request.getParameter("regist_month"));
        dto.setC_no(request.getParameter("c_no"));
        dto.setClass_area(request.getParameter("class_area"));
        // 수강료는 JS에서 계산되어 넘어온 값
        dto.setTuition(Integer.parseInt(request.getParameter("tuition")));
        dto.setTeacher_code(request.getParameter("teacher_code"));

        // 3. DAO를 통해 DB에 삽입
        ClassDAO dao = ClassDAO.getInstance();
        int rowCount = dao.insertClass(dto);

        // 4. (Req 8-⑥) 결과 처리: 알림창 띄우고 홈으로 이동
        // sendRedirect로는 alert를 띄울 수 없으므로,
        // 응답(response)으로 JavaScript 코드를 직접 출력합니다.
        
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        if (rowCount > 0) {
            out.println("alert('수강신청이 정상적으로 등록 되었습니다!');");
            out.println("location.href='" + request.getContextPath() + "/';");
        } else {
            out.println("alert('수강신청 등록에 실패했습니다.');");
            out.println("history.back();"); // 실패 시 이전 페이지(입력폼)로
        }
        out.println("</script>");
        out.flush();
    }
}