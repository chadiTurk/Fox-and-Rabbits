import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class EntityManager {
	
	private Handler handler;
	private Fox fox;
	private ArrayList<Entity> entities;
	private int spawnX,spawnY;
	
	public EntityManager(Handler handler, Fox fox){
		this.handler = handler;
		this.fox = fox;
		entities = new ArrayList<>();
		this.fox = fox;
		initBoard();
	}
	
	public void tick() {
		fox.tick();
		tickEntities();
		eatRabbit();
	}
	
	public void render(Graphics g) {
		for (Entity e: entities) {
			 e.render(g);
		}
		fox.render(g);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public static int generateRandomPos() {
		Random r = new Random();
		int low = 40;
		int high = 651;
		int result = r.nextInt(high-low) + low;
		
		return result;
	}
	
	private void tickEntities(){
		if(fox.isMoving()) {
			for (int i=0;i<entities.size();i++) {
				 Entity e = entities.get(i);
				 e.tick();	
			}
		}
		
		if(fox.hasMovedThreeTimes()) {
			doubleRabbits();
		}
	}
	
	private void eatRabbit() {
		for (int i=0;i<entities.size();i++) {
			 	Entity e = entities.get(i);
			 if(!e.isActive())
				 entities.remove(e);
		}
	}
	
	private void addRabbit() {
		spawnX = generateRandomPos();
		spawnY = generateRandomPos();
		entities.add(new Rabbit(handler,spawnX,spawnY));
	}
	
	private void doubleRabbits() {
		int size = entities.size();
		if(entities.size() < 50) {
			for(int i=0;i<size;i++) {
				if(entities.size()<50)
					addRabbit();
			}
		}
	}
	
	private void initBoard() {
		for(int i=0;i<3;i++)
			addRabbit();
	}
	
}
