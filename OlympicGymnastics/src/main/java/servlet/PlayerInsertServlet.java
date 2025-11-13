// servlet/PlayerInsertServlet.java
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlayerDAO;
import model.PlayerDTO;

@WebServlet("/PlayerInsertServlet") // 선수 등록 처리 전용 URL
public class PlayerInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PlayerInsertServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청(주소창 직접 입력) 시, 등록 폼으로 되돌려 보냄
        response.sendRedirect(request.getContextPath() + "/player_insert.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. 인코딩 설정 (POST 방식의 한글 깨짐 방지)
        request.setCharacterEncoding("UTF-8");
        
        // 2. 폼 데이터(파라미터) 가져오기
        String player_no = request.getParameter("player_no");
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String nation_code = request.getParameter("nation_code");
        String skill_level = request.getParameter("skill_level");
        
        // 3. DTO 객체 생성 및 데이터 저장
        PlayerDTO dto = new PlayerDTO();
        dto.setPlayer_no(player_no);
        dto.setName(name);
        dto.setBirth(birth);
        dto.setNation_code(nation_code);
        dto.setSkill_level(skill_level);
        
        // 4. DAO 객체 생성 및 insertPlayer 메소드 호출
        PlayerDAO dao = new PlayerDAO();
        int result = dao.insertPlayer(dto);
        
        // 5. 결과에 따른 페이지 이동 (Redirect)
        String nextView = "index.jsp"; // 기본적으로 홈으로 이동
        
        if (result <= 0) {
            // 등록 실패 시, 등록 폼으로 다시 이동
            nextView = "player_insert.jsp";
        }
        
        // getContextPath()를 사용하여 절대 경로로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/" + nextView);
    }
}