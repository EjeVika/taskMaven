package taskMaven;

import java.sql.SQLException;
import java.util.List;

/**

 */
public interface GuestBookController {
    public void addMessage(String message) throws SQLException;
    List<Record> getRecords() throws SQLException;
}
