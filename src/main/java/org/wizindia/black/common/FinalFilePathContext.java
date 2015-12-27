package org.wizindia.black.common;

/**
 * Created by nischal.k on 12/12/15.
 */
public class FinalFilePathContext {
    private long feedId;
    private String contextId;

    public FinalFilePathContext(long feedId, String contextId) {
        this.feedId = feedId;
        this.contextId = contextId;
    }

    public long getFeedId() {
        return feedId;
    }

    public String getContextId() {
        return contextId;
    }
}
