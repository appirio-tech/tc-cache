/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache.address;

import java.util.Map;
import java.util.TreeMap;

import com.topcoder.shared.dataAccess.RequestInt;
import com.topcoder.web.common.cache.MaxAge;

/**
 * This class generates the cache key from the supplied RequestInt object.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class RequestAddress implements CacheAddress {
    /**
     * The generate cache key
     */
    private final String key;
    
    /**
     * Constructor with request.
     * 
     * @param request the request to generate the key
     */
    public RequestAddress(RequestInt request) {
        key = parseKey(request);
    }
    
    /**
     * Constructor with request and maxAge.
     * 
     * @param request the request to generate the key
     * @param maxAge ignored
     */
    public RequestAddress(RequestInt request, MaxAge maxAge) {
        this(request);
    }
    
    /**
     * Generate the key from the request
     * 
     * @param request the request to generate the key
     * @return the cache key
     */
    private static String parseKey(RequestInt request) {
        StringBuilder keyBuilder = new StringBuilder(100);
        Map props = new TreeMap(request.getProperties());
        for (Object o : props.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            keyBuilder.append("/");
            keyBuilder.append(entry.getKey());
            keyBuilder.append("=");
            keyBuilder.append(entry.getValue());
        }
        return keyBuilder.toString();
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
