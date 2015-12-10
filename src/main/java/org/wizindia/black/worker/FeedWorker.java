package org.wizindia.black.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.domain.Feed;
import org.wizindia.black.jpa.FeedDao;

/**
 * Created by nischal.k on 11/12/15.
 */
public class FeedWorker {
    @Autowired
    FeedDao feedDao;
    public Feed save(String context, String fileName, Long userId) {
        long unixTime = System.currentTimeMillis() / 1000L;
        Feed feed = new Feed(context, fileName, unixTime, unixTime, userId);
        feedDao.save(feed);
        return feed;
    }
}
