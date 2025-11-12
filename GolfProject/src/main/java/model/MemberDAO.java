package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM;

public class MemberDAO {

    // 싱글톤 패턴
    private static MemberDAO instance = new MemberDAO();
    private MemberDAO() {}
    public static MemberDAO getInstance() {
        return instance;
    }

    /**
     * 회원 정보를 TBL_MEMBER_202201 테이블에 삽입하는 메소드
     */
    public int insertMember(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;

        String sql = "INSERT INTO TBL_MEMBER_202201 " +
                     "(C_NO, C_NAME, PHONE, ADDRESS, REGIST_DATE, C_TYPE) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getC_no());
            pstmt.setString(2, dto.getC_name());
            pstmt.setString(3, dto.getPhone());
            pstmt.setString(4, dto.getAddress());
            pstmt.setString(5, dto.getRegist_date());
            pstmt.setString(6, dto.getC_type());

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt);
        }
        
        return rowCount;
    }
    
    /**
     * [새로 추가] 모든 회원 정보를 조회하는 메소드
     * (PDF 4페이지 '회원조회' 기능)
     */
    public List<MemberDTO> selectAllMembers() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberDTO> list = new ArrayList<>();
        
        String sql = "SELECT * FROM TBL_MEMBER_202201 ORDER BY C_NO ASC";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.setC_no(rs.getString("C_NO"));
                dto.setC_name(rs.getString("C_NAME"));
                dto.setPhone(rs.getString("PHONE"));
                dto.setAddress(rs.getString("ADDRESS"));
                dto.setRegist_date(rs.getString("REGIST_DATE"));
                dto.setC_type(rs.getString("C_TYPE"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return list;
    }

    /**
     * [새로 추가] 회원번호(PK)로 특정 회원 1명의 정보를 조회하는 메소드
     */
    public MemberDTO selectMemberByNo(String cNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO dto = null;
        
        String sql = "SELECT * FROM TBL_MEMBER_202201 WHERE C_NO = ?";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dto = new MemberDTO();
                dto.setC_no(rs.getString("C_NO"));
                dto.setC_name(rs.getString("C_NAME"));
                dto.setPhone(rs.getString("PHONE"));
                dto.setAddress(rs.getString("ADDRESS"));
                dto.setRegist_date(rs.getString("REGIST_DATE"));
                dto.setC_type(rs.getString("C_TYPE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        return dto;
    }

    /**
     * [새로 추가] 회원 정보를 수정하는 메소드
     * (PDF 4페이지 '회원정보 수정' 기능)
     * (회원번호, 회원명은 변경 불가하므로 UPDATE 대상에서 제외)
     */
    public int updateMember(MemberDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;

        // C_NO를 조건으로 PHONE, ADDRESS, REGIST_DATE, C_TYPE만 수정
        String sql = "UPDATE TBL_MEMBER_202201 SET " +
                     "PHONE = ?, " +
                     "ADDRESS = ?, " +
                     "REGIST_DATE = ?, " +
                     "C_TYPE = ? " +
                     "WHERE C_NO = ?";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getPhone());
            pstmt.setString(2, dto.getAddress());
            pstmt.setString(3, dto.getRegist_date());
            pstmt.setString(4, dto.getC_type());
            pstmt.setString(5, dto.getC_no()); // WHERE 조건절

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt);
        }
        return rowCount;
    }
    
}