package Client;
import java.io.IOException;
import java.sql.SQLException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import Control.UserController;


public class Network extends Listener {

	Client client;
	String ip = "localhost";
	int port = 27960;
	
	public void connect(){
		client = new Client();
		client.getKryo().register(PacketUpdateMovement.class);
		client.getKryo().register(PacketUpdatePlayerData.class);
		client.getKryo().register(PacketUpdateAppleX.class);
		client.getKryo().register(PacketUpdateAppleY.class);
		client.getKryo().register(PacketUpdateScore.class);
		client.getKryo().register(PacketStatus.class);
		client.getKryo().register(boolean.class);
		client.addListener(this);
		client.start();
		try {
			client.connect(5000, ip, port, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void received(Connection c, Object o){
		
		 if(o instanceof PacketUpdateMovement){
			PacketUpdateMovement packet = (PacketUpdateMovement) o;
			BoardOnline.player2.x = packet.x;
			BoardOnline.player2.y = packet.y;
			
		}else if(o instanceof PacketUpdatePlayerData){
			PacketUpdatePlayerData packet = (PacketUpdatePlayerData) o;
		    BoardOnline.player2.direction = packet.direction;
		    if(packet.playerStatus==1)//IF Player 1 Loses Then The Second Wins
	        {
		    	BoardOnline.win=true;
	        	client.close();
		    	try {
					UserController.UpdateWinLose(BoardOnline.player.Name, "Wins");//Add 1 Win to the Player
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        	
	        }else if(packet.playerStatus==2)//IF Player 1 Wins Then The Second Losses
	        {
	        	BoardOnline.lose=true;
	        	client.close();

	        	try {
					UserController.UpdateWinLose(BoardOnline.player.Name, "Losses");//Add 1 Loss to the Player
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        	
	        }else if(packet.playerStatus==3)//IF Player 1 Losses Then The Second Wins
	        {
	        	BoardOnline.win=true;
	        	client.close();
	        	try {
					UserController.UpdateWinLose(BoardOnline.player.Name, "Wins");//Add 1 Win to the Player
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }
		    
		}else if(o instanceof PacketUpdateAppleX){
			PacketUpdateAppleX packet = (PacketUpdateAppleX) o;
		    BoardOnline.Apple.x = packet.x;
		    
		}else if(o instanceof PacketUpdateAppleY){
			PacketUpdateAppleY packet = (PacketUpdateAppleY) o;
		    BoardOnline.Apple.y = packet.y;
		    
	    }else if(o instanceof PacketUpdateScore){
	    	PacketUpdateScore packet = (PacketUpdateScore) o;
	        BoardOnline.player2.score = packet.score;
	        
	    }else if(o instanceof PacketStatus){
	    	PacketStatus packet = (PacketStatus) o;
	    	BoardOnline.EnemiesReady=packet.EnemiesReady;
	        BoardOnline.AppleIsEaten = packet.AppleEaten;
	        BoardOnline.GameStatus=packet.GameStatus;
	        
	     }
	}
}
