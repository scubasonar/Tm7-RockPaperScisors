package cst438;


/**
 * Player class to track player info during a session
 */
class Player
{
	private int totalWon;
	private int totalLost;
	private int totalDraws;
	
	private String emailAddress;
	
	public Player()
	{
		setTotalLost(0);
		setTotalWon(0);
	}
	
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public void setEmailAddress(String email)
	{
		if (email == null)
		{
			return;
		}
		
		emailAddress = email;
	}
	
	public int getTotalLost()
	{
		return totalLost;
	}
	
	public void incrementTotalLost()
	{
		totalLost++;
	}
	
	public void setTotalLost(int lost)
	{
		if (lost < 0)
		{
			return;
		}
		
		totalLost = lost;
	}
	
	public int getTotalWon()
	{
		return totalWon;
	}
	
	public void incrementTotalWon()
	{
		totalWon++;
	}
	
	public void setTotalWon(int won)
	{
		if (won < 0)
		{
			return;
		}
		
		totalWon = won;
	}
	
	public int getTotalDraws()
	{
		return totalDraws;
	}
	
	public void incrementTotalDraws()
	{
		totalDraws++;
	}
	
	public void setTotalDraws(int draw)
	{
		if (draw < 0)
		{
			return;
		}
		
		totalDraws = draw;
	}

}