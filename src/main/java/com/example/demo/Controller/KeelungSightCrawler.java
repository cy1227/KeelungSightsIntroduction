package com.example.demo.Controller;


import java.util.*;

import com.example.demo.Repository.SightRepository;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class KeelungSightCrawler {
    private final String url = "https://www.travelking.com.tw/tourguide/taiwan/keelungcity/";
    private String link = "";

    private String[] names = {"七堵", "中山", "中正", "仁愛", "安樂", "信義", "暖暖"};
    public Map<String, Sight[]> allData = new HashMap<String, Sight[]>();

    @Autowired
    private SightRepository sightRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void saveSightDataToDatabase() {
        System.out.println("進入函式");
        mongoTemplate.dropCollection(Sight.class);
         String[] names = {"七堵", "中山", "中正", "仁愛", "安樂", "信義", "暖暖"};
        for (String zone : names) {
            Sight[] sights = getItems(zone);
            for (Sight sight : sights) {
                //保存到 MongoDB (兩個寫法都可）
                //mongoTemplate.save(sight, "sights");
                sightRepository.save(sight);
            }
        }
    }
    public  KeelungSightCrawler() throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements h4Elements = doc.select("h4");
            for (Element h4Element : h4Elements) {
                String text = h4Element.text();
                for(int i = 0; i<7 ; i++) { //爬取所有七個區的景點
                    List<Sight> sightList =new ArrayList<Sight>();
                    if (text.contains(names[i])) {
                        String zone = h4Element.text(); //zone = 七堵區
                        Element ulElements = h4Element.nextElementSibling();
                        Elements liElements = ulElements.select("li");
                        for (Element liElement : liElements) {//依序爬取景點裡面的內容
                            //景點名稱sightName 2
                            String sightName = liElement.text();
                            String link = liElement.selectFirst("a").attr("href");
                            //要進入的網址
                            link = "https://www.travelking.com.tw" + link;
                            doc = Jsoup.connect(link).get();
                            Elements div = doc.select("#point_area");
                            //景點類別
                            Elements typeElement = doc.select(".point_type");
                            Elements categoryElement = typeElement.select("strong");
                            String category = categoryElement.text();

                            //photo url
                            Elements photoElement = div.select(".gpic");
                            String src = photoElement.select("img").attr("data-src");
                            if(photoElement.isEmpty()){//無圖片的處理
                                src = "https://storage.googleapis.com/futurecity-cms-cwg-tw/article/202010/article-5f8e578e3815b.jpg";
                            }
                            //description
                            String des = div.select("meta[itemprop=description]").attr("content");

                            //address
                            String address = div.select("meta[itemprop=address]").attr("content");
                            //加入物件
                            Sight newSight = new Sight();
                            newSight.setZone(zone);
                            newSight.setSightName(sightName);
                            newSight.setCategory(category);
                            newSight.setURL(src);
                            newSight.setDescription(des);
                            newSight.setAddress(address);

                            sightList.add(newSight);
                        }
                        allData.put(names[i],  sightList.toArray(new Sight[sightList.size()]));
                        System.out.println(names[i]);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Sight[] getItems(String zone)  {
        return allData.get(zone);
    }
}
