package model;

public class TeacherDTO {

    // 1. TBL_TEACHER_202201 테이블 컬럼 
    private String teacher_code;
    private String class_name;
    private String teacher_name;
    private int class_price;
    private String teacher_regist_date;

    // 2. Getters and Setters
    public String getTeacher_code() {
        return teacher_code;
    }
    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
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
    public int getClass_price() {
        return class_price;
    }
    public void setClass_price(int class_price) {
        this.class_price = class_price;
    }
    public String getTeacher_regist_date() {
        return teacher_regist_date;
    }
    public void setTeacher_regist_date(String teacher_regist_date) {
        this.teacher_regist_date = teacher_regist_date;
    }
}