// model/PlayerDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM; // DBM 클래스 임포트

public class PlayerDAO {

    // 1. 국적 목록 조회 (드롭다운용)
    public List<NationDTO> getNationList() {
        List<NationDTO> list = new ArrayList<>();
        String sql = "SELECT nation_code, nation_name FROM tbl_olympic_nation";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                NationDTO dto = new NationDTO();
                dto.setNation_code(rs.getString("nation_code"));
                dto.setNation_name(rs.getString("nation_name"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs); // DBM 클래스로 자원 해제
        }
        return list;
    }
    
    // 2. 기술난이도 목록 조회 (드롭다운용)
    public List<SkillDTO> getSkillList() {
        List<SkillDTO> list = new ArrayList<>();
        String sql = "SELECT skill_level, skill_name FROM tbl_olympic_skill";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                SkillDTO dto = new SkillDTO();
                dto.setSkill_level(rs.getString("skill_level"));
                dto.setSkill_name(rs.getString("skill_name"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return list;
    }
    
    // 3. 선수 등록 (폼 처리용)
    public int insertPlayer(PlayerDTO dto) {
        int result = 0;
        String sql = "INSERT INTO tbl_olympic_player (player_no, name, birth, nation_code, skill_level) "
                   + "VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getPlayer_no());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getBirth());
            pstmt.setString(4, dto.getNation_code());
            pstmt.setString(5, dto.getSkill_level());
            
            result = pstmt.executeUpdate(); // INSERT 실행
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt); // rs가 없으므로 이 메소드 사용
        }
        return result;
    }
    
 // 4. 선수 목록 조회 (목록 페이지용)
    public List<PlayerListDTO> getPlayerList() {
        List<PlayerListDTO> list = new ArrayList<>();
        
        // 3개 테이블 조인
        String sql = "SELECT p.player_no, p.name, n.nation_name, p.birth, s.skill_name "
                   + "FROM tbl_olympic_player p "
                   + "JOIN tbl_olympic_nation n ON p.nation_code = n.nation_code "
                   + "JOIN tbl_olympic_skill s ON p.skill_level = s.skill_level "
                   + "ORDER BY p.player_no"; // 선수번호 순 정렬
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                PlayerListDTO dto = new PlayerListDTO();
                
                String playerNo = rs.getString("player_no");
                String birth = rs.getString("birth"); // "19970101"
                
                // --- 데이터 가공 시작 ---
                
                // 1. 성별 가공 
                String gender = "";
                if (playerNo.startsWith("1")) {
                    gender = "남자";
                } else if (playerNo.startsWith("2")) {
                    gender = "여자";
                }
                
                // 2. 생년월일 가공 
                // "19970101" -> "1997년 01월 01일"
                String birth_formatted = "";
                if (birth != null && birth.length() == 8) {
                    birth_formatted = birth.substring(0, 4) + "년 " 
                                    + birth.substring(4, 6) + "월 " 
                                    + birth.substring(6, 8) + "일";
                } else {
                    birth_formatted = birth; // 형식 안맞으면 원본 출력
                }
                
                // --- 데이터 가공 끝 ---
                
                // DTO에 가공된 데이터 저장
                dto.setPlayer_no(playerNo);
                dto.setName(rs.getString("name"));
                dto.setNation_name(rs.getString("nation_name"));
                dto.setSkill_name(rs.getString("skill_name"));
                dto.setGender(gender); // 가공된 성별
                dto.setBirth_formatted(birth_formatted); // 가공된 생년월일
                
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return list;
    }
    
    public String getSkillLevelByPlayerNo(String playerNo) {
        String skillLevel = null;
        String sql = "SELECT skill_level FROM tbl_olympic_player WHERE player_no = ?";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, playerNo);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                skillLevel = rs.getString("skill_level");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return skillLevel;
    }
}