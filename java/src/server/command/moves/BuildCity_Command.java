package server.command.moves;

import com.google.gson.JsonElement;
import com.sun.net.httpserver.HttpExchange;

import server.command.ServerCommand;
import server.main.ServerInvalidRequestException;
import server.manager.ServerManager;
import shared.communication.moves.BuildCity_Input;

public class BuildCity_Command extends ServerCommand {

	private BuildCity_Input params = null;
	
	/**
	 * Command object for building a city
	 * @param json
	 * @param playerID
	 * @param gameID
	 */
	public BuildCity_Command(HttpExchange exchange)
	{
		super(exchange);
		//here we will deserialize the JSON into a BuildCity_Input object
	}

	@Override
	public JsonElement execute()
	{
		return ServerManager.instance().getMovesFacade().buildCity(params, super.playerId, super.gameId);
	}

	@Override
	public JsonElement execute(String json) throws ServerInvalidRequestException {
		// TODO Auto-generated method stub
		return null;
	}

}
