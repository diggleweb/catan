package client.join;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.data.PlayerInfo;
import client.misc.MessageView;
import client.session.SessionManager;
import shared.communication.game.AddAI_Input;
import shared.models.Player;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {

	public PlayerWaitingController(IPlayerWaitingView view) {

		super(view);
		
		SessionManager.instance().addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		System.out.println("Starting PlayerWaiting Update");
		boolean updated = false;
		
		SessionManager session = SessionManager.instance();
		for(Player player : session.getClientModel().getPlayers()){ //iterates through all players
			if(player != null)
			{
				PlayerInfo newPlayer = new PlayerInfo();
				newPlayer.setColor(player.getCatanColor());
				newPlayer.setId(player.getPlayerID());
				newPlayer.setName(player.getName());
				newPlayer.setPlayerIndex(player.getIndex());
				
				for(PlayerInfo pi : session.getGameInfo().getPlayers()){ //checks all players already known about
					if(pi.getName().equals(player.getName())){ //if that player is already known, we don't need to add them
						newPlayer = null;
						if(!pi.getColor().equals(player.getCatanColor())){
							updated = true;
							pi.setColor(player.getCatanColor()); //in case colors have changed
						}
						break;
					}
				}
				if(newPlayer!=null){ //if the player wasn't found, add him/her to the game
					updated = true;
					session.getGameInfo().addPlayer(newPlayer);
				}
			}
		}
		updatePlayers();
		if(!isFull())
			SessionManager.instance().getPoller().getClientModel().setVersion(-1);
		
		if(isFull() && getView().isModalShowing()) {
//			OverlayView.closeAllModals();
			getView().closeModal();
			SessionManager.instance().setupGame();
		} else if (updated && !isFull()){
			getView().closeModal();
			getView().showModal();
		}
		System.out.println("Ending PlayerWaiting Update");
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() 
	{
		SessionManager.instance().forceUpdate();
		updatePlayers();
		
		if (isFull()) 
		{
//			OverlayView.closeAllModals();
			getView().closeModal();
			SessionManager.instance().setupGame();
			SessionManager.instance().forceUpdate();
		} 
		else 
		{
			getView().showModal();
		}
	}

	@Override
	public void addAI() 
	{
		try 
		{
			AddAI_Input req = new AddAI_Input("LARGEST_ARMY"); //only type of AI supported by current server
			SessionManager.instance().getServer().addAI(req);
			SessionManager.instance().forceUpdate();
		} 
		catch (Exception e) 
		{
			MessageView alertView = new MessageView();
			alertView.setTitle("Error");
			alertView.setMessage(e.getLocalizedMessage());
			alertView.showModal();
		}
	}
	
	private boolean isFull()
	{
		if(SessionManager.instance().getGameInfo().getPlayers().size()>=4) //modal only closes if there are four or more players
			return true;
		return false;
	}
	
	private void updatePlayers() 
	{
		ArrayList<PlayerInfo> players =  new ArrayList<PlayerInfo>(SessionManager.instance().getGameInfo().getPlayers());
		PlayerInfo [] playerArray = players.toArray(new PlayerInfo[players.size()]);
		getView().setPlayers(playerArray);
	}
}