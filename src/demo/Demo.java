package demo;

import Dao.CouDao;
import Dao.CouDaoImp;
import model.Course;

import java.util.Scanner;

/**
 * @author 高云飞
 * @site WWW.java.com
 * @company 致远有限公司
 * @create 2019-12-27 10:22
 */
public class Demo {
    public static CouDaoImp coudao=new CouDaoImp();
    public static Scanner sc = new Scanner(System.in);
    public static Course course=new Course();
    public static String tId,tName,cName,cTime,cPlace,cClass;
    public static int id;
    public static void main(String[] args)throws Exception{mainTest();}
    public static void mainTest() throws Exception {
        System.out.println("==========菜单==========\n" +
                "1、向课程表中插入数据\n" +
                "2、删除课程表数据\n" +
                "3、修改课程表数据\n" +
                "4、查询课程表数据\n" +
                "5、按条件查询课程表数据\n"+
                "6、统计不同教师和课程数\n"+
                "7、退出菜单");
        System.out.println("请输入操作序号：");

        int i = sc.nextInt();

        switch (i) {
            case 1:
                System.out.println("请输入教师ID：");
                 tId = sc.next();
                System.out.println("请输入教师姓名：");
                 tName = sc.next();
                System.out.println("请输入课程名称：");
                 cName = sc.next();
                System.out.println("请输入课程时间：");
                 cTime = sc.next();
                System.out.println("请输入课程地点：");
                 cPlace = sc.next();
                System.out.println("请输入课程教室：");
                 cClass = sc.next();
                if (coudao.isLawfull(tId, tName, cName, cTime, cPlace, cClass)) {
                     course = new Course(tId, tName, cName,
                            cTime, cPlace, cClass);
                    coudao.insert(course);
                    System.out.println("添加成功！");
                    mainTest();
                } else {
                    System.out.println("课程冲突！请重新输入教务信息");
                    mainTest();
                }
                break;

            case 2:
                System.out.println("请输入要删除的教师ID:");
                String tid = sc.next();
                Course course = new Course(tid, null, null,
                        null, null, null);
                coudao.delete(course);
                System.out.println("删除成功！");
                mainTest();
                break;
            case 3:
                System.out.println("请输入要修改的教务编号：");
                int id=sc.nextInt();
                if(coudao.isExistById(id))
                {
                    System.out.println("请输入教师ID：");
                     tId = sc.next();
                    System.out.println("请输入教师姓名：");
                     tName = sc.next();
                    System.out.println("请输入课程名称：");
                     cName = sc.next();
                    System.out.println("请输入课程时间：");
                     cTime = sc.next();
                    System.out.println("请输入课程地点：");
                     cPlace = sc.next();
                    System.out.println("请输入课程教室：");
                     cClass = sc.next();

                     course = new Course(tId, tName, cName,
                            cTime, cPlace, cClass);
                    coudao.update(course);
                    System.out.println("修改成功！");
                    mainTest();
                } else {
                    System.out.println("课程冲突！请重新输入教务信息");
                    mainTest();
                }
                break;
            case 4:
               coudao.selectAll();
                mainTest();
                break;
            case 5:
               selCondition();
                mainTest();
                break;
            case 6:
                coudao.countAll();
                mainTest();
                break;
            case 7:
                System.out.println("退出系统!");
                break;
        }



    }

    public static void selCondition() throws Exception {
        Course coursee=new Course();
        CouDao coudao=new CouDaoImp();

        System.out.println("==========按条件查询教务==========\n"
        +"1、根据教务信息序号查询\n"
                +"2、根据老师的学工号(ID)查询\n"
                +"3、根据老师的姓名查询\n"
                + "4、根据上课的课程查询\n"
                +"5、根据上课的时间查询\n"
                +"5、根据上课的班级查询\n"
                +"6、退出\n");
        System.out.println("请输入序号：");
        Scanner sc=new Scanner(System.in);
        int i=sc.nextInt();
         switch(i)
         {
             case 1:
                 System.out.println("请输入要查询的教务序号：");
                 id=sc.nextInt();
                 coudao.selectById(id);
                 selCondition();
             case 2:
                 System.out.println("请输入老师的学工号(ID)：");
                 tId=sc.next();
                 coursee= new Course(tId, null, null,
                         null, null, null);
                 coudao.selectCondition(coursee);
                 selCondition();
                 break;
             case 3:
                 System.out.println("请输入老师的姓名：");
                 tName=sc.next();
                 ((CouDaoImp) coudao).selectBytName(tName);
                 selCondition();
                 break;
             case 4:
                 System.out.println("请输入上课的课程：");
                 cName=sc.next();
                 ((CouDaoImp) coudao).selectBytName(cName);
                 selCondition();
                 break;
             case 5:
                 System.out.println("请输入上课的时间：");
                 cTime=sc.next();
                 ((CouDaoImp) coudao).selectBycTime(cTime);
                 selCondition();
                 break;
             case 6:
                 System.out.println("请输入上课的班级：");
                 cClass=sc.next();
                 ((CouDaoImp) coudao).selectBycClass(cClass);
                 selCondition();
                 break;
             case 7:
                 System.out.println("退出");
                 break;

         }
    }
}
