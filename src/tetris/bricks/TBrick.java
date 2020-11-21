package tetris.bricks;

import java.awt.Color;

import tetris.Coordinate;

/**
 * Implements a "T"-shaped brick for Tetris
 * 
 * @author Kai H&ouml;ver (kai@tk.informatik.tu-darmstadt.de)
 */
public class TBrick extends Brick {
	
  /**
   * Defines the shape of the brick by initializing the coords attribute
   */
  @Override
  public void defineShape() {
	coords[0] = new Coordinate(-1,0);
	coords[1] = new Coordinate(0,0);
	coords[2] = new Coordinate(1,0);
	coords[3] = new Coordinate(0,1);
  }

  @Override
  public Color getColor() {
	return Color.blue;
  }
}
