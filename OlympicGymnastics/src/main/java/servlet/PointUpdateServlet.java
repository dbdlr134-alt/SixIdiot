// servlet/PointUpdateServlet.java
package servlet;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlayerDAO;
import model.PointDAO;
import model.PointDTO;

@WebServlet("/PointUpdateServlet")
public class PointUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // --- PointInsertServlet과 동일한 로직 ---
        try {
            // 1. 폼 데이터 가져오기
            String player_no = request.getParameter("player_no");
            int point_1 = Integer.parseInt(request.getParameter("point_1"));
            int point_2 = Integer.parseInt(request.getParameter("point_2"));
            int point_3 = Integer.parseInt(request.getParameter("point_3"));
            int point_4 = Integer.parseInt(request.getParameter("point_4"));

            // 2. DAO 객체 생성
            PlayerDAO playerDAO = new PlayerDAO();
            PointDAO pointDAO = new PointDAO();
            
            // 3. 계산을 위한 skill_level 조회
            String skillLevelStr = playerDAO.getSkillLevelByPlayerNo(player_no);
            if (skillLevelStr == null) {
                response.sendRedirect("point_insert.jsp");
                return;
            }
            int skillLevel = Integer.parseInt(skillLevelStr);
            
            // 4. 총점 및 평균 계산 (비즈니스 로직)
            int[] scores = { point_1, point_2, point_3, point_4 };
            Arrays.sort(scores);
            
            int total = scores[0] + scores[1] + scores[2]; // 
            double avg_base = (double) total / 3.0; // 
            double bonus_rate = (double) skillLevel / 100.0;
            double bonus_point = avg_base * bonus_rate; // 
            double final_ave = avg_base + bonus_point; // 

            // 5. DTO에 모든 데이터 저장
            PointDTO dto = new PointDTO();
            dto.setPlayer_no(player_no);
            dto.setPoint_1(point_1);
            dto.setPoint_2(point_2);
            dto.setPoint_3(point_3);
            dto.setPoint_4(point_4);
            dto.setTotal(total);
            dto.setAve(final_ave);
            
            // 6. DAO를 통해 DB에 저장 (INSERT or UPDATE)
            pointDAO.insertOrUpdatePoint(dto);
            
            // 7. index.jsp로 리다이렉트 
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("point_insert.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("point_insert.jsp");
        }
    }
}