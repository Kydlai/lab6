package items;

/**
 * Параметр, который используется в Dragon
 */
public enum DragonCharacter {
    WISE("wise"),
    GOOD("good"),
    FICKLE("fickle");

    private final String name;

    DragonCharacter(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    /**
     * Возвращает объект DragonCharacter по его названию
     * @param string Название объекта
     * @return Объект DragonCharacter
     */
    public static DragonCharacter selector(String string){
        DragonCharacter[] dragonCharacters = {DragonCharacter.GOOD, DragonCharacter.FICKLE, DragonCharacter.WISE};
        for (int i = 0; i < dragonCharacters.length; i++) {
            if (dragonCharacters[i].toString().equals(string))
                return dragonCharacters[i];
        }
        return null;
    }

}
