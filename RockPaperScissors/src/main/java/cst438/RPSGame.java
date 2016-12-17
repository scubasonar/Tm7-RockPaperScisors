package cst438;

import java.util.Random;

import cst438.GameState.GameStatus;

public class RPSGame
{
	public enum AttackType {ROCK, PAPER, SCISSORS};
	
	static Random randomGenerator = new Random();
	
	Player _player;
	GameState _gameState;
	GameSettings _gameSettings;
	
	public RPSGame()
	{
		_player = new Player();
		_gameState = new GameState();
		_gameSettings = new GameSettings(3);
	}
	
	public RPSGame(Player player)
	{
		_player = player;
		_gameState = new GameState();
		_gameSettings = new GameSettings(3);
	}
	
	public GameState.GameStatus playGame(AttackType playerAttack)
	{
		int halfWayPoint = _gameSettings.GetHalfWay();
		AttackType computerAttack = generateComputerPick();
		
		if(_gameState.getRoundsWon() > halfWayPoint)
			_gameState.setGameStatus(GameState.GameStatus.WIN);
		else if(_gameState.getRoundsLost() > halfWayPoint)
			_gameState.setGameStatus(GameState.GameStatus.LOSE);
		else
			_gameState.setGameStatus(GameState.GameStatus.PLAY);
			
		return _gameState.getGameStatus();
		
	}
	
	public 
	/**
	 * Simulates one turn of Rock, Paper, Scissors. Selects a random choice
	 * for the computer controlled player.
	 * @param value An enum of type Value, with possible values of: Rock, Paper
	 * or Scissors
	 * @param computerSelection An enum of type Value, with possible values of: Rock, Paper
	 * or Scissors
	 * @param player An object of type Player that holds the stats for the current session
	 * @return Returns 0 for a loss, 1 for a tie, and 2 for a win
	 */
	GameStatus playTurn(AttackType playerAttack, AttackType computerAttack)
	{
		GameStatus roundStatus = GameStatus.LOSE;
		
		if (playerAttack.equals(computerAttack))
		{
			roundStatus = GameStatus.TIE;
			_player.incrementTotalDraws();
		}
		else if (playerAttack.equals(AttackType.ROCK))
		{
			if (computerAttack.equals(AttackType.PAPER))
			{
				roundStatus = GameStatus.LOSE;
				_player.incrementTotalLost();
			}
			else
			{
				roundStatus = GameStatus.WIN;
				_player.incrementTotalWon();
			}
		}
		else if (playerAttack.equals(AttackType.PAPER))
		{
			if (computerAttack.equals(AttackType.SCISSORS))
			{
				roundStatus = GameStatus.LOSE;
				_player.incrementTotalLost();
			}
			else
			{
				roundStatus = GameStatus.WIN;
				_player.incrementTotalWon();
			}
		}
		else
		{
			if (computerAttack.equals(AttackType.ROCK))
			{
				roundStatus = GameStatus.LOSE;
				_player.incrementTotalLost();
			}
			else
			{
				roundStatus = GameStatus.WIN;
				_player.incrementTotalWon();
			}
		}
		
		return roundStatus;
	}

	
	// convert a string for an attack to it's type
	public static AttackType GetAttackType(String attack)
	{
		if(attack.toLowerCase().equals("rock"))
			return AttackType.ROCK;
		else if(attack.toLowerCase().equals("rock"))
			return AttackType.PAPER;
		else 
			return AttackType.SCISSORS;
	}
	
	private static AttackType generateComputerPick()
	{
		int num = randomGenerator.nextInt(3);
		
		switch (num)
		{
		case 0:
			return AttackType.PAPER;
		case 1:
			return AttackType.ROCK;
		case 2:
			return AttackType.SCISSORS;
		}
		
		return AttackType.PAPER;
	}
}