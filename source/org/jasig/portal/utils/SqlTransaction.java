package org.jasig.portal.utils;

import java.sql.*;
import org.jasig.portal.services.LogService;
import org.jasig.portal.RdbmServices;

/**
 * This type is a place to centralize the portal's sql transaction code.
 * @author: Dan Ellentuck
 * @version $Revision$
 */
public class SqlTransaction {
/**
 * SqlTransaction constructor comment.
 */
public SqlTransaction() {
	super();
}
/**
 * @param conn java.sql.Connection
 * @exception java.sql.SQLException
 */
public static void begin(Connection conn) throws java.sql.SQLException
{
	try
	{
          RdbmServices.setAutoCommit(conn, false);
	}
	catch (SQLException sqle)
	{
		LogService.instance().log(LogService.ERROR, sqle);
		throw sqle;
	}
}
/**
 * @param conn java.sql.Connection
 * @exception java.sql.SQLException
 */
public static void commit(Connection conn) throws java.sql.SQLException
{
	try
	{
			RdbmServices.commit(conn);
			RdbmServices.setAutoCommit(conn, true);

	}
	catch (SQLException sqle)
	{
		LogService.instance().log(LogService.ERROR, sqle);
		throw sqle;
	}
}
/**
 *
 */
protected static void logNoTransactionWarning()
{
	String msg = "You are running the portal on a database that does not support transactions.  " +
				 "This is not a supported production environment for uPortal.  " +
				 "Sooner or later, your database will become corrupt.";
	LogService.instance().log(LogService.WARN, msg);
}
/**
 * @param conn java.sql.Connection
 * @exception java.sql.SQLException
 */
public static void rollback(Connection conn) throws java.sql.SQLException
{
	try
	{
			RdbmServices.rollback(conn);
			RdbmServices.setAutoCommit(conn, true);
	}
	catch (SQLException sqle)
	{
		LogService.instance().log(LogService.ERROR, sqle);
		throw sqle;
	}
}
/**
 * @param conn java.sql.Connection
 * @param newValue boolean
 * @exception java.sql.SQLException.
 */
public static void setAutoCommit(Connection conn, boolean newValue) throws java.sql.SQLException
{
	try
	{
          RdbmServices.setAutoCommit(conn, newValue);
	}
	catch (SQLException sqle)
	{
		LogService.instance().log(LogService.ERROR, sqle);
		throw sqle;
	}
}
}
