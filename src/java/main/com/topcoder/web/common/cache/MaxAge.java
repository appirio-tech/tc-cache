/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache;

import com.topcoder.shared.dataAccess.DataAccessConstants;

/**
 * The expiration enumeration.
 * 
 * Changes in v1.1:
 * Two new values: ONE_SECOND and ONE_MINUTE are added.
 * 
 * @author dok, TCSDEVELOPER
 * @version 1.1
 * @since 1.0
 */
public enum MaxAge {
	ONE_SECOND(1000),
	ONE_MINUTE(1000 * 60),
    FIVE_MINUTES(1000 * 60 * 5), 
    QUARTER_HOUR(1000 * 60 * 15), 
    HALF_HOUR(1000 * 60 * 30), HOUR(1000 * 60 * 60), 
    THREE_HOUR(1000 * 60 * 60 * 3), 
    MAX(DataAccessConstants.DEFAULT_EXPIRE_TIME);
    private final int age;

    MaxAge(int age) {
        this.age = age;
    }

    /**
     * @return the age in milli
     */
    public int age() {
        return age;
    }
}
