/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache.redis;

import com.topcoder.security.TCSubject;
import com.topcoder.shared.dataAccess.Request;
import com.topcoder.web.common.cache.address.RequestAddress;
import com.topcoder.web.common.cache.address.TCSubjectAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides test helper methods.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class TestHelper {
    /**
     * Create RequestAddress instance.
     * 
     * @param keys the keys
     * @param values the values
     * @return the RequestAddress instance.
     * @throws Exception if any error occurs
     */
    public static RequestAddress createRequestAddress(String[] keys, String[] values) throws Exception {
        Map requestProps = new HashMap();
        
        for (int i = 0; i < keys.length; ++i) {
            requestProps.put(keys[i], values[i]);
        }
        
        Request req = new Request(requestProps);
        RequestAddress addrKey = new RequestAddress(req);
        
        return addrKey;
    }
    
    /**
     * Create TCSubjectAddress instance.
     * 
     * @param userId the user id
     * @param dataSource the data source
     * @return the TCSubjectAddress instance.
     */
    public static TCSubjectAddress createTCSubjectAddress(long userId, String dataSource) {
        TCSubject tcSubject = new TCSubject(userId);
        TCSubjectAddress subAddrKey = new TCSubjectAddress(tcSubject, dataSource);
        
        return subAddrKey;
    }
    
    
}
