package tetris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import tetris.bricks.*;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.SwingTimer;

public class Game extends GraphicsProgram {

	private static final long serialVersionUID = 1L;
	private final int COLUMNS = 10;
	private final int ROWS = 18;
	private final int TIMER_RATE = 1000;
	private final Color DEFAULT_COLOR = Color.BLACK;

	public double width;
	public double height;
	public double rectX;
	public double rectY;
	public double rectWidth;
	public double rectHeight;
	public int fontSizeDouble;
	public String fontSize;

	private Board board;
	
	private SwingTimer timer;
	public static boolean gameRunning;
	private double sqSize;
	
	public int score = 0;
	public String stringHighscore = readHighscore();
	public int readHighscore = Integer.parseInt(stringHighscore);
	public int highscore = readHighscore;
	

	public PausablePlayer p;
	public FileInputStream song;

	private Brick currentBrick;
	public Brick nextBrick;
	
	public boolean gameRestart = false;
	
	GRect restart;
	
	public Game(){
		readSong();
		readHighscore();
	}
	
	public String readHighscore(){
		String line = null;
		try {
		FileInputStream fis = new FileInputStream(new File("examples/Highscore.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(fis));
		
		line = bf.readLine();
		bf.close();
		fis.close();
		} catch (IOException exception) {
			System.out.println("ERROR! Could not read file");
			exception.printStackTrace();
		}
		return line;
	}
	
	public void writeHighscore(){
		String writtenHighscore = String.valueOf(highscore);
		try {
	        BufferedWriter b = new BufferedWriter (new FileWriter ("examples/Highscore.txt"));
	          b.write(writtenHighscore);
	          b.newLine();
	          b.close();
	      }
	      catch (IOException e) {
	        System.out.println("Fehler: "+e.toString());
	      }

	}
	
	public void readSong(){
		try {
			FileInputStream filename = new FileInputStream("examples/Tetris.mp3"); 
		    PausablePlayer player = new PausablePlayer(filename); 
		    song = filename;
		    p = player;
		    }
			catch (final Exception e) {
		        throw new RuntimeException(e);
		    }
	}
	public void displayBoard() {
		removeAll();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				double x = j * sqSize;
				double y = i * sqSize;
				GRect sq = new GRect(x, y, sqSize, sqSize);
				sq.setFilled(true);
				sq.setFillColor(board.field[i][j]);
				add(sq);
				
			}
		}
		printLabels();
		
		if(gameRestart) {
		gameRestart = false;
		gameRunning = false;
		timer.stop();
		initBoard();
		displayBoard();
		printRestart();
		}
		
	}

	
	private void newBrick() {	
		currentBrick = nextBrick;
		nextBrick = createNewBrick();
		
		currentBrick.position.x = COLUMNS / 2-1;
		currentBrick.position.y = currentBrick.maxY();
		for (int i = 0; i < currentBrick.coords.length; i++) {
			int y = currentBrick.position.y + currentBrick.coords[i].y;
			int x = currentBrick.position.x + currentBrick.coords[i].x;
			// Check if brick can be placed
			if(board.field[y][x]== board.getDefaultColor()) 
				board.field[y][x] = currentBrick.getColor();
			
			else {
				
				if(score > highscore){
					highscore = score; 
					writeHighscore();
					System.out.println("New Highscore!");
					System.out.println("You scored: " + score);}
				
				else {
					System.out.println("You scored: " + score); 
					System.out.println("Highscore: " + highscore);}
				
				gameRestart = true;
				break;
			}
		}
		
		displayBoard();
	}

	/**
	 * Chooses randomly a new brick from the set of brick types 
	 * @return a new brick instance of random type
	 */
	public Brick createNewBrick() {
		Brick brick = null;
		int randomBrickIndex = (int) (Math.random() * BrickType.values().length);
		String className = BrickType.values()[randomBrickIndex].toString();
		try {
			Class<?> c = Class.forName("tetris.bricks."+className);
			brick = (Brick) c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return brick;
	}
	
	public void printNextBrick() {
		if(nextBrick.getColor() == Color.RED) printIBrick();
		if(nextBrick.getColor() == Color.MAGENTA) printLBrick();
		if(nextBrick.getColor() == Color.YELLOW) printOBrick();
		if(nextBrick.getColor() == Color.GREEN) printSBrick();
		if(nextBrick.getColor() == Color.BLUE) printTBrick();
	}
	
	public void printIBrick() {
		double y = height * 0.1; 
		GRect sq = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y, sqSize, sqSize);
		sq.setFilled(true);
		sq.setFillColor(Color.RED);
		add(sq);
		
		GRect sq2 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + sqSize, sqSize, sqSize);
		sq2.setFilled(true);
		sq2.setFillColor(Color.RED);
		add(sq2);
		
		GRect sq3 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq3.setFilled(true);
		sq3.setFillColor(Color.RED);
		add(sq3);
		
		GRect sq4 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq4.setFilled(true);
		sq4.setFillColor(Color.RED);
		add(sq4);
		
	}
	
	public void printLBrick(){
		double y = height * 0.1; 
		GRect sq = new GRect(rectX + (rectWidth/2 - sqSize/2) - sqSize, rectY + 3*y + sqSize, sqSize, sqSize);
		sq.setFilled(true);
		sq.setFillColor(Color.MAGENTA);
		add(sq);
		
		GRect sq2 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + sqSize, sqSize, sqSize);
		sq2.setFilled(true);
		sq2.setFillColor(Color.MAGENTA);
		add(sq2);
		
		GRect sq3 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq3.setFilled(true);
		sq3.setFillColor(Color.MAGENTA);
		add(sq3);
		
		GRect sq4 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq4.setFilled(true);
		sq4.setFillColor(Color.MAGENTA);
		add(sq4);
	}
	
	public void printOBrick(){
		double y = height * 0.1; 
		GRect sq = new GRect(rectX + (rectWidth/2 - sqSize/2) + sqSize, rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq.setFilled(true);
		sq.setFillColor(Color.YELLOW);
		add(sq);
		
		GRect sq2 = new GRect(rectX + (rectWidth/2 - sqSize/2) + sqSize, rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq2.setFilled(true);
		sq2.setFillColor(Color.YELLOW);
		add(sq2);
		
		GRect sq3 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq3.setFilled(true);
		sq3.setFillColor(Color.YELLOW);
		add(sq3);
		
		GRect sq4 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq4.setFilled(true);
		sq4.setFillColor(Color.YELLOW);
		add(sq4);
	}
	
	public void printSBrick(){
		double y = height * 0.1; 
		GRect sq = new GRect(rectX + (rectWidth/2 - sqSize/2) + sqSize, rectY + 3*y + sqSize, sqSize, sqSize);
		sq.setFilled(true);
		sq.setFillColor(Color.GREEN);
		add(sq);
		
		GRect sq2 = new GRect(rectX + (rectWidth/2 - sqSize/2) + sqSize, rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq2.setFilled(true);
		sq2.setFillColor(Color.GREEN);
		add(sq2);
		
		GRect sq3 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq3.setFilled(true);
		sq3.setFillColor(Color.GREEN);
		add(sq3);
		
		GRect sq4 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq4.setFilled(true);
		sq4.setFillColor(Color.GREEN);
		add(sq4);
	}
	
	public void printTBrick(){
		double y = height * 0.1; 
		GRect sq = new GRect(rectX + (rectWidth/2 - sqSize/2) - sqSize, rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq.setFilled(true);
		sq.setFillColor(Color.BLUE);
		add(sq);
		
		GRect sq2 = new GRect(rectX + (rectWidth/2 - sqSize/2) + sqSize, rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq2.setFilled(true);
		sq2.setFillColor(Color.BLUE);
		add(sq2);
		
		GRect sq3 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 2*sqSize, sqSize, sqSize);
		sq3.setFilled(true);
		sq3.setFillColor(Color.BLUE);
		add(sq3);
		
		GRect sq4 = new GRect(rectX + (rectWidth/2 - sqSize/2), rectY + 3*y + 3*sqSize, sqSize, sqSize);
		sq4.setFilled(true);
		sq4.setFillColor(Color.BLUE);
		add(sq4);
	}

	/*
	 * Defines the keys in the game.
	 * @see acm.program.Program#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 37:					// key left
			moveCurrentBrickLeft(); break;
		case 38:					// key up
			rotateCurrentBrick(); break;
		case 39:					// key right
			moveCurrentBrickRight(); break;	
		case 40:					// key down
			board.letBrickFallDown(currentBrick); break;
		case 80: 					// p for pause the game
			pauseGame(); 
			break;
		case 73: // i Taste for debugging TODO: remove
			System.out.println("current: (" +currentBrick.position.x +","+currentBrick.position.y +")");
			System.out.println("minY:"+currentBrick.minY()+", maxY:"+currentBrick.maxY());
			System.out.println("minX:"+currentBrick.minX()+", maxX:"+currentBrick.maxX());
			for(int i=0; i<currentBrick.coords.length;i++) {
				if (i==currentBrick.coords.length-1)	System.out.println("("+currentBrick.coords[i].x +","+currentBrick.coords[i].y +")");
				else System.out.print("("+currentBrick.coords[i].x +","+currentBrick.coords[i].y +")\t"); 
			}
			 break;
		}
	}

	private void pauseGame() {
		if(gameRunning)	{
			timer.stop();
			gameRunning = false;
	         p.pause();
		} else {
			gameRunning = true;
			timer.restart();
			try{
	         p.resume(); }
			catch (final Exception e) {
		        throw new RuntimeException(e);
		    }    
	        
		}
	}

	private void moveCurrentBrickLeft() {
		// check if it is possible to move left
		if (board.canMoveBrickLeft(currentBrick)) {
			board.moveBrickLeft(currentBrick);
			displayBoard();
		}
	}

	private void moveCurrentBrickRight() {
		// check if it is possible to move right
		if(board.canMoveBrickRight(currentBrick)) {
			board.moveBrickRight(currentBrick); 
			displayBoard();
			
		}
	}
	
	private void moveCurrentBrickDown() {
		// check if brick can move down any more
		//check if brick has not reached bottom of board yet
		
		if(board.canMoveBrickDown(currentBrick)) {
			board.moveBrickDown(currentBrick);
			displayBoard();
		} else {
			int noOfRemoveLines = board.removeFullLines();
			if (noOfRemoveLines>0)
				{score += Math.pow(noOfRemoveLines,2);}
			newBrick();
		}
	}

	private void rotateCurrentBrick() {
		if( board.canRotateBrick(currentBrick) ) {
			board.rotateBrick(currentBrick);
			displayBoard();
		}
	}

	//private void restartGame() {
		//gameRunning = false;
		//timer.stop();
	//	initBoard();
		//startGame();
	//}

	
	public void init() {
		printWelcome();
		initBoard();
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveCurrentBrickDown();
			}
		};
		timer = new SwingTimer(TIMER_RATE, listener);
		timer.setDelay(1000);
		// add listener for keyboard events
		addKeyListeners();
	}

	
	private void initBoard() {
		board = new Board(ROWS, COLUMNS, DEFAULT_COLOR);
	}
	
	
	public void initSize(){
		sqSize = (double) getHeight() / ROWS;
		width = getWidth();
		height = getHeight();
		rectX = width * 0.37;
		rectY = height * 0.05;
		rectWidth = width * 0.14;
		rectHeight = height * 0.08;
		fontSizeDouble = (int) -(width * 0.015);
		fontSize = String.valueOf(fontSizeDouble);
	}
	
	private void printWelcome() {
		GLabel label = new GLabel("GdI1 Tetris");
		label.setFont("SansSerif-20");
		GLabel label2 = new GLabel("Start with mouse click");
		label2.setFont("SansSerif-15");
		double x = (getWidth() - label.getWidth()) / 2;
		double y = (getHeight() + label.getAscent()) / 2;
		add(label, x, y);
		add(label2,x, y+20);
	}
	

	private void printRestart() {
		double y = height * 0.1;
		restart = new GRect(rectX, rectY + 2*y, rectWidth, rectHeight);
		restart.setFilled(true);
		restart.setFillColor(Color.GREEN);
		add(restart);
		restart.addMouseListener(this);
		
		GLabel restartLabel = new GLabel("Restart Game");
		restartLabel.setFont("SansSerif" + fontSize);
		double restartMidX = restart.getX() + (restart.getWidth() / 2 - restartLabel.getWidth() / 2); 
		double restartMidY = restart.getY() + (restart.getHeight() / 2 + restartLabel.getHeight()/ 4); 
		add(restartLabel, restartMidX, restartMidY);
	}

	
	public void printLabels() {
		setBackground(Color.BLACK);
		
		double y = height * 0.1;
		GRect score = new GRect(rectX, rectY , rectWidth, rectHeight);
		score.setFilled(true);
		score.setFillColor(Color.CYAN);
		add(score);
		
		GLabel scoreLabel = new GLabel("Score: " + this.score);
		scoreLabel.setFont("SansSerif" + fontSize);
		double scoreMidX = score.getX() + ((score.getWidth() / 2) - (scoreLabel.getWidth() / 2)); 
		double scoreMidY = score.getY() + (score.getHeight() / 2 + scoreLabel.getHeight()/ 4); 
		add(scoreLabel, scoreMidX, scoreMidY);
		
		GRect highscore = new GRect (rectX, rectY + y, rectWidth, rectHeight);
		highscore.setFilled(true);
		highscore.setFillColor(Color.ORANGE);
		add(highscore);
		
		GLabel highscoreLabel = new GLabel("Highscore: " + this.highscore);
		highscoreLabel.setFont("SansSerif" + fontSize);
		double highscoreMidX = highscore.getX() + (highscore.getWidth() / 2 - highscoreLabel.getWidth() / 2); 
		double highscoreMidY = highscore.getY() + (highscore.getHeight() / 2 + highscoreLabel.getHeight()/ 4); 
		add(highscoreLabel, highscoreMidX, highscoreMidY);
		
		printNextBrick();
		
	}
	

	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == restart){
			startGame();
		}
	}

 
	public void run() {
		waitForClick();
		initSize();
		startGame();
	}
	
	
	private void startGame() {
		nextBrick = createNewBrick();
		newBrick();
		try {
			p.play();
		    }
			catch (final Exception e) {
		        throw new RuntimeException(e);
		    }
		timer.start();
		gameRunning = true;
	}
		

	public static void main(String[] args) {
		new Game().start(args);
		
	}
}
