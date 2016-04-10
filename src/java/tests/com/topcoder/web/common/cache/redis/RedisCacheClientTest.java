/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache.redis;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.topcoder.web.common.cache.MaxAge;
import com.topcoder.web.common.cache.RedisCacheClient;
import com.topcoder.web.common.cache.address.RequestAddress;
import com.topcoder.web.common.cache.address.TCSubjectAddress;

/**
 * Unit test of RedisCacheClient with external Redis.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class RedisCacheClientTest {
    
    /**
     * The RedisCacheClient instance to test
     */
    private RedisCacheClient cacheClient;
    
    /**
     * Create the RedisCacheClient
     * 
     * @throws Exception if any error occurs
     */
    @Before
    public void setUp() throws Exception {
        cacheClient = new RedisCacheClient();
    }

    /**
     * Clear the cache
     * 
     * @throws Exception if any error occurs
     */
    @After
    public void tearDown() throws Exception {
        cacheClient.clearCache();
        cacheClient.close();
    }
    
    /**
     * Test the set/get/remove methods for String key and String value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_StringKey_StringValue() throws Exception {
        String key = "test1";
        String expectedStr = "value1";
        
        // set
        cacheClient.set(key, expectedStr);
        
        // get
        String actual = (String) cacheClient.get(key);
        Assert.assertEquals("The retrieved value doesn't match", expectedStr, actual);
        
        // remove
        actual = (String) cacheClient.remove(key);
        Assert.assertEquals("The removed value doesn't match", expectedStr, actual);
        
        // get again to check if it's removed
        actual = (String) cacheClient.get(key);
        Assert.assertNull("The key is removed", actual);
    }
    
    /**
     * Test the set/get/remove methods for String key and Object value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_StringKey_ObjectValue() throws Exception {
        String key = "test2";
        SerializableObj expectedObj = new SerializableObj(10, "value");
        
        // set
        cacheClient.set(key, expectedObj);
        
        // get
        SerializableObj actual = (SerializableObj) cacheClient.get(key);
        Assert.assertEquals("The retrieved value doesn't match", expectedObj, actual);
        
        // remove
        actual = (SerializableObj) cacheClient.remove(key);
        Assert.assertEquals("The removed value doesn't match", expectedObj, actual);
        
        // get again to check if it's removed
        actual = (SerializableObj) cacheClient.get(key);
        Assert.assertNull("The key is removed", actual);
    }
    
    /**
     * Test the set/get/remove methods for RequestAddress key and String value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_RequestAddressKey_StringValue() throws Exception {
        RequestAddress addrKey = TestHelper.createRequestAddress(new String[] {"key1"}, new String[] {"value1"});
        String expectedStr = "value1";
        
        // set
        cacheClient.set(addrKey, expectedStr);
        
        // get
        String actual = (String) cacheClient.get(addrKey);
        Assert.assertEquals("The value doesn't match", expectedStr, actual);
        
        // remove
        actual = (String) cacheClient.remove(addrKey);
        Assert.assertEquals("The removed value doesn't match", expectedStr, actual);
        
        // get again to check if it's removed
        actual = (String) cacheClient.get(addrKey);
        Assert.assertNull("The key is removed", actual);
        
    }
    
    /**
     * Test the set/get/remove methods for RequestAddress key and Object value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_RequestAddressKey_ObjectValue() throws Exception {
        RequestAddress addrKey = TestHelper.createRequestAddress(new String[] {"key2"}, new String[] {"value2"});
        SerializableObj expectedObj = new SerializableObj(10, "value");
        
        // set
        cacheClient.set(addrKey, expectedObj);
        
        // get
        SerializableObj actual = (SerializableObj) cacheClient.get(addrKey);
        Assert.assertEquals("The value doesn't match", expectedObj, actual);
        
        // remove
        actual = (SerializableObj) cacheClient.remove(addrKey);
        Assert.assertEquals("The removed value doesn't match", expectedObj, actual);
        
        // get again to check if it's removed
        actual = (SerializableObj) cacheClient.get(addrKey);
        Assert.assertNull("The key is removed", actual);
    }
    
    /**
     * Test the set/get/remove methods for TCSubjectAddress key and String value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_TCSubjectAddressKey_StringValue() throws Exception {
        TCSubjectAddress subAddrKey = TestHelper.createTCSubjectAddress(10, "jndi");
        String expectedStr = "value1";
        
        // set
        cacheClient.set(subAddrKey, expectedStr);
        
        // get
        String actual = (String) cacheClient.get(subAddrKey);
        Assert.assertEquals("The value doesn't match", expectedStr, actual);
        
        // remove
        actual = (String) cacheClient.remove(subAddrKey);
        Assert.assertEquals("The removed value doesn't match", expectedStr, actual);
        
        // get again to check if it's removed
        actual = (String) cacheClient.get(subAddrKey);
        Assert.assertNull("The key is removed", actual);
    }
    
    /**
     * Test the set/get/remove methods for TCSubjectAddress key and Object value. 
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_TCSubjectAddressKey_ObjectValue() throws Exception {
        TCSubjectAddress subAddrKey = TestHelper.createTCSubjectAddress(11, "db");
        SerializableObj expectedObj = new SerializableObj(11, "value2");
        
        // set
        cacheClient.set(subAddrKey, expectedObj);
        
        // get
        SerializableObj actual = (SerializableObj) cacheClient.get(subAddrKey);
        Assert.assertEquals("The value doesn't match", expectedObj, actual);
        
        // remove
        actual = (SerializableObj) cacheClient.remove(subAddrKey);
        Assert.assertEquals("The removed value doesn't match", expectedObj, actual);
        
        // get again to check if it's removed
        actual = (SerializableObj) cacheClient.get(subAddrKey);
        Assert.assertNull("The key is removed", actual);
    }
    
    /**
     * Test clearCache method
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_clearCache() throws Exception {
        // set multiple values
        cacheClient.set("key1", "value1");
        cacheClient.set("key2", new SerializableObj(13, "value2"));
        
        RequestAddress reqAddrKey = TestHelper.createRequestAddress(new String[] {"key3"}, new String[] { "value3"});
        cacheClient.set(reqAddrKey, "valuexx");
        
        TCSubjectAddress subAddrKey = TestHelper.createTCSubjectAddress(16, "tt");
        cacheClient.set(subAddrKey, "valuemm");
        
        // clear cache
        cacheClient.clearCache();
        
        Assert.assertNull("key1 is cleared", cacheClient.get("key1"));
        Assert.assertNull("key2 is cleared", cacheClient.get("key2"));
        Assert.assertNull("reqAddrKey is cleared", cacheClient.get(reqAddrKey));
        Assert.assertNull("subAddrKey is cleared", cacheClient.get(subAddrKey));
    }
    
    /**
     * Test set method with RequestAddress key and MaxAge to ensure the expiration works.
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_RequestAddressKey_withExpiration() throws Exception {
        RequestAddress reqAddrKey = TestHelper.createRequestAddress(new String[] {"key3"}, new String[] { "value3"});
        
        cacheClient.set(reqAddrKey, "testxxx", MaxAge.ONE_SECOND);
        
        // sleep 2 seconds
        Thread.sleep(2000);
        
        Assert.assertNull("The key is expired", cacheClient.get(reqAddrKey));
    }
    
    /**
     * Test set method with TCSubjectAddress key and MaxAge to ensure the expiration works.
     * 
     * @throws Exception if any error occurs
     */
    @Test
    public void test_TCSubjectAddressKey_withExpiration() throws Exception {
        TCSubjectAddress subAddrKey = TestHelper.createTCSubjectAddress(16, "tt");
        
        cacheClient.set(subAddrKey, "testxss", MaxAge.ONE_SECOND);
        
        // sleep 2 seconds
        Thread.sleep(2000);
        
        Assert.assertNull("The key is expired", cacheClient.get(subAddrKey));
    }
}
