package org.wizindia.black.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

/**
 * Created by nischal.k on 26/12/15.
 */
@Entity
@Table(name = "Context")
public class Context {

    @Id
    @Column(name = "context_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long contextId;

    @Column(name = "folderPath")
    private String folderPath;

    @Column(name = "max_file_size")
    private long maxFileSize;

    @Column(name = "min_file_size")
    private long minFileSize;

    @Column(name = "allowed_extensions")
    private String allowedExtensions;

    @Column(name = "auth")
    private boolean isAuthRequired;

    protected Context() {
    }

    public Context(String folderPath, long maxFileSize, long minFileSize, String allowedExtensions, boolean isAuthRequired) {
        this.folderPath = folderPath;
        this.maxFileSize = maxFileSize;
        this.minFileSize = minFileSize;
        this.allowedExtensions = allowedExtensions;
        this.isAuthRequired = isAuthRequired;
    }

    public long getContextId() {
        return contextId;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public long getMinFileSize() {
        return minFileSize;
    }

    public String getAllowedExtensions() {
        return allowedExtensions;
    }

    public boolean isAuthRequired() {
        return isAuthRequired;
    }
}
