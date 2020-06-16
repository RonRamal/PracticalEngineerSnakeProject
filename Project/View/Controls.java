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

public class Controls extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    public JFrame g;
    private JButton Back = new JButton("");//Back Button
	private JLabel ControlBackGround = new JLabel("");//For BackGround
	/**
	 * Create the frame.
	 */
	    public Controls(JFrame g) {
		this.g = g;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);//Setting Layout
		
		//Setting Images for BackGround,Back Button,GameIcon
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Pics/BackGrounds/GameIcon.png"));
		ControlBackGround.setIcon(new ImageIcon(Controls.class.getResource("/Pics/BackGrounds/KeysControls.png")));
		
		//When The Mouse is on the Button Change Button Place For Rolling Effect
		Back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				Back.setBounds(303, 351, 117, 45);		
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				Back.setBounds(303, 359, 117, 45);
			}
		});
		
		//When Pressing the Button Close This frame and Open Menu Frame
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				g.setVisible(true);
				dispose();
			}
		});
		Back.setIcon(new ImageIcon(Controls.class.getResource("/Pics/Buttons/Back.png")));
		
				//So The Image can fit to the button
				Back.setOpaque(false);
				Back.setContentAreaFilled(false);
				Back.setBorderPainted(false);
				
				//Setting Bounds for the Buttons 
				Back.setBounds(303, 359, 117, 45);
				contentPane.add(Back);

				//Setting Bounds for BackGround
				ControlBackGround.setBounds(0, 0, 702, 404);
		    	contentPane.add(ControlBackGround);
	}
}
