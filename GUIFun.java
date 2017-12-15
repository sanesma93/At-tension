import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.*;



public class GUIFun
{
	private MyPanel panel;
	private JFrame frame;
	//private GameBoard gBoard;
	private Game game;
	private int xOffset = 150;
	private int yOffset = 150;
	private Color co;
	private Shape shap;
	private JPanel mainMenuPanel;
	private JButton mainMenuButton;
	private JButton firstGameButton;
	private JButton secondGameButton;
	private JButton thirdGameButton;
	private JButton fourthGameButton;
	private Box buttonBox;
	
	
	public GUIFun()
	{
		game = new Game(this);
		frame = new JFrame();		
		panel = new MyPanel();	
		frame.add(panel);
		panel.setLayout(new BorderLayout());
		frame.addKeyListener( game.new ArrowKeyListener());  //instantiating inner classes is weird
		mainMenuButton = new JButton("Main Menu");
		panel.add(mainMenuButton, BorderLayout.SOUTH);
		mainMenuButton.setVisible(false);
		mainMenuButton.addActionListener(game.new MainMenuButtonListener());
		
		
		buttonBox = new Box(BoxLayout.Y_AXIS);
		mainMenuPanel = new JPanel();
		FlowLayout flow = new FlowLayout();
		flow.setAlignment(FlowLayout.TRAILING); //the layout stuff is bad but I really don't want to fix it right now
		mainMenuPanel.setLayout(flow);
		
		firstGameButton = new JButton("first game");
		secondGameButton = new JButton("second game");
		thirdGameButton = new JButton("third game");
		fourthGameButton = new JButton("fourth game");
		buttonBox.add(firstGameButton);
		buttonBox.add(secondGameButton);
		buttonBox.add(thirdGameButton);
		buttonBox.add(fourthGameButton);
		mainMenuPanel.add(buttonBox);
		
		firstGameButton.addActionListener(game.new FirstGameButtonListener());
		// add listeners
	}
	
	public void updateAllTheThings()
	{
		frame.requestFocusInWindow(); // to get focus back after a button click because otherwise keyboard input won't work anymore. apparently this is a 'hacky' solution but the better one looked like a huge pain
		
		if(game.getGameState() == GameState.STARTSCREEN)
		{			
			frame.remove(panel);
			frame.add(mainMenuPanel);
			mainMenuPanel.repaint();
		}
		else
		{
			frame.remove(mainMenuPanel);
			frame.add(panel);
			
			if (game.getGameState() == GameState.GAMEOVER)
			{
				mainMenuButton.setVisible(true);
			}
			else
			{
				mainMenuButton.setVisible(false);
			}
		}
		
		panel.repaint();		
	}
		
	
		private void los()
		{	
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(800, 500);
			frame.setVisible(true);
		}
	
	

	public static void main(String[] args)
	{
		GUIFun gui = new GUIFun();
		
		gui.los();	
		
		gui.game.startGame();
		

	}

	
	public class MyPanel extends JPanel
	{
		private void drawTriangular(Graphics g)
		{
			int[] xPoints = {25,50,0};
			for(int i = 0; i < xPoints.length; ++i)
			{
				xPoints[i] += xOffset;
			}
			int[] yPoints = {0,50,50};
			for(int i = 0; i < yPoints.length; ++i)
			{
				yPoints[i] += yOffset;
			}
			g.fillPolygon(xPoints, yPoints, 3);
		}
		
		private void drawStar(Graphics g)
		{
			int[] xPoints = {25,32,50,32,25,17,0,17};
			for(int i = 0; i < xPoints.length; ++i)
			{
				xPoints[i] += xOffset;
			}
			int[] yPoints = {0,17,25,32,50,32,25,17};
			for(int i = 0; i < yPoints.length; ++i)
			{
				yPoints[i] += yOffset;
			}
		
			g.fillPolygon(xPoints, yPoints, 8);
		}
		
		private void paintFirstGame(Graphics g)
		{
			xOffset = 250; // resetting the offset because this method is called multiple times for unknown reasons
			//System.out.println("backgroundpaintinggrey");
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			
			GameBoard gBoard = game.getgBoard();
			
			Figure[] figs = gBoard.getFigures();
						
			
			for(Figure fig : figs)
			{			
				switch(fig.getColour())
				{
					case RED:
						//System.out.println("red");
						co = Color.RED;
						break;
					case GREEN:
						//System.out.println("green");
						co = Color.GREEN;
						break;
					case BLUE:
						//System.out.println("blue");
						co = Color.BLUE;
						break;
					case YELLOW:
						//System.out.println("yellow");
						co = Color.YELLOW;						
				}
			
				g.setColor(co);
				
				shap = fig.getShape();
			
				switch(shap)
				{
				case ROUND:
					//System.out.println("round");
					g.fillOval(xOffset, yOffset, 100, 100);
					break;
				case SQUARE:
					//System.out.println("square");
					g.fillRect(xOffset, yOffset, 100, 100);
					break;
				case TRIANGULAR:
					//System.out.println("triangle");
					drawTriangular(g);					
					break;
				case STAR:
					//System.out.println("star");
					drawStar(g);					
				}
				xOffset +=200;  // moving the second shape to the right
			}
		}
		
		private void paintGameOver(Graphics g)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			char[] gameOver = {'G','a','m','e',' ','o','v','e','r'};  // yeah, this is ugly
			g.setColor(Color.BLACK);
			g.drawChars(gameOver, 0, 9, 350, yOffset);
		}
		
		/*
		private void paintStartScreen(Graphics g)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			char[] welcome = {'S','e','l','e','c','t',' ','a',' ','G','a','m','e','!'};  // yeah, this is ugly
			g.setColor(Color.BLACK);
			g.drawChars(welcome, 0, 14, 350, 50);
		}
		*/
		
		public void paintComponent(Graphics g)
		{	
			if(game.getGameState() == GameState.FIRSTGAME)
			{
				paintFirstGame(g);
			}
			else if (game.getGameState() == GameState.GAMEOVER)
			{
				paintGameOver(g);
			}
			else if(game.getGameState() == GameState.STARTSCREEN)
			{
				//paintStartScreen(g);
			}
			
			
		}
	}
}