package Server;

import java.io.IOException;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import Model.GameMusic;



public class ServerProgram extends Listener {

	static Server server;
	int count = 0;
	static final int port = 27960;
	
	public static void main(String[] args) throws IOException{
		server = new Server();
		server.getKryo().register(PacketUpdateMovement.class);
		server.getKryo().register(PacketUpdatePlayerData.class);
		server.getKryo().register(PacketUpdateAppleX.class);
		server.getKryo().register(PacketUpdateAppleY.class);
		server.getKryo().register(PacketUpdateScore.class);
		server.getKryo().register(PacketStatus.class);
		server.getKryo().register(boolean.class);

		server.bind(port, port);
		server.start();
		server.addListener(new ServerProgram());
		System.out.println("The server is ready");
	}
	
	public void connected(Connection c){
		System.out.println("Connection received.");
		count++;//If a Player Connects then Add to the Count
		if(count >= 2){//Do This only if Two Players Enter	
		 
			PacketStatus packetStatus = new PacketStatus();
			packetStatus.EnemiesReady=true;
			
			server.sendToAllUDP(packetStatus);

			
	        GameMusic.GameAudio.loop();//Activate Game Music
			PacketUpdateAppleX packetX = new PacketUpdateAppleX();//New Apple x 
			PacketUpdateAppleY packetY = new PacketUpdateAppleY();//New Apple y
          
			int r = (int) (Math.random() * 52)+1;//Choose Random Number For x
	        packetX.x = ((r * 10));//Set Vertical Number of apple

			server.sendToAllUDP(packetX);//Send New Apple'x To Two Players
	        System.out.println("sent an update x Apple packet");
	        
	        r = (int) (Math.random() * 26)+1;//Choose Random Number For y
	        packetY.y = ((r * 10));//Set Horizontal Number of apple
	        
	        server.sendToAllUDP(packetY);//Send New Apple'y To Two Players
	        System.out.println("sent an update y Apple packet");
          
		 }
		}
	
	public void received(Connection c, Object o){
		if(count>=2)
		{
		if(o instanceof PacketUpdateMovement){
			PacketUpdateMovement packet = (PacketUpdateMovement) o;
			server.sendToAllExceptUDP(c.getID(), packet);
			System.out.println("received and sent an update X - Y packet");
			
		}else if(o instanceof PacketUpdatePlayerData){
	    	PacketUpdatePlayerData packet = (PacketUpdatePlayerData) o;
		     server.sendToAllExceptUDP(c.getID(), packet);
		     System.out.println("received and sent an update Data packet");
	   		     
	    }else if(o instanceof PacketUpdateScore){
	    	PacketUpdateScore packet = (PacketUpdateScore) o;
	        server.sendToAllExceptUDP(c.getID(), packet);
		    System.out.println("received and sent an update Score packet");
		     
	     } else if(o instanceof PacketStatus){
	    	PacketStatus packet = (PacketStatus) o;
	    	
	    	if(packet.AppleEaten==true)
	    	{
				PacketUpdateAppleX packetX = new PacketUpdateAppleX();
				PacketUpdateAppleY packetY = new PacketUpdateAppleY();
	    		int r = (int) (Math.random() * 60)+1;
		        packetX.x = ((r * 10));//Set Vertical Number of apple
		        r = (int) (Math.random() * 30)+1;
		        packetY.y = ((r * 10));//Set Horizontal Number of apple
				server.sendToAllUDP(packetX);
				server.sendToAllUDP(packetY);
	    	}
	    	
	    	 packet.AppleEaten=false;
	    	 server.sendToAllUDP(packet);
		     System.out.println("received and sent an update AppleStatus packet");
	    }
		}
		
	}
	
	public void disconnected(Connection c){
		
        GameMusic.GameAudio.stop();
		System.out.println("Connection dropped.");
	}
}
