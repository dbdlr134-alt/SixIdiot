package model;

// 통계 쿼리 결과를 담기 위한 DTO
public class StatisticsDTO {
    
    private String hospcode;
    private String hospname;
    private int count; // 접종 건수
    
    // Getters and Setters
    public String getHospcode() {
        return hospcode;
    }
    public void setHospcode(String hospcode) {
        this.hospcode = hospcode;
    }
    public String getHospname() {
        return hospname;
    }
    public void setHospname(String hospname) {
        this.hospname = hospname;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
}