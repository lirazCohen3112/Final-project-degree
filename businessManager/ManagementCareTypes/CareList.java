package com.example.thefinalproject.businessManager.ManagementCareTypes;

import java.util.ArrayList;

public class CareList {
    public static ArrayList<Care> cares = new ArrayList<>();

    public static void addCare (String name , String explanation , String cost){
        cares.add(new Care(name , explanation , cost));
    }

    public static ArrayList<Care> getCareList (){
        return cares;
    }

    public static void add(Care c) {
        cares.add(new Care(c.getName() , c.getExplanation() , c.getCost()));
    }

    public static void clear() {
        cares.clear();
    }

}
