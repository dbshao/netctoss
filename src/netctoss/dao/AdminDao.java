package netctoss.dao;

import netctoss.entity.Admin;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by itachi on 2017/1/18.
 */

@Repository("adminDao")
public class AdminDao {
    private DataSource ds;

    public DataSource getDs() {
        return ds;
    }

    @Resource(name = "dataSource")
    public void setDs(DataSource ds) {
        //System.out.println("注入数据源+"+ds);
        this.ds = ds;
    }

    public Admin findByAdminCode(String adminCode)  {
        Admin admin=null;
        Connection conn=null;
        String sql="select * from admin_info where admin_code=?";
        try {
            conn=ds.getConnection();
            PreparedStatement ps=conn.prepareCall(sql);
            ps.setString(1,adminCode);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                admin=new Admin();
                admin.setAdminId(rs.getInt("admin_id"));
                admin.setAdminCode(adminCode);
                admin.setEmail(rs.getString("email"));
                admin.setTelephone(rs.getString("telephone"));
                admin.setName(rs.getString("name"));
                admin.setPassword(rs.getString("password"));
                admin.setEnrolldate(rs.getTimestamp("enrolldate"));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("查询失败",e);
        }finally {
            if(conn!=null){
                //关闭
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
