import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
	
	protected float x,y;
	protected float speed;
	protected float xMove, yMove;
	protected int width,height;
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active = true;
	
	public Entity(Handler handler,float x,float y, int width, int height,float speed) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		xMove = 0;
		yMove = 0;
		bounds = new Rectangle(0,0,width,height);
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	public void move(){
		if(checkCollision(xMove,0f)) {
		}
		else if(!checkCollision(xMove,0f)) {
			moveX();
		}
			
		if(checkCollision(0f,yMove )) {
		}
		else if(!checkCollision(xMove,0f)) {
			moveY();	
		}
			
	}
	
	public void moveX() {
		if(xMove > 0) { //Right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.getTilewidth();
			
			if(!collision(tx,(int) (y + bounds.y) / Tile.getTileheight()) && 
					!collision(tx, (int) (y + bounds.y + bounds.height) / Tile.getTileheight())) {
				x += xMove;
			}
		}
		else if(xMove < 0 ) {//Left
			int tx = (int) (x + xMove + bounds.x) / Tile.getTilewidth();
			
			if(!collision(tx,(int) (y + bounds.y) / Tile.getTileheight()) && 
					!collision(tx, (int) (y + bounds.y + bounds.height) / Tile.getTileheight())) {
				x += xMove;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) {//Up
			int ty = (int) (y + yMove + bounds.y) / Tile.getTileheight();
			
			if(!collision((int) (x + bounds.x) / Tile.getTilewidth(), ty) &&
				!collision((int) (x + bounds.x + bounds.width) / Tile.getTilewidth(), ty)) {
				y += yMove;
			}
		}else if(yMove > 0) {//Down
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.getTileheight();
			
			if(!collision((int) (x + bounds.x) / Tile.getTilewidth(), ty) &&
				!collision((int) (x + bounds.x + bounds.width) / Tile.getTilewidth(), ty)) {
				y += yMove;
			}
		}
	}
	protected boolean collision(int x,int y) {
		return handler.getBoard().getTile(x, y).isBarrier();	
	}
	
	public boolean checkCollision(float xOffset, float yOffset) {
		for(Entity e : handler.getBoard().getEntityManager().getEntities()) { 
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset) )) {
				e.active = false;
				return true;
			}	
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x  + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}

	public boolean isActive() {
		return active;
	}

	
}
