package tetris.bricks;

import java.awt.Color;

import tetris.Coordinate;

public class LBrick extends Brick {
	
	public void defineShape() {
		coords[0] = new Coordinate(-1,-1);
		coords[1] = new Coordinate(0,-1);
		coords[2] = new Coordinate(0,0);
		coords[3] = new Coordinate(0,1);
	}
	
	public Color getColor() {
		return Color.MAGENTA;
	  }

}
