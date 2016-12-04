package cst438;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RPSGame
 */
public class RPSGame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RPSGame() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Create new session or retrieve previous session
		HttpSession session = request.getSession(true);
		Player player = (Player) session.getAttribute("player");
		
		if (player == null)
		{
			player = new Player();
		}
		session.setAttribute("player", player);
		
		String newGame = request.getParameter("newGame");
		if (newGame != null)
		{
			session.invalidate();
			player = null;
			response.sendRedirect("index.jsp");
			return;
		}
		
		request.setAttribute("totalWon", player.getTotalWon());
		request.setAttribute("totalLost", player.getTotalLost());
		request.setAttribute("totalDraw", player.getTotalDraws());
		
		String value = request.getParameter("value");
		if (value != null)
		{
			String computerSelection = generateComputerPick();
			int turnResult = 0;
			request.setAttribute("computer", computerSelection);
			request.setAttribute("player", value);

			turnResult = playTurn(value, computerSelection, player);
			
			
			if (turnResult == 0)
			{
				request.getRequestDispatcher("rpsLose.jsp").forward(request, response);
			}
			else if (turnResult == 1)
			{
				request.getRequestDispatcher("rpsTie.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("rpsWin.jsp").forward(request, response);
			}
		}
		else
		{
			request.getRequestDispatcher("rpsStart.jsp").forward(request, response);
		}
		
	}
	
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
	public static int playTurn(String value, String computerSelection, Player player)
	{
		int playerWon = 0;
		
		if (value.equals(computerSelection))
		{
			playerWon = 1;
			player.incrementTotalDraws();
		}
		else if (value.equals("Rock"))
		{
			if (computerSelection.equals("Paper"))
			{
				playerWon = 0;
				player.incrementTotalLost();
			}
			else
			{
				playerWon = 2;
				player.incrementTotalWon();
			}
		}
		else if (value.equals("Paper"))
		{
			if (computerSelection.equals("Scissors"))
			{
				playerWon = 0;
				player.incrementTotalLost();
			}
			else
			{
				playerWon = 2;
				player.incrementTotalWon();
			}
		}
		else
		{
			if (computerSelection.equals("Rock"))
			{
				playerWon = 0;
				player.incrementTotalLost();
			}
			else
			{
				playerWon = 2;
				player.incrementTotalWon();
			}
		}
		
		return playerWon;
	}
	
	static Random randomGenerator = new Random();
	
	private static String generateComputerPick()
	{
		int num = randomGenerator.nextInt(3);
		String result = null;
		
		switch (num)
		{
		case 0:
			result = "Rock";
			break;
		case 1:
			result = "Paper";
			break;
		case 2:
			result = "Scissors";
			break;
		}
		return result;
	}

}
