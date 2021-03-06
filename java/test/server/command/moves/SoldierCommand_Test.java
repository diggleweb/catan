package server.command.moves;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import server.command.ExchangeWrapper;
import server.main.ServerInvalidRequestException;
import server.manager.ServerManager;

public class SoldierCommand_Test 
{
	ExchangeWrapper mockExchange; 
	Soldier_Command cmdObj; 
	
	@Before
	public void init(){
		mockExchange = new ExchangeWrapper(null);
		
		ServerManager.instance().reset();
		ServerManager.instance().setFakeFacades();
	}
	
	@Test
	public void testSoldier() throws ServerInvalidRequestException{
		String jsonString = "{\"type\": \"Soldier\", \"playerIndex\": 1," +
				"\"victimIndex\": 2, \"location\": { \"x\": 1,\"y\": 1}}";
		JsonObject json = new JsonParser().parse(jsonString)
				.getAsJsonObject();
		mockExchange.setJson(json);
		cmdObj = new Soldier_Command(mockExchange);
		cmdObj.setPlayerID(1);
		cmdObj.setGameID(1);
		
		assert(cmdObj.execute().getClass() == JsonPrimitive.class);
	}
}
