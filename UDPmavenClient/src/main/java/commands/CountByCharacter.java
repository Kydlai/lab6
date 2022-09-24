package commands;

import exceptions.WrongDragonCharacterException;
import items.Dragon;
import items.DragonCharacter;

import java.util.ArrayList;


/**
 * Класс команды count_by_character
 */
public class CountByCharacter extends Comand {

    protected ComandTypes comandType = ComandTypes.COUNT_BY_CHARACTER;
    private DragonCharacter character;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "count_by_character character : вывести количество элементов, значение поля character которых равно заданному";
    }

    @Override
    public void execute(String arg) {
        toTransport = true;
        this.character = DragonCharacter.selector(arg);
        if(character == null){
            toTransport = false;
            try {
                throw new WrongDragonCharacterException();
            } catch (WrongDragonCharacterException e) {}
        }
    }
}
