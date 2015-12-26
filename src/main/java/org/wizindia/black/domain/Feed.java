package org.wizindia.black.domain;

import javax.persistence.*;

/**
 * Created by nischal.k on 10/12/15.
 */
@Entity
@Table(name = "feed")
public class Feed {

    @Id
    @Column(name = "file_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long feedId;

    @Column(name = "context_id")
    private Context context;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "last_modified")
    private Long lastModified;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "deleted")
    private boolean deleted;

    protected Feed() {}

    public Feed(Context context, String fileName, Long createdOn, Long lastModified, Long userId) {
        this.feedId = (long)0;
        this.context = context;
        this.fileName = fileName;
        this.createdOn = createdOn;
        this.lastModified = lastModified;
        this.userId = userId;
        this.deleted = false;
    }

    public Feed(Context context, String fileName, Long createdOn, Long lastModified, Long userId, boolean deleted) {
        this.context = context;
        this.fileName = fileName;
        this.createdOn = createdOn;
        this.lastModified = lastModified;
        this.userId = userId;
        this.deleted = deleted;
    }

    public Long getFeedId() {
        return feedId;
    }

    public Context getContext() {
        return context;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getCreatedOn() {
        return createdOn;
    }

    public Long getLastModified() {
        return lastModified;
    }

    public Long getUserId() {
        return userId;
    }
}
