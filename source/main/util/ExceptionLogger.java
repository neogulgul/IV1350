package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import se.kth.iv1350.constants.Constants;

/**
 * Responsible for logging exceptions.
 */
public class ExceptionLogger
{
	private static final String LOG_FILENAME = "sale-exceptions-log.txt";
	private static final ExceptionLogger INSTANCE = new ExceptionLogger();
	private PrintWriter logWriter;

	private static String createTimeString()
	{
		LocalDateTime now = LocalDateTime.now();
		return String.format(
			"%d %s %s %s:%s:%s",
			now.getYear(),
			Constants.MONTHS[now.getMonthValue() - 1],
			Util.intToStringOfAtLeastTwoDigits(now.getDayOfMonth()),
			Util.intToStringOfAtLeastTwoDigits(now.getHour()),
			Util.intToStringOfAtLeastTwoDigits(now.getMinute()),
			Util.intToStringOfAtLeastTwoDigits(now.getSecond())
		);
	}

	private ExceptionLogger()
	{
		try
		{
			logWriter = new PrintWriter(new FileWriter(LOG_FILENAME, true), true);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Getter for the instance of the logger which avoids the need to create the logger multiple times.
	 * @return A reference to the logger instance.
	 */
	public static ExceptionLogger getInstance()
	{
		return INSTANCE;
	}

	/**
	 * Logs an exception.
	 * @param exception Exception to be logged.
	 */
	public void log(Exception exception)
	{
		logWriter.println(createTimeString());
		exception.printStackTrace(logWriter);
		logWriter.println();
	}
}
