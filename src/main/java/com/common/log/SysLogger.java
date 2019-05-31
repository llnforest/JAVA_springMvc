/**  
* 2016年3月7日  
* SysLogger.java 
* author:wangZhen
*/ 
package com.common.log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 该类是一个调试日志类，用于进行系统调试日志的记录.
 * 提供调试日志的所有方法.
 * 该类目前是使用slf4j作为日志基类
 * @author wangZhen
 *
 */
public class SysLogger {
	
	private Logger logger;
	
    public SysLogger(Object obj)
    {
    	this.logger = LoggerFactory.getLogger(obj.getClass());
    }
    public SysLogger(Class<?> clsss)
    {
    	this.logger = LoggerFactory.getLogger(clsss);
    }
    
    public SysLogger(String logType)
    {
    	this.logger = LoggerFactory.getLogger(logType);
    }


    

    /**
     * 判断系统输出信息的开关是否已经打开.
     * 2017年3月31日
     * @return
     * author:wangzhen
     */
    public boolean isInfoEnabled()
    {

	return this.logger.isInfoEnabled();
    }

    /**
     * 该方法是判断当前系统的警告日志是否已经打开，如果打开则返回TRUE，否则返回FALSE.
     * 2017年3月31日
     * @return
     * author:wangzhen
     */
    public boolean isWarnEnabled()
    {
	return this.logger.isWarnEnabled();
    }


    /**
     * 该方法是判断当前的的调试日志是否打开，如果打开返回TRUE，如果没有打开返回FALSE.
     * 2017年3月31日
     * @return
     * author:wangzhen
     */
    public boolean isDebugEnabled()
    {
	return this.logger.isDebugEnabled();
    }


    /**
     * 判断出错日志是否打开.如果打开返回TRUE，如果关闭的话返回FALSE.
     * 2017年3月31日
     * @return
     * author:wangzhen
     */
    public boolean isErrorEnabled()
    {
	return this.logger.isErrorEnabled();
    }


    /**
     * 该方法是输出给定的信息.
     * 2017年3月31日
     * @param msg 需要被输出的调试信息.
     * @return
     * author:wangzhen
     */
    public void info(Object msg)
    {
	// 如果开关打开则输出信息
	if (this.isInfoEnabled())
	{
	    this.logger.info(getCaller("info") + " msg:" + msg);
	}
    }


    /**
     * 需要被输出的警告信息.
     * 2017年3月31日
     * @param msg 需要被输出的调试信息.
     * @return
     * author:wangzhen
     */
    public void warn(Object msg)
    {
	// 如果开关打开则输出信息
	if (this.isWarnEnabled())
	{
	    this.logger.warn(getCaller("warn") + " msg:" + msg);
	}
    }


    /**
     * 该方法是输出相应的调试信息.
     * 2017年3月31日
     * @param msg 需要被输出的调试信息.
     * @return
     * author:wangzhen
     */
    public void debug(Object msg)
    {
	if (this.isDebugEnabled())
	{

	    this.logger.debug(getCaller("debug") + " msg:" + msg);

	}
    }


    /**
     * 该方法是输出程序中的出错信息.
     * 2017年3月31日
     * @param msg 需要输出的出错信息.
     * @param arg
     * author:wangzhen
     */
    public void error(Object msg, Throwable arg)
    {
	if (this.isErrorEnabled())
	{
	    this.logger.error(getCaller("error") + " msg:" + msg, arg);
	    arg.printStackTrace();
	}
    }


    /**
     * 该方法是输出程序中的出错信息.
     * 2017年8月31日
     * @param msg 需要输出的出错信息.
     * author:wangzhen
     */
    public void error(Object msg)
    {
	if (this.isErrorEnabled())
	{
		String aa = getCaller("error") ;
	    this.logger.error(aa+ " msg:" + msg);
	}
    }



    /**
     * 该方法是获取对给定名称方法的调用者.
     * 2017年8月31日
     * @param mName
     * @return
     * author:wangzhen
     */
    public String getCaller(String mName)
    {
	    String re = "";
	    int i;
	    StackTraceElement stack[] = (new Throwable()).getStackTrace();
	    for (i = 0; i < stack.length; i++)
	    {
		StackTraceElement ste = stack[i];
		// 找到当前方法在栈中的位置
		if (ste.getClassName().equals("com.common.log.SysLogger")
			&& ste.getMethodName().equals(mName))
		{
		    int j = i + 1;
		    if (j < stack.length)
		    {
			ste = stack[j];
			re = ste.getClassName() + "." + ste.getMethodName() + "() line:" + ste.getLineNumber();
			break;
		    }
		}
	    }
	    return re;
    }

}
