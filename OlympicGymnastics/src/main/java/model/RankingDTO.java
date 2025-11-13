// model/RankingDTO.java
package model;

public class RankingDTO {
    
    private String player_no;
    private String name;
    private String nation_name;
    private String skill_name;
    private int total;
    private double ave;
    private String rank; // "금", "은", "동", "4", "5"...
    
    // Getters and Setters
    public String getPlayer_no() {
        return player_no;
    }
    public void setPlayer_no(String player_no) {
        this.player_no = player_no;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNation_name() {
        return nation_name;
    }
    public void setNation_name(String nation_name) {
        this.nation_name = nation_name;
    }
    public String getSkill_name() {
        return skill_name;
    }
    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
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
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
}