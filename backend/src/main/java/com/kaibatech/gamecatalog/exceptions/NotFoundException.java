package com.kaibatech.gamecatalog.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Not Found");
    }

}
