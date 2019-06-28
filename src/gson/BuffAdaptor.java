package gson;

import Model.Buffs.*;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class BuffAdaptor implements JsonSerializer<Buff>, JsonDeserializer<Buff> {
    private static HashMap<String, Class> map = new HashMap<>();

    static
    {
        map.put("CellEffect", CellEffect.class);
        map.put("DestroyNegatives", DestroyNegatives.class);
        map.put("DestroyPositives", DestroyPositives.class);
        map.put("Disarm", Disarm.class);
        map.put("Holy", Holy.class);
        map.put("IncreaseMana", IncreaseMana.class);
        map.put("Kill", Kill.class);
        map.put("Poison", Poison.class);
        map.put("Power", Power.class);
        map.put("Stun", Stun.class);
        map.put("Weakness", Weakness.class);
    }
    @Override
    public Buff deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Class cls = map.get(jsonElement.getAsJsonObject().get("kind").getAsString());
        return context.deserialize(jsonElement, cls);
    }

    @Override
    public JsonElement serialize(Buff buff, Type type, JsonSerializationContext context) {
        JsonObject retVal = context.serialize(buff).getAsJsonObject();
        retVal.add("kind", new JsonPrimitive(buff.getKind()));
        return retVal;
    }
}
