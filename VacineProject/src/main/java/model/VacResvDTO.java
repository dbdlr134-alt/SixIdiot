package model;

// TBL_VACCRESV_202108 테이블의 컬럼에 대응 [cite: 15]
public class VacResvDTO {
    private String resvno;
    private String jumin;
    private String vcode;
    private String hospcode;
    private String resvdate;
    private String resvyime; // PDF 명세의 'RESVYIME' 오타 반영 [cite: 15]

    // Getters and Setters
    public String getResvno() {
        return resvno;
    }
    public void setResvno(String resvno) {
        this.resvno = resvno;
    }
    public String getJumin() {
        return jumin;
    }
    public void setJumin(String jumin) {
        this.jumin = jumin;
    }
    public String getVcode() {
        return vcode;
    }
    public void setVcode(String vcode) {
        this.vcode = vcode;
    }
    public String getHospcode() {
        return hospcode;
    }
    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
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
}