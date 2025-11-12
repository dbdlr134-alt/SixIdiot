package model;

public class MemberDTO {

    // 1. TBL_MEMBER_202201 테이블 컬럼
    private String c_no;
    private String c_name;
    private String phone;
    private String address;
    private String regist_date;
    private String c_type;

    // 2. Getters and Setters
    public String getC_no() {
        return c_no;
    }
    public void setC_no(String c_no) {
        this.c_no = c_no;
    }
    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRegist_date() {
        return regist_date;
    }
    public void setRegist_date(String regist_date) {
        this.regist_date = regist_date;
    }
    public String getC_type() {
        return c_type;
    }
    public void setC_type(String c_type) {
        this.c_type = c_type;
    }
}