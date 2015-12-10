package org.wizindia.black.jpa;

import org.springframework.stereotype.Repository;
import org.wizindia.black.domain.Feed;

import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
@Repository(value = "FeedDao")
public interface FeedDao {
    Feed save(Feed feed);
    List<Feed> get(String context);
    Feed get(long feedId);
}
