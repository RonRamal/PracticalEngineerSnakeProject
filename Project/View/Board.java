package View;
import Model.*;
import Model.GameMusic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private int B_WIDTH = 620; //board width
    private int B_HEIGHT = 316; //board height
    private int DELAY = 140;// Number we use to determine the speed of the Game
    private int Appletype;//For Checking which Effect To use
    private boolean inGame = true;//Status for the Game 
    private int winner = 0;//Flag To determine Winner
    private Timer timer;//Timer For The Game
    private Image bg;//BackGround of the game
    private Apple a = new Apple();//First Apple
    private Apple b = new Apple();//Second Apple
    private Player p1=new Player(1);//Player One
    private Player p2=new Player(2);//Player Two 
    private boolean Done=false;//Boolean for Game Ending
    private int Pause=0;//for Pause Button
    /**
     * constructor to initialize game board
     */
    public Board() {	
    	
        addKeyListener(new TAdapter());
        setFocusable(true); 
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		timer = new Timer(DELAY, this);
		timer.start();
		GameMusic.GameAudio.loop();//Start GameMuisc 
    }
    //Painting the Game Component 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g);//paint the background
        drawApples(g);//Paint the Apples
        drawPlayer(g, p1);//Paint Snake 1
        drawPlayer(g, p2);//Paint Snake 2
        drawScore(g);//Show two players Score
    }
    private void drawScore(Graphics b) {
    	 String playerscore1="P1 Score: "+p1.getScore() ;	
    	 String playerscore2="P2 Score: "+p2.getScore() ;	
         Font small = new Font("Helvetica", Font.BOLD, 16);//First Font
         FontMetrics metr = getFontMetrics(small);//Size of Font
         b.setColor(Color.white);//Color of Text
         b.setFont(small);//Setting the Font
         b.drawString(playerscore1, (B_WIDTH - metr.stringWidth(playerscore1)-525) / 2, B_HEIGHT / 2-142);//Show the text
         b.drawString(playerscore2, (B_WIDTH - metr.stringWidth(playerscore1)-525) / 2, B_HEIGHT / 2-125);//Show the text
        Toolkit.getDefaultToolkit().sync();
     }
    private void drawBackground(Graphics b) {
       bg = new ImageIcon("src/Pics/BackGrounds/ControlBackground.png").getImage();//loading BackGround Image	
       b.drawImage(bg, 0, 0, null);//Drawing the backGround Image
       Toolkit.getDefaultToolkit().sync();
    }
    private void drawApples(Graphics g) {
    	if(inGame)
    	{
    	g.drawImage(a.getApple(), a.getApple_x(), a.getApple_y(), this);//Drawing the First apple 
        g.drawImage(b.getApple(), b.getApple_x(), b.getApple_y(), this);//Drawing the Second apple 
        Toolkit.getDefaultToolkit().sync();
    	}
     }
    // We draw the images for the snake first,We draw the head then the main body
    private void drawPlayer(Graphics g, Player p) {	
        if (inGame) {
        	int x[]= p.getx();
        	int y[]=p.getY();
            for (int z = 0; z < p.getDots(); z++) {
                if (z == 0)//Check if Head
                {
                    g.drawImage(p.getHead(), x[z], y[z], null);//Draw the head of Snake 1 
                } 
                else 
                {
                    g.drawImage(p.getBody(), x[z], y[z], null);//Draw the else of the Snake's Body
                }
            }
            Toolkit.getDefaultToolkit().sync();
        } 
        else
        {   GameMusic.GameAudio.stop();//Stop The GameMusic In Case The Game Ends
            GameMusic.Apple.stop();//Stop The Music For the Weed Apple In Case The Game Ends
            gameOver(g,winner);//Activate GameOver And Show The Winner
        }        
    }
  
    //Show GameOver Text And Which Player Wins 
    private void gameOver(Graphics g,int flag) {  	
    	String msg = "Game Over";
        String msg2 = "Player One WINS!!";
        String msg3 = "Player Two WINS!!";
        String msg4  ="Wins by highest Score";
        String playerscore="The Winner's Score: " ;	
        Font small = new Font("Helvetica", Font.BOLD, 14);//First Font
        Font Large = new Font("Showcard Gothic", Font.BOLD, 42);//Second Font
        FontMetrics metr = getFontMetrics(small);//Size of Font
        g.setColor(Color.white);//Color of Text
        g.setFont(small);//Setting the Font
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);//Show the text
        if(flag==1)
        {
        	g.drawString(msg2, (B_WIDTH - 25 - metr.stringWidth(msg)) / 2, B_HEIGHT - 140);//"Show Which Player wins"
        	if(p1.getScore()>=8)//Check If Player 1 Wins
        	{ 
        		g.setFont(Large);
        		g.drawString(msg4, (B_WIDTH-450 - metr.stringWidth(msg)) / 2, B_HEIGHT - 240);//Show Text if player wins by high score
            	g.drawString(playerscore + p1.getScore(), (B_WIDTH -330  - metr.stringWidth(playerscore)) / 2, B_HEIGHT - 70);//Show the player score
        		this.setVisible(true);
        	}
        }
        else{
        	g.drawString(msg3, (B_WIDTH -25 - metr.stringWidth(msg)) / 2, B_HEIGHT -140);//"Show Which Player wins"
        	if(p2.getScore()>=8)//Check If Player 2 Wins
        	{   
        		g.setFont(Large);
        		g.drawString(msg4, (B_WIDTH-450 - metr.stringWidth(msg)) / 2, B_HEIGHT - 240);//Show Text if player wins by high score
            	g.drawString(playerscore + p2.getScore(), (B_WIDTH -330  - metr.stringWidth(playerscore)) / 2, B_HEIGHT -70);//Show the Player score

        		this.setVisible(true);
        	}
        }  
        CloseOnGameover();//Call The Function For Game Ending
 
    }
   
    public void CloseOnGameover()
    {
	        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
		    Thread thread=new Thread(){	
		    public void run(){
		while(Done==false)
		{
			long timeTosleep=4000;
			while(timeTosleep>0)
			{				
				try
				{
					Thread.sleep(timeTosleep);
					break;
				}
				catch(InterruptedException e)
				{
					e.printStackTrace();
				}
			}//After a Couple Of Seconds After The Game Ends Close The Window
			  Done=true;
		      topFrame.dispose();
		}
	  }
    };
	         thread.start();
  }  
    @SuppressWarnings("unused")
	private void effect(int snake,Apple a)
    {
    	Appletype=a.getType();//Apple type for knowing which effect to use
    	switch (Appletype)
		{
		case 1:
			if(snake==1)//If Player 1 Eats The Apple
			{
			  p1.setPScore(p1.getScore()+1);
			}
			else p2.setPScore(p2.getScore()+1);
	        break;
		case 2:
			if(snake==1)//If Player 1 Eats The Apple
			{
				p2.setDots(p2.getDots()+4);
			}
			else p1.setDots(p1.getDots()+4);;
	        break;
		case 3:
			if(snake==1)//If Player 1 Eats The Apple
			{
				p2.setSpeed(20);
				Effect effect = new Effect(a, p2);
			}
			else{
				p1.setSpeed(20);
				Effect effect = new Effect(a, p1);
			}  
	        break;
		case 4:
			if(snake==1)//If Player 1 Eats The Apple
			{
				if(p1.getDots()!=1)
				{
				  p1.setDots(p1.getDots()-1);
				}	
			}
			else{
				if(p2.getDots()!=1)
				{
				  p2.setDots(p2.getDots()-1);
				}
			}
	        break;
		case 5:
			GameMusic.GameAudio.stop();//Stop GameMusic
			GameMusic.Apple.play();//Play Music For The Weed Apple
	     	if(snake==1)//If Player 1 Eats The Apple
	     	{ 
	     	    p2.setControlFlag(1);//Change Controls For Player 2
				Effect effect = new Effect(a, p2);
	        }
	    	else{	    		
	    		p1.setControlFlag(1);//Change Controls For Player 1
				Effect effect = new Effect(a, p1);
	    	}
	        break;
		case 6:
			if(snake==1)//If Player 1 Eats The Apple
			{	
		        p2.setControlFlag(2);//Stop Player 2 
		        p2.setDirection(Direction.NoWhere);//Change Player 2 Place 
				Effect effect = new Effect(a, p2);
			}
			else{
				p1.setControlFlag(2);//Stop Player 1
				p1.setDirection(Direction.NoWhere);//Change Player 1 Place 
				Effect effect = new Effect(a, p1);
			}
	        break;
		case 7:
			if(snake==1)//If Player 1 Eats The Apple
			{
				p1.setPScore(p1.getScore()+3);
			}
			else{
				p2.setPScore(p2.getScore()+3);
			}
	        break;
		 case 8:
			 if(snake==1)//If Player 1 Eats The Apple
			 {
				  p2.hide();//Hide Player 2 
				  Effect effect2 = new Effect(a, p2); 
			 }
			 else{
				  p1.hide();//Hide Player 1
				  Effect effect1 = new Effect(a, p1);
			 }
	        break;
		}
    }
    //Check if the head of the Snake hits the apple The Locate Another One
    private void checkApple(Player p,Apple a)
    { 
    	int Snakex=p.getHeadPoint().x;//Snake Y
    	int Snakey=p.getHeadPoint().y;//Snake Y
    	int Applex=a.getApple_x();//Apple X
    	int Appley=a.getApple_y();//Apple Y
    	//Check if Head of the Snake Hits the apple 
        if (((Snakex >= Applex-10) && (Snakex <= Applex+10))&& ((Snakey >=Appley-10) && (Snakey <=Appley+10)) )
        {  
        	if (p.equals(p1))//check if Player 1
        	{
        		GameMusic.Eating.play();
        		effect(1,a);
        	}
        	else
        	{
        		GameMusic.Eating.play();
        		effect(2,a);
        	}
        		
        	if(p.getScore()>=8)//Check Score of the Player, If He Wins
        	{  
        	   GameMusic.GameOver.play();
        	   winner = (p.getPNum()==1)?1:2;
        	   inGame=false; 
        	}
            a.locateNewApple();//Locate Another Apple after Eating This Apple
        }
      }
    //Check if Snakes Collides with the border or itself
    private void checkCollision(Player p) {
        int[] x = p.getx();
        int[] y = p.getY();
           
    	//Check if Snake Hits itself 
        for (int z = p.getDots(); z > 0; z--)
        {
            if ((z > 3) && (p.getHeadPoint().x == x[z]) && (p.getHeadPoint().y == y[z])) {
                winner = (p.getPNum()==1)?2:1;//if Snake 1 Hits Itself Then Choose Player 2 As The Winner, The Same For Snake 2
                GameMusic.GameOver.play();//Play GameOver Music
                inGame = false;//Change GameStatus
            }
        }
        if (y[0] >= B_HEIGHT)//Check if Snake Hits bottom
        {   
            winner = (p.getPNum()==1)?2:1;//if Snake 1 Hits The wall Then Choose Player 1 As The Winner, The Same For Snake 2
    	 	GameMusic.GameOver.play();//Play GameOver Music
            inGame = false;//Change GameStatus
        }

        if (y[0] < 0)//Check if Snake hits UP
        {  
            winner = (p.getPNum()==1)?2:1;//if Snake 1 Hits The wall Then Choose Player 1 As The Winner, The Same For Snake 2
            GameMusic.GameOver.play();//Play GameOver Music
            inGame = false;//Change GameStatus
        }

        if (x[0] >= B_WIDTH) //Check if Snake hits Right
        {  
            winner = (p.getPNum()==1)?2:1;//if Snake 1 Hits The wall Then Choose Player 1 As The Winner, The Same For Snake 2
            GameMusic.GameOver.play();//Play GameOver Music
            inGame = false;//Change GameStatus
        }

        if (x[0] < 0)//Check if snake hits left
        {  
            winner = (p.getPNum()==1)?2:1;//if Snake 1 Hits The wall Then Choose Player 1 As The Winner, The Same For Snake 2
            GameMusic.GameOver.play();//Play GameOver Music
            inGame = false;//Change GameStatus
        }
    }
    //Action Performed 
    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
        	//Check Snake 1
            checkApple(p1,a);
            checkApple(p1,b);
            checkCollision(p1);
            p1.move();
            //Check Snake 2
            checkApple(p2,a);
            checkApple(p2,b);
            checkCollision(p2);
            p2.move();
        }
        repaint();
    }
    //Key bindings of The Two Snakes
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if(p1.getControlFlag()==0&&p2.getControlFlag()==0)  
            {
            	switch (key)
            	{
            	case KeyEvent.VK_LEFT://Left Arrow Snake 1 
            		if(p1.getDirection()==Direction.Right) break;
            		p1.setDirection(Direction.Left);
            		break;
            	case KeyEvent.VK_RIGHT://Right Arrow Snake 1
            		if(p1.getDirection()==Direction.Left) break;
            		p1.setDirection(Direction.Right);
            		break;
            	case KeyEvent.VK_UP://Up Arrow Snake 1
            		if(p1.getDirection()==Direction.Down) break;
            		p1.setDirection(Direction.Up);
            		break;
            	case KeyEvent.VK_DOWN://Down Arrow Snake 1
            		if(p1.getDirection()==Direction.Up) break;
            		p1.setDirection(Direction.Down);
            		break;
            	case KeyEvent.VK_A://Left Arrow Snake 2
            		if(p2.getDirection()==Direction.Right) break;
            		p2.setDirection(Direction.Left);
            		break;
            	case KeyEvent.VK_D://Right Arrow Snake 2
            		if(p2.getDirection()==Direction.Left) break;
            		p2.setDirection(Direction.Right);
            		break;
            	case KeyEvent.VK_W://up Arrow Snake 2
            		if(p2.getDirection()==Direction.Down) break;
            		p2.setDirection(Direction.Up);
            		break;
            	case KeyEvent.VK_S://Down Arrow Snake 2
            		if(p2.getDirection()==Direction.Up) break;
            		p2.setDirection(Direction.Down);
            		break;
            	case KeyEvent.VK_P://Pause Game
            		if(Pause==0)
            		{
            		  GameMusic.GameAudio.stop();	
            		  timer.stop();
            		  Pause=1;
            		  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
            		}
            		else{
            		  GameMusic.GameAudio.loop();
            		  timer.start();
            		  Pause=0;
            		}
            		break;
            	}
            }
            else if(p1.getControlFlag()==0&&p2.getControlFlag()==1)
            {
            	switch (key)
            	{ 
            	case KeyEvent.VK_LEFT://Left Arrow Snake 1 
            		if(p1.getDirection()==Direction.Right) break;
        		 	p1.setDirection(Direction.Left);
        		 	break;
            	case KeyEvent.VK_RIGHT://Right Arrow Snake 1 
            		if(p1.getDirection()==Direction.Left) break;
            		p1.setDirection(Direction.Right);
            		break;
            	case KeyEvent.VK_UP://Up Arrow Snake 1 
            		if(p1.getDirection()==Direction.Down) break;
            		p1.setDirection(Direction.Up);
            		break;
            	case KeyEvent.VK_DOWN://Down Arrow Snake 1 
            		if(p1.getDirection()==Direction.Up) break;
            		p1.setDirection(Direction.Down);
            		break;
            	case KeyEvent.VK_A://Left Arrow Snake 2 ->Goes Right
            		if(p2.getDirection()==Direction.Left) break;
            		p2.setDirection(Direction.Right);
            		break;
            	case KeyEvent.VK_D://Right Arrow Snake 2 ->Goes Left
            		if(p2.getDirection()==Direction.Right) break;
            		p2.setDirection(Direction.Left);
            		break;
            	case KeyEvent.VK_W://Up Arrow Snake 2 ->Goes Down
            		if(p2.getDirection()==Direction.Up) break;
            		p2.setDirection(Direction.Down);
            		break;
            	case KeyEvent.VK_S://Down Arrow Snake 2 ->Goes Up
            		if(p2.getDirection()==Direction.Down) break;
            		p2.setDirection(Direction.Up);
            		break;
            	case KeyEvent.VK_P://Pause Game
            		if(Pause==0)
            		{
            		  GameMusic.GameAudio.stop();	
            		  timer.stop();
            		  Pause=1;
            		  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
            		}
            		else{
            		  GameMusic.GameAudio.loop();
            		  timer.start();
            		  Pause=0;
            		}
            		break;
            	}
            }
            else if(p1.getControlFlag()==1&&p2.getControlFlag()==0)
            {
            	switch (key)
       	 		{ 
       	 		case KeyEvent.VK_LEFT://Left Arrow Snake 1 ->Goes Right
       	 		    if(p1.getDirection()==Direction.Left) break;
       	 			p1.setDirection(Direction.Right);
       	 			break;
       	 		case KeyEvent.VK_RIGHT://Right Arrow Snake 1 ->Goes Left
       	 	    	if(p1.getDirection()==Direction.Right) break;
       	 			p1.setDirection(Direction.Left);
       	 			break;
       	 		case KeyEvent.VK_UP://Up Arrow Snake 1 ->Goes Down
       	 		    if(p1.getDirection()==Direction.Up) break;
       	 			p1.setDirection(Direction.Down);
       	 			break;
       	 		case KeyEvent.VK_DOWN://Down Arrow Snake 1 ->Goes Up
       	 	    	if(p1.getDirection()==Direction.Down) break;
       	 			p1.setDirection(Direction.Up);
       	 			break;
       	 		case KeyEvent.VK_A://Left Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Right) break;
       	 			p2.setDirection(Direction.Left);
       	 			break;
       	 		case KeyEvent.VK_D://Right Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Left) break;
       	 			p2.setDirection(Direction.Right);
       	 			break;
       	 		case KeyEvent.VK_W://Up Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Down) break;
       	 			p2.setDirection(Direction.Up);
       	 			break;
       	 		case KeyEvent.VK_S://Down Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Up) break;
       	 			p2.setDirection(Direction.Down);
       	 			break;
	       	 	case KeyEvent.VK_P://Pause Game
	        		if(Pause==0)
	        		{
	        		  GameMusic.GameAudio.stop();	
	        		  timer.stop();
	        		  Pause=1;
	        		  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
	        		}
	        		else{
	        		  GameMusic.GameAudio.loop();
	        		  timer.start();
	        		  Pause=0;
	        		}
	        		break;
       	 		}
            }
            else if(p1.getControlFlag()==2&&p2.getControlFlag()==0)
            {
            	switch (key)
       	 		{
       	 		case KeyEvent.VK_LEFT://Left Arrow Snake 1 ->Stops
       	 			p1.setDirection(Direction.NoWhere);
       	 			break;
       	 		case KeyEvent.VK_RIGHT://Right Arrow Snake 1 ->Stops
       	 			p1.setDirection(Direction.NoWhere);
       	 			break;
       	 		case KeyEvent.VK_UP://Up Arrow Snake 1 ->Stops
       	 			p1.setDirection(Direction.NoWhere);
       	 			break;
       	 		case KeyEvent.VK_DOWN://Down Arrow Snake 1 ->Stops
       	 			p1.setDirection(Direction.NoWhere);
       	 			break;
       	 		case KeyEvent.VK_A://Left Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Right) break;
       	 			p2.setDirection(Direction.Left);
       	 			break;
       	 		case KeyEvent.VK_D://Right Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Left) break;
       	 			p2.setDirection(Direction.Right);
       	 			break;
       	 		case KeyEvent.VK_W://Up Arrow Snake 2
       	 		    if(p2.getDirection()==Direction.Down) break;
       	 			p2.setDirection(Direction.Up);
       	 			break;
       	 		case KeyEvent.VK_S://Down Arrow Snake 2
       	 	    	if(p2.getDirection()==Direction.Up) break;
       	 			p2.setDirection(Direction.Down);
       	 			break;
       	    	case KeyEvent.VK_P://Pause Game
			   	 	if(Pause==0)
					{
					  GameMusic.GameAudio.stop();	
					  timer.stop();
					  Pause=1;
					  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
					}
					else{
					  GameMusic.GameAudio.loop();
					  timer.start();
					  Pause=0;
					}
					break;
			   	 		}
                    }
            else{
            	if(p1.getControlFlag()==1&&p2.getControlFlag()==1)
            	{
            	switch (key)
           	 	{
           	 	case KeyEvent.VK_LEFT://Left Arrow Snake 1 ->Goes Right
           	 	    if(p1.getDirection()==Direction.Right) break;
           	 		p1.setDirection(Direction.Right);
           	 		break;
           	 	case KeyEvent.VK_RIGHT://Right Arrow Snake 1 ->Goes Left
           	     	if(p1.getDirection()==Direction.Left) break;
           	 		p1.setDirection(Direction.Left);
           	 		break;
           	 	case KeyEvent.VK_UP://Up Arrow Snake 1 ->Goes Down
           	    	if(p1.getDirection()==Direction.Down) break;
           	 		p1.setDirection(Direction.Down);
           	 		break;
           	 	case KeyEvent.VK_DOWN://Down Arrow Snake 1 ->Goes Up
           	 	    if(p1.getDirection()==Direction.Up) break;
           	 		p1.setDirection(Direction.Up);
           	 		break;
            	case KeyEvent.VK_A://Left Arrow Snake 2 ->Goes Right
         		    if(p2.getDirection()==Direction.Left) break;
         		    p2.setDirection(Direction.Right);
         	    	break;
             	case KeyEvent.VK_D://Right Arrow Snake 2 ->Goes Left
         		    if(p2.getDirection()==Direction.Right) break;
         		    p2.setDirection(Direction.Left);
         	    	break;
         	    case KeyEvent.VK_W://Up Arrow Snake 2 ->Goes Down
         		    if(p2.getDirection()==Direction.Up) break;
         		    p2.setDirection(Direction.Down);
         		    break;
         	    case KeyEvent.VK_S://Down Arrow Snake 2 ->Goes Up
         		    if(p2.getDirection()==Direction.Down) break;
         		    p2.setDirection(Direction.Up);
         		    break;
         	    case KeyEvent.VK_P://Pause Game
         	    	if(Pause==0)
            		{
            		  GameMusic.GameAudio.stop();	
            		  timer.stop();
            		  Pause=1;
            		  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
            		}
            		else{
            		  GameMusic.GameAudio.loop();
            		  timer.start();
            		  Pause=0;
            		}
            		break;
           	 	}
              } 
           else{
            	if(p1.getControlFlag()==0&&p2.getControlFlag()==2)
            	{
            	switch (key)
           	 	{
           	 	case KeyEvent.VK_LEFT://Left Arrow Snake 1 
           	 	    if(p1.getDirection()==Direction.Right) break;
           	 		p1.setDirection(Direction.Left);
           	 		break;
           	 	case KeyEvent.VK_RIGHT://Right Arrow Snake 1 
           	     	if(p1.getDirection()==Direction.Left) break;
           	 		p1.setDirection(Direction.Right);
           	 		break;
           	 	case KeyEvent.VK_UP://Up Arrow Snake 1 
           	    	if(p1.getDirection()==Direction.Down) break;
           	 		p1.setDirection(Direction.Up);
           	 		break;
           	 	case KeyEvent.VK_DOWN://Down Arrow Snake 1 
           	 	    if(p1.getDirection()==Direction.Up) break;
           	 		p1.setDirection(Direction.Down);
           	 		break;
           	 	case KeyEvent.VK_A://Left Arrow Snake 2 ->Stops
           	 		p2.setDirection(Direction.NoWhere);
           	 		break;
           	 	case KeyEvent.VK_D://Right Arrow Snake 2 ->Stops
           	 		p2.setDirection(Direction.NoWhere);
           	 		break;
           	 	case KeyEvent.VK_W://Up Arrow Snake 2 ->Stops
           	 		p2.setDirection(Direction.NoWhere);
           	 		break;
           	 	case KeyEvent.VK_S://Down Arrow Snake 2 ->Stops
           	 		p2.setDirection(Direction.NoWhere);
           	 		break;
            	case KeyEvent.VK_P://Pause Game
            		if(Pause==0)
            		{
            		  GameMusic.GameAudio.stop();	
            		  timer.stop();
            		  Pause=1;
            		  JOptionPane.showMessageDialog(null, "Game Paused, Press p to continue","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
            		}
            		else{
            		  GameMusic.GameAudio.loop();
            		  timer.start();
            		  Pause=0;
            		}
            		break;
           	 	}
              }
        	} 
        }
    }
  }
}
