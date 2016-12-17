package cst438;

import java.util.List;

public class GameState 
{
	enum GameStatus {PLAY, WIN, LOSE, TIE};
	
	// we track rounds won separate from games won. Games won gets saved with a player but games can 
	// be more than one round. 
	private int _roundsWon;
	private int _roundsLost;
	private int _roundsDraw;
	
	private GameStatus _gameStatus = GameStatus.PLAY;
	private GameStatus _LastRoundStatus = GameStatus.LOSE;
	
	public GameState()
	{
			
	}
	
	public int getRoundsWon()
	{
		return _roundsWon;
	}
	
	public int getRoundsLost()
	{
		return _roundsLost;
	}
	
	public GameStatus getGameStatus()
	{
		return _gameStatus;
	}
	
	public void setGameStatus(GameStatus status)
	{
		_gameStatus = status;
	}
}
