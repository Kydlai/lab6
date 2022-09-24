package commands;

import items.Dragon;
import server.ProjectCollection;
import server.Server;


/**
 * Класс команды filter_starts_with_description
 */
public class FilterStartsWithDescription extends Comand {

    private ComandTypes comandType = ComandTypes.FILTER_STARTS_WITH_DESCRIPTION;
    private String description;


    @Override
    public String getName(){
        return comandType.toString();
    }

    public String getInfo(){
        return "filter_starts_with_description description : вывести элементы, значение поля description которых начинается с заданной подстроки";
    }

    public String execute(){
        Server.toSend = true;
        return Server.collection.getCollection().stream().filter(s -> s.getDescription().indexOf(description) == 0 ? true : false)
                .map(s -> s.getInfo()).reduce((s1, s2) -> s1 + s2).orElse("Элементы не обнаружены");
    }

    public String execute(String argument){
        description = argument;
        return this.execute();
    }
}
