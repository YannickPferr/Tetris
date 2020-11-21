package tetris;

import java.awt.Color;
import tetris.bricks.Brick;

/**
 * This class specifies a board for the Tetris game
 * and its behavior. 
 * 
 * @author Kai H&ouml;ver (kai@tk.informatik.tu-darmstadt.de)
 */
public class TetrisBoard {
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
  public TetrisBoard(int boardHeight, int boardWidth, Color backgroundColor) {
    height = boardHeight;
    width = boardWidth;
    defaultColor = backgroundColor;
    initBoard();
  }
  
  /**
   * Initializes the board with empty fields
   */
  private void initBoard() {
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
	Coordinate[] coords = brick.coords;
	Coordinate position = brick.position;
    position.x += 1;
    for (int i = 0; i < coords.length; i++) {
      // determine the appropriate position for this element
      // and assign the brick's color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = brick.getColor();
    }
  }
  
  /**
   * Checks if a brick can be moved to the right
   * 
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickRight(Brick brick) {
	Coordinate position = brick.position;
    if (position.x + brick.maxX() >= width - 1) 
      return false; // does not fit!

    // first, remove the "original" brick
    removeBrick(brick);
    
    Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++) {
      int x = position.x + coords[i].x + 1;
      int y = position.y + coords[i].y;
      if (x < width && field[y][x] != defaultColor) {
        addBrick(brick);
        return false;
      }
    }
    return true;
  }
  
  /**
   * Moves a brick one step to the left
   * @param brick the Brick to be moved
   */
  public void moveBrickLeft(Brick brick) {
	Coordinate[] coords = brick.coords;
	Coordinate position = brick.position;
    // check if it is possible to move left  
    position.x -= 1;
    for (int i = 0; i < coords.length; i++) {
      // determine the appropriate position for this element
      // and assign the brick's color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = brick.getColor();
    }
  }
  
  /**
   * Checks if a brick can be moved to the left
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickLeft(Brick brick) {
	Coordinate position = brick.position;
    if (position.x + brick.minX() <= 0) 
      return false;
    removeBrick(brick);
    Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++) {
      int x = position.x + coords[i].x - 1;
      int y = position.y + coords[i].y;
      if (field[y][x] != getDefaultColor()) {
        addBrick(brick);
        return false;
      }
    }
    return true;
  }

  /**
   * Moves a brick one step down
   * 
   * @param brick the Brick to be moved
   */
  public void moveBrickDown(Brick brick) {
	Coordinate position = brick.position;
	Coordinate[] coords = brick.coords;
    position.y += 1;
    for (int i = 0; i < coords.length; i++) {
      // determine the appropriate position for this element
      // and assign the brick's color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = brick.getColor();
    }
  }
  
  /**
   * Checks if a brick can be moved one step down
   * @param brick the brick to be moved
   * @return true if brick can be moved, otherwise false
   */
  public boolean canMoveBrickDown(Brick brick) {
	Coordinate position = brick.position;
    boolean aboveBottom = position.y + brick.maxY() < height-1;
    if (!aboveBottom)
      return false;
    // Check for the lower piece of the brick if there is a brick below    
    removeBrick(brick);
    Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++) {
      int x = position.x + coords[i].x;
      int y = position.y + coords[i].y + 1;
      if (field[y][x] != getDefaultColor()) {
        addBrick(brick);
        return false;
      }
    }
    return true; 
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
    removeBrick(brick);
    brick.rotateShapeRight();
    Coordinate position = brick.position;
    Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++) {
      // determine the appropriate position for this element
      // and assign the brick's color
      field[position.y + coords[i].y][position.x + coords[i].x] 
    		  = brick.getColor();
    }
  }
  
  /**
   * Checks if a brick can be rotated clockwise
   * @param brick the brick to be rotated
   * @return true if brick can be rotated, otherwise false
   */
  public boolean canRotateBrick(Brick brick) {
    removeBrick(brick);
    brick.rotateShapeRight();
    Coordinate position = brick.position;
    Coordinate[] coords = brick.coords;
    for (int i = 0; i < coords.length; i++) {
      int x = position.x + coords[i].x;
      int y = position.y + coords[i].y + 1;
      if (x < 0 || x >= width || y < 0 || y >= height) {
        brick.rotateShapeLeft();
        return false;
      }
      if (field[y][x] != getDefaultColor() ) {
        brick.rotateShapeLeft();
        return false;
      }
    }
    brick.rotateShapeLeft();
    return true;
  }
  
  /**
   * removes all full lines from the board, i.e. lines with no defaultColor)
   * 
   * @return the number of lines that could be removed
   */
  public int removeFullLines() {
    int noOfLinesRemoved = 0;
    for (int i = height - 1; i >= 0; i--) {
      boolean lineIsFull = true;
      for (int j = 0; j < width; j++) {
    	// has background color => line cannot be full
        if (field[i][j].equals(getDefaultColor())) {
          lineIsFull = false;
          break;
        }
      }
      if (lineIsFull) {
        removeLine(i);
        noOfLinesRemoved++;
        i++;
      }
    }
    return noOfLinesRemoved;
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
