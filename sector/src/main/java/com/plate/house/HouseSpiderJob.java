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
import java.util.stream.Collectors;

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
            .addCookie("acw_sc__v2", "62fbafa54590788d0dad578edef75ab238b50079");

    @Override
    public void process(Page page) {
        System.out.println("in page...");
        //房屋总量
        String houseTotalNum = page.getHtml().xpath("//div[@class='list-converge-box']/strong/text()").get();
        List<Selectable> nodesAnsList = page.getHtml().xpath("//div[@class='exponent-div']").nodes();
        //城市名称
        String cityName = nodesAnsList.get(0).xpath("//h5/text()").toString();
        String cityAvgPrice = nodesAnsList.get(1).xpath("//p[@class=num]/text()").get();
        String lastMonth = nodesAnsList.get(2).xpath("//p[@class=num]/text()").get();
        String markDownPriceNum = nodesAnsList.get(3).xpath("//p[@class=num]/a/text()").get();
        String newHouseNum = nodesAnsList.get(4).xpath("//p[@class=num]/a/text()").get();

        System.out.println("houseTotalNum:" + houseTotalNum + " cityName:" + cityName + ": cityAvgPrice:" + cityAvgPrice + " lastMonth:" + lastMonth + " markDownPriceNum: " + markDownPriceNum + " newHouseNum: " + newHouseNum);

        List<Selectable> nodesList = page.getHtml().xpath("//ul[@class='list-table-box']/li").nodes();
        for (int i = 0; i < nodesList.size(); i++) {
            Selectable sele = nodesList.get(i);
            //房源标题
            String title = sele.xpath("//a[@class='list-table-tab-box']/@alt").get();
            //房源地址
            String href = sele.xpath("//a[@class='list-table-tab-box']/@href").get();
            Selectable spanList = sele.xpath("//span[@class='list-table-info-box']");
            //房源基本信息
            String propertyBaseInfo = spanList.xpath("//span[2]/text()").get();
            List<String> addrList = spanList.xpath("//span[1]//a[@class='area_class']/text()").all();
            //房源地址
            String addr = addrList.stream().map(String::valueOf).collect(Collectors.joining(","));
            //更新时间
            String updateTime = spanList.xpath("//span[3]/em/text()").get();
            //房源总价
            String totalPrice = sele.xpath("//span[@class='right_span']/span[1]/b/text()").get();
            //房源均价
            String propertyAvgPrice = sele.xpath("//span[@class='right_span']/strong/text()").get();

            System.out.println("title: "+title+" href:"+href +" propertyBaseInfo:"+propertyBaseInfo+" addr:"+addr+" updateTime" +updateTime +" totalPrice:"+totalPrice+" propertyAvgPrice:"+propertyAvgPrice);

        }



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
