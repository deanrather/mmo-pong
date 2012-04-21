//File: Player.java
//Date: 21 Oct 06

package pong;

/**
* The Player class.
* @author Dean Rather
*/
public class Player{
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			ATTRIBUTES			////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////// 		CONSTANTS	////////////////////////////
	
	////////////////////////////// 		CLASS		////////////////////////////
	
	////////////////////////////// 		INSTANCE	////////////////////////////
	String 	name;
	int 	points;
	String 	message;
	Paddle 	paddle;
	Goal 	goal;
	int 	id;
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////		CONSTRUCTORS			////////////////////
	////////////////////////////////////////////////////////////////////////////
	public Player(int id){
		this.name 		= "New Player";
		this.points 	= 0;
		this.message 	= "Hi Everyone!";
		this.paddle 	= new Paddle();		
		this.goal		= new Goal();
		this.id 		= id;
	}
	/**
	 * copy constructor
	 * @author Dean Rather
	 * @param p player to copy
	 */
	public Player(Player p){
		this.name 		= p.getName();
		this.points 	= p.getPoints();
		this.message 	= p.getMessage();
		this.paddle 	= p.getPaddle()	;
		this.goal		= p.getGoal();
		this.id 		= p.getId();
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////			METHODS				////////////////////
	////////////////////////////////////////////////////////////////////////////
	public String getMessage() {
		//message = Xml.getMessage(id);
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Paddle getPaddle() {
		return paddle;
	}
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Goal getGoal() {
		return goal;
	}
	public void setGoal(Goal goal) {
		this.goal = goal;
	}
}