package gson;

import Model.card.Card;
import Model.card.Hero;
import Model.card.Minion;
import Model.card.Spell;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class CardAdapter implements JsonSerializer<Card>, JsonDeserializer<Card> {
    private static HashMap<String, Class> map = new HashMap<>();

    static
    {
        map.put("hero", Hero.class);
        map.put("minion", Minion.class);
        map.put("spell", Spell.class);
    }

    @Override
    public Card deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Class cls = map.get(jsonElement.getAsJsonObject().get("type").getAsString());
        return context.deserialize(jsonElement, cls);
    }

    @Override
    public JsonElement serialize(Card card, Type type, JsonSerializationContext context) {
        JsonObject retVal = context.serialize(card).getAsJsonObject();
        retVal.add("type", new JsonPrimitive(card.getCardType()));
        return retVal;
    }
}
