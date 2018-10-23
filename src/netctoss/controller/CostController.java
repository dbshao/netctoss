package netctoss.controller;

import netctoss.entity.Cost;
import netctoss.service.CostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by itachi on 2017/1/19.
 */

//需要在类上加上controller注解，注册bean，用equestMapping设置规划的路径
@Controller
@RequestMapping("/cost")
public class CostController {
//    注入服务业务
    private CostService costService;

    public CostService getCostService() {
        return costService;
    }

    @Resource(name="costService")
    public void setCostService(CostService costService) {
        this.costService = costService;
    }

//    规划路径
    @RequestMapping("/find.do")
    public String find(ModelMap model){
        List<Cost> list=costService.findAll();
        model.addAttribute("costs",list);
        return "cost/cost_list";
    }

    @RequestMapping("/toAdd.do")
    public String toAdd(){
        return "cost/cost_add";
    }

    @RequestMapping("/add.do")
    public String save(HttpServletRequest req){
        System.out.println("112");
        String name=req.getParameter("name");
        String costType=req.getParameter("costType");
        String baseDuration=req.getParameter("baseDuration");
        String baseCost=req.getParameter("baseCost");
        String unitCost=req.getParameter("unitCost");
        String descr=req.getParameter("descr");
        Cost c=new Cost();
        c.setName(name);
        c.setCostType(costType);
        c.setBaseDuration(Integer.parseInt(baseDuration));
        c.setBaseCost(Double.parseDouble(baseCost));
        c.setUnitCost(Double.parseDouble(unitCost));
        c.setDescr(descr);
        costService.save(c);
        return "redirect:/cost/find.do";
    }

   @RequestMapping("/toDelete.do")
    public String delete(HttpServletRequest req){
        System.out.println("**********");
        String id=req.getQueryString();
        System.out.println(id);
        costService.delete(Integer.parseInt(id));
        return "redirect:/cost/find.do";
    }
}
