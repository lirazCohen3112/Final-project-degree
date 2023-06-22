package com.example.thefinalproject.businessManager.financialManagement;


public class Movement {

    public String typeMovement;

    public int size;
    public String description;
    public Movement(String typeMovement , int size , String description){
        this.typeMovement = typeMovement;
        this.size = size;
        this.description = description;

    }

    public void setTypeMovement(int movement){

    }

    public void setSize(int size){
        this.size = size;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getTypeMovement(){
        return typeMovement;
    }

    public int getSize(){
        return size;
    }

    public String getDescription(){
        return description;
    }

}


