package model;

// DAO에서 JOIN 쿼리 결과를 담기 위한 DTO
public class ReservationDetailDTO {
    
    // TBL_JUMIN
    private String pname;    // 이름
    private String jumin;    // 주민번호
    private String gender;   // 성별 (가공됨)
    private String tel;      // 전화번호

    // TBL_VACCRESV
    private String resvdate; // 예약일자 (가공됨)
    private String resvyime; // 예약시간 (가공됨)
    private String vname;    // 백신종류 (가공됨)

    // TBL_HOSP
    private String hospname; // 병원명
    private String hosptel;  // 대표전화
    private String hospaddr; // 병원주소
    
    // Getters and Setters
    public String getPname() {
        return pname;
    }
    public void setPname(String pname) {
        this.pname = pname;
    }
    public String getJumin() {
        return jumin;
    }
    public void setJumin(String jumin) {
        this.jumin = jumin;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getResvdate() {
        return resvdate;
    }
    public void setResvdate(String resvdate) {
        this.resvdate = resvdate;
    }
    public String getResvyime() {
        return resvyime;
    }
    public void setResvyime(String resvyime) {
        this.resvyime = resvyime;
    }
    public String getVname() {
        return vname;
    }
    public void setVname(String vname) {
        this.vname = vname;
    }
    public String getHospname() {
        return hospname;
    }
    public void setHospname(String hospname) {
        this.hospname = hospname;
    }
    public String getHosptel() {
        return hosptel;
    }
    public void setHosptel(String hosptel) {
        this.hosptel = hosptel;
    }
    public String getHospaddr() {
        return hospaddr;
    }
    public void setHospaddr(String hospaddr) {
        this.hospaddr = hospaddr;
    }
}