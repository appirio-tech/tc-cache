package com.topcoder.web.common.cache;

import com.topcoder.shared.util.TCResourceBundle;

/**
 * @author dok
 * @version $Revision: 60445 $ Date: 2005/01/01 00:00:00
 *          Create Date: Apr 30, 2007
 */
public class CacheClientFactory {

    private static TCResourceBundle bundle = new TCResourceBundle("cache");
    
    private static final String JBOSS_CACHE_CLIENT = JbossCacheClient.class.getName();

    public static CacheClient create() {
    	String cacheClientClazz = bundle.getProperty("cache_client_class", JBOSS_CACHE_CLIENT);

        try {
        	return (CacheClient) Class.forName(cacheClientClazz).newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
    
}
