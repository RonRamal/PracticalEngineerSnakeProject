package Client;


import java.sql.SQLException;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Control.UserController;

public class BoardOnline extends BasicGame {

	public BoardOnline(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	static Player1 player;//Player one
	static Player2 player2;//Player Two
	static AppleOnline Apple;//GameApple
	static Network network;
    static Image background;//BackGround Picture
    static Image AppleImage;//GameApple Image
    static Image Enemy;
    static int GameStatus;//Boolean for GameStatus
    static Animation PlayerAnimation1Right;//Player 1 Right Animation
    static Animation PlayerAnimation1Left;//Player 1 Left Animation
    static Animation PlayerAnimation1Up;//Player 1 Up Animation
    static Animation PlayerAnimation1Down;//Player 1 Down Animation
    static Animation PlayerAnimation2Right;//Player 2 Right Animation
    static Animation PlayerAnimation2Left;//Player 2 Left Animation
    static Animation PlayerAnimation2Up;//Player 2 Up Animation
    static Animation PlayerAnimation2Down;//Player 2 Down Animation
    static Animation FirstEnemyAnimationRight;//First Enemy Animation
    static Animation SecondEnemyAnimationLeft;//Second Enemy Animation
    static boolean EnemiesReady;//Boolean so enemies move once two players connect
    static boolean wall,win,lose;//Boolean For Player Status
    static boolean AppleIsEaten;//Boolean if Apple has been eaten
    static float FirstEnemyX;//First Enemy X
    static float SecondEnemyX;//Second Enemy X
    static boolean Once;//boolean for database
    static int[] duration = {150,150,150};//Frame Number For Each Picture in Sprite

	
	public static void main(String[] args) throws Exception {
		try{
			AppGameContainer app;
			app=new AppGameContainer(new BoardOnline("SnakeMultiPlayer"));
			app.setDisplayMode(620, 316, false);
			app.setTargetFrameRate(60);
			app.setShowFPS(false);
			app.setVSync(true);
			app.setAlwaysRender(true);
			app.setSmoothDeltas(true);
			app.start();

		}catch(SlickException e1){
			e1.printStackTrace();
		}	
	}
	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		 background.draw();//Draw BackGround Image
		 AppleImage.draw(Apple.x, Apple.y);//Draw GameApple Image
		 FirstEnemyAnimationRight.draw(FirstEnemyX, 30);//Draw First Enemy Animation
		 SecondEnemyAnimationLeft.draw(SecondEnemyX, 265);//Draw Second Enemy Animation
		 Enemy.draw(400, 80);//Draw third enemy image
		 Enemy.draw(250, 150);//Draw fourth enemy image
		 
		 switch (player2.direction)//For Drawing Player 2 Animation
	        {
	        case 3:
	        	PlayerAnimation2Left.draw(player2.x, player2.y);//Left
	        	break;
	        case 4:
	        	PlayerAnimation2Right.draw(player2.x, player2.y);//Right
	        	break;
	        case 1:
	        	PlayerAnimation2Up.draw(player2.x, player2.y);//Up
	        	break;
	        case 2:
	        	PlayerAnimation2Down.draw(player2.x, player2.y);//Down
	        	break;
	        case 5:
	        	PlayerAnimation2Down.draw(player2.x, player2.y);//StandStill
	        	break;
	        } 
		 
		 switch (player.direction)//For Drawing Player 1 Animation
	        {
	        case 3:
	   		    PlayerAnimation1Left.draw(player.x, player.y);//Left
	        	break;
	        case 4:
	   		    PlayerAnimation1Right.draw(player.x, player.y);//Right
	        	break;
	        case 1:
	   		    PlayerAnimation1Up.draw(player.x, player.y);//Up
	        	break;
	        case 2:
	   		    PlayerAnimation1Down.draw(player.x, player.y);//Down
	        	break;
	        case 5:
	   		    PlayerAnimation1Down.draw(player.x, player.y);//StandStill
	        	break;
	        } 
		 
		 g.drawString("You'r Score: "+player.score, 10, 10);//Show Player 1 Score
		 g.drawString("Opponent's Score: "+player2.score, 10, 25);//Show Player 2 Score
		 
		 if(lose==true)//IF player Losses
		 {
			 g.drawString("You Lose, GoodLuck Next Time", 160, 140); 
		     g.drawString("Press Escape to EXIT", 185, 160);


		 }else if(win==true){//IF player Wins
			 
		     g.drawString("Congratulations You Win!", 185, 140);
		     g.drawString("Press Escape to EXIT", 200, 160);

			 
		 }else if(wall==true){//IF player Hits The Wall
			 
			 g.drawString("You Hit the wall, GoodLuck Next Time", 160, 140); 
		     g.drawString("Press Escape to EXIT", 200, 160);

		 }
	}
	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		 background = new Image("Pics/BackGrounds/ControlBackground.png");
		 AppleImage = new Image("Pics/Apples/Apple1.png");
		 Enemy = new Image("Pics/Enemies/Enemy.png");
         GameStatus=0;
		 AppleIsEaten = false;  
         wall=false;
         win=false;
         lose=false;
		 player = new Player1();
		 player2 = new Player2();
		 Apple = new AppleOnline();
		 FirstEnemyX=400;//First Enemy starting place
		 SecondEnemyX=50;//second Enemy Starting place
		 EnemiesReady=false;
		 Once=false;
		 
		 Image[] PlayerWalkRight= {new Image("Pics/Snakes/Snake1/SnakeRIGHT1.png"), new Image("Pics/Snakes/Snake1/SnakeRIGHT2.png"), new Image("Pics/Snakes/Snake1/SnakeRIGHT3.png")};
		 Image[] PlayerWalk1Left= {new Image("Pics/Snakes/Snake1/SnakeLEFT1.png"), new Image("Pics/Snakes/Snake1/SnakeLEFT2.png"), new Image("Pics/Snakes/Snake1/SnakeLEFT3.png")};
		 Image[] PlayerWalkUp= {new Image("Pics/Snakes/Snake1/SnakeUP1.png"), new Image("Pics/Snakes/Snake1/SnakeUP2.png"), new Image("Pics/Snakes/Snake1/SnakeUP3.png")};
		 Image[] PlayerWalkDown= {new Image("Pics/Snakes/Snake1/SnakeDOWN1.png"), new Image("Pics/Snakes/Snake1/SnakeDOWN2.png"), new Image("Pics/Snakes/Snake1/SnakeDOWN3.png")};
		 
		 Image[] Player2WalkRight= {new Image("Pics/Snakes/Snake2/Snake2RIGHT1.png"), new Image("Pics/Snakes/Snake2/Snake2RIGHT2.png"), new Image("Pics/Snakes/Snake2/Snake2RIGHT3.png")};
		 Image[] Player2Walk1Left= {new Image("Pics/Snakes/Snake2/Snake2LEFT1.png"), new Image("Pics/Snakes/Snake2/Snake2LEFT2.png"), new Image("Pics/Snakes/Snake2/Snake2LEFT3.png")};
		 Image[] Player2WalkUp= {new Image("Pics/Snakes/Snake2/Snake2UP1.png"), new Image("Pics/Snakes/Snake2/Snake2UP2.png"), new Image("Pics/Snakes/Snake2/Snake2UP3.png")};
		 Image[] Player2WalkDown= {new Image("Pics/Snakes/Snake2/Snake2DOWN1.png"), new Image("Pics/Snakes/Snake2/Snake2DOWN2.png"), new Image("Pics/Snakes/Snake2/Snake2DOWN3.png")};
		
		 Image[] EnemyWalkRight= {new Image("Pics/Enemies/EnemyRIGHT1.png"), new Image("Pics/Enemies/EnemyRIGHT2.png"), new Image("Pics/Enemies/EnemyRIGHT3.png")};
		 Image[] EnemyWalkLeft= {new Image("Pics/Enemies/EnemyLEFT1.png"), new Image("Pics/Enemies/EnemyLEFT2.png"), new Image("Pics/Enemies/EnemyLEFT3.png")};
		
		 FirstEnemyAnimationRight = new Animation(EnemyWalkRight,duration,true);
		 SecondEnemyAnimationLeft = new Animation(EnemyWalkLeft,duration,true);
		 
		 PlayerAnimation1Right = new Animation(PlayerWalkRight,duration,true);
		 PlayerAnimation1Left = new Animation(PlayerWalk1Left,duration,true);
		 PlayerAnimation1Up = new Animation(PlayerWalkUp,duration,true);
		 PlayerAnimation1Down = new Animation(PlayerWalkDown,duration,true);
		 PlayerAnimation2Right = new Animation(Player2WalkRight,duration,true);
		 PlayerAnimation2Left = new Animation(Player2Walk1Left,duration,true);
		 PlayerAnimation2Up = new Animation(Player2WalkUp,duration,true);
		 PlayerAnimation2Down = new Animation(Player2WalkDown,duration,true);
		 
		 network = new Network();
		 network.connect();//Connect to Server
		 
		    //Update Player position At Start
			//Send the player's X & Y value
			PacketUpdateMovement packet = new PacketUpdateMovement();
			packet.x = player.x;
			packet.y = player.y;
			network.client.sendUDP(packet);
			
			//Send the player's Direction and status value
			PacketUpdatePlayerData packetData = new PacketUpdatePlayerData();
			packetData.direction = player.direction;
			packetData.playerStatus=player.status;
			network.client.sendUDP(packetData);
	}
	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		 player.updateDirection();//Update Player Direction
         player.Move();//Update Player Movement
         player.checkCollision();//Check If Player Hits Wall
         player.checkCollisionWithApple();//Check If Player Hits Apple
         player.checkCollisionWithEnemy();//Check If Player Hits Enemy
         player.CheckScore();//Check Player Score
		 GameOver();//Check Always Player Status

         if(EnemiesReady==true)//For Moving Enemies
         {
	         if(FirstEnemyX<620)
	         {    
	        	 FirstEnemyX+=2f; 
	        	 if(FirstEnemyX>=620){//IF Enemy Reaches Right Wall
	        		 FirstEnemyX=-4f;
	        	 }
	         }
	         if(SecondEnemyX<620)
	         {    
	        	 SecondEnemyX-=2f; 
	        	 if(SecondEnemyX<=0){//IF Enemy Reaches Left Wall
	        		 SecondEnemyX=618f;
	        	 }
	         }
         } 
		    //Update position
			//Send the player's X-Y value
			PacketUpdateMovement packet = new PacketUpdateMovement();
			packet.x = player.x;
			packet.y = player.y;
			network.client.sendUDP(packet);
			
			//Send the player's Direction and Status value
			PacketUpdatePlayerData packetPlayerData = new PacketUpdatePlayerData();
			packetPlayerData.direction = player.direction;
			packetPlayerData.playerStatus=player.status;
			network.client.sendUDP(packetPlayerData);
			
			//Send All Game Components Status value
			PacketStatus packetStatus = new PacketStatus();		
			packetStatus.AppleEaten =AppleIsEaten; 
			packetStatus.GameStatus=GameStatus;
			packetStatus.EnemiesReady=EnemiesReady;
			network.client.sendUDP(packetStatus);
			
			//Send the player's Score value
			PacketUpdateScore packetScore = new PacketUpdateScore();	
			packetScore.score = player.score;
			network.client.sendUDP(packetScore);
			
			
	  }
	@SuppressWarnings("static-access")
	public void GameOver()
	{
		if(GameStatus==1)//Check If Game Ends
		 {	 
			 if(player.status==1&&Once==false){//Check If Player Hits Wall
			 
				 try {
						UserController.UpdateWinLose(player.Name, "Losses");
						Once=true;//so we dont add another win to the player
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 wall=true;
			 }
			 else if(player.status==2&&Once==false){//Check If Player Wins 

				 try {
						UserController.UpdateWinLose(player.Name, "Wins");
						Once=true;//So we dont add another lose to the player
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 win=true;
			 } 
			 else if(player.status==3&&Once==false){//Check If Player Loses

				 try {
					   Once=true;
						UserController.UpdateWinLose(player.Name, "Losses");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
				 lose=true;
			 }
			 player.direction=5;//Change Player Place
	
		 }
	}
}




