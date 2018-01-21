
public class GameBoard {
//board for backend implementation of the 4 figures to be displayed through the UI
	
	private Figure[] figures;
	
	
	
	public GameBoard (Figure[] figures)
	{
		this.figures = figures;
	}
	
	public Figure[] getFigures()
	{
		return figures;
	}
	
	
}
