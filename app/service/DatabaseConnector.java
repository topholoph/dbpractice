package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//import org.json.JSONException;
//
//import scala.util.parsing.json.JSONArray;
//import scala.util.parsing.json.JSONObject;

public class DatabaseConnector {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	String dbUrl = "jdbc:mysql://localhost:3306/hasenbein";
	String user = "root";
	String password = "";

	public static String test() {

		String ausgabe = "unbekannter Fehler";

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String dbUrl = "jdbc:mysql://localhost:3306/hasenbein";
		String user = "root";
		String password = "";

		try {
			connection = Database.getConnection(dbUrl, user, password);
			ausgabe = "Datgenbank CXonnection erfolgreich:";

			// Let's Make a Plan
			// 1. Create SQL for PreparedStatement using Parameters

			String sql = "SELECT * FROM benutzer WHERE benutzer_id > ?";

			// 2. Create a Prepared Statement

			preparedStatement = connection.prepareStatement(sql);

			// 3. Insert Parameter value(s) into PreparedStatement

			preparedStatement.setInt(1, 2);

			// 4. Execute the PreparedStatement

			resultSet = preparedStatement.executeQuery();

			// 5. Process the ResultSet (if applicable)

			ausgabe = "gefunden:";
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

		return ausgabe;

	}
//	public static String alleBenutzer() {
//	
//	String ausgabe = "Unbekannter Fehler";
//	Connection connection = null;
//	PreparedStatement preparedStatement = null;
//	ResultSet resultSet = null;
//
//	String dbUrl = "jdbc:mysql://localhost:3306/hasenbein";
//	String user = "root";
//	String password = "";
//
//	try {
//		connection = Database.getConnection(dbUrl, user, password);
//		String sql = "SELECT * FROM benutzer";
//		preparedStatement = connection.prepareStatement(sql);
//		resultSet = preparedStatement.executeQuery();
//		
//		while (resultSet.next()) {
//			ausgabe += resultSet.getString("benutzername") + ", ";
//		}
//
//	} catch (SQLException e) {
//		e.printStackTrace();
//		ausgabe +=  "ERROR:" + e.getErrorCode() + " -> " + e.getMessage();
//	} finally {
//		Database.closeResultSet(resultSet);
//		Database.closePreparedStatement(preparedStatement);
//		Database.closeConnection(connection);
//	}
//
//	
//	return ausgabe;
//
//}
//
//public static JSONArray convertResultSetToJson( ResultSet rs )  throws SQLException, JSONException
//{
//JSONArray json = new JSONArray();
//ResultSetMetaData rsmd = rs.getMetaData();
//personJson = Json.toJson(person); // {"firstName": "Foo", "lastName": "Bar", "age": 30}
//while(rs.next()) {
//  int numColumns = rsmd.getColumnCount();
//  JSONObject obj = new JSONObject();
//
//  for (int i=1; i<numColumns+1; i++) {
//    String column_name = rsmd.getColumnName(i);
//
//    if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
//     obj.put(column_name, rs.getArray(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
//     obj.put(column_name, rs.getInt(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
//     obj.put(column_name, rs.getBoolean(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
//     obj.put(column_name, rs.getBlob(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
//     obj.put(column_name, rs.getDouble(column_name)); 
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
//     obj.put(column_name, rs.getFloat(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
//     obj.put(column_name, rs.getInt(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
//     obj.put(column_name, rs.getNString(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
//     obj.put(column_name, rs.getString(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
//     obj.put(column_name, rs.getInt(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
//     obj.put(column_name, rs.getInt(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
//     obj.put(column_name, rs.getDate(column_name));
//    }
//    else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
//    obj.put(column_name, rs.getTimestamp(column_name));   
//    }
//    else{
//     obj.put(column_name, rs.getObject(column_name));
//    }
//  }
//
//  json.put(obj);
//}
//
//return json;
//}

}