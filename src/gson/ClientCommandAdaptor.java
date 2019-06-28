package gson;

import com.google.gson.*;
import command.CommandType;
import command.clientCommand.ClientCommand;
import command.clientCommand.SaveCommand;
import command.clientCommand.SignInCommand;
import command.clientCommand.SignUpCommand;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ClientCommandAdaptor implements JsonSerializer<ClientCommand>, JsonDeserializer<ClientCommand> {
    private static HashMap<String, Class> map = new HashMap<>();
    static
    {
        map.put(CommandType.SIGNIN.toString(), SignInCommand.class);
        map.put(CommandType.SIGNUP.toString(), SignUpCommand.class);
        map.put(CommandType.SAVE.toString(), SaveCommand.class);
    }

    @Override
    public ClientCommand deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Class cls = map.get(jsonElement.getAsJsonObject().get("type").getAsString());
        return context.deserialize(jsonElement, cls);
    }

    @Override
    public JsonElement serialize(ClientCommand clientCommand, Type type, JsonSerializationContext context) {
        JsonObject retVal = context.serialize(clientCommand).getAsJsonObject();
        retVal.add("type", new JsonPrimitive(String.valueOf(clientCommand.getType())));
        return retVal;
    }
}
