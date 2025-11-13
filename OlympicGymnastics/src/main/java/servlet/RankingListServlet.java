// servlet/RankingListServlet.java
package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.RankingDAO;
import model.RankingDTO;

@WebServlet("/RankingListServlet")
public class RankingListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. DAO를 통해 정렬된 랭킹 목록 조회
        RankingDAO dao = new RankingDAO();
        List<RankingDTO> rankingList = dao.getRankingList();
        
        int currentRank = 1;
        for (RankingDTO dto : rankingList) {
            if (currentRank == 1) {
                dto.setRank("금");
            } else if (currentRank == 2) {
                dto.setRank("은");
            } else if (currentRank == 3) {
                dto.setRank("동");
            } else {
                dto.setRank(String.valueOf(currentRank)); // 4등부터는 숫자
            }
            currentRank++;
        }
        
        // 3. request 객체에 랭킹이 매겨진 목록 저장
        request.setAttribute("rankingList", rankingList);
        
        // 4. 'ranking_list.jsp'로 포워딩
        RequestDispatcher rd = request.getRequestDispatcher("/ranking_list.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}