package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VacResvDAO;
import model.VacResvDTO;

// @WebServlet 어노테이션으로 서블릿을 URL과 매핑합니다.
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReservationServlet() {
        super();
    }

    // 폼이 'post' 방식이므로 doPost() 메소드에서 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // 2. 폼 데이터(파라미터) 받기
        String resvno = request.getParameter("resvno");
        String jumin = request.getParameter("jumin");
        String vcode = request.getParameter("vcode");
        String hospcode = request.getParameter("hospcode");
        String resvdate = request.getParameter("resvdate");
        String resvyime = request.getParameter("resvyime");

        // 3. DTO 객체 생성 및 데이터 저장
        VacResvDTO dto = new VacResvDTO();
        dto.setResvno(resvno);
        dto.setJumin(jumin);
        dto.setVcode(vcode);
        dto.setHospcode(hospcode);
        dto.setResvdate(resvdate);
        dto.setResvyime(resvyime);

        // 4. DAO 객체 호출 (싱글톤)
        VacResvDAO dao = VacResvDAO.getInstance();
        int result = dao.insertReservation(dto);

        // 5. 결과 처리 (요구사항: 알림창 후 홈으로 이동 [cite: 41])
        PrintWriter out = response.getWriter();
        out.println("<script>");
        if (result == 1) { // 1이면 등록 성공
            out.println("alert('접종예약정보가 등록되었습니다!');");
            out.println("location.href='index.jsp';");
        } else { // 0이면 등록 실패
            out.println("alert('등록에 실패했습니다. 다시 시도해주세요.');");
            out.println("history.back();"); // 이전 페이지(입력 폼)로 돌아가기
        }
        out.println("</script>");
        out.close();
    }
}