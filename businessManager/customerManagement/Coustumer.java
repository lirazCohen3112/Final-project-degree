package com.example.thefinalproject.businessManager.customerManagement;

import com.example.thefinalproject.businessManager.ManagementCareTypes.Care;

import java.util.ArrayList;

public class Coustumer {

    private String name;
    private int phoneNum;
    private String email;
    private static ArrayList<Care> careHistory = new ArrayList<>();
    private Care futureCare;

    public Coustumer(){

    }
    public Coustumer(String name , int phoneNum , String email ){
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public int getPhoneNum(){
        return phoneNum;
    }

    public String getEmail(){
        return email;
    }

    public void setName( String name){
        this.name = name;
    }

    public void setPhoneNum( int phoneNum){
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void addTocareHistory(Care care){
        careHistory.add(care);
    }

    public static ArrayList<Care> getCareHistor (){
        return careHistory;
    }

    public Care getFutureCare(Care futureCare) {
        return futureCare;
    }

    public Care setFutureCare(){
        return futureCare;
    }

}
