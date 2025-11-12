package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TeacherDAO;
import model.TeacherDTO;

// edit.jsp의 <form action="${root}/teacher_update.do" ...> 와 연결
@WebServlet("/teacher_update.do")
public class TeacherUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TeacherUpdateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 한글 인코딩
        request.setCharacterEncoding("UTF-8");

        // 2. 폼 데이터(edit.jsp)를 DTO 객체에 매핑
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacher_code(request.getParameter("teacher_code")); // readonly 필드
        dto.setTeacher_name(request.getParameter("teacher_name"));
        dto.setClass_name(request.getParameter("class_name"));
        dto.setClass_price(Integer.parseInt(request.getParameter("class_price")));
        dto.setTeacher_regist_date(request.getParameter("teacher_regist_date"));

        // 3. DAO를 통해 데이터베이스 수정
        TeacherDAO dao = TeacherDAO.getInstance();
        dao.updateTeacher(dto); // updateTeacher 실행 (결과 값은 생략)

        // 4. 수정 완료 후, 강사 조회 페이지(list.do)로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/teacher/list.do");
    }
}