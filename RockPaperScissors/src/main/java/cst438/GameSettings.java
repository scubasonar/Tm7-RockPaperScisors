package cst438;

public class GameSettings {

	private int _maxRounds = 3;
	private int _halfWayPoint = 1; // get more wins or losses than this to win the game
	
	public GameSettings(int max_rounds)
	{
		_maxRounds = max_rounds;
	}
	
	public int GetMaxRounds()
	{
		return _maxRounds;
	}
	
	public int GetHalfWay()
	{
		return _halfWayPoint;
	}
	
	public void SetMaxRounds(int rounds)
	{
		_halfWayPoint = rounds / 2;
		_maxRounds = rounds;
	}
}
