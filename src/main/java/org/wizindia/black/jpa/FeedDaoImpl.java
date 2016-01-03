package org.wizindia.black.jpa;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.domain.Context;
import org.wizindia.black.domain.Feed;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nischal.k on 10/12/15.
 */
public class FeedDaoImpl implements FeedDao {
    @Autowired
    SessionFactory sessionFactory;

    final static Logger logger = LoggerFactory.getLogger(FeedDaoImpl.class);

    @Override
    public Feed save(Feed feed) {
        Session session = sessionFactory.getCurrentSession();
        session.save(feed);
        return feed;
    }

    @Override
    public List<Feed> get(String context, String fileName) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from Feed where context= :context and fileName= :fileName");
        query.setParameter("context", context);
        query.setParameter("fileName", fileName);
        return query.list();
    }

    @Override
    public Feed get(long feedId) {
        Feed feed = null;
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from Feed where feedId= :feedId");
        query.setParameter("feedId", feedId);
        List<Feed> feedList = query.list();
        if(CollectionUtils.isNotEmpty(feedList))
            feed = feedList.get(0);
        return feed;
    }

    @Override
    public Context save(Context context) {
        Session session = sessionFactory.getCurrentSession();
        session.save(context);
        return context;
    }

    @Override
    public Context getContext(String contextId) {
        Context context = null;
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from Context where contextId= :contextId");
        query.setParameter("contextId", contextId);
        List<Context> contextList = query.list();
        if(CollectionUtils.isNotEmpty(contextList))
            context = contextList.get(0);
        return context;
    }

    @Override
    public int markFeedDeleted(long feedId) {
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("update Feed set deleted= :deleted where feedId= :feedId");
        query.setParameter("deleted", true);
        query.setParameter("feedId", feedId);
        List<Context> contextList = query.list();
        return contextList.size();
    }
}
