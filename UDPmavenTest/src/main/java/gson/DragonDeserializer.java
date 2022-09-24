package gson;

import com.google.gson.*;
import items.*;
import server.Server;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DragonDeserializer implements JsonDeserializer<Dragon> {
    @Override
    public Dragon deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

        JsonObject data = json.getAsJsonObject();
        String name = data.get("name").getAsString();
        Coordinates coordinates = (Coordinates) context.deserialize(data.get("coordinates"), Coordinates.class);
        Integer age = data.get("age").getAsInt();
        String description = data.get("description").getAsString();
        DragonType dragonType = DragonType.selector(data.get("type").getAsString());

        DragonCharacter character = DragonCharacter.selector(data.get("character").getAsString());
        DragonHead head = (DragonHead) context.deserialize(data.get("head"), DragonHead.class);

        return new Dragon(name, coordinates, age, description, dragonType, character, head, Server.idCounter++);

    }
}
