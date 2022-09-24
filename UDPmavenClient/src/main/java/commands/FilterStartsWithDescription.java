package commands;


import exceptions.CanNotBeNullException;
import exceptions.WrongArgumentsException;

/**
 * Класс команды filter_starts_with_description
 */
public class FilterStartsWithDescription extends Comand {

    private ComandTypes comandType = ComandTypes.FILTER_STARTS_WITH_DESCRIPTION;
    private String description = "";

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo(){
        return "filter_starts_with_description description : вывести элементы, значение поля description которых начинается с заданной подстроки";
    }

    public void execute(String arg){
        toTransport = true;
        this.description = arg;

        if(description.equals("")){
            toTransport = false;
            try{
                throw new CanNotBeNullException();
            } catch (CanNotBeNullException ex){}
        }

    }
}
