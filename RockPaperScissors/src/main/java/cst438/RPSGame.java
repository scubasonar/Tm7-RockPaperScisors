package cst438;

import java.io.IOException;
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
	public enum Value {Rock, Paper, Scissors}
       
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
		
		request.getRequestDispatcher("rpsStart.jsp").forward(request, response);
		
	}

}

/**
 * Player class to track player info during a session
 */
class Player
{
	private int won;
	private int lost;
	private RPSGame.Value value;
	
	public Player()
	{
		setWon(0);
		setLost(0);
		setValue(null);
	}
	
	public RPSGame.Value getValue()
	{
		return value;
	}
	
	public void setValue(RPSGame.Value value)
	{
		this.value = value;
	}
	
	public int getLost()
	{
		return lost;
	}
	
	public void incrementLost()
	{
		lost++;
	}
	
	public void setLost(int lost)
	{
		if (lost < 0)
		{
			return;
		}
		
		this.lost = lost;
	}
	
	public int getWon()
	{
		return won;
	}
	
	public void incrementWon()
	{
		won++;
	}
	
	public void setWon(int won)
	{
		if (won < 0)
		{
			return;
		}
		
		this.won = won;
	}
}