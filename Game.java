import java.math.*;
import java.util.Random;
public class Game {

	public void startRound {
		// decide if sameShape or Colour
		// wait for input or count one second
		//since this decides what correct answer will be expected, it will just wait for the correct answer (colour or shape)

	}
	
	/**
	 * 
	 * @return
	 */
	public GameBoard spawnSameShape () { // new game board with 2 figures created upon each turn (key press)
		Random r = new Random();
		int shapeNumber = r.nextInt(3);
		Shape shaperino;
		switch (shapeNumber) {
			case 0:
				shaperino = Shape.ROUND;
				break;
			case 1:
				shaperino = Shape.SQUARE;
				break;
			case 2:
				shaperino = Shape.STAR;
				break;
			case 3: 
				shaperino = Shape.TRIANGULAR;
				break;
		}
		//int colour1 = r.nextInt(3);
		//int colour2 = r.nextInt(3);
		int[] colournumbers = new int[2];
		colournumbers[0] = r.nextInt(3);
		colournumbers[1] = r.nextInt(3);
		//beautiful solution
		while (colournumbers[0] == colournumbers[1]) {
			colournumbers[1] = r.nextInt(3);
		}
		Colour[] colours = new Colour[2];
		for (int i = 0; i < 2; i++) {
			switch (colournumbers[i]) {
				case 0:
					colours[i] = Colour.BLUE;
					break;
				case 1:
					colours[i] = Colour.GREEN;
					break;
				case 2:
					colours[i] = Colour.RED;
					break;
				case 3:
					colours[i] = Colour.YELLOW;
					break;
			}
		

		}
		
		Figure[] figures = {new Figure(colours[0], shaperino), new Figure(colours[1], shaperino)};
		return new GameBoard(figures);
		
		// use math.random for the colour
	}		//prevent both attributes from beign equal
	
	public void spawnSameColour {
		// use math.random for the shape
	}		//prevent both attributes from beign equal:    basically determine first attribute randomly and then 
		//	the second one can be any other attribute BUT that one. 
	
	
}
