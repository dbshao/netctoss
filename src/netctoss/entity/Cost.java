package netctoss.entity;

import java.io.Serializable;
import java.sql.Timestamp;

//实际开发过程中 让实体类继承可序列化借口
public class Cost implements Serializable{
    //资费id
    private Integer costId;
    //资费名(100元套餐 200元包月)
    private String name;
    //基本时长(套餐内能用多长时间)
    private Integer baseDuration;
    //基本费用(套餐内费用)
    private Double baseCost;
    //单位费用(超出套餐时每分钟的费用)
    private Double unitCost;
    //状态：0-开通；1-暂停
    private String status;
    //描述
    private String descr;
    /*
     * java.util.Date 下三个子类     包含年月日时分秒
     * 1.java.sql.Date     只有年月日，没有时分秒
     * 2.java.sql.Time    只有时分秒
     * 3.java.sql.Timestamp  年月日时分秒都有
     */
    //创建时间
    private Timestamp creatime;
    //开通时间
    private Timestamp startime;
    //资费类型  1.包月，2.套餐，3.计时
    private String costType;
    public Integer getCostId() {
        return costId;
    }
    public void setCostId(Integer costId) {
        this.costId = costId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBaseDuration() {
        return baseDuration;
    }
    public void setBaseDuration(Integer baseDuration) {
        this.baseDuration = baseDuration;
    }
    public Double getBaseCost() {
        return baseCost;
    }
    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }
    public Double getUnitCost() {
        return unitCost;
    }
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public Timestamp getCreatime() {
        return creatime;
    }
    public void setCreatime(Timestamp creatime) {
        this.creatime = creatime;
    }
    public Timestamp getStartime() {
        return startime;
    }
    public void setStartime(Timestamp startime) {
        this.startime = startime;
    }
    public String getCostType() {
        return costType;
    }
    public void setCostType(String costType) {
        this.costType = costType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Cost{");
        sb.append("costId=").append(costId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", baseDuration=").append(baseDuration);
        sb.append(", baseCost=").append(baseCost);
        sb.append(", unitCost=").append(unitCost);
        sb.append(", status='").append(status).append('\'');
        sb.append(", descr='").append(descr).append('\'');
        sb.append(", creatime=").append(creatime);
        sb.append(", startime=").append(startime);
        sb.append(", costType='").append(costType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
