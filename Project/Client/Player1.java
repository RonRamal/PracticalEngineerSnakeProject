package Client;
import java.sql.SQLException;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.SlickException;

import Control.UserController;
import Model.GameMusic;


public class Player1 {

	float speed = 2f;//Player Movement Speed 
	float x =250;//Player x
	float y =260;//Player y
    int direction=5;//Player Direction At Game Start initialize it to 5
	int score=0;//Player Score
	float status=0;//Player Status
	static public String Name;
	
	public void Move() throws SlickException{	
		
	if(direction==2){//Move Down
		y += speed;
	}
	if(direction==1){//Move Up
		y -= speed;
	}
	if(direction==3){//Move Left
		x -= speed;
	}
	if(direction==4){//Move Right 
		x += speed;
	}
	if(direction==5){//Place Player in the Middle
		x=260;
		y=200;
	}
  }
	 public void checkCollision() {
	        
	        if (y >= 316)//Check if Snake Hits Down
	        {   
	        	 status=1;
	        	 BoardOnline.GameStatus=1;
	    	     direction=5;
	        }

	        if (y < 0)//Check if Snake hits Up
	        {  
	        	 status=1;
	        	 BoardOnline.GameStatus=1;
	    	     direction=5;
	        }
	        if (x >= 620) //Check if Snake hits Right
	        {  
	        	 status=1;
	        	 BoardOnline.GameStatus=1;
	    	     direction=5;
	        }

	        if (x < 0)//Check if snake hits left
	        {  
	        	 status=1;
	        	 BoardOnline.GameStatus=1;
	    	     direction=5;
	        }
	    }
	 
	 public void CheckScore()//Check IF Player Reaches High Score
	 {
		 if(score>=8)
		 {
			 BoardOnline.GameStatus=1;
			 status=2;
			 
		 }else if(score<=-5)
		 {
			 BoardOnline.GameStatus=1;
			 status=3;
		 }
	 }
	 public void checkCollisionWithApple() {	 
	        if (((x >= BoardOnline.Apple.x-10) && (x <= BoardOnline.Apple.x+10))&&
	        		((y >=BoardOnline.Apple.y-10) && (y <=BoardOnline.Apple.y+10)) )//Check if Player Hits Apple
	        {   
	    	     score++;
	    	     BoardOnline.AppleIsEaten=true;//Change Boolean To True, So the Server Sends Another x,y
	    	     GameMusic.Eating.play();//Play Eating Sound
	        }
	    }
	 public void checkCollisionWithEnemy() {
		 
	        if (((x >=BoardOnline.FirstEnemyX-10) && (x<= BoardOnline.FirstEnemyX+10))&&(y >=20) && (y <= 40))//Check if Player Hits First Enemy		
	        {   
	    	     score--;
	    	     direction=5;   	     
	        }
	        if (((x >=390) && (x<= 410))&&(y >=70) && (y <= 90))//Check if Player Hits Second Enemy			
	        {   
	    	     score--;
	    	     direction=5;   	     
	        }
	        if (((x >=BoardOnline.SecondEnemyX-10) && (x<= BoardOnline.SecondEnemyX+10))&&(y >=255) && (y <= 275))//Check if Player Hits Third Enemy		
	        {   
	    	     score--;
	    	     direction=5;   	     
	        }
	        if (((x >=240) && (x<= 260))&&(y >=140) && (y <= 160))//Check if Player Hits Fourth Enemy		
	        {   
	    	     score--;
	    	     direction=5;   	     
	        }
	    }
	 
	public void updateDirection() throws SlickException{
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			if(direction!=1)//Check If Player Not Going Up
			{
			direction=2;//Change To Down Direction 
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			if(direction!=2)//Check If Player Not Going Down
			{
			direction=1;//Change To Up Direction 
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			if(direction!=4)//Check If Player Not Going Right
			{
			direction=3;//Change To Left Direction 
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			if(direction!=3)//Check If Player Not Going Left
			{
			direction=4;//Change To Right Direction 
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			try {
				UserController.Exited(Name);//Change Status to Offline
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			 }
			System.exit(0);//Exit Application
		}
	}	
}
