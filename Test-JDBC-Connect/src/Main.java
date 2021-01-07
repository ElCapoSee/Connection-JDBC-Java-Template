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
////////////////////////////////////////////////////////////
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import oracle.jdbc.OracleTypes;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws SQLException {
        String id = "gfjg";
        long content = 39568228819L;
        System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

        try {
            //Class.forName("org.postgresql.Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException var12) {
            System.out.println("Where is your Oracle JDBC Driver? Include in your library path!");
            var12.printStackTrace();
            return;
        }

        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;

        /*
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://esb-dbp1-lt3.hq.bc:5432/monitoring", "esb-jdbc-test", "lY4Qt3aJ89hSD9C");
        } catch (SQLException var11) {
            System.out.println("Connection Failed! Check output console");
            var11.printStackTrace();
            return;
        }*/
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@esb_test.hq.bc:1521:esbtest", "ESBLOGS", "Xq2!fmcTVD");
        } catch (SQLException var11) {
            System.out.println("Connection Failed! Check output console");
            var11.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        /*
        char dm = (char) 34;
        connection.setAutoCommit(false);
        String sql = "{call public.\"testGetProcessNames\"(?)}";
        CallableStatement cs = connection.prepareCall(sql);
        cs.registerOutParameter(1, 2012);
        cs.executeUpdate();
        ResultSet result = (ResultSet)cs.getObject(1);
        ArrayList contents = new ArrayList(100);

        while(result.next()) {
            contents.add(result.getString(1));
        }

        Iterator var9 = contents.iterator();

        while(var9.hasNext()) {
            String data = (String)var9.next();
            System.out.println(data);
        }

        connection.close();
        */
        /*
        try {
            final CallableStatement statement = connection.prepareCall("{call lib_logviewer.GetProcessVariableList(?,?)}");
            try {
                statement.setLong(1, content);
                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.execute();
                final ResultSet result = (ResultSet) statement.getObject(2);
                int columns = result.getMetaData().getColumnCount();
                try {
                    final List<String> names = new ArrayList<String>(100);
                    while (result.next()) {
                        names.add(result.getString(1));
                        for (int i = 1; i <= columns; i++){
                            System.out.print(result.getString(i) + "\t");
                        }
                    }

                    for(String data : names) {
                        System.out.println(data);
                    }
                } finally {
                    result.close();
                }
            } finally {
                statement.close();
            }
            */
            try {
                final CallableStatement statement = connection.prepareCall("{call lib_secure.CalcHash(?,?)}");
                try {
                    statement.setString(1, id);
                    statement.registerOutParameter(2, OracleTypes.RAW);
                    statement.execute();
                    String test = statement.getString(2);
                    System.out.println(test);
                } finally {
                    statement.close();
                }
        } finally {
            connection.close();
        }
    }

}
