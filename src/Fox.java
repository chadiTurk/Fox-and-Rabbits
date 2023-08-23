
import java.awt.Graphics;

public class Fox extends Entity{
	private int distance;
	 
	public Fox(Handler handler,int x, int y) {
		super(handler,x, y,34,34,5.0f);
		
		bounds.x = 16;
		bounds.y = 16;
		bounds.width = 16;
		bounds.height = 16;
		this.distance = 0;
	}

	@Override
	public void tick() {
		getInput();
		move();	
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.up) {
			distance +=1;
			yMove = -speed;
		}
		else if(handler.down) {
			distance +=1;
			yMove = speed;
		}
			
		else if(handler.left) {
			distance +=1;
			xMove = -speed;
		}
			
		else if(handler.right) {
			distance +=1;
			xMove = speed;
		}

	}
	
	public boolean isMoving() {
		if(handler.up || handler.down || (handler.left) 
				|| handler.right) {
			return true;
		}
		
		return false;
	}
	
	public boolean hasMovedThreeTimes() {
		if(this.distance == 20) {
			this.distance = 0;
			return true;
		}
		
		return false;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(Images.getFox(),(int) x,(int) y,width,height,null);	
	}
	
}
