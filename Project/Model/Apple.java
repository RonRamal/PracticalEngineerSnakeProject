package Model;

import java.awt.Image;
import javax.swing.ImageIcon;
public class Apple extends Thread{
	
	private final int DOT_SIZE =10; //Apple and Snake Dot Size
    private final int RAND_POSX = 53;//Number we use to calculate the apple Location
    private final int RAND_POSY = 24;//Number we use to calculate the apple Location
	public int apple_x;//Apple vertical Location
	public int apple_y;//Apple Horizontal Location
	private Image apple;//Image of the appleD
	private int type;//Apple Type
	public Apple() {
		locateNewApple();
		start(); 
	}
	public void locateNewApple() {
		type = (int) (Math.random() * 8+1);//set Image random
		ImageIcon iia;
		switch (type)//Load Image By Type
		{
		case 1:
			iia = new ImageIcon("src/Pics/Apples/apple1.png");//Normal Apple
	        apple = iia.getImage();
	        break;
		case 2:
			iia = new ImageIcon("src/Pics/Apples/apple2.png");
	        apple = iia.getImage();
	        break;
		case 3:
			iia = new ImageIcon("src/Pics/Apples/Apple3.png");
	        apple = iia.getImage();
	        break;
		case 4:
			iia = new ImageIcon("src/Pics/Apples/Apple4.png");
	        apple = iia.getImage();
	        break;
		case 5:
			iia = new ImageIcon("src/Pics/Apples/Apple5.png");
	        apple = iia.getImage();
	        break;
		case 6:
			iia = new ImageIcon("src/Pics/Apples/Apple6.png");
	        apple = iia.getImage();
	        break;
		case 7:
			iia = new ImageIcon("src/Pics/Apples/Apple7.png");//Gold Apple
	        apple = iia.getImage();
	        break;
		case 8:
			iia = new ImageIcon("src/Pics/Apples/Apple8.png");//Death Apple
	        apple = iia.getImage();
	        break;
		}
        locateApple();
	}
	public int getApple_x() {
		return apple_x;
	}
	public void setApple_x(int apple_x) {
		this.apple_x = apple_x;
	}
	public int getApple_y() {
		return apple_y;
	}
	public void setApple_y(int apple_y) {
		this.apple_y = apple_y;
	}
	public Image getApple() {
		return apple;
	}
	public void setApple(Image apple) {
		this.apple = apple;
	}	
	public int getType()
	{
		return type;
	}
	public void locateApple()
	{
		int r = (int) (Math.random() * RAND_POSX)+1;//Choose Random Number For Apple x
        apple_x = ((r * DOT_SIZE));//Set Vertical Number of apple
        r = (int) (Math.random() * RAND_POSY)+1;//Choose Random Number For Apple y
        apple_y = ((r * DOT_SIZE));//Set Horizontal Number of apple
	}
	public void start()//Load New Apple Every 5 Seconds
	{
		    Thread t=new Thread(){
			public void run(){
		while(true)
		{
			long timeTosleep=6000;
			long start,end,slept;
			boolean interrupted=false;
			while(timeTosleep>0)
			{				
				start=System.currentTimeMillis();
				try
				{
					Thread.sleep(timeTosleep);
					break;
				}
				catch(InterruptedException e)
				{
					end=System.currentTimeMillis();
					slept=end-start;
					timeTosleep-=slept;
					interrupted=true;
				}
			}
			if(interrupted)
			{
				Thread.currentThread().interrupt();
			}
			locateNewApple();
		}
	  }
    };
	         t.start();
  }  
}
