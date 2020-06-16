package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class SnakeGame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board b;


	public SnakeGame() {
		b = new Board();
	    setResizable(false);
	    add(b);
	    // this.setUndecorated(true);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        pack();
        setTitle("SnakeGame");
        ImageIcon img = new ImageIcon("src/Pics/BackGrounds/GameIcon.png");
        setIconImage(img.getImage());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
	
	
}
