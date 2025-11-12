package model;

/**
 * 9) 회원별 수강조회 (3개 테이블 JOIN 결과)를 담기 위한 DTO
 */
public class MemberClassViewDTO {

    // TBL_CLASS_202201
    private String regist_month;
    private String c_no;
    private String class_area;
    private int tuition;
    
    // TBL_MEMBER_202201
    private String c_name;
    
    // TBL_TEACHER_202201
    private String class_name;
    private String teacher_name;

    
    // Getters and Setters
    public String getRegist_month() {
        return regist_month;
    }
    public void setRegist_month(String regist_month) {
        this.regist_month = regist_month;
    }
    public String getC_no() {
        return c_no;
    }
    public void setC_no(String c_no) {
        this.c_no = c_no;
    }
    public String getClass_area() {
        return class_area;
    }
    public void setClass_area(String class_area) {
        this.class_area = class_area;
    }
    public int getTuition() {
        return tuition;
    }
    public void setTuition(int tuition) {
        this.tuition = tuition;
    }
    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public String getClass_name() {
        return class_name;
    }
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
    public String getTeacher_name() {
        return teacher_name;
    }
    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}