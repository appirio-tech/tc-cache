package com.topcoder.web.common.cache.address;

import java.lang.reflect.InvocationTargetException;

import com.topcoder.security.TCSubject;
import com.topcoder.shared.dataAccess.RequestInt;
import com.topcoder.shared.util.TCResourceBundle;
import com.topcoder.web.common.cache.JbossCacheClient;
import com.topcoder.web.common.cache.MaxAge;

/**
 * @author dok
 * @version $Revision: 60728 $ Date: 2005/01/01 00:00:00
 *          Create Date: May 7, 2007
 */
public class AddressFactory {
    //add config so that we can swap out implementations easily.
    // between the different cache implementations

    private static final TCResourceBundle bundle = new TCResourceBundle("cache");
    
    private static final String JBOSS_REQUEST_CACHE_ADDRESS_CLASS = com.topcoder.web.common.cache.address.jboss.RequestAddress.class.getName();
    private static final String JBOSS_TC_SUBJECT_CACHE_ADDRESS_CLASS = com.topcoder.web.common.cache.address.jboss.TCSubjectAddress.class.getName();
    
    private static Class getCacheAddressClass(boolean tcSubjectAddress) {
    	String cacheAddressClazz = tcSubjectAddress ? 
    			bundle.getProperty("tc_subject_cache_address_class", JBOSS_TC_SUBJECT_CACHE_ADDRESS_CLASS) :
    			bundle.getProperty("request_cache_address_class", JBOSS_REQUEST_CACHE_ADDRESS_CLASS);
    	
    	try {
			return Class.forName(cacheAddressClazz);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

    }
    
    public static CacheAddress create(RequestInt request) {
    	Class cacheAddressClazz = getCacheAddressClass(false);
    	
    	try {
			return (CacheAddress) cacheAddressClazz.getConstructor(RequestInt.class).newInstance(request);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    	
    }

    public static CacheAddress create(RequestInt request, MaxAge maxAge) {
    	
    	Class cacheAddressClazz = getCacheAddressClass(false);
    	
    	try {
			return (CacheAddress) cacheAddressClazz.getConstructor(RequestInt.class, MaxAge.class).newInstance(request, maxAge);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    public static CacheAddress create(TCSubject subject, String dataSource) {
    	Class cacheAddressClazz = getCacheAddressClass(true);
    	
    	try {
			return (CacheAddress) cacheAddressClazz.getConstructor(TCSubject.class, String.class).newInstance(subject, dataSource);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

    public static CacheAddress create(TCSubject subject, String dataSource, MaxAge maxAge) {
    	
    	Class cacheAddressClazz = getCacheAddressClass(true);
    	
    	try {
			return (CacheAddress) cacheAddressClazz.getConstructor(TCSubject.class, String.class, MaxAge.class).newInstance(subject, dataSource, maxAge);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
    }

}
