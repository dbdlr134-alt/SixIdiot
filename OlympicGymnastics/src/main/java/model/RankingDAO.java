// model/RankingDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DBM;

public class RankingDAO {

    // 랭킹 목록 조회 (평균 'ave' 기준 내림차순 정렬)
    public List<RankingDTO> getRankingList() {
        List<RankingDTO> list = new ArrayList<>();
        
        // 4개 테이블 조인
        String sql = "SELECT "
                   + "    p.player_no, p.name, n.nation_name, s.skill_name, "
                   + "    pt.total, pt.ave "
                   + "FROM "
                   + "    tbl_olympic_player p "
                   + "JOIN "
                   + "    tbl_olympic_nation n ON p.nation_code = n.nation_code "
                   + "JOIN "
                   + "    tbl_olympic_skill s ON p.skill_level = s.skill_level "
                   + "JOIN "
                   + "    tbl_olympic_point pt ON p.player_no = pt.player_no "
                   + "ORDER BY "
                   + "    pt.ave DESC"; // 평균 점수(ave) 내림차순
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                RankingDTO dto = new RankingDTO();
                dto.setPlayer_no(rs.getString("player_no"));
                dto.setName(rs.getString("name"));
                dto.setNation_name(rs.getString("nation_name"));
                dto.setSkill_name(rs.getString("skill_name"));
                dto.setTotal(rs.getInt("total"));
                dto.setAve(rs.getDouble("ave"));
                // dto.setRank()는 서블릿에서 설정
                
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return list;
    }
}