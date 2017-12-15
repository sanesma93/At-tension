
public class Figure {
	
	private Colour colour;
	private Shape shape;
	
	/**
	 * 
	 * @param colour 
	 * @param shape
	 */
	public Figure(Colour colour, Shape shape) {
	  setColour(colour);
	  setShape(shape);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	
	
}
