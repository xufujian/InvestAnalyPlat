package com.model.house;

import lombok.Data;

/**
 * @author: 今天风很大
 * @date:2022/8/15 22:36
 * @Description: 房源详细信息
 */
@Data
public class HouseDetailInfo {
    private String houseTitle;//房源标题
    private String addTime;   //房源上架时间
    private String updateTime;//房源更新时间
    private String photoUrl;  //房源照片地址
    private String houseType; //房屋户型
    private String totalPrice;//房屋总价
    private String unitPrice;//房屋单价
    private String floor;    //楼层
    private String Decorate; //房屋装修
    private String houseAge; //房龄
    private String buildType; //建筑类型
    private String houseUse;  //房屋用途
    private String downPayment; //参考首付
    private String houseLookAt; //房屋朝向
    private String buildArea;   //建筑面积
    private String property;    //产权
    private String spiderTime;  //爬取时间
}
