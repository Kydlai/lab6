package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

import exceptions.*;


/**
 * Класс управления работой команд
 */
public class ComandManager {
    public static ArrayList<Comand> comands = new ArrayList<Comand>();

    public ComandManager(){
        this.comands.add(new Help());
        this.comands.add(new Info());
        this.comands.add(new Show());
        this.comands.add(new Add());
        this.comands.add(new UpdateId());
        this.comands.add(new RemoveById());
        this.comands.add(new ExecuteScript());
        this.comands.add(new Clear());
        this.comands.add(new Exit());
        this.comands.add(new RemoveFirst());
        this.comands.add(new RemoveLover());
        this.comands.add(new Reorder());
        this.comands.add(new CountByCharacter());
        this.comands.add(new FilterStartsWithDescription());
        this.comands.add(new PrintDescending());
    }

    /**
     * Возвращает информацию о всех командах. Используется при выполнении команды help
     * @return Информация о всех командах
     */
    public String help(){
        String output = "";

        for(int i = 0; i < comands.size(); i++){
            output += comands.get(i).getInfo() + "\n";
        }

        return output;
    }

    /**
     * Находит и выполняет команду, полученную от пользователя
     *
     * @param args Строковое представление команды пользователя
     * @return Информация для пользователя
     */
    public Comand excecute(String args){
        String[] input = new String[1];

        boolean flag = true;
        if(args.length() > 12)
            if(args.substring(0, 12).toLowerCase(Locale.ROOT)!="execute_script") {
                input = args.split(" ");
                flag = false;
            }
        if(flag){
            input = args.split(" ");
            if(input.length > 0)
            input[0] = input[0].toLowerCase(Locale.ROOT);
            else
                try{
                    throw new WrongArgumentsException();
                } catch (WrongArgumentsException e) {
                    return null;
                }
        }
        Comand comand = null;
        for(int i = 0; i < comands.size(); i++)
            if (comands.get(i).getComandType().toString().equals(input[0]))
                comand = comands.get(i);

        if(comand == null){
            try {
                throw new DontFindCommandException();
            } catch (DontFindCommandException exception) {}
        }
        else{
            String argument;
            if(input.length > 1)
                argument = input[1];
            else
                argument = "";
            comand.execute(argument);
            return comand;
        }

        return null;
    }
}
