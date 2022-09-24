package gson;

import com.google.gson.*;
import items.Dragon;

import java.lang.reflect.Type;

public class DragonSerializer implements JsonSerializer<Dragon> {
    @Override
    public JsonElement serialize(Dragon dragon, Type type, JsonSerializationContext context) {
        if(dragon != null) {
            JsonObject result = new JsonObject();

            result.addProperty("name", dragon.getName());
            result.addProperty("age", dragon.getAge());
            result.addProperty("description", dragon.getDescription());
            if(dragon.getType() != null)
            result.addProperty("type", dragon.getType().toString());
            if(dragon.getCharacter() != null)
            result.addProperty("character", dragon.getCharacter().toString());

            result.add("coordinates", context.serialize(dragon.getCoordinates()));
            result.add("head", context.serialize(dragon.getHead()));

            return result;
        } else{
            return new JsonNull();
        }
    }
}
