/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache.redis;

import java.io.Serializable;

/**
 * This serializable object used for test.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class SerializableObj implements Serializable {
    
    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 8842379275236692472L;
    
    /**
     * Int value.
     */
    private int intValue;
    
    /**
     * String value.
     */
    private String strValue;
    
    /**
     * Constructor
     */
    public SerializableObj() {}
    
    /**
     * Constructor
     * 
     * @param intValue int value
     * @param strValue String value
     */
    public SerializableObj(int intValue, String strValue) {
        this.intValue = intValue;
        this.strValue = strValue;
    }
    
    /**
     * Get the int value
     * 
     * @return the int value
     */
    public int getIntValue() {
        return intValue;
    }
    
    /**
     * Set the int value.
     * 
     * @param intValue the int value.
     */
    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }
    
    /**
     * Get the String value
     * 
     * @return the String value
     */
    public String getStrValue() {
        return strValue;
    }
    
    /**
     * Set the String value
     * 
     * @param strValue the String value
     */
    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    /**
     * Return true if the given object equals to this object.
     * 
     * @param obj the object to compare. 
     * @return true if the given object equals to this object, return false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (! (obj instanceof SerializableObj)) {
            return false;
        }
        
        SerializableObj other = (SerializableObj) obj;
        
        if (other.intValue != this.intValue) {
            return false;
        }
        
        if (other.strValue == null) {
            return this.strValue == null;
        } else {
            return other.strValue.equals(this.strValue);
        }
        
    }
    
}
