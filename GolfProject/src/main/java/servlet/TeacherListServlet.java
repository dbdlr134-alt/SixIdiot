package servlet;

import java.io.IOException;
import java.util.List; // List import

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TeacherDAO;
import model.TeacherDTO;

// header.jsp의 '강사조회' 링크와 연결될 URL
@WebServlet("/teacher/list.do") 
public class TeacherListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TeacherListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. DAO를 통해 강사 목록을 DB에서 가져옴
        TeacherDAO dao = TeacherDAO.getInstance();
        List<TeacherDTO> teacherList = dao.selectAllTeachers();
        
        // 2. request 객체에 조회된 목록(teacherList)을 속성으로 저장
        request.setAttribute("teacherList", teacherList);
        
        // 3. 데이터를 출력할 /teacher/list.jsp 페이지로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/teacher/list.jsp");
        rd.forward(request, response);
    }

    // POST 요청은 별도로 처리하지 않으므로 doGet을 호출하게 둡니다.
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}