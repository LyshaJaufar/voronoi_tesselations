package voronoi_tessellation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
 
import javax.imageio.ImageIO;
import javax.swing.JFrame;
 
public class Voronoi extends JFrame {
	static double p = 3;
	static BufferedImage I;
	static int color[], cells = 100, size = 700;
	Seed[] seeds = new Seed[cells];;
 
	public Voronoi() {
		super("Voronoi Diagram");
		setBounds(0, 0, size, size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
		int n = 0;
		Random rand = new Random();
		
		color = new int[cells];
		for (int i = 0; i < cells; i++) {
			color[i] = rand.nextInt(16777215);
			seeds[i] = new Seed(rand.nextInt(size), rand.nextInt(size), color[i]);
 
		}
		
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				n = 0;
				for (int i = 0; i < cells; i++) {
					if (distance(seeds[i].x, x, seeds[i].y, y) < distance(seeds[n].x, x, seeds[n].y, y)) {
						n = i;
					}
				}
				I.setRGB(x, y, color[n]);
			}
		}
 
		Graphics2D g = I.createGraphics();
		g.setColor(Color.BLACK);
		for (int i = 0; i < cells; i++) {
			g.fill(new Ellipse2D .Double(seeds[i].x - 2.5, seeds[i].y - 2.5, 5, 5));
		}
 
		try {
			ImageIO.write(I, "png", new File("voronoi.png"));
		} catch (IOException e) {
 
		}
 
	}
 
	public void paint(Graphics g) {
		g.drawImage(I, 0, 0, this);
	}
 
	static double distance(int x1, int x2, int y1, int y2) {
		double d;
	    d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)); // Euclidian
	//  d = Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan
	//  d = Math.pow(Math.pow(Math.abs(x1 - x2), p) + Math.pow(Math.abs(y1 - y2), p), (1 / p)); // Minkovski
	  	return d;
	}
 
	public static void main(String[] args) {
		new Voronoi().setVisible(true);
	}
}