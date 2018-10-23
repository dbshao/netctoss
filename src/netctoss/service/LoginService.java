package netctoss.service;

import netctoss.dao.AdminDao;
import netctoss.entity.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by itachi on 2017/1/18.
 */

@Service("loginService")
public class LoginService {
    private AdminDao adminDao;

    public AdminDao getAdminDao() {
        return adminDao;
    }

    @Resource(name="adminDao")
    public void setAdminDao(AdminDao adminDao) {
        //System.out.println("注入了adminDao");
        this.adminDao = adminDao;
    }


    public Admin login(String adminCode,String password) {
        Admin admin=null;

            admin=adminDao.findByAdminCode(adminCode);
            if(admin==null){
                throw new ApplicationException("账户不存在");
            }
            if(!admin.getPassword().equals(password)){
                throw new ApplicationException("密码错误");
            }
           /* 异常的分类：系统异常（数据库，服务暂停，网络中断）发生之后，程序无法恢复，只能通知用户稍后重试
                    应用异常：用户的问题产生异常（输入错误的账号和密码，应提示进行正确的操作）*/

        return admin;
    }
}
