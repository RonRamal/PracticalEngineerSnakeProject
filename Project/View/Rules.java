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
public class Rules extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame A;
	private JButton BackButton = new JButton("");//Back Button
    private JLabel RulesBackGround = new JLabel("");//Label For BackGround
	/**
	 * Create the frame.
	 */
	public Rules(JFrame frame) {
		this.A=frame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 735, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);//Setting Layout
		
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
		
		//Setting Images for BackGround,Back Button,GameIcon
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Pics/BackGrounds/GameIcon.png"));
		RulesBackGround.setIcon(new ImageIcon(Rules.class.getResource("/Pics/BackGrounds/RulesBackGroundr.png")));
		BackButton.setIcon(new ImageIcon(Rules.class.getResource("/Pics/Buttons/Back.png")));
		
		//Setting Bounds For Buttons And BackGround
		BackButton.setBounds(396, 404, 117, 45);
		contentPane.add(BackButton);
		
		//Adding Them to the Panel
		RulesBackGround.setBounds(0, 0, 719, 436);
		contentPane.add(RulesBackGround);
	}
}
