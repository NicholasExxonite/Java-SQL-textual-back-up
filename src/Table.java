import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Table {
    private HashMap<String, Column> t_columns = new HashMap<>();
    private ArrayList<String> t_refrences = new ArrayList<>();
    private boolean hasForgeinkey;
    private String t_name;

    /**
     * A class that extracts all the columns by a given name, from the table
     * @param name
     * @param db_metadata
     * @throws SQLException
     */
    public Table(String name, DatabaseMetaData db_metadata)throws SQLException
    {
        this.t_name = name;
        hasForgeinkey = false;

        /**
         * get the cols, foreign and primary keys based on name.
         */
        ResultSet col_data = db_metadata.getColumns(null, null, name, null);
        ResultSet pri_keys = db_metadata.getPrimaryKeys(null, null, name);
        ResultSet for_keys = db_metadata.getImportedKeys(null, null, name);


        /**
         * while there's data in the cols, extract the data by name and save it in an arraylist
         * of columns.
         */
        while(col_data.next()){
            {
                String col_name = col_data.getString("COLUMN_NAME");
                Column col = new Column(col_data);
                t_columns.put(col_name, col);
            }
        }
        /**
         * while a pri key exists and the col contains a pri key, get and set the pri key
         * for that column.
         */
        while(pri_keys.next())
        {
            String p_key = pri_keys.getString("COLUMN_NAME");
            if(t_columns.containsKey(p_key))
            {
                t_columns.get(p_key).setPrimary();
            }
        }
        /**
         * the same while function as, but for foreign keys. Here we save the foreign key reference as well.
         */
        while(for_keys.next())
        {
            String k = for_keys.getString("FKCOLUMN_NAME");
            if(t_columns.containsKey(k))
            {
                String f_table = for_keys.getString("PKTABLE_NAME");
                String f_key = for_keys.getString("PKCOLUMN_NAME");
                t_columns.get(k).setReference(f_table + "(" + f_key + ")");
                t_refrences.add(f_table);
                hasForgeinkey = true;
            }
        }
    }

    /**
     * A method that creates the correct query for the db.
     * @return - query
     */
    public String createQuary()
    {
        /**
         * the create table command. We use the extracted
         */
        String query = "CREATE TABLE" + " " + t_name + " " + "(\n";
        Collection<Column> cols = this.t_columns.values();
        /**
         * for each column, add the cols info to the query string.
         */
        for(Column c : cols)
        {
            query += "  " + c.getName() + " " + c.getType() + (!c.isNullable() ? " NOT NULL" : "") + ","+"\n";
        }
        Column[] col_array = new Column[cols.size()];
        col_array = cols.toArray(col_array);
        query = query + " " +  "PRIMARY KEY" + " " + "(";

        for(int c = 0; c < cols.size(); c++){
            Column col = col_array[c];
            if(col.isPrimary())
            {

                query = query + col.getName() + "," + " ";

            }
        }
        query = query.substring(0, query.lastIndexOf(", "));
        query = query + ")" + "," + "\n";

        for(Column c : cols)
        {
            if(c.HasRef())
            {
                query = query + " " + "FOREIGN KEY" + " (" + c.getName()
                        + ") " + "REFERENCES " + c.getReference() + ","+  "\n";

            }
        }
        query = query.substring(0, query.lastIndexOf(","));
        query += "\n);";

        /**
         * we return the finished query.
         */
        return query;
    }

    public Collection<String> sqlStatements(Statement st) throws SQLException
    {
        /**
         * we start executing the select query
         */
        ResultSet res_set = st.executeQuery("SELECT * FROM " + t_name);
        /**
         * an arraylist in which we save all the statements
         */
        Collection<String> insert_statements = new ArrayList<>();
        Collection<Column> cols = getT_columns();
        while(res_set.next())
        {
            String statement = "INSERT INTO" + " " + t_name +  " " + "VALUES" + " " + "(";
            for(Column col : cols)
            {
                if(col.getType().contains("INT"))
                {
                    int val = res_set.getInt(col.getName());
                    statement += (val) + ", ";
                }
                else if(col.getType().contains("VARCHAR"))
                {
                    String val = res_set.getString(col.getName());
                    val = "\"" + val +"\"";
                    statement += (val) + ", ";
                }
                else{
                    System.out.println("Error: Wrong variable type");
                }
            }
            /**
             * Take the whole insert statement and split it from start until the last ,.
             * Because we automatically add
             * the commas at the back. Then add the closing bracket and semi-column
             */
            statement = statement.substring(0, statement.lastIndexOf(",")) + ");";
//            statement += ");";

            /**
             * add the new statement to the collection of statements
             */
            insert_statements.add(statement);
        }

        /**
         * we return the insert statements
         */
        return insert_statements;
    }

    public ArrayList<String> getT_refrences() {
        return t_refrences;
    }

    /**
     * A method to get the values of the columns.
     * @return The values of your collection
     */
    public Collection<Column> getT_columns() {
        return t_columns.values();
    }

    public String getT_name() {
        return t_name;
    }

    public boolean HasForgeinkey() {
        return hasForgeinkey;
    }

    public void setForgeinkey(boolean hasForgeinkey) {
        this.hasForgeinkey = hasForgeinkey;
    }

    public String createString(){
        String v = "";

        Collection<Column> c = this.t_columns.values();
        for(Column col:c)
        {
            v +=col.toString() + "\n";
        }

        return v;
    }
}
