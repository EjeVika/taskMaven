package taskMaven;

import java.util.Date;

/**

 */
class Record {
    private long id;
    private Date postDate;
    private String postMessage;

    public Record(long id,Date postDate, String postMessage){
        this.id = id;
        this.postDate = postDate;
        this.postMessage = postMessage;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
