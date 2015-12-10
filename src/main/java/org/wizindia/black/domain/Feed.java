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

    @Column(name = "context")
    private String context;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "created_on")
    private Long createdOn;

    @Column(name = "last_modified")
    private Long lastModified;

    @Column(name = "user_id")
    private Long userId;

    protected Feed() {}

    public Feed(String context, String fileName, Long createdOn, Long lastModified, Long userId) {
        this.feedId = (long)0;
        this.context = context;
        this.fileName = fileName;
        this.createdOn = createdOn;
        this.lastModified = lastModified;
        this.userId = userId;
    }

    public Long getFeedId() {
        return feedId;
    }

    public String getContext() {
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
