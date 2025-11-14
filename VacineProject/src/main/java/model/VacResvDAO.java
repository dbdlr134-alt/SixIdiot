package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBM; // DBM 클래스 임포트

public class VacResvDAO {

    // 싱글톤 패턴
    private static VacResvDAO instance = new VacResvDAO();
    private VacResvDAO() {}
    public static VacResvDAO getInstance() {
        return instance;
    }

    /**
     * 접종 예약 정보를 TBL_VACCRESV_202108 테이블에 저장합니다. [cite: 41]
     * @param dto 폼에서 입력받은 예약 정보가 담긴 DTO
     * @return 1 (성공) 또는 0 (실패)
     */
    public int insertReservation(VacResvDTO dto) {
        int result = 0;
        
        // TBL_VACCRESV_202108 테이블에 INSERT
        String sql = "INSERT INTO TBL_VACCRESV_202108 " +
                     "(RESVNO, JUMIN, VCODE, HOSPCODE, RESVDATE, RESVYIME) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBM.getConn(); // DBM 유틸리티로 Connection 얻기
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, dto.getResvno());
            pstmt.setString(2, dto.getJumin());
            pstmt.setString(3, dto.getVcode());
            pstmt.setString(4, dto.getHospcode());
            pstmt.setString(5, dto.getResvdate());
            pstmt.setString(6, dto.getResvyime());
            
            result = pstmt.executeUpdate(); // 쿼리 실행 (성공 시 1 반환)
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBM.close(conn, pstmt); // DBM 유틸리티로 자원 해제
        }
        
        return result;
    }
    
    /**
     * 예약번호를 기준으로 3개 테이블을 JOIN하여
     * 명세서에 맞는 포맷으로 가공된 상세 정보를 조회합니다.
     * @param resvno 조회할 예약번호
     * @return ReservationDetailDTO (조회 성공 시) 또는 null (실패 시)
     */
    public ReservationDetailDTO getReservationDetails(String resvno) {
        ReservationDetailDTO dto = null; // 결과를 담을 객체
        
        // 3개 테이블 JOIN 및 데이터 포맷팅 쿼리
        String sql = "SELECT " +
            "    j.PNAME, " +
            "    j.JUMIN, " +
            "    CASE " + // 성별
            "        WHEN SUBSTR(j.JUMIN, 8, 1) = '1' THEN '남' " +
            "        WHEN SUBSTR(j.JUMIN, 8, 1) = '2' THEN '여' " +
            "        ELSE '기타' " +
            "    END AS GENDER, " +
            "    j.TEL, " +
            "    DATE_FORMAT(STR_TO_DATE(v.RESVDATE, '%Y%m%d'), '%Y년 %m월 %d일') AS RESV_DATE_FORMAT, " + // 예약일자
            "    TIME_FORMAT(STR_TO_DATE(v.RESVYIME, '%H%i'), '%H:%i') AS RESV_TIME_FORMAT, " + // 예약시간
            "    h.HOSPNAME, " +
            "    h.HOSPTEL, " +
            "    h.HOSPADDR, " +
            "    CASE v.VCODE " + // 백신종류 (PDF 명세 오타 V001='A', V002='B', V003='C'로 추정)
            "        WHEN 'V001' THEN 'A백신' " +
            "        WHEN 'V002' THEN 'B백신' " +
            "        WHEN 'V003' THEN 'C백신' " +
            "        ELSE '기타' " +
            "    END AS VACCINE_NAME " +
            "FROM " +
            "    TBL_VACCRESV_202108 v " +
            "JOIN " +
            "    TBL_JUMIN_202108 j ON v.JUMIN = j.JUMIN " +
            "JOIN " +
            "    TBL_HOSP_202108 h ON v.HOSPCODE = h.HOSPCODE " +
            "WHERE " +
            "    v.RESVNO = ?"; // 입력받은 예약번호로 조회
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; // SELECT 쿼리 결과를 받을 ResultSet

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, resvno); // ?에 예약번호 바인딩
            
            rs = pstmt.executeQuery();
            
            // 결과가 존재하면 (조회 성공)
            if (rs.next()) {
                dto = new ReservationDetailDTO(); // DTO 객체 생성
                
                dto.setPname(rs.getString("PNAME"));
                dto.setJumin(rs.getString("JUMIN"));
                dto.setGender(rs.getString("GENDER"));
                dto.setTel(rs.getString("TEL"));
                dto.setResvdate(rs.getString("RESV_DATE_FORMAT"));
                dto.setResvyime(rs.getString("RESV_TIME_FORMAT"));
                dto.setHospname(rs.getString("HOSPNAME"));
                dto.setHosptel(rs.getString("HOSPTEL"));
                dto.setHospaddr(rs.getString("HOSPADDR"));
                dto.setVname(rs.getString("VACCINE_NAME"));
            }
            // 결과가 없으면 dto는 그대로 null
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // ResultSet까지 닫아줍니다.
            DBM.close(conn, pstmt, rs); 
        }
        
        return dto; // 조회 결과 DTO 또는 null 반환
    }
    
    /**
     * 병원별 접종 건수 및 총 누계를 조회합니다. (수정: 중간 집계 제거)
     * @return List<StatisticsDTO> (통계 결과 리스트)
     */
    public List<StatisticsDTO> getStatistics() {
        List<StatisticsDTO> list = new ArrayList<>();
        
        // [수정된 SQL] : HAVING 절을 추가하여 중간 집계(병원별 소계)를 제거
        String sql = "SELECT " +
            "    IF(GROUPING(h.HOSPCODE), '총누계', h.HOSPCODE) AS HOSPCODE, " +
            "    IF(GROUPING(h.HOSPCODE), '', h.HOSPNAME) AS HOSPNAME, " +
            "    COUNT(v.RESVNO) AS COUNT " +
            "FROM " +
            "    TBL_HOSP_202108 h " +
            "JOIN " +
            "    TBL_VACCRESV_202108 v ON h.HOSPCODE = v.HOSPCODE " +
            "GROUP BY " +
            "    h.HOSPCODE, h.HOSPNAME WITH ROLLUP " +
            // [HAVING 절 추가]
            // GROUPING(h.HOSPNAME) = 0 : '가-병원' 등 실제 병원 행
            // GROUPING(h.HOSPCODE) = 1 : '총누계' 행
            // 이 두 경우만 선택하고, (H001, '', 4) 같은 중간 집계는 제외
            "HAVING " +
            "    GROUPING(h.HOSPNAME) = 0 OR GROUPING(h.HOSPCODE) = 1 " +
            "ORDER BY " +
            "    GROUPING(h.HOSPCODE), h.HOSPCODE ASC"; // 정렬 순서 유지

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBM.getConn();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                StatisticsDTO dto = new StatisticsDTO();
                dto.setHospcode(rs.getString("HOSPCODE"));
                dto.setHospname(rs.getString("HOSPNAME"));
                dto.setCount(rs.getInt("COUNT"));
                
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