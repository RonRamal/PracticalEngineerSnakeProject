package Model;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import java.util.Random;
/**
 * @author Ron
 * Class Player handles player snake in game
 */
public class Player {
	private int pNum; //indicate player number
	private String Name;//Player Name
	private String PassWord;//Player PassWord 
	private int wins,losses;//Wins and Losses (used this for Top Player Table)
    private int PScore=0; //player score
    private int ControlFlag=0;//Which Controls to Use
    private final static int ALL_DOTS = 300; //max Dots snake can have
	public  int x[] = new int[ALL_DOTS];//Snake vertical Location - first is head
    public  int y[] = new int[ALL_DOTS];//Snake Horizontal Location
    private int dots=4;//Number of Dots (Length) of snake At The Beginning
    private Image body;//Body of Snake 
    private Image head;//Head of Snake
    private int Speed = 10;//Snake Dot Size Movement 
    private Direction direction = Direction.Right;//Snake direction
    private int lastDots;//For hide act
    /**
     * constructor to initilaze  player snake
     */
    public Player() {};
	
	public Player(int pNum)
	{
		this.pNum = pNum;
		if(this.pNum==1)
		{
			head=new ImageIcon("src/Pics/Snakes/head11.png").getImage();//Player 1 Head
	    	body=new ImageIcon("src/Pics/Snakes/Body1.png").getImage();//Player 1 Body
		}
		else
		{
			head=new ImageIcon("src/Pics/Snakes/head1.png").getImage();//Player 2 Head
	    	body=new ImageIcon("src/Pics/Snakes/Body2.png").getImage();//Player 2 Body
		}	
    	Random r = new Random();//Random Number We Use For Placing the Snake
        int Snx= (r.nextInt(20)+1) * 10;   	
        int Sny=Snx ;           
        //loop for building snake 
        for (int z = 0; z < dots; z++) {
            x[z] = Snx - z * 10;
            y[z] = Sny;
        }	
    }
	
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	private void init()
	{
		int Snx =100;
		int Sny=50;
        //loop for building snake 
        for (int z = 0; z < dots; z++) {
            x[z] = Snx - z * 10;
            y[z] = Sny;
        }	
	}
	public Direction getDirection() {
		return direction;
	}
	public int getSpeed() {
		return Speed;
	}
	public void setSpeed(int Speed) {
		this.Speed = Speed;
	}
	public void setDirection(Direction direction) {;
		this.direction = direction;
	}
	public int[] getx() {
		return x;
	}
	public int[] getY() {
		return y;
	}
	public int getPNum()
	{
		return pNum;
    }
	public void setPNum(int pNum) 
	{
		this.pNum = pNum;
	}
	 public void setX(int[] x){
		this.x = x;
	}
	 public void setY(int[] y){
		this.y = y;
	}
	public int getDots() {
			return dots;
	}
	public void setDots(int dots) {
			this.dots = dots;
	}
	public void hide() {
		this.lastDots = this.dots;
		this.dots = 0;
	}
	public void reveal() {
		this.dots = this.lastDots;
	}
	public Image getBody() {
		return body;
	}
	public void setBody(Image body) {
		this.body = body;
	}
	public Image getHead() {
		return head;
	}
	public void setHead(Image head) {
		this.head = head;
	}
    public int getScore() {
		return PScore;
	}
	public void setPScore(int PScore) {
		this.PScore = PScore;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getControlFlag() {
		return this.ControlFlag;
	}
	public void setControlFlag(int ControlFlag) {
		this.ControlFlag = ControlFlag;
	}
	public Point getHeadPoint()
	{
		return new Point(x[0], y[0]);
	}
	/**
	 * method for changing snake location based on location
	 * @return snake
	 */
	 public Player move() {
	        for (int z = dots; z > 0; z--)//Move the head of snake and Shift the dots
	        {
	            x[z] = x[(z - 1)];
	            y[z] = y[(z - 1)];
	        }
	        switch (direction)//Move The Snake By Its Direction
	        {
	        case Left:
	        	x[0] -= Speed;
	        	if(this.pNum==1)//If Player 1 the load Images Of Snake 1 
	        	{
	        		head=new ImageIcon("src/Pics/Snakes/head14.png").getImage();
		        	body=new ImageIcon("src/Pics/Snakes/Body1.png").getImage();    	
	        	}
	        	else{
	        		head=new ImageIcon("src/Pics/Snakes/head4.png").getImage();
		        	body=new ImageIcon("src/Pics/Snakes/Body2.png").getImage();
	        	}
	        	break;
	        case Right:
	        	x[0] += Speed;
	        	if(this.pNum==1)//If Player 1 the load Images Of Snake 1 
	        	{
	        		head=new ImageIcon("src/Pics/Snakes/head11.png").getImage();
		        	body=new ImageIcon("src/Pics/Snakes/Body1.png").getImage();
	        	}
	        	else{
	        		head=new ImageIcon("src/Pics/Snakes/head1.png").getImage();
		        	body=new ImageIcon("src/Pics/Snakes/Body2.png").getImage();
	        	}
	        	break;
	        case Up:
	        	y[0] -= Speed;
	        	if(this.pNum==1)//If Player 1 the load Images Of Snake 1 
	        	{
	        		head=new ImageIcon("src/Pics/Snakes/head13.png").getImage();
		         	body=new ImageIcon("src/Pics/Snakes/body1.png").getImage();	
	        	}
	        	else{
	        		head=new ImageIcon("src/Pics/Snakes/head3.png").getImage();
	            	body=new ImageIcon("src/Pics/Snakes/body2.png").getImage();
	        	}
	        	break;
	        case Down:
	        	y[0] += Speed;
	        	if(this.pNum==1)//If Player 1 the load Images Of Snake 1 
	        	{
	        		head=new ImageIcon("src/Pics/Snakes/head12.png").getImage();
		         	body=new ImageIcon("src/Pics/Snakes/body1.png").getImage();	
	        	}
	        	else{
	        		head=new ImageIcon("src/Pics/Snakes/head2.png").getImage();
	            	body=new ImageIcon("src/Pics/Snakes/body2.png").getImage();
	        	}
	        	break;
	        case NoWhere:
	            init();
	            x[0]=100;
	            y[0]=50;
	            if(this.pNum==1)//If Player 1 the load Images Of Snake 1 
	        	{
	        		head=new ImageIcon("src/Pics/Snakes/head11.png").getImage();
		         	body=new ImageIcon("src/Pics/Snakes/body1.png").getImage();	
	        	}
	        	else{
	        		head=new ImageIcon("src/Pics/Snakes/head1.png").getImage();
	            	body=new ImageIcon("src/Pics/Snakes/body2.png").getImage();
	        	}
	            break;
	        }   
	        return this;
	    }
}
