package com.example.myapplication;

public class Medicine {
    private  String imgLink ;
    private  String num ;
    private  String communicate ;
    private  String location ;
    private  String name ;

    public Medicine() {
    }

    public Medicine(String imgLink, String num, String communicate, String location, String name) {
        this.imgLink = imgLink;
        this.num = num;
        this.communicate = communicate;
        this.location = location;
        this.name = name;
    }

    public String getImgLink() {
        return imgLink;
    }

    public String getNum() {
        return num;
    }

    public String getCommunicate() {
        return communicate;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setCommunicate(String communicate) {
        this.communicate = communicate;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "imgLink='" + imgLink + '\'' +
                ", num='" + num + '\'' +
                ", communicate='" + communicate + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}











