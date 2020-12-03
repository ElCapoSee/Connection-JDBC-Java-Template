import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        final String id = "000546123";
        final String content = "TestContentJava";

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres",
                    "test123");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        }else {
            System.out.println("Failed to make connection!");
        }
        final char dm = (char) 34;

        // Get parametrs
        // Example
        /*String sql = "{call public."+ dm + "testGetContentFunc"+ dm + "(?, ?)}";
        CallableStatement cs = connection.prepareCall(sql);
        System.out.println(cs);
        cs.setString(1, id);
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.executeUpdate();
        String result = cs.getString(2);
        System.out.println(result);
        connection.close();*/

        //Save parametrs
        // Example
       /*
        String sql = "{call public."+ dm + "testSaveContentFunc"+ dm + "(?, ?)}";
        CallableStatement cs = connection.prepareCall(sql);
        System.out.println(cs);
        cs.setString(1, id);
        cs.setString(2, content);
        cs.executeUpdate();
        System.out.println("Success!");
        connection.close();
        */

        // Get parametrs
        // Example
        connection.setAutoCommit(false);
        String sql = "{call public."+ dm + "testGet"+ dm + "(?)}";
        CallableStatement cs = connection.prepareCall(sql);
        cs.registerOutParameter(1, Types.REF_CURSOR);
        cs.executeUpdate();
        final ResultSet result = (ResultSet) cs.getObject(1);
            final List<String> contents = new ArrayList<String>(100);
            while (result.next()) {
                contents.add(result.getString(1));
            }
        for(String data : contents){

            System.out.println(data);
        }
        connection.close();
    }
}
