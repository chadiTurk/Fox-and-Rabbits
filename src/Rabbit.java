
import java.awt.Graphics;
import java.util.Random;

public class Rabbit extends Entity{
	
	public Rabbit(Handler handler,float x, float y) {
		super(handler,x, y,34,34,8.0f);
		
		bounds.x = 10;
		bounds.y = 10;
		bounds.width = 16;
		bounds.height = 16;
		speed = 5;
	}

	@Override
	public void tick() {
		getInput();
		move();
	}
	
	@Override
	public boolean checkCollision(float xOffset, float yOffset) {
		for(Entity e : handler.getBoard().getEntityManager().getEntities()) { 
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset) )) {
				return true;
			}
		}
		return false;		
	}
	
	@Override
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
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Images.getRabbit(),(int) x, (int) y,width,height,null);
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
			
		int randomMove = generateRandomNumber();
		switch(randomMove)
		{
			case 1:
				yMove = -speed;
				break;
			case 2:
				yMove = speed;
				break;
			case 3:
				xMove = -speed;
				break;
			case 4:
				xMove = speed;
				break;
		}		
	
	}
	
	public int generateRandomNumber() {
		Random r = new Random();
		int low = 1;
		int high = 5;
		int result = r.nextInt(high-low) + low;
		
		return result;
	}
}
