package matchingGame;

import javax.swing.JButton;

public class Card 
{
	private String img;
	private int id;
	private JButton button;
	
	public Card(String img, int id)
	{
		this.img = img;
		this.id = id;
		this.button = new JButton();
	}
	
	public void setImg(String img)
	{
		this.img = img;
	}
	
	public String getImg()
	{
		return this.img;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return this.id;
	}
	public void setButton(JButton button)
	{
		this.button = button;
	}
	
	public JButton getButton()
	{
		return this.button;
	}
}
