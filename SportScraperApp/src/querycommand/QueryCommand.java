package querycommand;

/**
 * Interface for querycommand package classes that allows them to be runnable.
 */
public interface QueryCommand extends Runnable {
	
	/**
	 * Allows QueryCommand to be runnable.
	 */
	@Override
	public void run();
}