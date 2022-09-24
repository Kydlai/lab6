package gson;

import com.google.gson.*;
import items.DragonHead;

import java.lang.reflect.Type;

public class DragonHeadDeserializer implements JsonDeserializer<DragonHead> {
    @Override
    public DragonHead deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = json.getAsJsonObject();
        Long size = data.get("size").getAsLong();
        Float eyesCount = data.get("eyes count").getAsFloat();

        return new DragonHead(size, eyesCount);
    }
}
