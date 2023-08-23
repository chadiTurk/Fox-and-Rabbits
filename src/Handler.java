import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Handler implements KeyListener{
	
	private Game game;
	private Board board;
	private boolean[] keys;
	protected boolean up,down,left,right;
	
	public Handler(Game game) {
		this.game = game;
		keys = new boolean[256];
	}

	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {	
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	public void setBoard(Board board) {
		this.board = board;
	}
	
	public Board getBoard() {
		return board;
	}
	
}
