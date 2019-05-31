package com.common.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2017年9月8日  
 */
public class StringUtilTest extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetUUID() {
		assertEquals(StringUtil.getUUID().length(), 32);
	}

	@Test
	public void testString2List() {
		fail("Not yet implemented");
	}

	@Test
	public void testUriMatches() {
		fail("Not yet implemented");
	}

}
