package taskMaven;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class GuestBook implements GuestBookController {
//    private List<Record> records;
    private Connection connection;
    private String tableName;
    private String firstColumnName;
    private String secColumnName;
    private String thirdColumnName;

    public GuestBook(Connection connection, String tableName,
                     String firstColumnName,String secColumnName,String thirdColumnName){
//        this.records = new ArrayList<>();
        this.connection = connection;
        this.tableName = tableName;
        this.firstColumnName =firstColumnName;
        this.secColumnName = secColumnName;
        this.thirdColumnName = thirdColumnName;

    }


    @Override
    public void addMessage(String message) throws SQLException {
//        Record currentRecord = new Record();
        Date currentDate = new Date();
        SimpleDateFormat sDF =(SimpleDateFormat) SimpleDateFormat.getInstance();
        sDF.applyPattern("");
        long id = 1;
//        currentRecord.setID(id);
//        currentRecord.setPostDate(currentDate);
//        currentRecord.setPostMessage(message);
        String str = "INSERT INTO "+tableName+
                "("+firstColumnName+", "+secColumnName+", "+thirdColumnName+")"+
                "VALUES"+
                "(?,?,?)";

        Statement prepStat = connection.prepareStatement(str);
/*        String str = "INSERT INTO "+tableName+
                        "("+firstColumnName+", "+secColumnName+", "+thirdColumnName+")"+
                        "VALUES"+
                        "("+id+", "+currentDate+", "+message+")";
*/        prepStat.execute(id,currentDate,message);
    }

    @Override
    public List<Record> getRecords() {
//        return records;
        return null;
    }
}
