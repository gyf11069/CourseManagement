package Dao;

import model.Course;

/**
 * @author 高云飞
 * @site WWW.java.com
 * @company 致远有限公司
 * @create 2019-12-28 10:54
 */
public interface CouDao {

    public void insert(Course course) throws Exception;

    public void delete(Course course) throws Exception;

    public void update(Course course) throws Exception;

    public void selectById(int id) throws Exception;

    public void selectAll() throws Exception;

    public void selectCondition(Course course) throws Exception;

    public void countAll()throws Exception;

    void selectBytName(String tName) throws Exception;

    public  void selectBycTime(String tName) throws Exception ;
    public  void selectBycClass(String cClass) throws Exception ;
}
