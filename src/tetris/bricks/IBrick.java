package tetris.bricks;

import java.awt.Color;

import tetris.Coordinate;

public class IBrick extends Brick {
	
	public void defineShape() {
		coords[0] = new Coordinate(0,-1);
		coords[1] = new Coordinate(0,0);
		coords[2] = new Coordinate(0,1);
		coords[3] = new Coordinate(0,2);
	}
	
	public Color getColor() {
		return Color.RED;
	  }

}
