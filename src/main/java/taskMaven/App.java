package taskMaven;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        System.out.println( "Hello World!" );

        Class.forName("org.h2.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase","user1","password1")){
            Statement statement1 = connection.createStatement();
    //        Statement st1 = connection.prepareStatement("");

            String tableName = "posts";
            String firstColumnName = "ID";
            String secColumnName = "postDate";
            String thirdColumnName = "postMessage";

            String str = "CREATE TABLE "+tableName+"(" +
                            firstColumnName+" int," +
                            secColumnName +" Date," +
                            thirdColumnName +" varchar(1000)" +
                            ")";
            statement1.execute(str);
            GuestBookController myGuestBook =
                    new GuestBook(connection,tableName,firstColumnName,secColumnName,thirdColumnName);

            myGuestBook.addMessage("hi hi hi");
            List<Record> records = new ArrayList<>();
            records = myGuestBook.getRecords();
            for (Record r:records){
                System.out.println("id = " + r.getID() + ", Date = " + r.getPostDate());
                System.out.println(r.getPostMessage());
            }

        }






    }
}
