package Control;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Player;

public class UserController {
	public static boolean login(String UserName,String PassWord) throws SQLException
	{
		DataBaseConnection.open();
		String query="SELECT * FROM players WHERE UserName= '"+UserName+"'";
		try{
			ResultSet rs = DataBaseConnection.GetResultSet(query);
			if(rs!=null)
			{
				rs.next();
				String pass=rs.getString("PassWord");
				if(PassWord.equals(pass))
				{
					DataBaseConnection.close();
					return true;
				}	
				else
				{
					DataBaseConnection.close();
					return false;
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.close();
		return false;
	}
	public static boolean isUserExist(String userName) throws SQLException
	{
		DataBaseConnection.open();
		String query="SELECT * FROM players WHERE UserName= '"+userName+"'";
		try{
			ResultSet rs = DataBaseConnection.GetResultSet(query);
			if(rs.next())
			{
				DataBaseConnection.close();
				return true;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.close();
		return false;
	}
	
	public static void UpdateWinLose(String userName,String col) throws SQLException
	{
		DataBaseConnection.open();
		String update="UPDATE players SET "+col+" = "+ col + " + 1 WHERE UserName= '"+userName+"'";
		try{
			DataBaseConnection.stmt.execute(update);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.stmt.close();
		DataBaseConnection.close();
	}
	public static void Entered(String userName) throws SQLException
	{
		DataBaseConnection.open();
		String update="UPDATE players SET Available=1 WHERE UserName= '"+userName+"'";
		try{
			DataBaseConnection.stmt.execute(update);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.stmt.close();
		DataBaseConnection.close();
	}
	public static int CheckIFOnline(String userName) throws SQLException
	{
		DataBaseConnection.open();
		int Online=0;
		String query="SELECT * FROM players WHERE UserName= '"+userName+"'";
		ResultSet rs = DataBaseConnection.GetResultSet(query);
		if(rs.next())
		{
			Online=rs.getInt("Available");
			DataBaseConnection.stmt.close();
			DataBaseConnection.close();
		}
		return Online;
	}
	
	public static void Exited(String userName) throws SQLException//Checks if player name exists in DataBase
	{
		DataBaseConnection.open();
		String update="UPDATE players SET Available=0 WHERE UserName= '"+userName+"'";
		try{
			DataBaseConnection.stmt.execute(update);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.stmt.close();
		DataBaseConnection.close();
	}
	
	public static void register(String userName, String password) throws SQLException//Registers player
	{
		DataBaseConnection.open();
		String query="INSERT INTO players VALUES ('"+userName+"','"+password + "', 0,0,0)";
		try{
			DataBaseConnection.stmt.execute(query);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		DataBaseConnection.stmt.close();
		DataBaseConnection.close();
	}
	
	public static ArrayList<Player> ScoreBoard() throws SQLException//Returns 3 players with most wins
	{
		ArrayList<Player> playerarray= new ArrayList<Player>();
		DataBaseConnection.open();
		String query="SELECT * FROM players ORDER BY Wins DESC limit 3";
		try{
			ResultSet rs = DataBaseConnection.GetResultSet(query);
			while(rs.next())
			{
				Player player =new Player();
				player.setName(rs.getString("UserName"));
				player.setWins(rs.getInt("Wins"));
				player.setLosses(rs.getInt("Losses"));
				playerarray.add(player);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		DataBaseConnection.close();
		return playerarray;
	}
	public static ArrayList<Player> WorstScoreBoard() throws SQLException//Returns 3 players with most losses
	{
		ArrayList<Player> playerarray= new ArrayList<Player>();
		DataBaseConnection.open();
		String query="SELECT * FROM players ORDER BY Losses DESC limit 3";
		try{
			ResultSet rs = DataBaseConnection.GetResultSet(query);
			while(rs.next())
			{
				Player player =new Player();
				player.setName(rs.getString("UserName"));
				player.setWins(rs.getInt("Wins"));
				player.setLosses(rs.getInt("Losses"));
				playerarray.add(player);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		DataBaseConnection.close();
		return playerarray;
	}
}
