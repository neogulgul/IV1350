package se.kth.iv1350.integration;

/**
 * Exception for when a database is unavailable.
 */
public class DatabaseUnavailableException extends RuntimeException
{
	/**
	 * Constructor.
	 * @param systemName Name of the system that depends on the database.
	 */
	public DatabaseUnavailableException(String systemName)
	{
		super("Database for \"" + systemName + "\" is unavailable.");
	}
}
