import java.sql.ResultSet;
import java.sql.SQLException;

public class Column {

    /**
     *  declaring variables for the columns info
     */
    private String name, type, reference;
    private boolean hasRef, primary, nullable;

    /**
     * A class that represents a column in the db
     * @param result_set
     * @throws SQLException
     */
    public Column(ResultSet result_set) throws SQLException
    {
        /**
         * at start we set the primary, hasref vars to false and the reference string to empty
         */
        primary = false;
        hasRef = false;
        reference = "";


        /**
         * we extract the data we need as strings from the result set
         */
        nullable = result_set.getString("NULLABLE").equals("1");
        name = result_set.getString("COLUMN_NAME");
        type = result_set.getString("TYPE_NAME");

    }

    /**
     * A method that sets the reference of a col
     * @param ref
     */
    public void setReference(String ref)
    {
        String temp = this.reference;

        this.reference = ref;
        /**
         * we set the hasref to true.
         */
        hasRef = true;

        //debug println
        //System.out.println("Reference set from: " + temp + " to: " + reference );
    }

    public boolean HasRef() {
        return hasRef;
    }

    public boolean isPrimary() {
        return primary;
    }
    public void setPrimary()
    {
        this.primary = true;
    }

    public boolean isNullable() {
        return nullable;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getReference() {
        return reference;
    }
}
