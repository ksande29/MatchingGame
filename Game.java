package matchingGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame {

	private JPanel contentPane;
	private int numCards = 8;
	private Deck deck = new Deck(numCards);
	
	private int numCardsTurned = 0;
	private Card cardOne;
	private Card cardTwo;
	private boolean match = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Game() 
	{
		setUpMainDisplay();
		makeTitle();
		makePadding();
		makeGamePanel();
	}
	
	private void setUpMainDisplay() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	private void makeTitle() 
	{
		JLabel Title = new JLabel("Matching Game");
		Title.setBorder(new EmptyBorder(20, 0, 20, 0));
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Bauhaus 93", Font.PLAIN, 60));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(Title, BorderLayout.NORTH);
	}
	
	private void makePadding() 
	{
		//left
		JPanel leftPadding = new JPanel();
		leftPadding.setBackground(Color.BLACK);
		leftPadding.setBorder(new EmptyBorder(0, 20, 0, 0));
		contentPane.add(leftPadding, BorderLayout.WEST);
		//right
		JPanel rightPadding = new JPanel();
		rightPadding.setBorder(new EmptyBorder(0, 0, 0, 20));
		rightPadding.setBackground(Color.BLACK);
		contentPane.add(rightPadding, BorderLayout.EAST);
		//bottom
		JPanel bottomPadding = new JPanel();
		bottomPadding.setBorder(new EmptyBorder(0, 0, 20, 0));
		bottomPadding.setBackground(Color.BLACK);
		contentPane.add(bottomPadding, BorderLayout.SOUTH);
	}
	
	private void makeGamePanel() 
	{
		//make panel
		JPanel gamePanel = new JPanel();
		gamePanel.setBackground(Color.WHITE);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		//make buttons
		for(int i = 0; i < numCards; i++)
		{
			Card currentCard = deck.getCard(i);	
			//make a new button
			JButton button = new JButton("");
			currentCard.setButton(button);
			button.setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/folk-pattern-black.png")));
			gamePanel.add(button);
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{					
					button.setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/" + currentCard.getImg())));
					numCardsTurned++;
					
					if (numCardsTurned == 3)
					{
						if (!match)
						{
							cardOne.getButton().setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/folk-pattern-black.png")));
							cardTwo.getButton().setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/folk-pattern-black.png")));
						}
						numCardsTurned = 1;
						match = false;
					}
					if (numCardsTurned == 1)
						cardOne = currentCard;
					if (numCardsTurned == 2)
					{
						cardTwo = currentCard;
						if (cardOne.getId() == cardTwo.getId())
							match = true;
					}
				}
			});
		}
	}


}
