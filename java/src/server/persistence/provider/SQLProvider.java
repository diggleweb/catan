package server.persistence.provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import server.persistence.dao.*;

public class SQLProvider extends IProvider {
	
	private Connection connection;

	public SQLProvider(int delta)
	{
		super.userDAO = new SQLUserDAO(this);
		super.gameDAO = new SQLGameDAO(this);
		super.commandDAO = new SQLCommandDAO(this);
		super.delta = delta;
		this.connection = null;
	}

	@Override
	public void startTransaction()
	{
		String dbName = /*"Database" + File.separator + */"catandb.sqlite";
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			connection.setAutoCommit(false);
						
		}
		catch (SQLException e) {
			System.out.println("Failed to connect to the SQL database");
			e.printStackTrace();
		}
	}

	@Override
	public void endTransaction(boolean commit)
	{
		try {
			if(commit) {
				connection.commit();
			}
			else {
				connection.rollback();
			}
		}
		catch (SQLException e) {
			System.out.println("Failed to commit or follow through with the transaction SQLProvider.endTransaction()");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}
}