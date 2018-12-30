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
	
	private Deck deck;
	private int numCardsTurned;
	private Card cardOne;
	private Card cardTwo;
	private boolean match;
	private JPanel gamePanel;
	private JPanel bottomPanel;

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
		resetVariables();	
		setUpMainDisplay();
		makeTitle();
		makePadding();
		makeBottomPanel();
		makeReset();
		makeGamePanel();
		makeButtons();
	}

	private void resetVariables() 
	{
		numCardsTurned = 0;
		cardOne = null;
		cardTwo = null;
		match = false;
		deck = new Deck(numCards);
	}
	
	private void setUpMainDisplay() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 600);
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
	}

	private void makeBottomPanel() 
	{
		bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(20, 0, 40, 0));
		bottomPanel.setBackground(Color.BLACK);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
	}

	private void makeReset() 
	{
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnRestart.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnRestart.setBackground(Color.WHITE);
		bottomPanel.add(btnRestart);
		btnRestart.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				resetVariables();		
				gamePanel.removeAll();
				gamePanel.revalidate();
				gamePanel.repaint();
				makeButtons();
			}
		});
	}
	
	private void makeGamePanel() 
	{		
		gamePanel = new JPanel();
		gamePanel.setBackground(Color.WHITE);
		contentPane.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(0, 4, 0, 0));
	}

	private void makeButtons() 
	{
		for(int i = 0; i < numCards; i++)
		{
			Card currentCard = deck.getCard(i);	
			//make a new button
			JButton button = new JButton("");
			currentCard.setButton(button);
			button.setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/pipes.png")));
			gamePanel.add(button);
			button.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{					
					//card is not already turned over
					if (!currentCard.equals(cardOne) && !currentCard.equals(cardTwo))
					{
						button.setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/" + currentCard.getImg())));
						numCardsTurned++;
						
						if (numCardsTurned == 3)
						{
							//turn cards back over
							if (!match)
							{
								cardOne.getButton().setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/pipes.png")));
								cardTwo.getButton().setIcon(new ImageIcon(GameOldIdea.class.getResource("/matchingGame/img/pipes.png")));
							}
							numCardsTurned = 1;
							match = false;
							cardTwo = null;
						}
						if (numCardsTurned == 1)
						{
							cardOne = currentCard;
						}
						if (numCardsTurned == 2)
						{
							cardTwo = currentCard;
							if (cardOne.getId() == cardTwo.getId())
								match = true;
						}
					}
				}
			});
		}
	}
}
