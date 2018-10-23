package test;

import netctoss.dao.CostDao;
import netctoss.entity.Admin;
import netctoss.dao.AdminDao;
import netctoss.entity.Cost;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by itachi on 2017/1/18.
 */
public class testCase {

    @Test
    public void test1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        Properties props=ac.getBean("jdbc",Properties.class);
        System.out.println(props);
    }

    @Test
    public void test2() throws SQLException{
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        DataSource ds=ac.getBean("dataSource",DataSource.class);
        Connection conn=ds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void test3() throws SQLException{
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        AdminDao dao=ac.getBean("adminDao",AdminDao.class);
        Admin a=dao.findByAdminCode("caocao");
        System.out.println(a);
    }

    @Test
    public void test5() throws SQLException{
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        CostDao dao=ac.getBean("costDao",CostDao.class);
        Cost cost=dao.findById(3);
        System.out.println(cost);
    }

    @Test
    public void test4(){
        String name="name";
        String costType="2";
        String baseDuration="222";
        String baseCost="33";
        String unitCost="233";
        String descr="love";
        Cost c=new Cost();
        c.setName(name);
        c.setCostType(costType);
        c.setBaseDuration(Integer.parseInt(baseDuration));
        c.setBaseCost(Double.parseDouble(baseCost));
        c.setUnitCost(Double.parseDouble(unitCost));
        c.setDescr(descr);
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        CostDao dao=ac.getBean("costDao",CostDao.class);
        System.out.println(dao.getDataSource());
        dao.save(c);
    }

    @Test
    public void test6(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        CostDao dao=ac.getBean("costDao",CostDao.class);
        dao.delete(12);
    }
}
