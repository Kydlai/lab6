package commands;

import java.util.ArrayList;

import exceptions.WrongArgumentsException;
//import main.*;
import items.*;


/**
 * Класс команды remove_by_id
 */
public class RemoveById extends Comand {

    protected ComandTypes comandType = ComandTypes.REMOVE_BY_ID;
    private Long id = -1L;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
    }

    public void execute(String arg){
        Long id = 0L;
        try {
            id = Long.parseLong(arg);
            toTransport = true;
        } catch (NumberFormatException e) {
            try{
                throw new WrongArgumentsException();
            } catch (WrongArgumentsException ex) {
                toTransport = false;
            }
        }
        this.id = id;
    }
}
