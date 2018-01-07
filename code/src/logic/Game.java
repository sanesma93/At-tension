/**
 * @author Santiago Escobar Martínez Acevedo Betancur
 */
package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;
public class Game {
	
	private Random r;
	private Figure[] recentFigures;
	private boolean answer;  // false = colour, true = shape
	private Shape[] availableShapes = {Shape.ROUND, Shape.SQUARE, Shape.STAR, Shape.TRIANGULAR};
	private Colour[] availableColours = {Colour.BLUE, Colour.GREEN, Colour.RED, Colour.YELLOW};
	private GameBoard gBoard; // I wasn't sure about the methods returning gameboards so I save it here instead
	private GUIFun gui;
	private Timer showFiguretimer;
	private GameState gameState;
	
	public Game(GUIFun gui)
	{
		r = new Random();  // I put this here because we need it in lots of methods
		recentFigures = new Figure[4];
		this.gui = gui;
		showFiguretimer = new Timer(2000, new ChangeFigureTimerListener());  // 2 seconds for now
		gameState = GameState.STARTSCREEN;
	}

	
	public void startGame()
	{
		// delay timer
		gui.updateAllTheThings();
	}
	
	
	
	
	/**
	 * Start game where user compares two figures
	 */	
	public void startFirstRound() {
		// start timer for round 60s?
		// register action listener
		// decide if sameShape or logic.Colour
		//System.out.println("starting first round");
		
		gameState = GameState.FIRSTGAME;
		showFiguretimer.start();
		
		if(r.nextInt(100) <= 49)
		{
			spawnSameShape();
			//System.out.println("calling update, if");
			gui.updateAllTheThings();
		}
		else
		{
			//System.out.println("calling update, else");
			spawnSameColour();
			gui.updateAllTheThings();			
		}
		
	}
	
	/**
	 * Start game part where use remembers previous figure
	 */
	public void startSecondRound() {
		// loop with ending condition
		
		// show randomly generated figure
		Figure[] fig = {new Figure(availableColours[r.nextInt(4)], availableShapes[r.nextInt(4)])};
		gBoard = new GameBoard(fig);
		recentFigures[0] = fig[0];
		
		
		// hide first figure, show second figure
		if(r.nextInt(2) == 0)
		{
			spawnShapeAgain(recentFigures[0].getShape(),2);
			
			//compare user input to answer and continue or game over accordingly
		}
		else
		{
			spawnColourAgain(recentFigures[0].getColour(),2);
			
			//compare user input to answer and continue or game over accordingly
		}
		
		//move up the figures in the array, the oldest one is dropped
		Figure[] temp = new Figure[4];
		temp[0] = recentFigures[1];
		recentFigures = temp;
		
		
	}
	
	/**
	 * Start game part where user remembers figure from TWO steps before
	 */
	public void startThirdRound() {
		
	}
	
	/**
	 * Start game part where user remembers figure from THREE steps before
	 */
	public void startFourthRound() {
		
	}
	
	/**
	 * create new logic.Figure with the same logic.Shape as the first parameter
	 * and a random colour.
	 * add it to the recentFigures Array and
	 * update Gameboard
	 * @param shape
	 * @param round
	 */
	public void spawnShapeAgain(Shape shape, int round)  //use same shape as the figure from (1,2 or 3) before
	{
		answer = true;
		Figure fig = new Figure(availableColours[r.nextInt(4)],shape);
		recentFigures[round-1] = fig;  // add the new figure at the current last spot of the array
		// gBoard = ?? do you want to fill it up with grey squares or something to represent the hidden shapes?
	}	
	
	
	
	/**
	 * create new logic.Figure with the same color as the first parameter
	 * and a random shape.
	 * add it to the recentFigures Array and
	 * update Gameboard
	 * @param colour
	 * @param round
	 */
	public void spawnColourAgain(Colour colour, int round)  //use same colour as the figure from (1,2 or 3) before
	{
		answer = false;
		Figure fig = new Figure(colour,availableShapes[r.nextInt(4)]);
		recentFigures[round-1] = fig;
		// gBoard = ?? do you want to fill it up with grey squares or something to represent the hidden shapes?
	}
	
	
	/**
	 * 
	 * @return
	 */
	public void spawnSameShape ()  // new game board with 2 figures created upon each turn (key press)
	{ 
		answer = true;
		int shapeNumber = r.nextInt(3);
		Shape shaperino = availableShapes[shapeNumber];
		
	
		int[] colournumbers = new int[2];
		colournumbers[0] = r.nextInt(4);
		colournumbers[1] = r.nextInt(4);
		//beautiful solution
		while (colournumbers[0] == colournumbers[1]) {
			colournumbers[1] = r.nextInt(4);
		}
		Colour[] colours = new Colour[2];
		
		colours[0] = availableColours[colournumbers[0]];
		colours[1] = availableColours[colournumbers[1]];
		
		
		Figure[] figures = {new Figure(colours[0], shaperino), new Figure(colours[1], shaperino)};
		gBoard = new GameBoard(figures);
		
		// use math.random for the colour
	}		//prevent both attributes from beign equal
	
	public void spawnSameColour() 
	{
		answer = false;		
		int colourNumber = r.nextInt(3);
		Colour colourino = availableColours[colourNumber];
		
		int[] shapenumbers = new int[2];
		shapenumbers[0] = r.nextInt(4);
		shapenumbers[1] = r.nextInt(4);
		
		while (shapenumbers[0] == shapenumbers[1]) {
			shapenumbers[1] = r.nextInt(4);
		}
		Shape[] shapes = new Shape[2];
		
		shapes[0] = availableShapes[shapenumbers[0]];
		shapes[1] = availableShapes[shapenumbers[1]];
		
		Figure[] figures = {new Figure(colourino, shapes[0]), new Figure(colourino, shapes[1])};
		gBoard = new GameBoard(figures);
	}		
	
	
	public GameBoard getgBoard()
	{
		return gBoard;
	}
	
	public GameState getGameState()
	{
		return gameState;
	}
	
	class ArrowKeyListener implements KeyListener
	{

			@Override
			public void keyPressed(KeyEvent e)
			{
				if(gameState == GameState.FIRSTGAME )
				{
				
					if(e.getKeyCode() == KeyEvent.VK_LEFT) // left = color
					{
						System.out.println("left pressed");
						//if answer quit game
						if(answer)
						{
							showFiguretimer.stop();
							gameState = GameState.GAMEOVER;
							gui.updateAllTheThings();
							System.out.println("game over");					
						}
						else
						{
							showFiguretimer.stop();
							startFirstRound();							
						}
					}
					else if (e.getKeyCode() == KeyEvent.VK_RIGHT)  // right = shape
					{
						System.out.println("right pressed");
						// if !answer quit game
						if(!answer)
						{
							showFiguretimer.stop();
							gameState = GameState.GAMEOVER;
							gui.updateAllTheThings();
							System.out.println("game over");
						}
						else
						{
							showFiguretimer.stop();
							startFirstRound();							
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				
			}

			@Override
			public void keyTyped(KeyEvent e)
			{
				
			}
				
	}
	
	class ChangeFigureTimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			//startFirstRound();		just for testing
			showFiguretimer.stop();
			gameState = GameState.GAMEOVER;
			gui.updateAllTheThings();
			System.out.println("too slow!");
		}
		
	}
	
	class MainMenuButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{			
			gameState = GameState.STARTSCREEN;
			showFiguretimer.stop();
			gui.updateAllTheThings();
		}		
	}	
	
	class FirstGameButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{			
			// put in function once it works
			gameState = GameState.COUNTDOWN;					
			gui.updateAllTheThings();
			
			System.out.println("after");
			startFirstRound();			
		}		
	}	
}

