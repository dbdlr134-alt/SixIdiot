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

// /teacher/add.jsp의 form action과 매핑
@WebServlet("/teacher_add.do") 
public class TeacherAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TeacherAddServlet() {
        super();
    }

    /**
     * GET 요청 시: 강사 등록 폼(add.jsp)으로 포워딩
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // /teacher/add.jsp 페이지로 이동
        RequestDispatcher rd = request.getRequestDispatcher("/teacher/add.jsp");
        rd.forward(request, response);
    }

    /**
     * POST 요청 시: 폼에서 전송된 데이터 처리
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. 한글 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 2. 폼 데이터(add.jsp)를 DTO 객체에 매핑
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacher_code(request.getParameter("teacher_code"));
        dto.setTeacher_name(request.getParameter("teacher_name"));
        dto.setClass_name(request.getParameter("class_name"));
        // 수강료는 숫자(int)이므로 형변환 
        dto.setClass_price(Integer.parseInt(request.getParameter("class_price")));
        dto.setTeacher_regist_date(request.getParameter("teacher_regist_date"));

        // 3. DAO를 통해 데이터베이스에 삽입
        TeacherDAO dao = TeacherDAO.getInstance();
        int rowCount = dao.insertTeacher(dto);

        // 4. 결과 처리 (성공 시 강사 목록 페이지로 리다이렉트)
        if (rowCount > 0) {
            // 등록 성공 시, 강사 조회 페이지(list.jsp)로 이동
            response.sendRedirect(request.getContextPath() + "/teacher/list.jsp");
        } else {
            // 등록 실패 시, 다시 등록 폼으로 이동 (에러 메시지 전달도 가능)
            response.sendRedirect(request.getContextPath() + "/teacher_add.do");
        }
    }
}