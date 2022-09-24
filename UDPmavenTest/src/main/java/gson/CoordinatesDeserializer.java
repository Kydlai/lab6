package gson;

import com.google.gson.*;
import items.Coordinates;

import java.lang.reflect.Type;

public class CoordinatesDeserializer implements JsonDeserializer<Coordinates> {
    @Override
    public Coordinates deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = json.getAsJsonObject();
        return new Coordinates(data.get("x").getAsDouble(), data.get("y").getAsDouble());
    }
}
