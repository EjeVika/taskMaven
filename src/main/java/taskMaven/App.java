package taskMaven;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

/**
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, IOException {

        Class.forName("org.h2.Driver");

        try(Connection connection = DriverManager.getConnection("jdbc:h2:mem:mydatabase","user1","password1")){

            // Creation of the table
            String tableName = "posts";
            String firstColumnName = "ID";
            String secColumnName = "postDate";
            String thirdColumnName = "postMessage";

            String tableCreation = "CREATE TABLE "+tableName+"(" +
                            firstColumnName+" INT AUTO_INCREMENT," +
                            secColumnName +" TIMESTAMP," +
                            thirdColumnName +" VARCHAR(1000)" +
                            ")";
            Statement stat1 = connection.createStatement();
            stat1.execute(tableCreation);
         //   stat1.close();

            GuestBookController myGuestBook =
                    new GuestBook(connection,tableName,firstColumnName,secColumnName,thirdColumnName);

            InputStream in = System.in;
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()){
                String userInput = sc.nextLine();
                if (!userInput.equals("quit")){
                    String[] mas = userInput.split(" ");
/* strange way to use enum in switch
                    Commands cmd = Commands.valueOf("ADD");
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
*/

                    switch (mas[0].toUpperCase()){
                        case "ADD":
                            StringBuilder message = new StringBuilder();
                            for (int i=1;i<mas.length;i++){
                                message = message.append(mas[i]).append(" ");
                            }
                            myGuestBook.addMessage(message.toString());
                            break;
                        case "LIST":
                            List<Record> records = myGuestBook.getRecords();
                            for (Record r:records){
                                System.out.println("Record #" + r.getID() + ". Time: " + formatDate(r.getPostDate()));
                                System.out.println(" - "+r.getPostMessage());
                            }
                            break;
                        default:
                            System.err.println("Unknown command.");

                    }
                }else{
                    System.out.println("exit");
                //    System.exit(0);
                    break;
                }
            }
        }
    }


    private static String formatDate(Date d){
        SimpleDateFormat simpleDF =(SimpleDateFormat) SimpleDateFormat.getInstance();
        simpleDF.applyPattern("HH:mm dd-MM-yyyy");
        StringBuffer strBuf = new StringBuffer();
        FieldPosition fp = new FieldPosition(1);
        simpleDF.format(d, strBuf, fp);
        return strBuf.toString();
    }
}
