package View;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import org.newdawn.slick.AppGameContainer;

import Client.BoardOnline;
import Client.Player1;
import Control.UserController;
import Model.GameMusic;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
public class GameMenu {
	
	private JFrame frame;
	private JButton SoloButton = new JButton("");//Button to Play Solo
	private JButton MultiButton = new JButton("");//Button to Play MultiPlayer
	private JButton PlayButton = new JButton("");//Button to Play
	private JButton Rules = new JButton("");//Rules Button
	private JButton ControlsButton = new JButton("");//ControlButton
	private JButton QuitButton = new JButton("");//Quit Button
	private JButton BackButton = new JButton("");//Back Button
	private JButton Register = new JButton("");//Register Button
	private JButton OnlineRules = new JButton("");//Online Rules Button
    private JButton ScoreBoard = new JButton("");//ScoreBoard Button
	private JTextField Login = new JTextField();//LogIn TextField
	private JPasswordField PassWord = new JPasswordField();//PassWord TextField
	private JLabel UserName = new JLabel("");//UserName Field
	private JLabel Pass = new JLabel("");//PassWorld Label
	private JButton Mute = new JButton("");//Mute Button
	private JButton LoginBtn = new JButton("");//LogIn Button
	private JLabel SnakeLogo = new JLabel("");//BackGround Snake Image
	private JLabel MainMenuBackGround = new JLabel("");//BackGround Image
	private boolean check=true;//Boolean For Mute Button
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			  try {
					GameMenu window = new GameMenu();
					window.frame.setVisible(true);
					GameMusic.MenuMusic.loop();//Start MenuMusic				
				  } 
			  catch (Exception e)
			      {
					e.printStackTrace();
				  }
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GameMenu() {
		initialize();
		//At First These Buttons are Inviable
		Login.setVisible(false);
		PassWord.setVisible(false);
		UserName.setVisible(false);
		Pass.setVisible(false);
		LoginBtn.setVisible(false);
		Register.setVisible(false);
		SoloButton.setVisible(false);
		OnlineRules.setVisible(false);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 714, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		PlayButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				PlayButton.setBounds(300, 166, 117, 45);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				PlayButton.setBounds(300, 173, 117, 45);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		SoloButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				SoloButton.setBounds(300, 166, 117, 45);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				SoloButton.setBounds(300, 173, 117, 45);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		MultiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				MultiButton.setBounds(300, 221, 117, 45);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				MultiButton.setBounds(300, 229, 117, 45);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		LoginBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				LoginBtn.setBounds(300, 267, 117, 46);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				LoginBtn.setBounds(300, 275, 117, 46);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		Register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Register.setBounds(508, 107, 117, 46);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Register.setBounds(508, 115, 117, 46);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		ControlsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ControlsButton.setBounds(300, 277, 117, 45);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				ControlsButton.setBounds(300, 285, 117, 45);
			}
		});
		QuitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				QuitButton.setBounds(300, 333, 117, 45);
				BackButton.setVisible(false);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				QuitButton.setBounds(300, 341, 117, 45);
				BackButton.setVisible(true);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BackButton.setBounds(300, 333, 117, 45);		
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BackButton.setBounds(300, 341, 117, 45);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		Rules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Rules.setBounds(300, 221, 117, 45);
				MultiButton.setVisible(false);		
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Rules.setBounds(300, 229, 117, 45);
				MultiButton.setVisible(true);
			}
		});
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		ScoreBoard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ScoreBoard.setBounds(530, 9, 117, 45);
			}
			public void mouseExited(MouseEvent arg0) {
				ScoreBoard.setBounds(530, 17, 117, 45);
			}
		});
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		OnlineRules.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				OnlineRules.setBounds(34, 107, 117, 45);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				OnlineRules.setBounds(34, 115, 117, 45);
			}
		});
		
		//When Pressing the Button Show New Buttons
		MultiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoloButton.setVisible(false);
				MultiButton.setVisible(false);
				Login.setVisible(true);
				PassWord.setVisible(true);
				UserName.setVisible(true);
				Pass.setVisible(true);
				LoginBtn.setVisible(true);
				Register.setVisible(true);
				OnlineRules.setVisible(true);
				ScoreBoard.setVisible(false);
				
			}
		});
		
		//When Pressing the Button Turn OFF Music And Change Picture Press Again to turn ON
		Mute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(check==true){
					GameMusic.MenuMusic.stop();
					Mute.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Mute.png")));
					check=false;
					}
					else{
						check=true;
						Mute.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/UnMute.png")));
						GameMusic.MenuMusic.loop();
					}	
			}
		});	
		
		//When Pressing the Button Show New Buttons
		PlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlsButton.setVisible(false);
				QuitButton.setVisible(false);
				Rules.setVisible(false);
				SoloButton.setVisible(true);
				MultiButton.setVisible(true);
				PlayButton.setVisible(false);
				BackButton.setVisible(true);
				ScoreBoard.setVisible(false);
			}
		});
		
		//When Pressing the Button Open Offline Game
		SoloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SnakeGame g = new SnakeGame();
				GameMusic.MenuMusic.stop();
				g.setVisible(true);
			}
		});
		
		//When Pressing the Button Open Rules For Offline Game
		Rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rules r=new Rules (frame);
				frame.setVisible(false);
				r.setVisible(true);
			}
		});
		
		//When Pressing the Button Open Score board For Online Game
		ScoreBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScoreBoard s=new ScoreBoard(frame);
				frame.setVisible(false);
				s.setVisible(true);
			}
		});
		
		//When Pressing the Button Open Rules For Online Game
		OnlineRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RulesOnline r=new RulesOnline (frame);
				frame.setVisible(false);
				r.setVisible(true);
			}
		});
		
		//When Pressing the Button Controls Page 
		ControlsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Controls g = new Controls(frame);
				frame.setVisible(false);
				g.setVisible(true);
			}
		});
		
		//When Pressing the Button Quit The Game
		QuitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//When Pressing the Button Go Back To the Main
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlsButton.setVisible(true);
				QuitButton.setVisible(true);
				Rules.setVisible(true);
				SoloButton.setVisible(false);
				MultiButton.setVisible(false);
				PlayButton.setVisible(true);
				Login.setVisible(false);
				PassWord.setVisible(false);
				UserName.setVisible(false);
				Pass.setVisible(false);
				LoginBtn.setVisible(false);
				Register.setVisible(false);
				OnlineRules.setVisible(false);	
				ScoreBoard.setVisible(true);

			}
		});
		
		//When Pressing the Button Register New Player
		Register.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
					try{
					    	if(UserController.isUserExist(Login.getText())==false){
					    		UserController.register(Login.getText(), PassWord.getText());
								JOptionPane.showMessageDialog(null, "You Have Been Added","NOTIFY", JOptionPane.INFORMATION_MESSAGE);
					    	}
					    	else
					    	{
					    		JOptionPane.showMessageDialog(null, "User Already Exist","ERROR", JOptionPane.ERROR_MESSAGE);
					    	}
					}catch(Exception ex){
						ex.printStackTrace();
					}
			}
		});
		
		//When Pressing the Button LogIn and Open Online Game
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try{
				if (UserController.CheckIFOnline(Login.getText())==1)
				{
					JOptionPane.showMessageDialog(null, "User is Already Online","ERROR", JOptionPane.ERROR_MESSAGE);
				}else{
				
					
			   
				@SuppressWarnings("deprecation")
				boolean success= UserController.login(Login.getText(),PassWord.getText());
			    Player1.Name=Login.getText();
				UserController.Entered(Login.getText());
               
				if(success){
					
					frame.dispose();
					GameMusic.MenuMusic.stop();
                   
					AppGameContainer app;
					app=new AppGameContainer(new BoardOnline("Snake"));
					app.setDisplayMode(620, 316, false);
					app.setTargetFrameRate(60);
					app.setShowFPS(false);
					app.setVSync(true);
					app.setAlwaysRender(true);
					app.setSmoothDeltas(true);
					app.start();    
				}
				else{
					JOptionPane.showMessageDialog(null, "UserName OR password ERROR","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		   }
			
		});
	
		
		//Load Images for All Buttons and labels
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/Pics/BackGrounds/GameIcon.png"));
		ScoreBoard.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Scoreboard.png")));
		PlayButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Play.png")));
		ControlsButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Keys.png")));
		SoloButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Solo.png")));
		BackButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Back.png")));
		MultiButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Multi.png")));
		Rules.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Rules.png")));
		UserName.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/User.png")));
		Mute.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/UnMute.png")));
		LoginBtn.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/LoginBtn.png")));
		Register.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Register.png")));
		QuitButton.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Quit.png")));
		SnakeLogo.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/BackGrounds/Snake.png")));
		Pass.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/PassWord.png")));
		MainMenuBackGround.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/BackGrounds/GameMenuBackGround.jpg")));
		OnlineRules.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Rules.png")));
		
		//So The Image can fit to the button
		PlayButton.setOpaque(false);
		PlayButton.setContentAreaFilled(false);
		PlayButton.setBorderPainted(false);
		
		//So The Image can fit to the button
		QuitButton.setOpaque(false);
		QuitButton.setContentAreaFilled(false);
		QuitButton.setBorderPainted(false);
			
		//So The Image can fit to the button
		Rules.setOpaque(false);
		Rules.setContentAreaFilled(false);
		Rules.setBorderPainted(false);
		
		//So The Image can fit to the button
		ControlsButton.setOpaque(false);
		ControlsButton.setContentAreaFilled(false);
		ControlsButton.setBorderPainted(false);
		
		//So The Image can fit to the button
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		
		//So The Image can fit to the button
		SoloButton.setOpaque(false);
		SoloButton.setContentAreaFilled(false);
		SoloButton.setBorderPainted(false);
	    
		//So The Image can fit to the button
		MultiButton.setOpaque(false);
		MultiButton.setContentAreaFilled(false);
		MultiButton.setBorderPainted(false);
		
		//So The Image can fit to the button
		Mute.setOpaque(false);
		Mute.setContentAreaFilled(false);
		Mute.setBorderPainted(false);
		
		//So The Image can fit to the button
		LoginBtn.setOpaque(false);
		LoginBtn.setContentAreaFilled(false);
		LoginBtn.setBorderPainted(false);
		
		//So The Image can fit to the button
		Register.setOpaque(false);
		Register.setContentAreaFilled(false);
		Register.setBorderPainted(false);
		
		//So The Image can fit to the button
		OnlineRules.setOpaque(false);
	    OnlineRules.setContentAreaFilled(false);
	    OnlineRules.setBorderPainted(false);
	    
	    //So The Image can fit to the button
  		ScoreBoard.setOpaque(false);
  		ScoreBoard.setContentAreaFilled(false);
  		ScoreBoard.setBorderPainted(false);
    
		//So The Image can fit the label
		UserName.setOpaque(false);
		Pass.setOpaque(false);
		
		//Setting Bounds for the Button and Adding it 
		PlayButton.setBounds(300, 173, 117, 45);
		frame.getContentPane().add(PlayButton);
		
		//Setting Bounds for the Button and Adding it 
		Rules.setBounds(300, 229, 117, 45);
		frame.getContentPane().add(Rules);
	
		//Setting Bounds for the Button and Adding it 
		ControlsButton.setBounds(300, 285, 117, 45);
		frame.getContentPane().add(ControlsButton);
		
		//Setting Bounds for the Button and Adding it 
		QuitButton.setBounds(300, 341, 117, 45);
		frame.getContentPane().add(QuitButton);
		
		//Setting Bounds for the Button and Adding it 
		SoloButton.setBounds(300, 173, 117, 45);
		frame.getContentPane().add(SoloButton);
		
		//Setting Bounds for the Button and Adding it 
		MultiButton.setBounds(300, 229, 117, 45);
		frame.getContentPane().add(MultiButton);
		
		//Setting Bounds for the Button and Adding it 
		BackButton.setBounds(300, 341, 117, 45);
		frame.getContentPane().add(BackButton);
		
		//Setting Bounds for the Button and Adding it 
		Mute.setBounds(10, 11, 75, 45);
		frame.getContentPane().add(Mute);
		
		//Setting Bounds for the Button and Adding it 
		LoginBtn.setBounds(300, 275, 117, 46);
		frame.getContentPane().add(LoginBtn);
		
		//Setting Bounds for the Button and Adding it 
		Register.setBounds(508, 115, 117, 46);
		frame.getContentPane().add(Register);
		
		//Setting Bounds for the Button and Adding it 
		OnlineRules.setBounds(34, 115, 117, 45);
		frame.getContentPane().add(OnlineRules);
		
		//Setting Bounds for the Button and Adding it 
		ScoreBoard.setBounds(530, 17, 117, 45);
		frame.getContentPane().add(ScoreBoard);
	
		//Setting Bounds for the Button and Adding it 
		Login.setBounds(300, 173, 117, 35);
		frame.getContentPane().add(Login);
		Login.setColumns(10);
		
		//Setting Bounds for the Button and Adding it 
		PassWord.setBounds(300, 229, 117, 35);
		frame.getContentPane().add(PassWord);
		PassWord.setColumns(10);
		
		//Setting Bounds for the Button and Adding it 
		UserName.setBounds(162, 163, 128, 55);
		frame.getContentPane().add(UserName);
	
		//Setting Bounds for the Label and Adding it 
		Pass.setBounds(162, 219, 128, 55);
		frame.getContentPane().add(Pass);
			
		//Setting Bounds for the Label and Adding it 
		SnakeLogo.setVerticalAlignment(SwingConstants.TOP);
		SnakeLogo.setBounds(260, 48, 222, 66);
		frame.getContentPane().add(SnakeLogo);
	
		//Setting Bounds for the BackGround and Adding it 
		MainMenuBackGround.setVerticalAlignment(SwingConstants.BOTTOM);
		MainMenuBackGround.setBackground(new Color(240, 240, 240));
		MainMenuBackGround.setBounds(0, 0, 711, 402);
		frame.getContentPane().add(MainMenuBackGround);
	}
}
