/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache.address;

import com.topcoder.security.TCSubject;
import com.topcoder.web.common.cache.MaxAge;

/**
 * This class generates the cache key from the supplied TCSubject and dataSource objects.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class TCSubjectAddress implements CacheAddress {

    /**
     * The generate cache key
     */
    private final String key;

    /**
     * Constructor with TCSubject.
     * 
     * @param sub the TCSubject to generate the key
     */
    public TCSubjectAddress(TCSubject sub) {
        this.key = "subject:" + sub.getUserId();
    }

    /**
     * Constructor with TCSubject and dataSource.
     * 
     * @param sub the TCSubject to generate the key
     * @param dataSource the dataSource used to generate the key
     */
    public TCSubjectAddress(TCSubject sub, String dataSource) {
        this.key = "subject:" + (dataSource == null ? "" : dataSource) + sub.getUserId();
    }
    
    /**
     * Constructor with TCSubject, dataSource and maxAge.
     * 
     * @param sub the TCSubject to generate the key
     * @param dataSource the dataSource used to generate the key
     * @param maxAge ignored
     */
    public TCSubjectAddress(TCSubject sub, String dataSource, MaxAge maxAge) {
        this(sub, dataSource);
    }
    
    /**
     * Return the key.
     * 
     * @return the key
     */
    @Override
    public String getKey() {
        return key;
    }
}
