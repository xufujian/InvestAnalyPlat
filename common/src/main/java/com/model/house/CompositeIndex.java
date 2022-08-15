package com.model.house;

import lombok.Data;
import org.omg.CORBA.WStringSeqHelper;

/**
 * @author: 今天风很大
 * @date:2022/8/15 21:59
 * @Description: 综合指数： 城市、总房源、城市均价、环比上月、降价房源、新上房源
 */
@Data
public class CompositeIndex {
    private String cityName; //城市名称
    private String houseTotalNum; //房源总套数
    private Long avgPrice;   //城市均价
    private String lastMonth;//环比上月
    private Integer markDownPriceNum; //降价房源 (套)
    private Integer newHouseNum;   //新上房源
    private String spiderTime;     //爬取时间

}
