package taskMaven;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException {

        Class.forName("org.h2.Driver");
        try(Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase","user1","password1")){
            Statement stat1 = connection.createStatement();

            String tableName = "posts";
            String firstColumnName = "ID";
            String secColumnName = "postDate";
            String thirdColumnName = "postMessage";

            String tableCreation = "CREATE TABLE "+tableName+"(" +
                            firstColumnName+" INT AUTO_INCREMENT," +
                            secColumnName +" TIMESTAMP," +
                            thirdColumnName +" VARCHAR(1000)" +
                            ")";
            stat1.execute(tableCreation);
            GuestBookController myGuestBook =
                    new GuestBook(connection,tableName,firstColumnName,secColumnName,thirdColumnName);
/*resource!!!*/            InputStream in = System.in;
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()){
                String userInput = sc.nextLine();
                if (!userInput.equals("quit")){
                    String[] mas = userInput.split(" ");
/*redo!!!*/                Commands cmd = Commands.valueOf("ADD"); // = new Commands();
                    try{
                        cmd = Commands.valueOf(mas[0].toUpperCase());

                        switch (cmd){
                            case ADD:
                                StringBuilder message = new StringBuilder();
                                for (int i=1;i<mas.length;i++){
                                    message = message.append(mas[i]).append(" ");
                                }
                                myGuestBook.addMessage(message.toString());
                                break;
                            case LIST:
                                List<Record> records = myGuestBook.getRecords();
                                for (Record r:records){
                                    System.out.println("id = " + r.getID() + ", Date = " + r.getPostDate());
                                    System.out.println(r.getPostMessage());
                                }
                                break;
                            default:
                                System.err.println("Unknown command.");

                        }
                    }catch(IllegalArgumentException e){
                        System.err.println("Unknown command.");
                        //e.printStackTrace();
                    }
                }else{
                    System.out.println("exit");
                    System.exit(0);
                }
            }
        }
    }
}
