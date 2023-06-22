package com.example.thefinalproject.businessManager.financialManagement;

import android.widget.ImageView;

import com.example.thefinalproject.businessManager.customerManagement.Coustumer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Account {
    private static DatabaseReference balanceRef = FirebaseDatabase.getInstance().getReference("balance");

    public static int balance = 0;
    public static ArrayList<Movement> movements = new ArrayList<>();

    public static void addMovement (String typeMovement , int size , String description ){

        movements.add(new Movement(typeMovement ,size , description));

        if(typeMovement == "expense")
        {
            balance = balance - (size);
        }
        else if(typeMovement == "revenue"){
            balance = balance + size;
        }
        balanceRef.setValue(balance);
    }

    public static void setBalance(int pBalance){
        balance = pBalance;
        balanceRef.setValue(balance);
    }

    public static int getBalance(){
        return balance;
    }

    public static void add(Movement c) {
        movements.add(new Movement(c.typeMovement , c.getSize() , c.getDescription()));

        if(c.getTypeMovement() == "expense")
        {
            balance = balance - c.getSize();
        }
        else if(c.getTypeMovement() == "revenue"){
            balance = balance + c.getSize();
        }
        balanceRef.setValue(balance);
    }

    public static ArrayList<Movement> getMovementList (){
        return movements;
    }

    public static void clear() {
        movements.clear();
    }
}



