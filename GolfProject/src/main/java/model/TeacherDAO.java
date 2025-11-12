package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM; // DBM 클래스 import

public class TeacherDAO {

    // 싱글톤 패턴 (선택 사항이지만 권장됩니다)
    private static TeacherDAO instance = new TeacherDAO();
    private TeacherDAO() {}
    public static TeacherDAO getInstance() {
        return instance;
    }

    /**
     * 강사 정보를 TBL_TEACHER_202201 테이블에 삽입하는 메소드
     */
    public int insertTeacher(TeacherDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0; // 성공 여부 (영향 받은 행의 수)

        // TBL_TEACHER_202201 테이블 
        String sql = "INSERT INTO TBL_TEACHER_202201 " +
                     "(TEACHER_CODE, CLASS_NAME, TEACHER_NAME, CLASS_PRICE, TEACHER_REGIST_DATE) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBM.getConn(); // 제공된 DBM 클래스 사용
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getTeacher_code());
            pstmt.setString(2, dto.getClass_name());
            pstmt.setString(3, dto.getTeacher_name());
            pstmt.setInt(4, dto.getClass_price());
            pstmt.setString(5, dto.getTeacher_regist_date());

            rowCount = pstmt.executeUpdate(); // INSERT 실행

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt); // DBM 클래스를 사용한 자원 해제
        }
        
        return rowCount;
    }

    public List<TeacherDTO> selectAllTeachers() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // 반환할 리스트 객체 생성
        List<TeacherDTO> list = new ArrayList<>(); 
        
        // TBL_TEACHER_202201 테이블
        String sql = "SELECT * FROM TBL_TEACHER_202201 ORDER BY TEACHER_CODE ASC";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            // 결과(ResultSet)를 순회하며 DTO에 담고, DTO를 List에 추가
            while (rs.next()) {
                TeacherDTO dto = new TeacherDTO();
                dto.setTeacher_code(rs.getString("TEACHER_CODE"));
                dto.setClass_name(rs.getString("CLASS_NAME"));
                dto.setTeacher_name(rs.getString("TEACHER_NAME"));
                dto.setClass_price(rs.getInt("CLASS_PRICE"));
                dto.setTeacher_regist_date(rs.getString("TEACHER_REGIST_DATE"));
                
                list.add(dto); // 리스트에 DTO 추가
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // DBM 클래스의 close 메소드에 rs 추가
            DBM.close(conn, pstmt, rs); 
        }
        
        return list; // DTO가 담긴 리스트 반환
    }
    
    public TeacherDTO selectTeacherByCode(String teacherCode) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        TeacherDTO dto = null; // 조회 결과를 담을 DTO
        
        String sql = "SELECT * FROM TBL_TEACHER_202201 WHERE TEACHER_CODE = ?";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, teacherCode);
            rs = pstmt.executeQuery();

            if (rs.next()) { // 데이터가 있다면
                dto = new TeacherDTO();
                dto.setTeacher_code(rs.getString("TEACHER_CODE"));
                dto.setClass_name(rs.getString("CLASS_NAME"));
                dto.setTeacher_name(rs.getString("TEACHER_NAME"));
                dto.setClass_price(rs.getInt("CLASS_PRICE"));
                dto.setTeacher_regist_date(rs.getString("TEACHER_REGIST_DATE"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt, rs);
        }
        
        return dto; // 조회된 DTO (없으면 null)
    }

    /**
     * [새로 추가] 강사 정보를 수정하는 메소드
     */
    public int updateTeacher(TeacherDTO dto) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int rowCount = 0;

        // 강사코드(PK)를 조건으로 나머지 정보를 수정
        String sql = "UPDATE TBL_TEACHER_202201 SET " +
                     "CLASS_NAME = ?, " +
                     "TEACHER_NAME = ?, " +
                     "CLASS_PRICE = ?, " +
                     "TEACHER_REGIST_DATE = ? " +
                     "WHERE TEACHER_CODE = ?";

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getClass_name());
            pstmt.setString(2, dto.getTeacher_name());
            pstmt.setInt(3, dto.getClass_price());
            pstmt.setString(4, dto.getTeacher_regist_date());
            pstmt.setString(5, dto.getTeacher_code()); // WHERE 조건절

            rowCount = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt);
        }
        
        return rowCount; // 성공한 행의 수 (1이면 성공)
    }
    
    /**
     * [새로 추가] 10) 강사별 매출 통계 조회
     * (CLASS와 TEACHER 테이블 JOIN 및 GROUP BY)
     */
    public List<TeacherSalesDTO> selectTeacherSales() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<TeacherSalesDTO> list = new ArrayList<>();

        // A = TBL_CLASS (수강료), B = TBL_TEACHER (강사정보)
        String sql = "SELECT " +
                     "    B.TEACHER_CODE, " +
                     "    B.TEACHER_NAME, " +
                     "    B.CLASS_NAME, " +
                     "    SUM(A.TUITION) AS total_sales " + // 총매출액
                     "FROM " +
                     "    TBL_CLASS_202201 A " +
                     "JOIN " +
                     "    TBL_TEACHER_202201 B ON A.TEACHER_CODE = B.TEACHER_CODE " +
                     "GROUP BY " +
                     "    B.TEACHER_CODE, B.TEACHER_NAME, B.CLASS_NAME " +
                     "ORDER BY " +
                     "    total_sales DESC"; // 매출액이 높은 순으로 정렬

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                TeacherSalesDTO dto = new TeacherSalesDTO();
                dto.setTeacher_code(rs.getString("TEACHER_CODE"));
                dto.setTeacher_name(rs.getString("TEACHER_NAME"));
                dto.setClass_name(rs.getString("CLASS_NAME"));
                dto.setTotal_sales(rs.getInt("total_sales"));
                
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