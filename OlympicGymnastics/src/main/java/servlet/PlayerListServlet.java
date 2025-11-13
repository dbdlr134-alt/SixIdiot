// servlet/PlayerListServlet.java
package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlayerDAO;
import model.PlayerListDTO;

@WebServlet("/PlayerListServlet") // 목록 조회 전용 URL
public class PlayerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PlayerListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. DAO를 통해 선수 목록 조회
        PlayerDAO dao = new PlayerDAO();
        List<PlayerListDTO> playerList = dao.getPlayerList();
        
        // 2. 조회된 목록을 request 객체에 "playerList"라는 이름으로 저장
        request.setAttribute("playerList", playerList);
        
        // 3. 'player_list.jsp'로 포워딩 (request 객체를 전달)
        RequestDispatcher rd = request.getRequestDispatcher("/player_list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청이 와도 GET과 동일하게 처리
        doGet(request, response);
    }
}