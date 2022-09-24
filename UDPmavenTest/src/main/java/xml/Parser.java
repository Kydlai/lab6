package xml;

import items.*;
import server.ProjectCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import server.Server;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


/**
 * Работает с XML файлом и сохраненяет/загружает коллекцию
 */
public class Parser {
    /**
     * Загружает коллекцию из XML файла
     * @param collection Класс коллекции, чтобы было куда загружать
     */
    public static void readerXML(ProjectCollection collection) {
        try {

            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("lab5.xml");

            Node root = document.getDocumentElement();

            NodeList nodes = root.getChildNodes();
            for (int i = 0; i < nodes.getLength(); i++) {
                Node book = nodes.item(i);
                if (book.getNodeType() != Node.TEXT_NODE) {

                    String name = null;
                    int age = 0;
                    String description = null;
                    DragonType dragonType = null;
                    DragonCharacter dragonCharacter = null;
                    Long size = null;
                    Float eyesCount = null;
                    double x = 0;
                    double y = 0;

                    NodeList bookProps = book.getChildNodes();
                    for(int j = 0; j < bookProps.getLength(); j++) {
                        Node bookProp = bookProps.item(j);

                        if(bookProp.getNodeName().equals("DragonHead")){
                            NodeList items = bookProp.getChildNodes();
                            for(int t = 0; t < items.getLength(); t++) {
                                Node item = items.item(t);

                                if (item.getNodeType() != Node.TEXT_NODE) {
                                    if(item.getNodeName().equals("size"))
                                        size = Long.valueOf(item.getChildNodes().item(0).getTextContent());
                                    if(item.getNodeName().equals("eyesCount"))
                                        eyesCount = Float.valueOf(item.getChildNodes().item(0).getTextContent());
                                }
                            }
                        }
                        else if(bookProp.getNodeName().equals("Coordinates")) {
                            NodeList items = bookProp.getChildNodes();
                            for (int t = 0; t < items.getLength(); t++) {
                                Node item = items.item(t);

                                if (item.getNodeType() != Node.TEXT_NODE) {
                                    if(item.getNodeName().equals("x"))
                                        x = Double.valueOf(item.getChildNodes().item(0).getTextContent());
                                    if(item.getNodeName().equals("y"))
                                        y = Double.valueOf(item.getChildNodes().item(0).getTextContent());
                                }
                            }
                        }else
                            if (bookProp.getNodeType() != Node.TEXT_NODE) {

                                if(bookProp.getNodeName().equals("name"))
                                    name = bookProp.getChildNodes().item(0).getTextContent();
                                if(bookProp.getNodeName().equals("age"))
                                    age = Integer.valueOf(bookProp.getChildNodes().item(0).getTextContent());
                                if(bookProp.getNodeName().equals("description"))
                                    description = bookProp.getChildNodes().item(0).getTextContent();
                                if(bookProp.getNodeName().equals("DragonType"))
                                    dragonType = DragonType.selector(bookProp.getChildNodes().item(0).getTextContent());
                                if(bookProp.getNodeName().equals("DragonCharacter"))
                                    dragonCharacter = DragonCharacter.selector(bookProp.getChildNodes().item(0).getTextContent());
                        }
                    }

                    collection.add(new Dragon(name, new Coordinates(x, y), age, description, dragonType, dragonCharacter, new DragonHead(size, eyesCount), Server.idCounter++));

                }
            }

        } catch (ParserConfigurationException ex) {
            //ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            //ex.printStackTrace(System.out);
        } catch (IOException ex) {
            //ex.printStackTrace(System.out);
        }
    }

    /**
     * сохранет информацию о коллекции в текстовом формате
     * @param collection Класс коллекции, чтобы было что сохранять
     * @return Текст в формате XML, хронящий данные о коллекции
     */
    public static String creatorXML(ProjectCollection collection){
        String output = "<collection>\n";
        for(int i = 0; i < collection.getCollection().size(); i++){
            Dragon dragon = collection.getCollection().get(i);
            output += "\t<dragon>\n";
            output += "\t\t<name>" + dragon.getName() + "</name>\n";
            output += "\t\t<age>" + dragon.getAge() + "</age>\n";
            output += "\t\t<description>" + dragon.getDescription() + "</description>\n";
            output += "\t\t<DragonType>" + dragon.getType().toString() + "</DragonType>\n";
            output += "\t\t<DragonCharacter>" + dragon.getCharacter() + "</DragonCharacter>\n";
            output += "\t\t<DragonHead>\n";
            output += "\t\t\t<size>" + dragon.getHead().getSize() + "</size>\n";
            output += "\t\t\t<eyesCount>" + dragon.getHead().getEyesCount() + "</eyesCount>\n";
            output += "\t\t</DragonHead>\n";
            output += "\t\t<Coordinates>\n";
            output += "\t\t\t<x>" + dragon.getCoordinates().getX() + "</x>\n";
            output += "\t\t\t<y>" + dragon.getCoordinates().getY() + "</y>\n";
            output += "\t\t</Coordinates>\n";
            output += "\t</dragon>\n";
        }
        output += "</collection>\n";
        return output;
    }


}
