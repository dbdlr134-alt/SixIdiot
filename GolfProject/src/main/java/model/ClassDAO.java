package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM;

public class ClassDAO {
    
    // 싱글톤
    private static ClassDAO instance = new ClassDAO();
    private ClassDAO() {}
    public static ClassDAO getInstance() {
        return instance;
    }

    /**
     * 수강신청 정보를 TBL_CLASS_202201 테이블에 삽입
     * (PDF 8-⑥ 요구사항)
     */
    public int insertClass(ClassDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;

        // TBL_CLASS_202201 테이블
        String sql = "INSERT INTO TBL_CLASS_202201 " +
                     "(REGIST_MONTH, C_NO, CLASS_AREA, TUITION, TEACHER_CODE) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getRegist_month());
            pstmt.setString(2, dto.getC_no());
            pstmt.setString(3, dto.getClass_area());
            pstmt.setInt(4, dto.getTuition());
            pstmt.setString(5, dto.getTeacher_code());

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt);
        }
        
        return rowCount;
    }
    
    /**
     * [새로 추가] 9) 회원별 수강정보 조회 (3-Table JOIN)
     * TBL_CLASS (A), TBL_MEMBER (B), TBL_TEACHER (C)
     */
    public List<MemberClassViewDTO> selectMemberClassView() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberClassViewDTO> list = new ArrayList<>();

        String sql = "SELECT " +
                     "    A.REGIST_MONTH, " +
                     "    A.C_NO, " +
                     "    B.C_NAME, " +
                     "    C.CLASS_NAME, " +
                     "    C.TEACHER_NAME, " +
                     "    A.CLASS_AREA, " +
                     "    A.TUITION " +
                     "FROM " +
                     "    TBL_CLASS_202201 A " +
                     "JOIN " +
                     "    TBL_MEMBER_202201 B ON A.C_NO = B.C_NO " +
                     "JOIN " +
                     "    TBL_TEACHER_202201 C ON A.TEACHER_CODE = C.TEACHER_CODE " +
                     "ORDER BY " +
                     "    A.REGIST_MONTH DESC, A.C_NO ASC"; // 정렬 (수강월 내림차순)

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberClassViewDTO viewDto = new MemberClassViewDTO();
                viewDto.setRegist_month(rs.getString("REGIST_MONTH"));
                viewDto.setC_no(rs.getString("C_NO"));
                viewDto.setC_name(rs.getString("C_NAME"));
                viewDto.setClass_name(rs.getString("CLASS_NAME"));
                viewDto.setTeacher_name(rs.getString("TEACHER_NAME"));
                viewDto.setClass_area(rs.getString("CLASS_AREA"));
                viewDto.setTuition(rs.getInt("TUITION"));
                
                list.add(viewDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        
        return list;
    }
}