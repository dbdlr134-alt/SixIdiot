// model/PointDAO.java
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM;

public class PointDAO {

    // 점수 등록 및 수정 (INSERT ... ON DUPLICATE KEY UPDATE)
    public int insertOrUpdatePoint(PointDTO dto) {
        int result = 0;
        // player_no가 PK이므로, 중복 시 UPDATE 수행
        String sql = "INSERT INTO tbl_olympic_point (player_no, point_1, point_2, point_3, point_4, total, ave) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?) "
                   + "ON DUPLICATE KEY UPDATE "
                   + "point_1 = VALUES(point_1), "
                   + "point_2 = VALUES(point_2), "
                   + "point_3 = VALUES(point_3), "
                   + "point_4 = VALUES(point_4), "
                   + "total = VALUES(total), "
                   + "ave = VALUES(ave)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getPlayer_no());
            pstmt.setInt(2, dto.getPoint_1());
            pstmt.setInt(3, dto.getPoint_2());
            pstmt.setInt(4, dto.getPoint_3());
            pstmt.setInt(5, dto.getPoint_4());
            pstmt.setInt(6, dto.getTotal());
            pstmt.setDouble(7, dto.getAve()); // DTO의 ave가 double이므로
            
            result = pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt);
        }
        return result;
    }
    
 // 2. 심사점수 목록 조회 (목록 페이지용)
    public List<PointListDTO> getPointList() {
        List<PointListDTO> list = new ArrayList<>();
        
        // 선수 테이블(p)과 점수 테이블(pt)을 조인
        String sql = "SELECT "
                   + "    p.player_no, p.name, "
                   + "    pt.point_1, pt.point_2, pt.point_3, pt.point_4, "
                   + "    pt.total, pt.ave "
                   + "FROM "
                   + "    tbl_olympic_player p "
                   + "JOIN "
                   + "    tbl_olympic_point pt ON p.player_no = pt.player_no "
                   + "ORDER BY "
                   + "    pt.ave DESC"; // 평균점수 높은 순으로 정렬
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while(rs.next()) {
                PointListDTO dto = new PointListDTO();
                dto.setPlayer_no(rs.getString("player_no"));
                dto.setName(rs.getString("name"));
                dto.setPoint_1(rs.getInt("point_1"));
                dto.setPoint_2(rs.getInt("point_2"));
                dto.setPoint_3(rs.getInt("point_3"));
                dto.setPoint_4(rs.getInt("point_4"));
                dto.setTotal(rs.getInt("total"));
                dto.setAve(rs.getDouble("ave"));
                
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