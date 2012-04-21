// File: MyMath.java
// Date: Aug 06.

package pong;

/**
 * Library of mathmatical and arithmetic methods
 * @author Dean Rather
 */
public class MyMath{
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////			METHODS				//////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	
	// Random Integer Between method
	/**
	 * Overloaded alternate method name for randIntBetween(int lower, int upper).
	 * Calls randIntBetween(int lower, int upper) with the same values.
	 * Returns a randomly generated integer between the two supplied values.
	 * The return value is inclusive of the two pass values. eg: lower = 0; upper = 3; possible outcomes: 0, 1, 2, 3.
	 * @param lower The lowest possible return value.
	 * @param upper the highest possible return value
	 * @return a random value between the lower and upper provided arguments (inclusive)
	 * @author 	Dean Rather
	 */
	public static int rand(int lower, int upper){
		return randIntBetween(lower, upper);
	}
	
	/**
	 * Overloaded alternate method name for randIntBetween(int lower, int upper).
	 * Calls randIntBetween(int lower, int upper) with the same values.
	 * Returns a randomly generated integer between the two supplied values.
	 * The return value is inclusive of the two pass values. eg: lower = 0; upper = 3; possible outcomes: 0, 1, 2, 3.
	 * @param lower The lowest possible return value.
	 * @param upper the highest possible return value
	 * @return a random value between the lower and upper provided arguments (inclusive)
	 * @author 	Dean Rather
	 */
	public static int random(int lower, int upper){
		return randIntBetween(lower, upper);
	}
	
	/**
	 * Returns a randomly generated integer between the two supplied values.
	 * The return value is inclusive of the two pass values. eg: lower = 0; upper = 3; possible outcomes: 0, 1, 2, 3.
	 * @param lower The lowest possible return value.
	 * @param upper the highest possible return value
	 * @return a random value between the lower and upper provided arguments (inclusive)
	 * @author 	Dean Rather
	 */
	public static int randIntBetween(int lower, int upper){
		return lower + (int)((Math.random()*(upper - lower + 1)));
	}
	
	// about method
	/**
	 * an overloaded method for about(int value, int tolerance) which defaults tolderance to 10.
	 * returns a randomly generated value that is about the same as the value provided. eg about(10) could return 9, 10, or 11.
	 * default tolerance is 10 % of the provided value, so about(500) could return anywhere between 450 and 550 (inclusive).
	 * @param value the value you wish to approximate
	 * @return approximately the value supplied
	 * @author 	Dean Rather
	 */
	public static int about(int value){
		int default_tolerance = 10;
		return about(value, default_tolerance);
	}
	
	/**
	 * returns a randomly generated value that is about the same as the value provided. eg about(10) could return 9, 10, or 11.
	 * if tolerance is 10 % of the provided value,  about(500) could return anywhere between 450 and 550 (inclusive).
	 * if tolerance is 5 % of the provided value,  about(500) could return anywhere between 475 and 525 (inclusive).
	 * @param value the value you wish to approximate
	 * @return approximately the value supplied
	 * @author 	Dean Rather
	 */
	public static int about(int value, int tolerance){
		int lower = (value * (100-tolerance)) / 100;
		int upper = (value * (100+tolerance)) / 100;
		return randIntBetween(lower, upper);
	}
	
	// chance method
	/**
	 * returns boolean true or false randomly generated depending on the likelyhood of the supplied percentage.
	 * supplied percentage is to be in an integer value between 0 and 100.
	 * chance(0) will always return false. chance(100) will always return true, chance(50) is like flipping a coin, and 
	  * chance(90) will return true 90% of the time, else false.
	 * @param percent the percent chance this function has of returning true.
	 * @return true "percent" percent of the time, otherwise false.
	 * @author 	Dean Rather
	 */
	public static boolean chance(int percent){
		if (randIntBetween(1,100) <= percent){
			return true;
		}else{
			return false;
		}
	}
	public static int stringToInt(String str){
		return Integer.valueOf(str);
	}
	
}