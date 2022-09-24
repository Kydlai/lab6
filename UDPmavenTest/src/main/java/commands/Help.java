package commands;

import server.ProjectCollection;
import server.Server;


/**
 * Класс команды help
 */
public class Help extends Comand {

    private ComandTypes comandType;

    @Override
    public String getName(){
        return comandType.toString();
    }

    public Help() {
        this.comandType = ComandTypes.HELP;
    }

    public String getInfo(){
        return "help : вывести справку по доступным командам";
    }

    public String execute(){
        Server.toSend = true;
        String output = Server.commands.get(0).getInfo();
        for(int i = 1; i < Server.commands.size(); ++i){
            output += "\n" + Server.commands.get(i).getInfo();
        }
        return output;
    }

    public String execute(String argument){
        return this.execute();
    }
}
