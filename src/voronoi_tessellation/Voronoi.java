package voronoi_tessellation;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
 
public class Voronoi extends JFrame {
	static double p = 3;
	static BufferedImage I;
	static int color[], cells = 100, size = 750;
	Seed[] seeds = new Seed[cells];
	String line = "";
	int counter = 0;
	int count = 0;
	int[] xCoords = new int[147];
	int[] yCoords = new int[147];
	int latitude = 0; 
	int longitude = 0;
	
 
	public Voronoi() throws IOException {
		super("Voronoi Diagram");
		setBounds(0, 0, size, size);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		I = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);

		// Get input
		// Get database
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter file name of your csv data file: ");
		String database = scanner.next();
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\eclipse-workspace\\voronoi_tessellation\\src\\databases\\" + database + ".csv"));
							
		// Get zoom amount
		System.out.println("Enter zoom amount for the latitude and longitude plot points of your data: ");
		int zoomAmount = scanner.nextInt();
		
		// Output image file name
		System.out.println("Enter file name of output image: ");
		String outputFileName = scanner.next();
		
		populate_database(br);
		draw_ellipses(zoomAmount);

 
		try {
			ImageIO.write(I, "png", new File("C:\\Users\\Admin\\eclipse-workspace\\voronoi_tessellation\\src\\images\\" + outputFileName + ".png"));
		} catch (IOException e) {
 
		}
 
	}
	
	public void draw_ellipses(int zoomAmount) {
		int n = 0;
		Random rand = new Random();
		
		color = new int[cells];
		for (int i = 0; i < cells; i++) {
			color[i] = rand.nextInt(16777215);
			seeds[i] = new Seed(Math.abs(xCoords[i]) * zoomAmount, Math.abs(yCoords[i]) * zoomAmount, color[i]);
 
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
	}
	
	public void populate_database(BufferedReader br) throws NumberFormatException, IOException {
		while (((line = br.readLine()) != null) && counter < 147) {
		    // use comma as separator
		    String[] cols = line.split(",");
		    
		    if (count == 0) {
		        for (int i = 0; i < cols.length; i++) {

		        	if (cols[i].toUpperCase().contains("RECLAT") || cols[i].toUpperCase().contains("LAT")) {
		        		latitude = i;

		        	} 
		        	if (cols[i].toUpperCase().contains("RECLONG") || cols[i].toUpperCase().contains("LONG")) {
		        		longitude = i;
		        	}
		        }
		    }

		    if (count != 0) {
		    	yCoords[counter] = (int) Math.round(Double.parseDouble(cols[latitude]));
		    	xCoords[counter] = (int) Math.round(Double.parseDouble(cols[longitude]));
		    	counter++;
		    }
		    count++;
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
 
	public static void main(String[] args) throws IOException {
		new Voronoi().setVisible(true);
	}
}