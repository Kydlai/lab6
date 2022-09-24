package commands;

import items.*;
import exceptions.*;
import client.*;
import commands.*;


import java.util.ArrayList;

public class Clear extends Comand {
    private ComandTypes comandType = ComandTypes.CLEAR;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "clear : очистить коллекцию";
    }

}
