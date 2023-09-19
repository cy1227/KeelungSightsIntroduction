//package com.example.demo.Service;
//
//import com.example.demo.Controller.KeelungSightCrawler;
//import com.example.demo.Controller.Sight;
//import com.example.demo.Repository.SightRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//    //private final MongoTemplate mongoTemplate;
//    private SightRepository repository;
//    //private KeelungSightCrawler crawler;
//
////    @Autowired
////    public UserService( SightRepository sightRepository, KeelungSightCrawler crawler, MongoTemplate mongoTemplate) {
////        this.mongoTemplate = mongoTemplate;
////        this.repository = sightRepository;
////        this.crawler = crawler;
////    }
//
////    public void saveSightDataToDatabase() {
////        mongoTemplate.dropCollection(Sight.class);
////         String[] names = {"七堵", "中山", "中正", "仁愛", "安樂", "信義", "暖暖"};
////        for (String zone : names) {
////            Sight[] sights = crawler.getItems(zone);
////            for (Sight sight : sights) {
////                // 使用 repository 將 Sight 對象保存到 MongoDB
////                repository.save(sight);
////            }
////        }
////    }
////    public String getDatabaseName() {
////        return mongoTemplate.getDb().getName();
////    }
//
////    public void saveSightToDatabase(Sight sight) {
////        repository.save(sight);
////    }
//}
