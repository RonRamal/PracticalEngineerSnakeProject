package View;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.UserController;
import Model.Player;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
public class ScoreBoard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame A;
	private JButton BackButton = new JButton("");//Back Button
    private JLabel RulesBackGround = new JLabel("");//Label For BackGround
    private String[] columns= {"User","Wins","Losses"};//For Table
    private Object[][] data={};//For Table
    private JTable table;//Table top Players
	private JScrollPane scrollPane = new JScrollPane(table);
	private JTable tableLoser;//Table Worst Players
	private JScrollPane scrollPaneLoser = new JScrollPane(tableLoser);
	private JLabel TopWinners = new JLabel("");//Winner Label
	private JLabel TopLosers = new JLabel("");//Loser Label
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public ScoreBoard(JFrame frame){
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
		
		//So The Image can fit the label
		TopWinners.setOpaque(false);
		TopLosers.setOpaque(false);
		
		//Loading the Images
		setIconImage(Toolkit.getDefaultToolkit().getImage("src/Pics/BackGrounds/GameIcon.png"));
		BackButton.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Pics/Buttons/Back.png")));
		RulesBackGround.setIcon(new ImageIcon(ScoreBoard.class.getResource("/Pics/BackGrounds/ScoreBoard.png")));
		TopWinners.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Winner.png")));
		TopLosers.setIcon(new ImageIcon(GameMenu.class.getResource("/Pics/Buttons/Worst.png")));


		//Setting Bounds For Buttons And BackGround
		BackButton.setBounds(396, 404, 117, 45);
		contentPane.add(BackButton);
		
		//Setting Bounds for Labels
		TopWinners.setBounds(43, 83, 125, 53);
		contentPane.add(TopWinners);
		TopLosers.setBounds(43, 240, 125, 48);
		contentPane.add(TopLosers);
		
		//Setting Bounds for Losers Table
		scrollPane.setBounds(43,142,480,72);
		contentPane.add(scrollPane);
		
		//Setting Bounds for Winner Table
		scrollPaneLoser.setBounds(43,295,480,72);
		contentPane.add(scrollPaneLoser);
		
		//Setting Bounds for BackGround Image
		RulesBackGround.setBounds(0, 0, 719, 436);
		contentPane.add(RulesBackGround);
		
		//Setting Table for Winners
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setFont(new Font("Helvetica",Font.BOLD,14));
		table.setModel(new DefaultTableModel(data,columns));
		table.setPreferredScrollableViewportSize(new Dimension(500,50));
		table.setFillsViewportHeight(true);
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		
		ArrayList<Player> playerlist = null;
		try {
			playerlist = UserController.ScoreBoard();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object[] row={};
		row= new Object[3];
		
		for(int i=0; i< playerlist.size();i++)
		{
			row[0]=playerlist.get(i).getName();
			row[1]=playerlist.get(i).getWins();
			row[2]=playerlist.get(i).getLosses();
			model.addRow(row);
		}
		
		//Setting Table for Losers
		tableLoser = new JTable();
		scrollPaneLoser.setViewportView(tableLoser);
		tableLoser.setFont(new Font("Helvetica",Font.BOLD,14));
		tableLoser.setModel(new DefaultTableModel(data,columns));
		tableLoser.setPreferredScrollableViewportSize(new Dimension(500,50));
		tableLoser.setFillsViewportHeight(true);
		DefaultTableModel modelLoser=(DefaultTableModel)tableLoser.getModel();
		
		ArrayList<Player> playerlistLoser = null;
		try {
			playerlistLoser = UserController.WorstScoreBoard();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object[] rowLoser={};
		rowLoser= new Object[3];
		
		for(int i=0; i< playerlistLoser.size();i++)
		{
			rowLoser[0]=playerlistLoser.get(i).getName();
			rowLoser[1]=playerlistLoser.get(i).getWins();
			rowLoser[2]=playerlistLoser.get(i).getLosses();
			modelLoser.addRow(rowLoser);
		}
	}
}
