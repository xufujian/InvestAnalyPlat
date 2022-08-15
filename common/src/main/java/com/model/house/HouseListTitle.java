package com.model.house;

import lombok.Data;

/**
 * @author: 今天风很大
 * @date:2022/8/15 22:26
 * @Description: 房源列表明细
 */
@Data
public class HouseListTitle {
    private String propertyTitle;     //房源信息标题
    private String propertyName;      //房源小区名称
    private String propertyAddr;      //房源所在地
    private String propertyBaseInfo;  //房源基本信息
    private String propertyUpdateTime;//房源更新时间
    private String propertyPhotoUrl;  //房源图片
    private String propertyAgent;     //房源委托中介
    private String propertyTotalPrice;//房源总价
    private String propertyAvgPrice;  //房源均价
}
