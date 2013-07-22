package taskMaven;


import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**

 */
public class GuestBook implements GuestBookController {
    private static final Logger log = Logger.getLogger(GuestBook.class);

    private final Connection connection;
    private final String tableName;
    private final String firstColumnName;
    private final String secColumnName;
    private final String thirdColumnName;

    public GuestBook(Connection connection, String tableName,
                     String firstColumnName,String secColumnName,String thirdColumnName){

        this.connection = connection;
        this.tableName = tableName;
        this.firstColumnName =firstColumnName;
        this.secColumnName = secColumnName;
        this.thirdColumnName = thirdColumnName;

    }


    @Override
    public void addMessage(String message) throws SQLException {

        Date currentDate = new Date();
        Timestamp sqlTime =  new Timestamp(currentDate.getTime());
        log.debug(currentDate.getTime());
        log.debug(sqlTime);


        String insertStr = "INSERT INTO "+tableName+
                "("+secColumnName+", "+thirdColumnName+")"+
                "VALUES"+
                "(?,?)";

        PreparedStatement addPrepStat = connection.prepareStatement(insertStr);

        addPrepStat.setTimestamp(1, sqlTime);
        addPrepStat.setString(2,message);
        addPrepStat.executeUpdate();
        //addPrepStat.close();
    }

    @Override
    public List<Record> getRecords() throws SQLException {
        List<Record> recordList = new ArrayList<>();
        String selectQuery ="SELECT * FROM "+tableName;
        Statement stat = connection.createStatement();
        ResultSet rs = stat.executeQuery(selectQuery);
        log.debug(rs);
        while (rs.next()) {
            Record r = new Record();
            log.debug(rs.getRow());
            r.setID(rs.getLong(firstColumnName));
            log.debug(rs.getLong(firstColumnName));
            r.setPostDate(new Date(rs.getTimestamp(secColumnName).getTime()));
            log.debug(new Date(rs.getDate(secColumnName).getTime()));
            r.setPostMessage(rs.getString(thirdColumnName));
            log.debug(rs.getString(thirdColumnName));
            log.debug(r.getID()+" "+r.getPostDate()+" "+r.getPostMessage());
            recordList.add(r);
        }
        //stat.close();
        return recordList;
    }

}
