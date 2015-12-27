package org.wizindia.black.jpa;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
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
    HibernateUtil hibernateUtil;

    final static Logger logger = LoggerFactory.getLogger(FeedDaoImpl.class);

    @Override
    public Feed save(Feed feed) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            session.save(feed);
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return feed;
    }

    @Override
    public List<Feed> get(String context, String fileName) {
        Session session = null;
        Transaction transaction = null;
        List<Feed> feedList = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            Query query =session.createQuery("from Feed where context= :context and fileName= :fileName");
            query.setParameter("context", context);
            query.setParameter("fileName", fileName);
            feedList = query.list();
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return feedList;
    }

    @Override
    public Feed get(long feedId) {
        Session session = null;
        Transaction transaction = null;
        Feed feed = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            Query query =session.createQuery("from Feed where feedId= :feedId");
            query.setParameter("feedId", feedId);
            List<Feed> feedList = query.list();
            if(CollectionUtils.isNotEmpty(feedList))
                feed = feedList.get(0);
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return feed;
    }

    @Override
    public Context save(Context context) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            session.save(context);
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return context;
    }

    @Override
    public Context getContext(String contextId) {
        Session session = null;
        Transaction transaction = null;
        Context context = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            Query query =session.createQuery("from Context where contextId= :contextId");
            query.setParameter("contextId", contextId);
            List<Context> contextList = query.list();
            if(CollectionUtils.isNotEmpty(contextList))
                context = contextList.get(0);
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return context;
    }

    @Override
    public int markFeedDeleted(long feedId) {
        Session session = null;
        Transaction transaction = null;
        int rowsUpdated = 0;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            Query query =session.createQuery("update Feed set deleted= :deleted where feedId= :feedId");
            query.setParameter("deleted", true);
            query.setParameter("feedId", feedId);
            List<Context> contextList = query.list();
            rowsUpdated = contextList.size();
            transaction.commit();
        } catch (RuntimeException e) {
            try{
                transaction.rollback();
            }catch(RuntimeException rbe){
                logger.error("Couldn’t roll back transaction", rbe);
            }
            throw e;
        }
        finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return rowsUpdated;
    }
}
