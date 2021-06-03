import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Main {
    public static void main(String[] args) throws SQLException {

//        for (String s : args)
//        {
//            System.out.println(s);
//        }
        String name = "Cars.db";
        //String dbpath = "src\\" + name;
        String _dbname =  name;

        Connect con = new Connect(_dbname);

//        Connection db_connection = con.getDbcon();

        ResultSet tb_data = con.getTable_data();



        /**
         * Creating an arraylist to hold all the tables.
         */
        ArrayList<Table> tables = new ArrayList<>();
        /**
         * while the result set from the con driver is not null, get the table name.
         * We pass the table name as argument and create a new table instance for each table.
         * Finally we save all the tables to our arraylist.
         */
        while(tb_data.next()){
            String t_name = tb_data.getString("TABLE_NAME");
            tables.add(new Table(t_name, con.getDbMetadata()));
            //System.out.println(t_name);
        }


        //print all of the table queries
        for(Table t : tables)
        {
            System.out.println(t.createQuary() + "\n");
        }
        //print all of the insert statements
        for(Table t : tables)
        {
            Collection<String> statements = t.sqlStatements(con.getDbcon().createStatement());
            for(String s : statements)
            {
                System.out.println(s);
            }
            System.out.println("\n");
        }
    }

}

