package shared.models;

import shared.definitions.HexType;
import shared.definitions.ResourceType;

public class ResourceCards {

	private int sheep;
	private int wheat;
	private int wood;
	private int brick;
	private int ore;
	
	public ResourceCards() {
		this.sheep = 0;
		this.wheat = 0;
		this.wood = 0;
		this.brick = 0;
		this.ore = 0;
	}
	
	public ResourceCards(int sheep, int wheat, int wood, int brick, int ore) 
	{
		this.sheep = sheep;
		this.wheat = wheat;
		this.wood = wood;
		this.brick = brick;
		this.ore = ore;
	}
	
	/**
	 * 
	 * @param cardTotals
	 * 
	 * This function will be called to update the ResourceCardList object.
	 * The cardTotals is an array containing a number for each of the card types held in the ResourceCardList
	 * With each number held in cardTotals, it will correspond to a specific card type in order:
	 * sheep
	 * wheat
	 * wood
	 * brick
	 * ore
	 * 
	 */
	public void updateResources(int[] cardTotals) {
		
	}
	
	public boolean hasResources(ResourceCards resources)
	{
		boolean hasCards = true;
		if(this.sheep < resources.getSheep())
		{
			hasCards = false;
		}
		else if(this.wheat < resources.getWheat())
		{
			hasCards = false;
		}
		else if(this.wood < resources.getWood())
		{
			hasCards = false;
		}
		else if(this.brick < resources.getBrick())
		{
			hasCards = false;
		}
		else if(this.ore < resources.getOre())
		{
			hasCards = false;
		}
		return hasCards;
	}
	
	/**
	 * used to determine if the owner of this recourseCards can offer the specified cards
	 * offered cards are negative
	 * @param offer
	 * @return
	 */
	public boolean canOfferCards(ResourceCards offer)
	{
		boolean hasCards = true;
		if((offer.sheep < 0) && (this.sheep < Math.abs(offer.sheep)))
		{
			hasCards = false;
		}
		else if((offer.wheat < 0) && (this.wheat < Math.abs(offer.wheat)))
		{
			hasCards = false;
		}
		else if((offer.wood < 0) && (this.wood < Math.abs(offer.wood)))
		{
			hasCards = false;
		}
		else if((offer.brick < 0) && (this.brick < Math.abs(offer.brick)))
		{
			hasCards = false;
		}
		else if((offer.ore < 0) && (this.ore < Math.abs(offer.ore)))
		{
			hasCards = false;
		}
		return hasCards;
	}

	public int getResourceValue(ResourceType resource)
	{
		switch (resource.toString()) {
		case "WOOD":
			return wood;
		case "BRICK":
			return brick;
		case "SHEEP":
			return sheep;
		case "WHEAT":
			return wheat;
		case "ORE":
			return ore;
		}
		return -1;
	}
	
	public void addOne(ResourceType resource)
	{
		switch (resource.toString()) {
		case "WOOD":
			this.wood++;
			return;
		case "BRICK":
			this.brick++;
			return;
		case "SHEEP":
			this.sheep++;
			return;
		case "WHEAT":
			this.wheat++;
			return;
		case "ORE":
			this.ore++;
			return;
		}
	}
	
	public void subtractOne(ResourceType resource)
	{
		switch (resource.toString()) {
		case "WOOD":
			this.wood--;
			return;
		case "BRICK":
			this.brick--;
			return;
		case "SHEEP":
			this.sheep--;
			return;
		case "WHEAT":
			this.wheat--;
			return;
		case "ORE":
			this.ore--;
			return;
		}
	}

	public void resetOneResourceValue(ResourceType resource)
	{
		switch (resource.toString()) {
		case "WOOD":
			this.wood = 0;
			return;
		case "BRICK":
			this.brick = 0;
			return;
		case "SHEEP":
			this.sheep = 0;
			return;
		case "WHEAT":
			this.wheat = 0;
			return;
		case "ORE":
			this.ore = 0;
			return;
		}
	}
	
	public void resetAllResourceValues()
	{
		this.wood = 0;
		this.brick = 0;
		this.sheep = 0;
		this.wheat = 0;
		this.ore = 0;
	}
	
	@Override
	public String toString() {
		return "ResourceCards [" 
				+ "\n\twood  = " + wood 
				+ "\n\tbrick = " + brick 
				+ "\n\tsheep = " + sheep 
				+ "\n\twheat = " + wheat 
				+ "\n\tore   = " + ore + "   ]";
	}

	
	public int getSheep() {
		return sheep;
	}

	public void setSheep(int sheep) {
		this.sheep = sheep;
	}

	public int getWheat() {
		return wheat;
	}

	public void setWheat(int wheat) {
		this.wheat = wheat;
	}

	public int getWood() {
		return wood;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public int getBrick() {
		return brick;
	}

	public void setBrick(int brick) {
		this.brick = brick;
	}

	public int getOre() {
		return ore;
	}

	public void setOre(int ore) {
		this.ore = ore;
	}

	public int getTotal()
	{
		return brick + ore + wheat + wood + sheep;
	}
	
	public String toJsonString()
	{
		return "{\"brick\":"+brick+",\"ore\":"+ore+",\"sheep\":"+sheep+",\"wheat\":"+wheat+",\"wood\":"+wood+"}";
	}
	
}
