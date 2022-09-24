package gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import items.DragonHead;

import java.lang.reflect.Type;

public class DragonHeadSerializer implements JsonSerializer<DragonHead> {
    @Override
    public JsonElement serialize(DragonHead dragonHead, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("size", dragonHead.getSize());
        result.addProperty("eyes count", dragonHead.getEyesCount());

        return result;
    }
}
