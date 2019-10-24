package top.yxf.test.test;

public class Student {
    private String clzz;//班级
    private String studentId;
    private String name;
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Student(String clzz, String studentId, String name) {
        super();
        this.clzz = clzz;
        this.studentId = studentId;
        this.name = name;
    }

    public String getClzz() {
        return clzz;
    }

    public void setClzz(String clzz) {
        this.clzz = clzz;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student [clzz=" + clzz + ", studentId=" + studentId + ", name=" + name + "]";
    }
}
