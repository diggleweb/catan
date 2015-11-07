package server.manager;

import java.util.HashMap;

import server.facade.*;
import shared.models.ClientModel;

public class ServerManager {
	
	HashMap<Integer, ClientModel> models;
	public static ServerManager _instance;
	private IUserFacade userFacade;
	private IGameFacade gameFacade;
	private IGamesFacade gamesFacade;
	private IMovesFacade movesFacade;
	
	public static ServerManager instance() {
		if (_instance == null)
			_instance = new ServerManager();
		return _instance;
	}

	public void setFacades() {
		userFacade = new UserFacade();
		gameFacade = new GameFacade();
		gamesFacade = new GamesFacade();
		movesFacade = new MovesFacade();
	}
	
	public void setFakeFacades() {
		userFacade = new FakeUserFacade();
		gameFacade = new FakeGameFacade();
		gamesFacade = new FakeGamesFacade();
		movesFacade = new FakeMovesFacade();
	}

	public IUserFacade getUserFacade() {
		return userFacade;
	}

	public IGameFacade getGameFacade() {
		return gameFacade;
	}

	public IGamesFacade getGamesFacade() {
		return gamesFacade;
	}

	public IMovesFacade getMovesFacade() {
		return movesFacade;
	}
}
