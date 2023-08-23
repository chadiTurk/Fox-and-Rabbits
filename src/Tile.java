import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	private static Tile[] tiles = new Tile[256];
	private static Tile grassTile = new GrassTile(0);
	private static Tile bushTile = new BushTile(1);
	
	
	private static final int tileWidth = 34;
	private static final int tileHeight = 34;
	

	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture,int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public static Tile getGrassTile() {
		return grassTile;
	}
	
	public static Tile getBushTile() {
		return bushTile;
	}
	
	public static Tile[] getTile() {
		return tiles;
	}
	
	public void render(Graphics g,int x,int y){
		g.drawImage(texture,x,y,tileWidth,tileHeight,null);
	}
	
	public boolean isBarrier() {
		return false;
	}
	
	public int getId() {
		return id;
	}
	
	public static int getTilewidth() {
		return tileWidth;
	}


	public static int getTileheight() {
		return tileHeight;
	}
	
}
