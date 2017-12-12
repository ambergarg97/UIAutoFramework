package tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class DataBaseTools {
	private String rootDir = null;// path variable for root dir :
									// "workspace project"
	private Properties prop = null;

	// database variables
	private Connection con = null;
	public Statement stmt = null;
	private String DB_Instance;
	private String DBMS_Name = null;
	private String DB_Url;
	private String DB_Name;
	private String DB_Port;
	private String DB_User;
	private String DB_Pass;
	private String DB_Driver;

	// default block to initialize rootDir and Properties object
	{
		rootDir = System.getProperty("user.dir");
		prop = new Properties();
	}

	// this method loads all parameters from the properties file :"testExecution.properties"
	// and returns Statement object to query database
	public Statement getDBStatementObject() {
		try {
			InputStream input = new FileInputStream(rootDir
					+ "\\src\\resources\\testExecution.properties");
			prop.load(input);

			DBMS_Name = prop.getProperty("dbms.name").toString();

			if (DBMS_Name != null) {
				DB_Instance = prop.getProperty("dbms.instanceId").toString();
				DB_Name = prop.getProperty("db.name").toString();
				DB_Port = prop.getProperty("db.port").toString();
				DB_User = prop.getProperty("db.user").toString();
				DB_Pass = prop.getProperty("db.pass").toString();
				DB_Driver = prop.getProperty("db.driver").toString();
				DB_Url = "jdbc:" + DBMS_Name + "://" + DB_Instance + ":"
						+ DB_Port + "/" + DB_Name + "";
			} else
				throw new IllegalArgumentException("The given database "
						+ DBMS_Name + " is not known or supported ");

			Class.forName(DB_Driver).newInstance();
			con = DriverManager.getConnection(DB_Url, DB_User, DB_Pass);
			// Statement object to send the SQL statement to the Database
			stmt = con.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return stmt;
	}
}
