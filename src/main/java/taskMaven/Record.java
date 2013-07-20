package taskMaven;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 20.07.13
 * Time: 11:31
 * To change this template use File | Settings | File Templates.
 */
public class Record {
    private long ID;
    private Date postDate;
    private String postMessage;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getPostMessage() {
        return postMessage;
    }

    public void setPostMessage(String postMessage) {
        this.postMessage = postMessage;
    }
}
