package org.wizindia.black.worker;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.FinalFilePathContext;
import org.wizindia.black.domain.Feed;
import org.wizindia.black.jpa.FeedDao;

import java.io.File;
import java.util.List;

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

    public FinalFilePathContext getFinalPath(String finalContext) {
        List<Feed> feedList = feedDao.get(finalContext);
        if(CollectionUtils.isNotEmpty(feedList)) {
            return new FinalFilePathContext(feedList.get(0).getFileName(), feedList.get(0).getContext());
        }
        return null;
    }
}
