package com.example.thefinalproject.businessManager.customerManagement;

import java.util.ArrayList;

public class CoustumerList {

    public static ArrayList<Coustumer> coustomers = new ArrayList<>();

    public static void addCoustomer (String name , int phoneNum , String email){
        coustomers.add(new Coustumer( name , phoneNum , email));
    }

    public static ArrayList<Coustumer> getCoustomersList (){
        return coustomers;
    }

    public static void add(Coustumer c) {
        coustomers.add(new Coustumer(c.getName() , c.getPhoneNum() , c.getEmail()));
    }

    public static void clear() {
        coustomers.clear();
    }

}
