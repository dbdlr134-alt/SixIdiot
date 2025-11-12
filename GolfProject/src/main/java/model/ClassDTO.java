package model;

public class ClassDTO {

    // TBL_CLASS_202201 테이블 컬럼
    private String regist_month;
    private String c_no;
    private String class_area;
    private int tuition;
    private String teacher_code;

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
    public String getTeacher_code() {
        return teacher_code;
    }
    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }
}