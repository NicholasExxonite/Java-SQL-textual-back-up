import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * Connect class that works as the middle man between dbBasic class and Main class.
 */
public class Connect{
    private String databaseName;
    private DatabaseMetaData dbMetadata;
    Connection dbcon;
    ResultSet table_data;
    DbBasic dbb;


    Connect(String dbname) throws SQLException {

        this.databaseName = dbname;
        dbb = new DbBasic(databaseName);
        dbcon = dbb.getCon();

        this.run();

    }


    /**
     * A method that calls the get connection method from dbbasic to establish JDBC connection
     * and stores the metadata and table data.
     * @throws SQLException
     */
    private void run() throws SQLException
    {

//        open();
        dbb.getConnection();

        dbMetadata = dbcon.getMetaData();

        this.table_data = dbMetadata.getTables(null, null, null, new String[] { "TABLE" });

    }

    /**
     *
     * @return the jdbc connection to the database.
     */
    public Connection getDbcon() {
        return dbcon;
    }

    /**
     *
     * @return the database metadata
     */
    public DatabaseMetaData getDbMetadata() {
        return dbMetadata;
    }
    //    public void open()
//    {
//        File dbfile = new File("C:\\MyFiles\\Java\\coursework_201\\src\\University.db");
//
//        if (!dbfile.exists())
//        {
//            System.out.println("DB file: " + databaseName + " doesn't exist.");
//            System.exit(0);
//        }
//
//        try {
//            Class.forName("org.sqlite.jdbc");
//            getConnection();
//        }
//        catch (ClassNotFoundException e)
//        {
//            e.printStackTrace();
//        }
//    }
//    public void getConnection()
//    {
//        try
//        {
//            dbcon = DriverManager.getConnection(databaseName);
//
//            /**
//             * sets autocommit to false, delaying updates until we call commit()
//             */
//            dbcon.setAutoCommit(false);
//        }
//        catch (SQLException e)
//        {
//            e.getStackTrace();
//        }
//    }

    public ResultSet getTable_data()
    {
        return table_data;
    }
}
