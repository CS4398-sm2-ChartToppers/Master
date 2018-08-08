package Model;

/**
 * Allows initialization of a SportScraperController object that is connected to
 * the model and the view.
 */
public class RetrieveRS implements Model {
	/**
	 * Initializes RetrieveRS object
	 */
	public RetrieveRS() { }
	
	/**
	 * Executes a qCommand in association with the model and view.
	 * 
	 * @param qCommand		The associated command
	 */
	public void execute(String qCommand) { System.out.println(qCommand); }
}