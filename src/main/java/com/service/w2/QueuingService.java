/**
 *
 * KcxxService.java
 * 2019年3月7日
 * author:Lynn
 */
package com.service.w2;

import java.util.ArrayList;

import com.service.BasePkNumService;

public interface QueuingService extends BasePkNumService {

	public String getProjectNames(ArrayList<String> projectArr);
}
