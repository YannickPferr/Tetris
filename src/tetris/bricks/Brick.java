package tetris.bricks;

import java.awt.Color;

import tetris.Coordinate;

/**
 * Represents a brick for the classic Tetris game
 * 
 * @author Kai H&ouml;ver (kai@tk.informatik.tu-darmstadt.de)
 */
public abstract class Brick {
  
  /**
   * The coordinates for this brick (always four elements)
   */
  public Coordinate[] coords = new Coordinate[4];  
  
  /**
   * the current position of the brick
   */
  public Coordinate position = new Coordinate(0, 0);
  
  /**
   * creates a new brick by defining its shape
   */
  public Brick() {
	defineShape();
  }
  
  /**
   * Defines the shape of the brick by initializing the coords attribute
   */
  public abstract void defineShape();
  
  /**
   * returns the color for this brick
   * 
   * @return the brick's color (constant for each concrete type)
   */
  public abstract Color getColor();
  
  /**
   * rotates the brick by 90 degrees to the right, adapting its coordinates
   */
  public void rotateShapeRight() {
    for (int i = 0; i < coords.length; i++) {
      int tmpX = coords[i].x;
      coords[i].x = -coords[i].y;
      coords[i].y = tmpX;
    }
  }
  
  /**
   * rotates the brick by 90 degrees to the left, adapting its coordinates
   */
  public void rotateShapeLeft() {
    for (int i = 0; i < coords.length; i++) {
      int tmpX = coords[i].x;
      coords[i].x = coords[i].y;
      coords[i].y = -tmpX;
    }
  }
  
  /**
   * returns the minimum y value for this brick at its current position
   * 
   * @return the minimum y value for the brick
   */
  public int minY() {
    int min = coords[0].y;
    for (Coordinate c : coords)
      min = Math.min(min, c.y);
    return min;
  }
  
  /**
   * returns the maximum y value for this brick at its current position
   * 
   * @return the maximum y value for the brick
   */
  public int maxY() {
	int max = coords[0].y;
	for (Coordinate c : coords)
	  max = Math.max(max, c.y);
	return max;
  }
  
  /**
   * returns the minimum x value for this brick at its current position
   * 
   * @return the minimum x value for the brick
   */

  public int minX() {
	int min = coords[0].x;
	for (Coordinate c : coords)
	  min = Math.min(min, c.x);
	return min;

  }
  
  /**
   * returns the maximum x value for this brick at its current position
   * 
   * @return the maximum x value for the brick
   */
  public int maxX() {
	int max = coords[0].x;
	for (Coordinate c : coords)
	  max = Math.max(max, c.x);
	return max;
  }
  
  /**
   * prints the current position of this brick
   */
  public void printPosition() {
	StringBuilder sb = new StringBuilder(100);
    for (int i = 0; i < this.coords.length; i++) {
      int x = position.x + coords[i].x;
      int y = position.y + coords[i].y;
      sb.append("(").append(x).append(",");
      sb.append(y).append(")\t");
    }
    sb.append(System.lineSeparator());
    System.out.println(sb.toString());
  }
}
