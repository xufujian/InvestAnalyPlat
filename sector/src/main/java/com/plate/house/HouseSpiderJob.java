package com.plate.house;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.List;

/**
 * @author: 今天风很大
 * @date:2022/8/14 14:14
 * @Description: 中国房产数据分析平台
 */
public class HouseSpiderJob implements PageProcessor {
    private static final Logger logger
            = LoggerFactory.getLogger(HouseSpiderJob.class);

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(10000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:103.0) Gecko/20100101 Firefox/103.0")
            .addCookie("acw_sc__v2", "62fa6274682c4e9ab139b9ddff165fe445606df3");

    @Override
    public void process(Page page) {
        System.out.println("in page...");
        //房屋总量
        String houseTotalNum = page.getHtml().xpath("//div[@class='list-converge-box']/strong/text()").get();
        List<Selectable> nodesList = page.getHtml().xpath("//div[@class='exponent-div']").nodes();
        //城市名称
        String cityName = nodesList.get(0).xpath("//h5/text()").toString();
        String cityAvgPrice = nodesList.get(1).xpath("//p[@class=num]/text()").get();
        String lastMonth = nodesList.get(2).xpath("//p[@class=num]/text()").get();
        String markDownPriceNum = nodesList.get(3).xpath("//p[@class=num]/a/text()").get();
        String newHouseNum = nodesList.get(4).xpath("//p[@class=num]/a/text()").get();

        System.out.println("houseTotalNum:" +  houseTotalNum+" cityName:"+cityName+": cityAvgPrice:"+cityAvgPrice+" lastMonth:"+lastMonth+ " markDownPriceNum: "+markDownPriceNum+" newHouseNum: "+newHouseNum);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = Spider.create(new HouseSpiderJob());
        String anjukeUrl = "https://hangzhou.anjuke.com/sale/?from=navigation";
        String zhugeUrl = "https://hz.ershoufang.zhuge.com/";
        String zhugeCityUrl = "http://www.zhuge.com/areachange?businessType=";
        spider.addUrl(zhugeUrl);
        spider.thread(1);
        spider.run();
        System.out.println(spider.getSite());
        logger.info("spider:" + spider);
        logger.info("ok");
    }
}
