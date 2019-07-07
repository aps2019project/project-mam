package gson;

import com.google.gson.*;
import command.CommandType;
import command.clientCommand.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class ClientCommandAdaptor implements JsonSerializer<ClientCommand>, JsonDeserializer<ClientCommand> {
    private static HashMap<String, Class> map = new HashMap<>();
    static
    {
        map.put(CommandType.SIGNIN.toString(), SignInCmd.class);
        map.put(CommandType.SIGNUP.toString(), SignUpCmd.class);
        map.put(CommandType.SAVE.toString(), SaveCmd.class);
        map.put(CommandType.BUY.toString(), BuyCmd.class);
        map.put(CommandType.REQUEST_GAME.toString(), RequestGameCmd.class);
        map.put(CommandType.SEARCH.toString(), SearchCmd.class);
        map.put(CommandType.SELL.toString(), SellCmd.class);
        map.put(CommandType.SHOWALL.toString(), ShowAllCmd.class);
        map.put(CommandType.ATTACK.toString(), AttackCmd.class);
        map.put(CommandType.MOVE.toString(), MoveCmd.class);
        map.put(CommandType.SELECT.toString(), SelectCmd.class);
        map.put(CommandType.INSERT.toString(), InsertCmd.class);
        map.put(CommandType.EXIT_GAME.toString(), ExitGameCmd.class);
        map.put(CommandType.ENDTURN.toString(), EndTurnCmd.class);
        map.put(CommandType.CREATE_CUSTOM.toString(), CustomCmd.class);
        map.put(CommandType.SCORE_BOARD.toString(), ScoreBoardCmd.class);
        map.put(CommandType.LOGOUT.toString(), LogOutCmd.class);
        map.put(CommandType.CHAT.toString(), ChatCmd.class);
        map.put(CommandType.REQUEST_LIVE.toString(), RequestLiveCmd.class);
        map.put(CommandType.GET_GAME.toString(), GetGameCmd.class);
        map.put(CommandType.END_GAME.toString(), EndGameCmd.class);
        map.put(CommandType.GET_LAST_GAME.toString(), GetLastGameCmd.class);
        map.put(CommandType.REPLAY.toString(), ReplayCmd.class);
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
