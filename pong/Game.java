//File: Game.java
//Date: 21 Oct 06

package pong;

import java.util.ArrayList;

/**
* The Game class.
* @author Dean Rather
*/
public class Game{
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			ATTRIBUTES			////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////// 		CONSTANTS	////////////////////////////
	
	////////////////////////////// 		CLASS		////////////////////////////
	
	////////////////////////////// 		INSTANCE	////////////////////////////
	public ArrayList<Player> 	playerlist;
	public Board 				board;
	public Ball					ball;
	Player me;
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////		CONSTRUCTORS			////////////////////
	////////////////////////////////////////////////////////////////////////////
	public Game(){
		playerlist 	= new ArrayList<Player>();
		board 		= new Board();
		ball 		= new Ball();

		ball.x = 50 * Pong.mod;
		ball.y = 50 * Pong.mod;
		
		board.x 		= 0;
		board.y 		= 25 * Pong.mod;
		board.width 	= 100 * Pong.mod;
		board.height 	= 50 * Pong.mod;
		
		/*
		playerlist.add(new Player("Dean"));
		playerlist.add(new Player("Tim"));

		playerlist.get(0).goal.x = 0 - playerlist.get(0).goal.x/2;
		playerlist.get(0).goal.y = 25 * Pong.mod - playerlist.get(0).goal.y/2;
		
		playerlist.get(1).goal.x = 100 * Pong.mod - playerlist.get(1).goal.x/2;
		playerlist.get(1).goal.y = 33.3 * Pong.mod - playerlist.get(1).goal.y/2;
		
		*/
		// get playerlist from server
		// add this player
		// send new player list to server
		
		
		playerlist = Xml.getPlayerlist();
		
		// make a unique ID
		int myId = 1;
		for(Player p: playerlist)
			if(myId++ > p.getId())
				break;
		
		me = new Player(myId);
		
		playerlist.add(me);
		
		Xml.sendPlayerlist(playerlist);
		
		arrangeBoard();
		moveBall();
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			METHODS				////////////////////
	////////////////////////////////////////////////////////////////////////////
	public void arrangeBoard(){
		/*
		playerlist.get(0).goal.x = 0 - playerlist.get(0).goal.x/2;
		playerlist.get(0).goal.y = 25 * Pong.mod - playerlist.get(0).goal.y/2;
		
		playerlist.get(1).goal.x = 100 * Pong.mod - playerlist.get(1).goal.x/2;
		playerlist.get(1).goal.y = 33.3 * Pong.mod - playerlist.get(1).goal.y/2;
		*/
	}
	
	public void moveBall(){
		ball.x++;
	}
	
	public void updatePlayerList(){
		playerlist = Xml.getPlayerlist();
		for(Player p: playerlist){
			if(p.getId() == me.getId()){
				playerlist.remove(p);
				playerlist.add(me);
			}
		}
		Xml.sendPlayerlist(playerlist);
	}
}