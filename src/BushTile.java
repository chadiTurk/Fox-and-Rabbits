
public class BushTile extends Tile{

	public BushTile(int id) {
		super(Images.getBush(), id);
	}
	

	@Override
	public boolean isBarrier() {
		return true;
	}
	
}
