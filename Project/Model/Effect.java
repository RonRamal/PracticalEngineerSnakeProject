package Model;

public class Effect extends Thread {
	
	private Apple a;//Apple
	private Player p;//Player who Ate The Apple
	private int type;//Apple Type
	private boolean done=false;//For the thread
	public Effect(Apple a,Player p)
	{   
		this.p=p;
		this.a=a;
		this.type=a.getType();
		start();
	}
	public int GetAppleType()
	{
	 return a.getType();
	}	
	public void start()
	{
		    Thread t=new Thread(){
			public void run(){
				
		while(done ==false)
		{
			long timeTosleep=5000;
			long start,end,slept;
			boolean interrupted=false;
			switch(type)//Every Type Has Different Effect
			{
			case 3:
				timeTosleep=6000;
				break;
			case 5:
				timeTosleep=10000;
				break;
			case 6:
				timeTosleep=4000;
				break;
			case 8:
				timeTosleep=3000;
				break;
				
			}
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
			switch (type)//Returns Things to Normal Effects By Apple Type
			{
			case 3://Changes The Player Speed to Normal
				p.setSpeed(10);
				done=true;
				break;
			case 5://Changes The Player ControlFlag to Normal 
				p.setControlFlag(0);
				GameMusic.GameAudio.loop();
				done=true;
				break;
			case 6://Changes The Player ControlFlag to Normal 
				p.setControlFlag(0);
				p.setDirection(Direction.Right);
				done=true;
				break;
			case 8://Reveal's The Player
				p.reveal();
				done=true;
				break;
			}	
			
		}
	  }
    };
	         t.start();
  }   
}
