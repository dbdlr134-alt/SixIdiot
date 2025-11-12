package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TeacherDAO;
import model.TeacherDTO;


@WebServlet("/teacher/edit.do")
public class TeacherEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TeacherEditServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. list.jsp에서 보낸 강사코드(code) 파라미터 받기
        String teacherCode = request.getParameter("code");
        
        // 2. DAO를 통해 해당 강사 정보 조회
        TeacherDAO dao = TeacherDAO.getInstance();
        TeacherDTO teacher = dao.selectTeacherByCode(teacherCode);
        
        // 3. request 객체에 조회한 DTO(teacher) 저장
        request.setAttribute("teacher", teacher);
        
        // 4. 수정 폼 페이지(edit.jsp)로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/teacher/edit.jsp");
        rd.forward(request, response);
    }
}