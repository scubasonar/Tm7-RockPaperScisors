package cst438;

/**
 * Player class to track player info during a session
 */
class Player
{
	private int totalWon;
	private int totalLost;
	private int rockCounter;
	private int paperCounter;
	private int scissorsCounter;
	private int rockWins;
	private int rockLosses;
	private int paperWins;
	
	public Player()
	{
		setWon(0);
		setLost(0);
	}
	
	public int getLost()
	{
		return totalLost;
	}
	
	public void incrementLost()
	{
		totalLost++;
	}
	
	public void setLost(int lost)
	{
		if (lost < 0)
		{
			return;
		}
		
		totalLost = lost;
	}
	
	public int getWon()
	{
		return totalWon;
	}
	
	public void incrementWon()
	{
		totalWon++;
	}
	
	public void setWon(int won)
	{
		if (won < 0)
		{
			return;
		}
		
		totalWon = won;
	}
}