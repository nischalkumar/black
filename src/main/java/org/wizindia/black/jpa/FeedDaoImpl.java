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
import org.wizindia.black.domain.Feed;
import org.wizindia.black.domain.Roles;
import org.wizindia.black.domain.User;

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
    public List<Feed> get(String context) {
        Session session = null;
        Transaction transaction = null;
        List<Feed> feedList = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            transaction.setTimeout(Configs.TIMEOUT);
            Query query =session.createQuery("from Feed where context= :login");
            query.setParameter("context", context);
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
}
