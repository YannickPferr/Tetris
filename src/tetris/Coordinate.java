package tetris;

/*
 * Represents the coordinate of a brick element
 * 
 * @author Kai H&ouml;ver (kai@tk.informatik.tu-darmstadt.de)
 */
public class Coordinate {
	
	/**
	 * the x coordinate
	 */
	public int x;

	/**
	 * the y coordinate
	 */
	public int y;
	
	/**
	 * creates a new Coordinate using the x and y values passed in
	 * @param x the x value for the coordinate
	 * @param y the y value for the coordinate
	 */
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
