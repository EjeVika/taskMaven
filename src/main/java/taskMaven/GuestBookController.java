package taskMaven;

import com.sun.prism.impl.Disposer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public interface GuestBookController {
    public void addMessage(String message) throws SQLException;
    List<Record> getRecords();
}
