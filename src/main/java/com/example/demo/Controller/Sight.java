package com.example.demo.Controller;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "sights")
public class Sight {
    @MongoId
    private String sightName;

    private String zone;
    private String category;
    private String photoURL;
    private String description;
    private String address;
    public Sight(){

    }
    public void setSightName(String name){
        this.sightName = name;
    }
    public void setZone(String z){
        this.zone = z;
    }
    public void setCategory(String c){
        this.category = c;
    }
    public void setDescription(String d){
        this.description = d;
    }
    public void setURL(String u){
        this.photoURL = u;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getSightName(){
        return sightName;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getZone() {
        return zone;
    }

    @Override
    public String toString(){
        return "SightName: " + sightName + "\n" +
                "Zone: " + zone + "\n" + "Category: " + category +"\n"+
                "PhotoURl:\n" +photoURL +"\n"+"Description: "+description+"\n"+
                "Address: " + address +"\n";
    }
}
