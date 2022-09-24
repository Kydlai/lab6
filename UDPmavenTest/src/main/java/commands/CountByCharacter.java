package commands;

import items.Dragon;
import items.DragonCharacter;
import server.ProjectCollection;
import server.Server;

import java.util.ArrayList;


/**
 * Класс команды count_by_character
 */
public class CountByCharacter extends Comand {

    private ComandTypes comandType = ComandTypes.COUNT_BY_CHARACTER;
    private DragonCharacter character;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "count_by_character character : вывести количество элементов, значение поля character которых равно заданному";
    }

    public String execute(){
        Server.toSend = true;
        return Long.toString(Server.collection.getCollection().stream().filter(s -> s.getCharacter().toString().equals(character.toString())).count());
    }

    public String execute(String argument){
        character = DragonCharacter.valueOf(argument);
        return this.execute();
    }
}
