//File: Pong.java
//Date: 21 Oct 06
/* Notes:
 * http://java.sun.com/docs/books/tutorial/reallybigindex.html
 * http://java.sun.com/docs/books/tutorial/deployment/applet/index.html
 */

package pong;

import java.applet.*;
import java.awt.*;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

/**
* The main entry point of the Pong applet.
* @author Dean Rather
*/
public class Pong extends Applet implements Runnable{
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			ATTRIBUTES			////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////// 		CONSTANTS	////////////////////////////
	
	////////////////////////////// 		CLASS		////////////////////////////
	Thread t;
	public static final double mod = 1 * 10 / 2;
	
	int width = 700;
	int height = 700;
	public static int margin = 20;
	
	int i;
	
	Image offscreenImage;
	Graphics offscreenGraphics;
	
	char inChar =' ';
	String currentmsg = "";
	
	Game game;
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			METHODS				////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public void trace(String msg){System.out.println(msg);}
	
	public void init(){
		trace("INIT method called");
		
		// GET playerlist
		// ADD me
		// PUT playerlist
		
		
		// this is called when the applet starts
		t = new Thread(this);
		t.start();
		
		i = 0;
//		
//		AppletContext a = getAppletContext();
//		URL url;
//		try {
//			url = new URL("http://www.officewarz.com/ajax/app/console.php?request=bW9kdWxlPXRlc3QmZnVuY3Rpb249d3JpdGUmYXJncz1kaXJlY3Rvcnk9Li4vLi4vLi4vc2FuZGJveC98ZmlsZW5hbWU9dGVzdC50eHR8bW9kZT13fGNvbnRlbnQ9V09BSC4gSVQgV09SS0VELiBBV0VTT01FIQ==");
//			a.showDocument(url);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		offscreenImage = createImage(width, height);
		offscreenGraphics = offscreenImage.getGraphics();
		trace("system started");
		game = new Game();
	}
	
	public void run(){
		while(true){
			repaint();
			try{
				t.sleep(1000 * 2);
			}catch(InterruptedException e){}
		}
	}
	
	public void destroy(){
		trace("DESTROY method called");
		
		// GET playerlist
		// REMOVE me
		// PUT playrlist
		
		// this method is called when the applet closes.
	}
	
	public void paint(Graphics g){
		// clear back buffer
		// Background
		offscreenGraphics.setColor(Color.black);
		offscreenGraphics.fillRect(0, 0, width, height);

		/*
		// Board
		offscreenGraphics.setColor(Color.gray);
		offscreenGraphics.fillRect(
				(int)game.board.x,
				(int)game.board.y,
				(int)game.board.width,
				(int)game.board.height);
		
		// Ball
		offscreenGraphics.setColor(Color.white);
		offscreenGraphics.fillOval(
				(int)game.ball.x, 
				(int)game.ball.y,
				(int)game.ball.radius,
				(int)game.ball.radius);
		*/
		// Player details:
		int cursorPos = 15;
		for(Player p: game.playerlist){
			/*
			// Paddles
			offscreenGraphics.fillRect(
					(int)p.paddle.x,
					(int)p.paddle.y,
					(int)p.paddle.width,
					(int)p.paddle.height);
			*/
			// Names
			offscreenGraphics.setColor(Color.red);
			if(p == game.me) offscreenGraphics.setColor(Color.yellow);
			offscreenGraphics.drawString(
					p.getName() + " (" + p.id + ")",
					0,
					cursorPos);
			
			cursorPos += 10;
			
			// Messages
			offscreenGraphics.drawString(
					"> " + p.getMessage(),
					0,
					cursorPos);

			cursorPos += 15;
		}
		
		/*
		// corner circles
		offscreenGraphics.fillOval((int)(0 * mod)-5, 	(int)(0 * mod)-5, 10, 10);
		offscreenGraphics.fillOval((int)(100 * mod)-5, 	(int)(0 * mod)-5, 10, 10);
		offscreenGraphics.fillOval((int)(0 * mod)-5, 	(int)(100 * mod)-5, 10, 10);
		offscreenGraphics.fillOval((int)(100 * mod)-5, 	(int)(100 * mod)-5, 10, 10);
		*/
		
		// Currently writing text
		offscreenGraphics.drawString("> " + currentmsg,(int)(10 * mod),(int)(90 * mod));
		
		// Finish
		g.drawImage(offscreenImage, 0, 0, this);
	}
	
	public boolean keyUp(Event e, int key){
		inChar = (char)key;

		//trace("my name is " + game.me.name);//FIXME im here
		if(inChar == '\n'){
			if(currentmsg.startsWith("/name ")){
				game.me.name = currentmsg.replace("/name " ,"");
			}else{			
				game.me.message = currentmsg;
			}
			game.updatePlayerList();
			currentmsg = "";
		}else{
			currentmsg += inChar;
		}
		
		return true;
	}
	
	public void update(Graphics g){
		
		i++;
		Xml.getPlayerlist();
		paint(g);
	}
}