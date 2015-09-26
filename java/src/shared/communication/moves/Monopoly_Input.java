package shared.communication.moves;

import shared.definitions.ResourceType;

public class Monopoly_Input 
{
	private final String type = "Monopoly";
	private int playerIndex;
	private ResourceType resource;
	
	public Monopoly_Input(int playerIndex, ResourceType resource)
	{
		super();
		this.playerIndex = playerIndex;
		this.resource = resource;
	}

	/**
	 * @return the playerIndex
	 */
	public int getPlayerIndex()
	{
		return playerIndex;
	}

	/**
	 * @param playerIndex the playerIndex to set
	 */
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}

	/**
	 * @return the resource
	 */
	public ResourceType getResource()
	{
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(ResourceType resource)
	{
		this.resource = resource;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	
	
}