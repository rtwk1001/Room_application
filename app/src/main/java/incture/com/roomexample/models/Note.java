package incture.com.roomexample.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Ritwik.Jain on 3/20/2018.
 */
@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int noteid;
    @ColumnInfo(name ="title")
    private String title;
    @ColumnInfo(name="content")
    private String content;
    @ColumnInfo(name="CreationDate")
    private String createdat;
    @Ignore
  public Note(){};
    public Note(String title, String content, String createdat) {
        this.title = title;
        this.content = content;
        this.createdat = createdat;
    }

    public int getNoteid() {
        return noteid;
    }

    public void setNoteid(int noteid) {
        this.noteid = noteid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }
}
