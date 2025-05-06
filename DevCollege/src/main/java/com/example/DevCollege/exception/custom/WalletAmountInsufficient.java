package com.example.DevCollege.exception.custom;

public class WalletAmountInsufficient extends RuntimeException{

    private final String field;
    private final String message;

    public WalletAmountInsufficient(String field, String message){
        this.field = field;
        this.message= message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
