package items;

import client.Client;
import exceptions.WrongDragonCharacterException;
import exceptions.WrongDragonTypeException;
import client.InputOutputStream;

import java.time.LocalDate;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для элементов коллекции
 */
public class Dragon extends Item {
    private Long id = Long.valueOf(0); //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer age = 0; //Значение поля должно быть больше 0
    private String description; //Поле не может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле не может быть null
    private DragonHead head;

    /**
     *
     * @param name Имя объекта
     * @param coordinates Координаты объекта
     * @param age Возраст объекта
     * @param description Описание объекта
     * @param type Тип объекта
     * @param character Характер объекта
     * @param head Голова объекта
     */
    public Dragon(String name, Coordinates coordinates, int age, String description, DragonType type, DragonCharacter character, DragonHead head) {
        if (name != null && name != "" && coordinates != null && age > 0 && description != null && type != null && character != null) {
            this.name = name;
            this.coordinates = coordinates;
            this.age = age;
            this.description = description;
            this.type = type;
            this.character = character;
            this.head = head;
            this.id = 0L;
            creationDate = java.time.LocalDate.now();
        } else {
            if (name == null || name == "")
                System.err.println("У объекта должно быть имя");
            if (coordinates == null)
                System.err.println("У объекта должны быть координаты");
            if (age <= 0)
                System.err.println("У объекта должен быть возраст больше 0");
            if (description == null)
                System.err.println("У объекта должно быть описание");
            if (type == null)
                System.err.println("У объекта должен быть тип");
            if (character == null)
                System.err.println("У объекта должен быть характер");
            System.err.println("Объект не был добавлен в коллекцию");
        }
    }

    /**
     * Конструктор, задающий значения по умолчанию
     * Используется только для дебага или значения по умолчанию, которое после должно смениться
     */
    public Dragon() {
        this.name = "name";
        this.coordinates = new Coordinates(1, 2);
        this.age = 1;
        this.description = "aga aga";
        this.type = DragonType.AIR;
        this.character = DragonCharacter.WISE;
        this.head = new DragonHead(1L, 2F);
        this.id = 1L;
    }

    @Override
    public String getInfo(int count) {
        String output = "\n";
        output += manager.addTabs(count, "Dragon:") + "\n";
        output += manager.addTabs(count, "name = " + name) + "\n";
        output += manager.addTabs(count, "id = " + id) + "\n";
        output += manager.addTabs(count, "Coordinates: ") + "\n";
        output += coordinates.getInfo(count + 1);
        output += manager.addTabs(count, "creationDate = " + creationDate) + "\n";
        output += manager.addTabs(count, "age = " + age) + "\n";
        output += manager.addTabs(count, "description = " + description) + "\n";
        output += manager.addTabs(count, "type = " + type.toString()) + "\n";
        output += manager.addTabs(count, "character = " + character.toString()) + "\n";
        output += manager.addTabs(count, "Head:") + "\n";
        output += head.getInfo(count + 1);
        return output;
    }

    /**
     * Вычисляет чсиленное значение, по которому будет происходить сортировка
     * @return Численное значение для сортировки
     */
    public int getNumber() {
        return (Math.toIntExact(id) + age) % 10000;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * getter для DragonCharacter
     * @return DragonCharacter объекта Dragon
     */
    public DragonCharacter getCharacter() {
        return character;
    }

    /**
     * getter для description
     * @return description объекта Dragon
     */
    public String getDescription() {
        return description;
    }

    /**
     * getter для ID
     * @return ID объекта Dragon
     */
    public Long getId() {
        return id;
    }

    /**
     * getter для name
     * @return name объекта Dragon
     */
    public String getName() {
        return name;
    }

    /**
     * getter для Coordinates
     * @return Coordinates объекта Dragon
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * getter для age
     * @return age объекта Dragon
     */
    public Integer getAge() {
        return age;
    }

    /**
     * getter для DragonType
     * @return DragonType объекта Dragon
     */
    public DragonType getType() {
        return type;
    }

    /**
     * getter для DragonHead
     * @return DragonHead объекта Dragon
     */
    public DragonHead getHead() {
        return head;
    }



    /**
     * Создает объект класса Dragon исходя из информации, полученной из файла
     * @return Созданный объект класса Dragon
     */
    public static Dragon fileRead(){
        Dragon dragon = new Dragon();

        InputOutputStream stream = new InputOutputStream(Client.inputFile);
        String fullInputString = stream.read();

        String[] inputString = fullInputString.split("\r\n");

        String name = CorrectedInput.correctedFileInput(stream);
        Integer age = CorrectedInput.integerFileInput(stream);
        String description = CorrectedInput.correctedFileInput(stream);

        DragonType dragonType = null;
        DragonCharacter dragonCharacter = null;

        while (dragonType == null) {
            Client.fileReaderCounter++;
            dragonType = Dragon.typeReader(inputString[Client.fileReaderCounter - 1]);
        }

        while (dragonCharacter == null) {
            Client.fileReaderCounter++;
            dragonCharacter = Dragon.characterReader(inputString[Client.fileReaderCounter - 1]);
        }

        Coordinates coordinates = Coordinates.fileRead();
        DragonHead head = DragonHead.fileRead();

        dragon = new Dragon(name, coordinates, age, description, dragonType, dragonCharacter, head);

        return dragon;
    }

    /**
     * Создает объект класса Dragon исходя из информации, полученной из консоли
     * @return Созданный объект класса Dragon
     */
    public static Dragon consoleRead() {

        Dragon dragon = new Dragon();
        Scanner input;
        input = new Scanner(System.in);
        try {
            String name = CorrectedInput.correctedConsoleInput("name", input);
            Integer age = CorrectedInput.integerConsoleInput("age", input);
            String description = CorrectedInput.correctedConsoleInput("description", input);

            System.out.println("Введите type");
            System.out.println("Возможные значения: air, water, fire, underground");
            DragonType dragonType = null;
            while (dragonType == null)
                dragonType = Dragon.typeReader(input.nextLine());

            System.out.println("Введите character");
            System.out.println("Возможные значения: good, wise, fickle");
            DragonCharacter dragonCharacter = null;
            while (dragonCharacter == null)
                dragonCharacter = Dragon.characterReader(input.nextLine());

            Coordinates coordinates = Coordinates.consoleRead();
            DragonHead head = DragonHead.consoleRead();

            dragon = new Dragon(name, coordinates, age, description, dragonType, dragonCharacter, head);
        } catch (NoSuchElementException ex){
            System.exit(20);
        }

        return dragon;
    }

    /**
     * Получает объект класса DragonCharacter по его названию, либо сообщает об ошибке ввода
     * @param string Название, по которому получается DragonCharacter
     * @return Полученный объект класса DragonCharacter
     */
    public static DragonCharacter characterReader(String string){
        String character = "";
        DragonCharacter dragonCharacter = DragonCharacter.GOOD;
        character = string.toLowerCase(Locale.ROOT);
        if (!character.equals(DragonCharacter.WISE.toString()) && !character.equals(DragonCharacter.GOOD.toString()) &&
                !character.equals(DragonCharacter.FICKLE.toString())) {
            try {
                throw new WrongDragonCharacterException();
            } catch (WrongDragonCharacterException exception) {
            }
            return null;
        } else {
            dragonCharacter = DragonCharacter.selector(character);
        }

        return dragonCharacter;
    }

    /**
     * Получает объект класса DragonType по его названию, либо сообщает об ошибке ввода
     * @param string Название, по которому получается DragonType
     * @return Полученный объект класса DragonType
     */
    public static DragonType typeReader(String string){
        String type = "";
        DragonType dragonType = DragonType.AIR;
        type = string.toLowerCase(Locale.ROOT);
        if ((!type.equals(DragonType.AIR.toString()) && !type.equals(DragonType.UNDERGROUND.toString()) &&
                !type.equals(DragonType.WATER.toString()) && !type.equals(DragonType.FIRE.toString())) || type == null) {
            try {
                throw new WrongDragonTypeException();
            } catch (WrongDragonTypeException exception) {
            }
            return null;
        } else {
            dragonType = DragonType.selector(type);
        }
        return dragonType;
    }
}
