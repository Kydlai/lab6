package commands;


public enum ComandTypes {
    INFO("info"),
    SHOW("show"),
    ADD("add"),
    HELP("help"),
    UPDATE_ID("update_id"),
    REMOVE_BY_ID("remove_by_id"),
    CLEAR("clear"),
    SAVE("save"),
    EXIT("exit"),
    REMOVE_FIRST("remove_first"),
    EXECUTE_SCRIPT("execute_script"),
    REMOVE_LOVER("remove_lover"),
    REORDER("reorder"),
    COUNT_BY_CHARACTER("count_by_character"),
    FILTER_STARTS_WITH_DESCRIPTION("filter_starts_with_description"),
    PRINT_DESCENDING("print_descending");

    private final String name;

    ComandTypes(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public static ComandTypes selector(String string){
        ComandTypes[] comandTypes =  ComandManager.comands.stream().map((p) -> p.getComandType()).toArray(ComandTypes[]::new);
        for (int i = 0; i < comandTypes.length; i++) {
            if (comandTypes[i].toString().equals(string))
                return comandTypes[i];
        }
        return null;
    }
}