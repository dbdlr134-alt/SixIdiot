package model;

/**
 * 10) 강사별 매출 통계 (JOIN + GROUP BY) 결과를 담기 위한 DTO
 */
public class TeacherSalesDTO {

    // TBL_TEACHER_202201
    private String teacher_code;
    private String teacher_name;
    private String class_name;
    
    // TBL_CLASS_202201 (SUM)
    private int total_sales; // 총매출액 (SUM(TUITION))

    
    // Getters and Setters
    public String getTeacher_code() {
        return teacher_code;
    }
    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }
    public String getTeacher_name() {
        return teacher_name;
    }
    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
    public String getClass_name() {
        return class_name;
    }
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
    public int getTotal_sales() {
        return total_sales;
    }
    public void setTotal_sales(int total_sales) {
        this.total_sales = total_sales;
    }
}