package Dao;

import model.Course;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 高云飞
 * @site WWW.java.com
 * @company 致远有限公司
 * @create 2019-12-28 11:03
 */
public class CouDaoImp implements CouDao {
    /*
     * 判断增加的数据是否合法
     * */
    public boolean isLawfull(String tId, String tName, String cName, String cTime, String cPlace, String cClass) {
        /*
         * 约束：
         * 1.在同一时间内，同一个教室只能被一个班级使用
         * 2.在同一时间内，一个班级只能安排一门课程
         * 3.在同一时间内，一个教师只能被安排在一个教室上一门课程
         * 4.两个班的不同课程不能在同一个时间被安排在同一个教室
         * 5.教师ID相同，姓名也得相同
         * */
        try {
            Connection con=JDBCConnection.getConnection();//获取连接
            String sql = "select * from course";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                //select
                String tid = rs.getString("teacherID");
                String tname = rs.getString("teacherName");
                String cname = rs.getString("courseName");
                String ctime = rs.getString("courseTime");
                String cplace = rs.getString("coursePlace");
                String cclass = rs.getString("courseClass");
                if(tid.equals(tId) && tname.equals(tName) &&
                        cname.equals(cName) && ctime.equals(cTime) &&
                        cplace.equals(cPlace) && cclass.equals(cClass))
                    return false;
                if(ctime.equals(cTime) && cplace.equals(cPlace) && !cclass.equals(cClass))
                    return false;
                if(ctime.equals(cTime) && cclass.equals(cClass) && !cname.equals(cName))
                    return false;
                if(ctime.equals(cTime) && tid.equals(tId) &&
                        (!cplace.equals(cPlace) || !cname.equals(cName)))
                    return false;
                if(!cclass.equals(cClass) && !cname.equals(cName) && ctime.equals(cTime))
                    return false;
                if(tid.equals(tId) && !tname.equals(tName))
                    return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }
    /*
     * 根据教务信息编号判断教务信息是否存在
     * */
    public boolean isExistById(int id)throws Exception {
            Connection con=JDBCConnection.getConnection();//获取连接
            String sql = "select * from course";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int tid = rs.getInt("id");
                if(tid == id)
                    return true;
            }
        return false;
    }
    /**
     *  新增教务信息
     */
    public void insert(Course course)throws Exception{
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="insert into course(id,teacherID,teacherName,courseName,courseTime,coursePlace,courseClass) values(null,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,course.getTeacherID());//给第1个坑赋值
        pstmt.setString(2,course.getTeacherName());//给第2个坑赋值
        pstmt.setString(3,course.getCourseName());//给第3个坑赋值
        pstmt.setString(4,course.getCourseTime());//给第4个坑赋值
        pstmt.setString(5,course.getCoursePlace());//给第5个坑赋值
        pstmt.setString(6,course.getCourseClass());//给第6个坑赋值
        pstmt.executeUpdate();//执行sql语句返回ResultSet对象
        JDBCConnection.close(pstmt,con);

    }

    /**
     * 删除教务信息
     * @param course
     * @throws Exception
     */
    @Override
    public void delete(Course course) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="delete from course where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,course.getId());//给第1个坑赋值
        pstmt.executeUpdate();//开始执行SQL
        JDBCConnection.close(pstmt,con);
    }
/**
 * 更新教务信息
 */

    @Override
    public void update(Course course) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="update course set teacherID=?,teacherName=?,courseName=?, courseTime=?,coursePlace=?,courseClass=?, where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,course.getTeacherID());//给第1个坑赋值
        pstmt.setString(2,course.getTeacherName());//给第2个坑赋值
        pstmt.setString(3,course.getCourseName());//给第3个坑赋值
        pstmt.setString(4,course.getCourseTime());//给第4个坑赋值
        pstmt.setString(5,course.getCoursePlace());//给第5个坑赋值
        pstmt.setString(6,course.getCourseClass());//给第6个坑赋值
        pstmt.setInt(7,course.getId());//给第1个坑赋值
        pstmt.executeUpdate();//开始执行SQL
        JDBCConnection.close(pstmt,con);
    }

    /**
     * 按编号查询教务信息
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public  void selectById(int id) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="select * from course where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,id);//给第1个坑赋值
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {

            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }
    @Override
    public  void selectBytName(String tName) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="select * from course where teacherName=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,tName);//给第1个坑赋值
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {

            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }


    @Override
    public  void selectBycTime(String cTime) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="select * from course where courseTime=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,cTime);//给第1个坑赋值
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {

            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }
    @Override
    public  void selectBycClass(String cClass) throws Exception {
        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="select * from course where courseClass=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,cClass);//给第1个坑赋值
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {

            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }
    /**
     * 查询所有教务信息
     * @return
     * @throws Exception
     */
    @Override
    public void selectAll() throws Exception {

        Connection con=JDBCConnection.getConnection();//获取连接
        String sql="select * from course ";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next())
        {

            int id=rs.getInt("id");
            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教务序号："+id+"教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }

    /**
     * 按条件查询教务信息
     * @param coursee
     * @return
     * @throws Exception
     */
    @Override
    public void selectCondition(Course coursee) throws Exception {

        Connection con=JDBCConnection.getConnection();//获取连接

        String sql="select * from course where 1=1 ";
        if(coursee.getTeacherID()!=null)
        {
            sql=sql+"and teacherID="+coursee.getTeacherID();
        }
        if(coursee.getTeacherName()!=null)
        {
            sql=sql+"and teacherName="+coursee.getTeacherName();
        }
        if(coursee.getCourseName()!=null)
        {
            sql=sql+"and courseName="+coursee.getCourseName();
        }
        if(coursee.getCourseTime()!=null)
        {
            sql=sql+"and courseTime="+coursee.getCourseTime();
        }
        if(coursee.getCoursePlace()!=null)
        {
            sql=sql+"and coursePlace="+coursee.getCoursePlace();
        }
        if(coursee.getCourseClass()!=null)
        {
            sql=sql+"and courseClass="+coursee.getCourseClass();
        }
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        if(rs.next())
        {

            int id=rs.getInt("id");
            String teacherID=rs.getString("teacherID");
            String teacherName=rs.getString("teacherName");
            String courseName=rs.getString("courseName");
            String courseTime=rs.getString("courseTime");
            String coursePlace=rs.getString("coursePlace");
            String courseClass=rs.getString("courseClass");
            System.out.println("教务序号："+id+"教师ID："+teacherID+"教师姓名："+teacherName+"课程名称："+courseName
                    +"课程时间："+courseTime+"课程地点："+coursePlace+"课程班级："+courseClass);
        }
        JDBCConnection.close(pstmt,con);
        rs.close();

    }

    /**
     * 统计不同的教师数和课程数
     * @throws Exception
     */
    @Override
    public void countAll()throws Exception{
        int countID=0;
        int countCourse=0;
        Connection con=JDBCConnection.getConnection();//获取连接
        try {
            String sql = "select teacherID from course group by teacherID";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();//执行SQL语句
            while (rs.next()) {
                countID++;
            }
            System.out.println("不同教师人数为:" + countID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String sql = "select courseName from course group by courseName";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();//执行SQL语句
            while (rs.next()) {
                countCourse++;
            }
            System.out.println("不同课程数为："+countCourse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
}
