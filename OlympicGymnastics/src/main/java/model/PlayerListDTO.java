// model/PlayerListDTO.java
package model;

// 이 DTO는 PlayerDAO가 JOIN 및 데이터 가공을 마친
// '출력용 데이터'를 담는 데 사용됩니다.
public class PlayerListDTO {
    
    private String player_no;
    private String name;
    private String nation_name;
    private String birth_formatted; // "yyyy년 mm월 dd일" 형식 
    private String gender;          // "남자" 또는 "여자" 
    private String skill_name;
    
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
    public String getBirth_formatted() {
        return birth_formatted;
    }
    public void setBirth_formatted(String birth_formatted) {
        this.birth_formatted = birth_formatted;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getSkill_name() {
        return skill_name;
    }
    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }
}