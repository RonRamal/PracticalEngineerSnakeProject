package View;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class RulesOnline extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame A;
	private JButton BackButton = new JButton("");//Back Button
    private JLabel RulesBackGround = new JLabel("");//For BackGround Image
	/**
	 * Create the frame.
	 */
	public RulesOnline(JFrame frame) {
		this.A=frame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);//Setting Layout
		
		//Setting Images for BackGround,Back Button,GameIcon
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Pics/BackGrounds/GameIcon.png"));
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		BackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				BackButton.setBounds(396, 395, 117, 45);		
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				BackButton.setBounds(396, 404, 117, 45);
			}
		});
		
		//When Pressing the Button Close This frame and Open Menu Frame
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A.setVisible(true);
				dispose();	
			}
		});
		
		//So The Image can fit to the button
		BackButton.setOpaque(false);
		BackButton.setContentAreaFilled(false);
		BackButton.setBorderPainted(false);
		BackButton.setIcon(new ImageIcon(RulesOnline.class.getResource("/Pics/Buttons/Back.png")));
		
		//Setting Bounds for the Buttons 
		BackButton.setBounds(396, 404, 117, 45);
		
		//Adding Them to the Panel
		contentPane.add(BackButton);
		RulesBackGround.setIcon(new ImageIcon(RulesOnline.class.getResource("/Pics/BackGrounds/OnlineRules.png")));
		RulesBackGround.setBounds(0, 0, 719, 436);
		contentPane.add(RulesBackGround);
	}
}
