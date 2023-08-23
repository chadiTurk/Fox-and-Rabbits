
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Game implements Runnable {
    
    
    private Display display;
    private int width,height;
    private String title;
    
    private boolean running = false;
    private Thread thread;
    
    
    private BufferStrategy bs;
    private Graphics g;
    
    private Board board;
    
    private Handler handler;
  
   
    public Game(String title, int width, int height){
        this.width = width;
        this.height = height;
        this.title = title;
    }
    
    private void init(){
       
         display = new Display(title,width,height);
         
         
     	 new Images();
         handler = new Handler(this);
         board = new Board(handler);
         handler.setBoard(board);
         display.getFrame().addKeyListener(handler);
    }
    
    private void tick(){
    	handler.tick();
    	
    	handler.getBoard().tick();
    }
    
    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g = bs.getDrawGraphics();

        handler.getBoard().render(g);
 
        bs.show();
        g.dispose();
        
    }
    public void run(){
  
       init();
       int fps = 60;
       double timePerTick = 1000000000 /fps;
       double delta = 0;
       long now;
       long lastTime = System.nanoTime();
       long timer = 0;

       while(running){
           
    	   now = System.nanoTime();
    	   delta += (now - lastTime) / timePerTick;
    	   timer += now - lastTime;
    	   lastTime = now;
    	   
    	   if(delta >= 1){
    		   tick();
               render();
               delta--;
    	   }
           
    	   if(timer >= 1000000000) {
    		   timer = 0;
    	   }
    	   
    	   render();
    	   endCondition();
    	   
       }
       
    }
 
    public synchronized void start(){
        if(running){
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop(){
        if(running == false){
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
	
	public void endCondition() {
		 if(handler.getBoard().isFull()){
  		   	 showLoss();
  	    	 stop();   	
  	   }
		 else if(handler.getBoard().isEmpty()) {
			 showWin();
			 stop();
		 }
	}
	
	private void showLoss() {
		 JOptionPane.showMessageDialog(null, "There are 50 rabbits on the board!", "Game over!", JOptionPane.INFORMATION_MESSAGE);
	}
	
    
	private void showWin() {
		 JOptionPane.showMessageDialog(null, "All rabits have been eliminated!", "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
	}

}
