package com.example.demo.Controller;

import com.example.demo.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
public class SightController {
    KeelungSightCrawler crawler;
    public SightController() throws IOException{
        crawler = new KeelungSightCrawler();
        System.out.println("end");
    }

    @RequestMapping("/SightAPI")
    public String SightAPI(String zone) throws Exception {

        Sight [] sights = crawler.getItems(zone);
        System.out.println("2");

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(sights);
        return jsonString;
    }
}
