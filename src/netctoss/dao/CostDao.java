package netctoss.dao;

import netctoss.entity.Cost;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itachi on 2017/1/19.
 */

@Repository("costDao")
public class CostDao {
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Cost> findAll() {
        String sql="select * from cost order by cost_id";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Cost> list=new ArrayList<Cost>();
        try {
            conn=dataSource.getConnection();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Cost cost = createCost(rs);
                list.add(cost);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            throw new RuntimeException("查询全部资费失败",e);
        }finally{
           if(conn!=null){
               try {
                   conn.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
        }

        return list;
    }

    public Cost findById(int id){
        String sql="select * from cost where cost_id=?";
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        Cost cost=new Cost();
        try {
            conn=dataSource.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                cost=createCost(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("根据ID查询资费失败",e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return cost;

    }

    public void save (Cost cost){
        String sql="insert into cost(name,base_duration,base_cost,unit_cost,status,descr,creatime,startime,cost_type)" +
                "values(?,?,?,?,0,?,now(),now(),?);";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=dataSource.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1,cost.getName());
            ps.setObject(2,cost.getBaseDuration());
            ps.setObject(3,cost.getBaseCost());
            ps.setObject(4,cost.getUnitCost());
            ps.setObject(5,cost.getDescr());
            ps.setObject(6,cost.getCostType());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("新增保存失败",e);
        } finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(int id){
        String sql="delete from cost where cost_id=?";
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            conn=dataSource.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("删除资费讯息失败",e);
        }finally {
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private Cost createCost(ResultSet rs) throws SQLException {
        Cost cost=new Cost();
        cost.setCostId(rs.getInt("cost_id"));
        cost.setName(rs.getString("name"));
        cost.setBaseDuration(rs.getInt("base_duration"));
        cost.setBaseCost(rs.getDouble("base_cost"));
        cost.setUnitCost(rs.getDouble("unit_cost"));
        cost.setStatus(rs.getString("status"));
        cost.setDescr(rs.getString("descr"));
        cost.setCreatime(rs.getTimestamp("creatime"));
        cost.setStartime(rs.getTimestamp("startime"));
        cost.setCostType(rs.getString("cost_type"));
        return cost;
    }
}
