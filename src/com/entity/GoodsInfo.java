package com.entity;



import java.sql.Timestamp;

/**
 * @author wuyi
 * @date 2019/8/12 11:39
 */
public class GoodsInfo {

    private int id;
    private String goodsInfoName;
    private String goodsInfoPic;
    private float goodsInfoPrice;
    private String goodsInfoDescription;
    private int goodsStock;
    private String flag;
    private int created;
    private Timestamp createdDate;

    public GoodsInfo() {
    }

    public GoodsInfo(int id, String goodsInfoName, String goodsInfoPic, float goodsInfoPrice, String goodsInfoDescription, int goodsStock, String flag, int created, Timestamp createdDate) {
        this.id = id;
        this.goodsInfoName = goodsInfoName;
        this.goodsInfoPic = goodsInfoPic;
        this.goodsInfoPrice = goodsInfoPrice;
        this.goodsInfoDescription = goodsInfoDescription;
        this.goodsStock = goodsStock;
        this.flag = flag;
        this.created = created;
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public String getGoodsInfoPic() {
        return goodsInfoPic;
    }

    public void setGoodsInfoPic(String goodsInfoPic) {
        this.goodsInfoPic = goodsInfoPic;
    }

    public float getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(float goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }

    public String getGoodsInfoDescription() {
        return goodsInfoDescription;
    }

    public void setGoodsInfoDescription(String goodsInfoDescription) {
        this.goodsInfoDescription = goodsInfoDescription;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "id=" + id +
                ", goodsInfoName='" + goodsInfoName + '\'' +
                ", goodsInfoPic='" + goodsInfoPic + '\'' +
                ", goodsInfoPrice=" + goodsInfoPrice +
                ", goodsInfoDescription='" + goodsInfoDescription + '\'' +
                ", goodsStock=" + goodsStock +
                ", flag='" + flag + '\'' +
                ", created='" + created + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
