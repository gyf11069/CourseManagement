package model;

/**
 * @author 高云飞
 * @site WWW.java.com
 * @company 致远有限公司
 * @create 2019-12-27 9:49
 */
/*课程表模型*/
public class Course {
    private int id;
    private String teacherID;
    private String teacherName;
    private String courseName;
    private String courseTime;
    private String coursePlace;
    private String courseClass;
    public Course(){}
    public Course(String teacherID, String teacherName, String courseName, String courseTime, String coursePlace, String courseClass) {
        this.teacherID = teacherID;
        this.teacherName = teacherName;
        this.courseName = courseName;
        this.courseTime = courseTime;
        this.coursePlace = coursePlace;
        this.courseClass = courseClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCoursePlace() {
        return coursePlace;
    }

    public void setCoursePlace(String coursePlace) {
        this.coursePlace = coursePlace;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }


}

