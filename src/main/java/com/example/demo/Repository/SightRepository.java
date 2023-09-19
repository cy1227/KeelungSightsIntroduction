package com.example.demo.Repository;

import com.example.demo.Controller.Sight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightRepository extends MongoRepository<Sight, String> {

    Sight findBySightName(String username);

}