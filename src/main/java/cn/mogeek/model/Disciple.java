package cn.mogeek.model;

import java.io.Serializable;


public class Disciple implements Cloneable , Serializable {

    /**
     * 姓名 主修方向 毕业院校 师兄 来源 日报 目标
     */
    private String student_name, major_subject, graduated_school,
            brother, come_from, daily_report, slogan, qq_num, student_id;

    /**
     * id 为主键
     */
    private Integer id;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getMajor_subject() {
        return major_subject;
    }

    public void setMajor_subject(String major_subject) {
        this.major_subject = major_subject;
    }

    public String getGraduated_school() {
        return graduated_school;
    }

    public void setGraduated_school(String graduated_school) {
        this.graduated_school = graduated_school;
    }

    public String getBrother() {
        return brother;
    }

    public void setBrother(String brother) {
        this.brother = brother;
    }

    public String getCome_from() {
        return come_from;
    }

    public void setCome_from(String come_from) {
        this.come_from = come_from;
    }

    public String getDaily_report() {
        return daily_report;
    }

    public void setDaily_report(String daily_report) {
        this.daily_report = daily_report;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQq_num() {
        return qq_num;
    }

    public void setQq_num(String qq_num) {
        this.qq_num = qq_num;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Disciple other = (Disciple)obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "[id: " + id
                + ", 学号：" + student_id
                + ", 姓名: " + student_name
                + ", 学习方向: " + major_subject
                + ", 口号: " + slogan
                + ", QQ号: " + qq_num
                + ", 辅导师兄: " + brother
                + ", 来源: " + come_from
                + ", 毕业院校: " + graduated_school
                + ", 日报链接: " + daily_report + "]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Disciple disciple = (Disciple)super.clone();
        return disciple;
    }
}
