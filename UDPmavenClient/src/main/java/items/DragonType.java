package items;

/**
 * Параметр, который используется в Dragon
 */
public enum DragonType {
    WATER("water"),
    UNDERGROUND("underground"),
    AIR("air"),
    FIRE("fire");

    private final String name;

    DragonType(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    /**
     * Возвращает объект DragonType по его названию
     * @param string Название объекта
     * @return Объект DragonType
     */
    public static DragonType selector(String string){
        DragonType[] dragonTypes = {DragonType.AIR, DragonType.WATER, DragonType.FIRE, DragonType.UNDERGROUND};
        for (int i = 0; i < dragonTypes.length; i++) {
            if (dragonTypes[i].toString().equals(string))
                return dragonTypes[i];
        }
        return null;
    }
}
