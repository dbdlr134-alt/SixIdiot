// model/PointDTO.java
package model;

public class PointDTO {
    private String player_no;
    private int point_1;
    private int point_2;
    private int point_3;
    private int point_4;
    private int total;
    private double ave; // double 또는 BigDecimal. 계산 편의를 위해 double 사용
    
    // Getters and Setters
    public String getPlayer_no() {
        return player_no;
    }
    public void setPlayer_no(String player_no) {
        this.player_no = player_no;
    }
    public int getPoint_1() {
        return point_1;
    }
    public void setPoint_1(int point_1) {
        this.point_1 = point_1;
    }
    public int getPoint_2() {
        return point_2;
    }
    public void setPoint_2(int point_2) {
        this.point_2 = point_2;
    }
    public int getPoint_3() {
        return point_3;
    }
    public void setPoint_3(int point_3) {
        this.point_3 = point_3;
    }
    public int getPoint_4() {
        return point_4;
    }
    public void setPoint_4(int point_4) {
        this.point_4 = point_4;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public double getAve() {
        return ave;
    }
    public void setAve(double ave) {
        this.ave = ave;
    }
}