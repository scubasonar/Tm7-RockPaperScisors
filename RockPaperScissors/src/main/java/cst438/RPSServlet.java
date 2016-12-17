package cst438;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class RPSGame
 */
public class RPSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//String buildVersion = RPSGame.class.getPackage().getImplementationVersion(); // isn't working
	static final String buildVersion = "12/4/16 10pm";  // change if you recompile
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RPSServlet()
    {
    	super();
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
		request.setAttribute("buildVersion", buildVersion);
		String email = null;
		
		if (player == null)
		{
			email = request.getParameter("email");
			Connection dbConn;
			try {
				dbConn = getConnection();
				if(dbConn != null)
				{
					player = createUser(dbConn, email);
				}
				else
					{
					System.out.println("Error: Could not connect to database to create a new user.");
					player = new Player();
					}
					
				}
			catch (SQLException e) {				
				e.printStackTrace();
			}	
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
			Connection dbConn;
			try {
				dbConn = getConnection();
				saveUser(dbConn, player);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
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
	
	public static Player createUser(Connection con, String user) throws SQLException
	{
		Statement stmt = null;
		String query = "select * from player where email = '" + user + "'";
		String userName = null;
		int wins = 0, losses = 0, draws = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next())
			{
				userName = rs.getString("email");
				wins = rs.getInt("wins");
				losses = rs.getInt("losses");
				draws = rs.getInt("draws");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		if (userName == null)
		{
			userName = user;
			wins = 0;
			losses = 0;
			draws = 0;
		}
		
		return new Player(userName, wins, losses, draws);
	}
	
	public static void saveUser(Connection con, Player player) throws SQLException
	{
		if (player == null || con == null)
		{
			return;
		}
		
	
		String userName = player.getEmailAddress();
		int wins = player.getTotalWon();
		int losses = player.getTotalLost();
		int draws = player.getTotalDraws();
		
		Statement stmt = null;
		String queryUpdate = "UPDATE player SET " +
						"wins = '" + wins + "', " +
						"losses = '" + losses + "', " +
						"draws = '" + draws + "' " +
						"WHERE email = '" + userName + "'";
		
		String queryInsert = "INSERT INTO player (email, wins, losses, draws) " +
						"VALUES ('" + userName + "', " +
						"'" + wins + "', " +
						"'" + losses + "', " +
						"'" + draws + "')";
		
		
		try {
			stmt = con.createStatement();
			int rowCount = stmt.executeUpdate(queryUpdate);
			
			if (rowCount == 0)
			{
				stmt.executeUpdate(queryInsert);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/world";
			String username = "root";
			String password = "ZCny74Gcr1-2";
			conn = DriverManager.getConnection(dbURL, username, password);
			System.out.println("Connected to database");

			} catch (SQLException e) {
				for (Throwable t : e) {
					t.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return conn;
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
