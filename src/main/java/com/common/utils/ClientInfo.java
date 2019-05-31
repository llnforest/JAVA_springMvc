package com.common.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**  
 *  
 * @author:wangzhen
 * @version:V1.0
 * 2018年8月22日  
 */
public class ClientInfo {
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getClientInfo(HttpServletRequest request){
		System.out.println("come in");
		HttpSession session = request.getSession();
		Map<String, String> map = (Map<String, String>) session.getAttribute(Const.SESSION_CLIENT);
		if(map == null){
			Map<String, String> newMap = new HashMap<String, String>();
			newMap.put("clientIp", getClientIp(request));
			newMap.put("clientOs", getClientOS(request));
			newMap.put("clientBrowser", getClientBrowser(request));
			newMap.put("clientName", getHostName(newMap.get("clientIp")));
			newMap.put("clientMac", getClientMac());
			session.setAttribute(Const.SESSION_CLIENT, newMap);
			return newMap;
		}
		return map;
	}
	/**
	 * 获取客户端名称
	 * 2019年2月13日
	 * @param ip
	 * @return
	 * @author:Lynn
	 */
	public static String getHostName(String ip){
		try{
			InetAddress inetAddress = InetAddress.getByName(ip);
			return inetAddress.getHostName();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 获取mac地址
	 * 2019年2月13日
	 * @return
	 * @author:Lynn
	 */
	public static String getClientMac(){
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
			StringBuffer sBuffer = new StringBuffer();
			for(int i = 0;i < mac.length;i ++){
				if(i != 0){
					sBuffer.append('-');
				}
				int temp = mac[i]&0xff;
				String str = Integer.toHexString(temp);
				if(str.length() == 1){
					sBuffer.append("0"+str);
				}else{
					sBuffer.append(str);
				}
			}
			return sBuffer.toString().toUpperCase();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 获取客户端ip
	 * 先从各代理请求头中找
	 * 处理本级ip
	 * 2018年8月23日
	 * @param request
	 * @return
	 * author:wangzhen
	 */
	public static String getClientIp(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	        ip = request.getRemoteAddr();
	        //如果是本机地址
	        if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){  
                //根据网卡取本机配置的IP  
                InetAddress inet=null;  
                try {  
                    inet = InetAddress.getLocalHost();  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                }  
                ip= inet.getHostAddress();  
            }  
	    }
	    if (ip.contains(",")) {
	        return ip.split(",")[0];
	    } else {
	        return ip;
	    }
	}
	
	
	/**
	 * 获取客户端操作系统
	 * 2018年8月23日
	 * @param request
	 * @return
	 * author:wangzhen
	 */
	public static String getClientOS(HttpServletRequest request){
		String userAgent = request.getHeader("user-agent");
        String cos = "unknow os";
        Pattern p = Pattern.compile(".*(Windows NT 6\\.1).*");
        Matcher m = p.matcher(userAgent);
        if(m.find()){
            cos = "Windows7";
            return cos;
        }
        p = Pattern.compile(".*(Windows NT 10\\.0).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "Windows10";
            return cos;
        }
        p = Pattern.compile(".*(Windows NT 5\\.1|Windows XP).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "WindowsXP";
            return cos;
        }
        p = Pattern.compile(".*(Windows NT 5\\.2).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "Windows2003";
            return cos;
        }
        p = Pattern.compile(".*(Win2000|Windows 2000|Windows NT 5\\.0).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "Windows2000";
            return cos;
        }
        p = Pattern.compile(".*(Mac|apple|MacOS8).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "MAC";
            return cos;
        }
        p = Pattern.compile(".*(WinNT|WindowsNT).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "WindowsNT";
            return cos;
        }
        p = Pattern.compile(".*Linux.*");
        if(m.find()){
            cos = "Linux";
            return cos;
        }
        p = Pattern.compile(".*68k|68000.*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "Mac68k";
            return cos;
        }
        p = Pattern.compile(".*(9x 4.90|Win9(5|8)|Windows 9(5|8)|95/NT|Win32|32bit).*");
        m = p.matcher(userAgent);
        if(m.find()){
            cos = "Windows9x";
            return cos;
        }
        return cos;
    }

	/**
	 * 获取客户端浏览器
	 * 2018年8月23日
	 * @param request
	 * @return
	 * author:wangzhen
	 */
	public static String getClientBrowser(HttpServletRequest request){
		  String agent = request.getHeader("user-agent").toLowerCase();
	      if(agent.indexOf("msie 7")>0){
	    	  return "IE7";
		  }else if(agent.indexOf("msie 8")>0){
			  return "IE8";
		  }else if(agent.indexOf("msie 9")>0){
			  return "IE9";
		  }else if(agent.indexOf("msie 10")>0){
			  return "IE10";
		  }else if(agent.indexOf("msie")>0){
			  return "IE";
		  }else if(agent.indexOf("opera")>0){
			  return "Opera";
		  }else if(agent.indexOf("opera")>0){
			  return "Opera";
		  }else if(agent.indexOf("firefox")>0){
			  return "Firefox";
		  }else if(agent.indexOf("webkit")>0){
			  return "Webkit";
		  }else if(agent.indexOf("gecko")>0 && agent.indexOf("rv:11")>0){
			  return "IE11";
		  }else{
			  return "Other Browser";
		  }
    }
}
