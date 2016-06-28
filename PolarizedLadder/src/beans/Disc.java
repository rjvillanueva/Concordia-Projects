package beans;

/**
 * This class is developed to instantiate the discs on the board.
 * 
 * @author Reina Villanueva
 *
 */

public class Disc {

	private int id;					// ID in list of discs
	private int player;				// player1 or player2
	private String position;		// position name on the board (eg. "A1")
	private String value;			// String representation on the board (ie. "." or "X" or "O")
	private int winPossibilities;	// total number of winning possibilities
	private int weight;				// weight of the disc
	
	/**
	 * Instantiate a Disc object.
	 * @param id
	 * @param player
	 * @param position
	 * @param value
	 * @param winPossibilities
	 */
	public Disc(int id, int player, String position, String value, int winPossibilities, int weight)
	{ 
		this.id = id;
		this.player = player;
		this.position = position;
		this.value = value;
		this.winPossibilities = winPossibilities;
		this.weight = weight;
	}

	/**
	 * Sets the id of the disc.
	 * @param id
	 */
	public void setID(int id)
	{
		this.id = id;
	}
	
	/**
	 * Gets the id of the disc.
	 * @return id
	 */
	public int getID()
	{
		return id;
	}
	
	/**
	 * Sets the position of the disc.
	 * @param position
	 */
	public void setPosition(String position)
	{
		this.position = position;
	}
	
	/**
	 * Gets the position of the disc.
	 * @return position
	 */
	public String getPosition()
	{
		return position;
	}
	
	/**
	 * Sets the player owner of the disc.
	 * @param player
	 */
	public void setPlayer(int player)
	{
		this.player = player;
	}
	
	/**
	 * Gets the player owner of the disc.
	 * @return player owner
	 */
	public int getPlayer()
	{
		return player;
	}
	
	/**
	 * Sets the String value of the disc.
	 * @param value
	 */
	public void setValue(String value)
	{
		this.value = value;
	}
	
	/**
	 * Gets the String value of the disc.
	 * @return value
	 */
	public String getValue()
	{
		return value;
	}
	
	/**
	 * Sets the total number of winning possibilities of the disc.
	 * @param winPossibilities
	 */
	public void setWinPossibilities(int winPossibilities)
	{
		this.winPossibilities = winPossibilities;
	}
	
	/**
	 * Gets the total number of winning possibilities of the disc.
	 * @return total number of winning possibilities
	 */
	public int getWinPossibilities()
	{
		return winPossibilities;
	}
	
	/**
	 * Sets the weight of the disc.
	 * @param weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Gets the weight of the disc.
	 * @return
	 */
	public int getWeight() {
		return weight;
	}
}
