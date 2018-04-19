import java.awt.*;

public class MapGenerator {
	public int map[][];
	public int brickWidth,brickHeight;

	public MapGenerator(int rows,int coloumns) {
		map = new int[rows][coloumns];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=1;
			}
		}
		brickWidth = 540/coloumns;
		brickHeight = 150/rows;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]==1) {
					g.setColor(Color.white);
					g.fillRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 80, i* brickHeight + 50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setmapvalue(int value,int x,int y) {
		map[x][y] = value;
	}

}
