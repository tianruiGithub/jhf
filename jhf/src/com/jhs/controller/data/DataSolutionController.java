package com.jhs.controller.data;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jhs.interceptor.MenuInterceptor;
import com.jhs.interceptor.SessionInterceptor;

/**
 * 方案Controller
 * @author liuyang
 *
 */
@Before({ SessionInterceptor.class, MenuInterceptor.class })
public class DataSolutionController extends Controller{

	public void get(){
		
	}
}
