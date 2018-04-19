import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Gameplay extends JPanel implements ActionListener,KeyListener {
	private boolean play = false;
	private int score;
	private int totalbricks;
	
	private Timer t,r;
	private int delay = 15;
	
	private int playerposX;
	private int ballposX;
	private int ballposY;
	private int balldirX;
	private int balldirY;
	
	private MapGenerator m;
	
	
	public Gameplay() {
		addKeyListener(this);
		readyplayerone();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t = new Timer(delay,this);
		t.start();
	}
	
	public void paint(Graphics g) {
		//Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//Score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,20));
		g.drawString(""+score, 590, 30);
		
		
		//Map
		m.draw((Graphics2D)g);
		
		//Border
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(691, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		
		//Player
		g.setColor(Color.green);
		g.fillRect(playerposX, 550, 100, 8);
		
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalbricks==0) {
			balldirX=balldirY=0;
			g.setColor(Color.green);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("YOU WIN", 240, 300);
			g.drawString("SCORE : "+ score, 250, 340);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press 'Enter' To Continue", 220, 380);
		}
		
		if(ballposY>570) {
			balldirX=balldirY=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("GAME OVER", 228, 300);
			g.drawString("SCORE : "+ score, 250, 340);
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("Press 'Enter' To Continue", 220, 380);
		}
		
		if(play==false) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("DEATH TO BRICKS",185, 300);
			g.setColor(Color.BLUE);
			g.setFont(new Font("serif",Font.BOLD,28));
			g.drawString("READY PLAYER ONE",180, 340);
		}
		g.dispose();
	}
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(playerposX>=590)
				playerposX=590;
			else
				moveRight();
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(playerposX<=3)
				playerposX=3;
			else
				moveLeft();
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			play = false;
			readyplayerone();
		}
	}

	public void keyReleased(KeyEvent e) {}

	public void keyTyped(KeyEvent e) {}

	public void actionPerformed(ActionEvent e) {
		t.start();
		if(play) {
			if(new Rectangle(playerposX, 550, 100, 1).intersects(ballposX, ballposY, 20, 20)&& ballposY<551) {
				balldirY = -balldirY;
			}
			A:for(int i=0;i<m.map.length;i++) {
				for(int j=0;j<m.map[0].length;j++) {
					if(m.map[i][j]>0) {
						int brickposX = j* m.brickWidth + 80;
						int brickposY = i* m.brickHeight +50;
						int brickwidth = m.brickWidth;
						int brickheight = m.brickHeight;
						
						Rectangle brickrect = new Rectangle(brickposX,brickposY,brickwidth,brickheight);
						Rectangle ballrect = new Rectangle(ballposX,ballposY,20,20);
						
						if(ballrect.intersects(brickrect)) {
							m.setmapvalue(0, i, j);
							totalbricks--;
							score+=5;
							if(ballposX + 19 <= brickposX || ballposX +1 >= brickposX+brickwidth) {
								balldirX = -balldirX;
							}else {
								balldirY = -balldirY;
							}
							
							break A;
						}
					}
				}
			}
			ballposX+=balldirX;
			if(ballposX<0 || ballposX>670)
				balldirX = -balldirX;
			ballposY+=balldirY;
			if(ballposY<0)
				balldirY = -balldirY;
		}
		repaint();
	}
	
	public void moveRight() {
		play = true;
		playerposX+=15;
	}
	
	public void moveLeft() {
		play = true;
		playerposX-=15;
	}
	
	public void readyplayerone() {
		playerposX = 350;
		ballposX = 320;
		ballposY = 210;
		balldirX = -3;
		balldirY = +3;
		play = false;
		score = 0;
		totalbricks = 21;
		m = new MapGenerator(3,7);
	}
	
}
