package org.wizindia.black.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.FinalFilePathContext;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.domain.Context;
import org.wizindia.black.domain.Feed;
import org.wizindia.black.jpa.FeedDao;
import org.wizindia.black.utils.EncryptionUtils;
import org.wizindia.black.utils.FeedUtils;

/**
 * Created by nischal.k on 11/12/15.
 */
public class FeedWorker {
    @Autowired
    FeedDao feedDao;
    @Autowired
    FeedUtils feedUtils;
    @Autowired
    EncryptionUtils encryptionUtils;

    public Feed save(Context context, String fileName, Long userId) {
        long unixTime = System.currentTimeMillis() / 1000L;
        Feed feed = new Feed(context, fileName, unixTime, unixTime, userId);
        return feedDao.save(feed);
    }

    public Feed getFeed(long feedId) {
        return feedDao.get(feedId);
    }

    public ContextRequest save(ContextRequest contextRequest) {
        Context context = new Context(encryptionUtils.escapeSpecialCharacter(contextRequest.getFolderPath()), contextRequest.getFolderPath(), contextRequest.getMaxFileSize(), contextRequest.getMinFileSize(), contextRequest.getAllowedExtensions(), contextRequest.isAuthRequired());
        return feedUtils.getContextRequest(feedDao.save(context));
    }

    public Context getContext(String contextId) {
        return feedDao.getContext(contextId);
    }

    public int markDeleted(long feedId) {
        return feedDao.markFeedDeleted(feedId);
    }
}
