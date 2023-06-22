package com.example.thefinalproject.businessManager.ManagementCareTypes;

public class Care {
    private String name;
    private String explanation;
    private String cost;


    public Care() {
        // Required for Firebase deserialization
    }

    public Care(String name , String explanation , String cost){
        this.name = name;
        this.explanation = explanation;
        this.cost = cost;
    }


    public String getName(){
        return name;
    }

    public String getExplanation(){
        return explanation;
    }

    public String getCost(){
        return cost;
    }

}
