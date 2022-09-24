package commands;


/**
 * Класс команды execute_script
 */
public class ExecuteScript extends Comand {

    protected ComandTypes comandType = ComandTypes.EXECUTE_SCRIPT;
    private String data;

    @Override
    public ComandTypes getComandType() {
        return comandType;
    }

    public String getInfo() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    public void execute(String arg) {
        toTransport = true;
        data = arg;
    }
}
