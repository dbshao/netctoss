package netctoss.service;

import netctoss.dao.CostDao;
import netctoss.entity.Cost;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by itachi on 2017/1/19.
 */

@Service
//服务层多用@service注入到springBean中
public class CostService {
//    注入需要的DAO，可以与数据库交流
    private CostDao costDao;

    public CostDao getCostDao() {
        return costDao;
    }

    @Resource(name="costDao")
//    在set方法上用@Resource实行注入，不建议在属性上注入
    public void setCostDao(CostDao costDao) {
        this.costDao = costDao;
    }

    public List<Cost> findAll(){
        return costDao.findAll();
    }

    public Cost findById(int id){
        return costDao.findById(id);
    }

    public void save(Cost cost){
        costDao.save(cost);
    }

    public void delete(int id){
        costDao.delete(id);
    }
}
