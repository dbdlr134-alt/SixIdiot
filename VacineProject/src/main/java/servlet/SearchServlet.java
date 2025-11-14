package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ReservationDetailDTO; // 상세 DTO 임포트
import model.VacResvDAO; // DAO 임포트

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. 인코딩 설정 (search.jsp가 post 방식이므로)
        request.setCharacterEncoding("UTF-8");

        // 2. 파라미터(예약번호) 받기
        String resvno = request.getParameter("resvno");
        
        // 3. DAO 호출
        VacResvDAO dao = VacResvDAO.getInstance();
        ReservationDetailDTO dto = dao.getReservationDetails(resvno);
        
        // 4. 결과(dto)를 request에 속성으로 저장
        //    (result.jsp에서 이 데이터를 꺼내 쓸 수 있도록)
        if (dto != null) {
            // 조회 성공 시 DTO 객체를 "detail"이라는 이름으로 저장
            request.setAttribute("detail", dto);
        } else {
            // 조회 실패(dto가 null) 시, 실패를 알릴 수 있도록 예약번호를 저장
            // (JSP에서 '예약번호 20210003의...' 메시지를 띄우기 위함)
            request.setAttribute("searchedResvno", resvno);
        }

        // 5. result.jsp로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }
}