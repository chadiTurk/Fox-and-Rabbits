import java.awt.Graphics;
import java.util.Random;

public class Board {
	
	private final int width = 22;
	private final int height = 22;
	private int spawnX, spawnY;
	private int[][] tiles = new int[width][height];
	
	private Handler handler;
	private EntityManager entityManager;
	
	public Board(Handler handler){
		this.handler = handler;
		spawnX = generateRandomPos();
		spawnY = generateRandomPos();
		entityManager = new EntityManager(handler, new Fox(handler,spawnX,spawnY));
		loadBoard();
	}
	
	public void tick() {
		entityManager.tick();
	}
	
	public void render(Graphics g){
		for(int j=0;j<height;j++) {
			for(int i=0;i<width;i++) {
				getTile(i,j).render(g,i * Tile.getTilewidth(),j * Tile.getTilewidth());
			}
		}
		
		entityManager.render(g);
	}
	
	public Tile getTile(int x,int y){
		
		if(x < 0 || y < 0 || x >= width || y >= width)
			return Tile.getGrassTile();
		
		Tile t = Tile.getTile()[tiles[x][y]];
		
		if(t == null) {
			return Tile.getBushTile();
		}
		
		return t;
	}
	
	private void loadBoard() {
		
		loadBoarders();
		for(int i=1;i<width-1;i++) {
			for(int j=1;j<height-1;j++) {
				tiles[i][j] = 0;
			}
		}
	}
	
	private void loadBoarders() {
		loadFirstRow();
		loadFirstColumn();
		loadLastColumn();
		loadLastRow();
	}
	private void loadFirstRow() {
		for(int i=0;i<width;i++) {
			tiles[i][0] = 1;
		}
	}
	
	private void loadFirstColumn() {
		for(int j=0;j<height;j++) {
			tiles[0][j] = 1;
		}
	}
	
	private void loadLastColumn() {
		for(int j=0;j<height;j++) {
			tiles[width-1][j] = 1;
		}
	}
	
	private void loadLastRow() {
		for(int i=0;i<width;i++) {
			tiles[i][height-1] = 1;
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	public static int generateRandomPos() {
		Random r = new Random();
		int low = 40;
		int high = 651;
		int result = r.nextInt(high-low) + low;
		
		return result;
	}
	
	public boolean isFull() {
		if(entityManager.getEntities().size() >= 50)
			return true;
		return false;
	}
	
	public boolean isEmpty() {
		if(entityManager.getEntities().isEmpty())
			return true;
		return false;
	}
}
