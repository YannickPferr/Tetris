package tetris;

import java.awt.Color;
import tetris.bricks.Brick;

/**
 * This class specifies a board for the Tetris game
 * and its behavior. 
 * 
 * @author Kai H&ouml;ver (kai@tk.informatik.tu-darmstadt.de)
 */
public class Board {
  /**
   * the default color for "unoccupied" elements
   */
  private Color defaultColor; // the color of an empty field

  /**
   * the current color values for the individual board fields
   */
  Color[][] field;

  /**
   * the height of the game board
   */
  int height;

  /**
   * the width of the game board
   */
  int width;
  
  
  /**
   * Creates a new TetrisBoard based on the parameters passed in
   * 
   * @param boardHeight number of rows of the board
   * @param boardWidth number of columns of the board
   * @param backgroundColor the color of empty fields
   */
  public Board(int boardHeight, int boardWidth, Color backgroundColor) {
    height = boardHeight;
    width = boardWidth;
    defaultColor = backgroundColor;
    initBoard();
  }
  
  /**
   * Initializes the board with empty fields
   */
  public void initBoard() {
    // Initialize board fields
    field = new Color[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        field[i][j] = getDefaultColor();
      }
    }  
  }
  
  /**
   * Returns the color of empty fields
   * 
   * @return the color of empty fields
   */
  public Color getDefaultColor() {
    return defaultColor;
  }
  
  /**
   * Moves a brick one step to the right
   * @param brick the Brick to be moved
   */
  public void moveBrickRight(Brick brick) {
    // TODO von Ihnen zu implementieren
	  removeBrick(brick);
	  brick.position.x++;
	  addBrick(brick);
	  
  }
  
  /**
   * Checks if a brick can be moved to the right
   * 
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickRight(Brick brick) {
    // TODO von Ihnen zu implementieren  
	// if (brick.position.x + brick.maxX() < width - 1) {
	//   if(field[brick.position.x + brick.coords[0].x] [(brick.position.y + brick.coords[0].y) + 1] == defaultColor
	// 		  && field[brick.position.x + brick.coords[1].x] [(brick.position.y + brick.coords[1].y) + 1] == defaultColor
	// 		  && field[brick.position.x + brick.coords[2].x] [(brick.position.y + brick.coords[2].y) + 1] == defaultColor
	// 		  && field[brick.position.x + brick.coords[3].x] [(brick.position.y + brick.coords[3].y) + 1] == defaultColor) {return true;}
		 
	//   else return false;
	//  }
	//  else return false;
	  if(brick.position.x + brick.maxX() < width - 1) {
		  if((field[brick.position.y + brick.coords[0].y][brick.position.x + brick.coords[0].x + 1] == defaultColor 
				          || brick.coords[0].y == brick.coords[1].y && brick.coords[0].x + 1 == brick.coords[1].x
							|| brick.coords[0].y == brick.coords[2].y && brick.coords[0].x + 1 == brick.coords[2].x
							|| brick.coords[0].y == brick.coords[3].y && brick.coords[0].x + 1 == brick.coords[3].x)
				  
				  && (field[brick.position.y + brick.coords[1].y][brick.position.x + brick.coords[1].x + 1] == defaultColor 
						  || brick.coords[1].y == brick.coords[0].y && brick.coords[1].x + 1 == brick.coords[0].x
						  	|| brick.coords[1].y == brick.coords[2].y && brick.coords[1].x + 1 == brick.coords[2].x
						  	|| brick.coords[1].y == brick.coords[3].y && brick.coords[1].x + 1 == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[2].y][brick.position.x + brick.coords[2].x + 1] == defaultColor 
						  || brick.coords[2].y == brick.coords[0].y && brick.coords[2].x + 1 == brick.coords[0].x
						  	|| brick.coords[2].y == brick.coords[1].y && brick.coords[2].x + 1 == brick.coords[1].x
						  	|| brick.coords[2].y == brick.coords[3].y && brick.coords[2].x + 1 == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[3].y][brick.position.x + brick.coords[3].x + 1] == defaultColor 
						  || brick.coords[3].y == brick.coords[0].y && brick.coords[3].x + 1 == brick.coords[0].x
						  	|| brick.coords[3].y == brick.coords[1].y && brick.coords[3].x + 1 == brick.coords[1].x
						  	|| brick.coords[3].y == brick.coords[2].y && brick.coords[3].x + 1 == brick.coords[2].x)) {return true;}
		  
		  else return false;
	 }
	  else return false;
	  
  }
  
  /**
   * Moves a brick one step to the left
   * @param brick the Brick to be moved
   */
  public void moveBrickLeft(Brick brick) {
      // TODO von Ihnen zu implementieren
	  removeBrick(brick);
	  brick.position.x--;
	  addBrick(brick);
	  
  }
  
  /**
   * Checks if a brick can be moved to the left
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickLeft(Brick brick) {
      // TODO von Ihnen zu implementieren
	 // if (brick.position.x + brick.minX() > 0) {
	//   if(field[brick.position.x + brick.coords[0].x] [(brick.position.y + brick.coords[0].y) - 1] == defaultColor
	// 		  && field[brick.position.x + brick.coords[1].x] [(brick.position.y + brick.coords[1].y) - 1] == defaultColor
	// 		  && field[brick.position.x + brick.coords[2].x] [(brick.position.y + brick.coords[2].y) - 1] == defaultColor
	// 			  && field[brick.position.x + brick.coords[3].x] [(brick.position.y + brick.coords[3].y) - 1] == defaultColor) {return true;}
		 
	//   else return false;
	//  }
	// else return false;
	  if(brick.position.x + brick.minX() > 0) {
		  if((field[brick.position.y + brick.coords[0].y][brick.position.x + brick.coords[0].x - 1] == defaultColor 
				          || brick.coords[0].y == brick.coords[1].y && brick.coords[0].x - 1 == brick.coords[1].x
							|| brick.coords[0].y == brick.coords[2].y && brick.coords[0].x - 1 == brick.coords[2].x
							|| brick.coords[0].y == brick.coords[3].y && brick.coords[0].x - 1 == brick.coords[3].x)
				  
				  && (field[brick.position.y + brick.coords[1].y][brick.position.x + brick.coords[1].x - 1] == defaultColor 
						  || brick.coords[1].y == brick.coords[0].y && brick.coords[1].x - 1 == brick.coords[0].x
						  	|| brick.coords[1].y == brick.coords[2].y && brick.coords[1].x - 1 == brick.coords[2].x
						  	|| brick.coords[1].y == brick.coords[3].y && brick.coords[1].x - 1 == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[2].y][brick.position.x + brick.coords[2].x - 1] == defaultColor 
						  || brick.coords[2].y == brick.coords[0].y && brick.coords[2].x - 1 == brick.coords[0].x
						  	|| brick.coords[2].y == brick.coords[1].y && brick.coords[2].x - 1 == brick.coords[1].x
						  	|| brick.coords[2].y == brick.coords[3].y && brick.coords[2].x - 1 == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[3].y][brick.position.x + brick.coords[3].x - 1] == defaultColor 
						  || brick.coords[3].y == brick.coords[0].y && brick.coords[3].x - 1 == brick.coords[0].x
						  	|| brick.coords[3].y == brick.coords[1].y && brick.coords[3].x - 1 == brick.coords[1].x
						  	|| brick.coords[3].y == brick.coords[2].y && brick.coords[3].x - 1 == brick.coords[2].x)) {return true;}
		  
		  else return false;
	 }
	  else return false;
  }

  /**
   * Moves a brick one step down
   * 
   * @param brick the Brick to be moved
   */
  public void moveBrickDown(Brick brick) {
      // TODO von Ihnen zu implementieren
	removeBrick(brick);
	brick.position.y++;
	addBrick(brick);
	
  }
  
  /**
   * Checks if a brick can be moved one step down
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickDown(Brick brick) {
      // TODO von Ihnen zu implementieren	  
	  if(brick.position.y + brick.maxY() < height - 1) {
		  if((field[brick.position.y + brick.coords[0].y + 1][brick.position.x + brick.coords[0].x] == defaultColor 
				          || brick.coords[0].y + 1 == brick.coords[1].y && brick.coords[0].x == brick.coords[1].x
							|| brick.coords[0].y + 1 == brick.coords[2].y && brick.coords[0].x == brick.coords[2].x
							|| brick.coords[0].y + 1 == brick.coords[3].y && brick.coords[0].x == brick.coords[3].x)
				  
				  && (field[brick.position.y + brick.coords[1].y + 1][brick.position.x + brick.coords[1].x] == defaultColor 
						  || brick.coords[1].y + 1 == brick.coords[0].y && brick.coords[1].x == brick.coords[0].x
						  	|| brick.coords[1].y + 1 == brick.coords[2].y && brick.coords[1].x == brick.coords[2].x
						  	|| brick.coords[1].y + 1 == brick.coords[3].y && brick.coords[1].x == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[2].y + 1][brick.position.x + brick.coords[2].x] == defaultColor 
						  || brick.coords[2].y + 1 == brick.coords[0].y && brick.coords[2].x == brick.coords[0].x
						  	|| brick.coords[2].y + 1 == brick.coords[1].y && brick.coords[2].x == brick.coords[1].x
						  	|| brick.coords[2].y + 1 == brick.coords[3].y && brick.coords[2].x == brick.coords[3].x)
						  
				  && (field[brick.position.y + brick.coords[3].y + 1][brick.position.x + brick.coords[3].x] == defaultColor 
						  || brick.coords[3].y + 1 == brick.coords[0].y && brick.coords[3].x == brick.coords[0].x
						  	|| brick.coords[3].y + 1 == brick.coords[1].y && brick.coords[3].x == brick.coords[1].x
						  	|| brick.coords[3].y + 1 == brick.coords[2].y && brick.coords[3].x == brick.coords[2].x)) {return true;}
		  
		  else return false;
	 }
	  
	   else return false;

  }
  
  /**
   * adds a brick onto the board
   * @param brick the brick to be added
   */
  public void addBrick(Brick brick) {
	Coordinate position = brick.position;
	Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++)
      // determine the appropriate position for this element
      // and assign the brick's color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = brick.getColor();    
  }

  /**
   * removes a brick from the board
   * @param brick the brick to be removed
   */
  public void removeBrick(Brick brick) {
	Coordinate position = brick.position;
	Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++)
      // determine the appropriate position for this element
      // and replace with the background color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = getDefaultColor();
  }

  /**
   * Lets a brick fall down until it is stopped by
   * either the bottom of the board or another brick 
   * 
   * @param brick the brick to be moved deep down
   */
  public void letBrickFallDown(Brick brick) {
    while (canMoveBrickDown(brick)) {
      moveBrickDown(brick);
    }  
  }
  
  /**
   * Rotates a brick clockwise
   * 
   * @param brick the brick to be rotated on the board
   */
  public void rotateBrick(Brick brick) {
      // TODO von Ihnen zu implementieren
	  removeBrick(brick);
	  brick.rotateShapeRight();
	  addBrick(brick);
	  
  }
  
  /**
   * Checks if a brick can be rotated clockwise
   * @param brick the brick to be rotated
   * @return true if brick can be rotated, otherwise false
   */
  public boolean canRotateBrick(Brick brick) {
      // TODO von Ihnen zu implementieren
	  
	  
	  if((brick.position.x - brick.minY() < width && brick.position.x - brick.maxY() >= 0 
			  && brick.position.y + brick.maxX() < height && brick.position.y + brick.minX() >= 0)
		
		  
		  && (field[brick.position.y + brick.coords[0].x][brick.position.x - brick.coords[0].y] == defaultColor
				  || (brick.coords[0].x == brick.coords[0].y && -brick.coords[0].y == brick.coords[0].x)
				  	|| (brick.coords[0].x == brick.coords[1].y && -brick.coords[0].y == brick.coords[1].x)
				  	|| (brick.coords[0].x == brick.coords[2].y && -brick.coords[0].y == brick.coords[2].x)
				  	|| (brick.coords[0].x == brick.coords[3].y && -brick.coords[0].y == brick.coords[3].x))
				  
				  && (field[brick.position.y + brick.coords[1].x][brick.position.x - brick.coords[1].y] == defaultColor
						  || (brick.coords[1].x == brick.coords[0].y && -brick.coords[1].y == brick.coords[0].x)
						  	|| (brick.coords[1].x == brick.coords[1].y && -brick.coords[1].y == brick.coords[1].x)
						  	|| (brick.coords[1].x == brick.coords[2].y && -brick.coords[1].y == brick.coords[2].x)
						  	|| (brick.coords[1].x == brick.coords[3].y && -brick.coords[1].y == brick.coords[3].x))
						  
				  && (field[brick.position.y + brick.coords[2].x][brick.position.x - brick.coords[2].y] == defaultColor
						  || (brick.coords[2].x == brick.coords[0].y && -brick.coords[2].y == brick.coords[0].x)
						  	|| (brick.coords[2].x == brick.coords[1].y && -brick.coords[2].y == brick.coords[1].x)
						  	|| (brick.coords[2].x == brick.coords[2].y && -brick.coords[2].y == brick.coords[2].x)
						  	|| (brick.coords[2].x == brick.coords[3].y && -brick.coords[2].y == brick.coords[3].x))
						  
				  && (field[brick.position.y + brick.coords[3].x][brick.position.x - brick.coords[3].y] == defaultColor
						  || (brick.coords[3].x == brick.coords[0].y && -brick.coords[3].y == brick.coords[0].x)
						  	|| (brick.coords[3].x == brick.coords[1].y && -brick.coords[3].y == brick.coords[1].x)
						  	|| (brick.coords[3].x == brick.coords[2].y && -brick.coords[3].y == brick.coords[2].x)
						  	|| (brick.coords[3].x == brick.coords[3].y && -brick.coords[3].y == brick.coords[3].x))) {return true;}
		  
	  else return false;
  }
  
  /**
   * removes all full lines from the board, i.e. lines with no defaultColor)
   * 
   * @return the number of lines that could be removed
   */
  public int removeFullLines() {
      // TODO von Ihnen zu implementieren
	  int removedLines = 0;
	 
	  for(int i = 0; i < height; i++) {
		  int counter = 0;
		  for(int j = 0; j < width; j++) {
			  if(!(field[i][j] == defaultColor)) {
				  counter++;
				  if(counter == 10) {removeLine(i); removedLines++;}
			  }
		  }
	  }
      
      return removedLines;
 
  }
  
  
  /**
   * removes a single line from the board
   * 
   * @param lineIndex the index of the line
   */
  private void removeLine(int lineIndex) {
    for (int i = lineIndex; i > 0; i--) {
      for (int j = 0; j < width; j++) {
        field[i][j] = field[i - 1][j];
      }
    }
  }
}
