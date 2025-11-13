// model/PlayerDTO.java
package model;

public class PlayerDTO {
    private String player_no;
    private String name;
    private String birth;
    private String nation_code;
    private String skill_level;
    
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
    public String getBirth() {
        return birth;
    }
    public void setBirth(String birth) {
        this.birth = birth;
    }
    public String getNation_code() {
        return nation_code;
    }
    public void setNation_code(String nation_code) {
        this.nation_code = nation_code;
    }
    public String getSkill_level() {
        return skill_level;
    }
    public void setSkill_level(String skill_level) {
        this.skill_level = skill_level;
    }
}	