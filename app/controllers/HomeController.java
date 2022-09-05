package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import play.mvc.Controller;
import play.mvc.Result;
import service.Database;

/**
 * This controller contains an action to handle HTTP requests to the
 * application's home page.
 */
public class HomeController extends Controller {

	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 */
	public Result irgendwas() {
		String ergebnis = service.DatabaseConnector.test();
		return ok(ergebnis);
	}

	/**
	 * An action that renders an HTML page with a welcome message. The configuration
	 * in the <code>routes</code> file means that this method will be called when
	 * the application receives a <code>GET</code> request with a path of
	 * <code>/</code>.
	 */
	public Result index() {

		String ausgabe = "Unbekannter Fehler";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String dbUrl = "jdbc:mysql://localhost:3306/tnsdb_1_12_5";
		String user = "root";
		String password = "feli1337";

		try {
			connection = Database.getConnection(dbUrl, user, password);
			ausgabe = "Datenbank Connection erfolgreich:";

			// 1. Create SQL for PreparedStatement using Parameters

			String sql = "SELECT name, guiDescription FROM eventtype WHERE EventPrioType_ID > ?";

			// 2. Create a Prepared Statement

			preparedStatement = connection.prepareStatement(sql);

			// 3. Insert Parameter value(s) into PreparedStatement

			preparedStatement.setInt(1, 10);

			// 4. Execute the PreparedStatement

			resultSet = preparedStatement.executeQuery();

			// 5. Process the ResultSet (if applicable)

			ausgabe = "gefunden: ";
			while (resultSet.next()) {

				ausgabe += resultSet.getString("benutzername") + ", ";
			}

		} catch (SQLException e) {
			e.printStackTrace();
			ausgabe += "ERROR:" + e.getErrorCode() + " -> " + e.getMessage();
		} finally {
			Database.closeResultSet(resultSet);
			Database.closePreparedStatement(preparedStatement);
			Database.closeConnection(connection);
		}

		return ok(ausgabe);

	}

}
