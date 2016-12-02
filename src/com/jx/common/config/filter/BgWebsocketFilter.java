package com.jx.common.config.filter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.java_websocket.WebSocketImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jx.background.service.BgConfigService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.chat.BgInstantChatServer;
import com.jx.common.config.chat.BgOnlineManageServer;

/**
 * 创建人：FH 创建时间：2014年2月17日
 * @version
 */
public class BgWebsocketFilter extends BaseController implements Filter {
	@Autowired
	private BgConfigService bgConfigService ;
	
	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException {
		
		ServletContext sc = fc.getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);                
		if(cxt != null && cxt.getBean("bgConfigService") != null && bgConfigService == null)            
			bgConfigService = (BgConfigService) cxt.getBean("bgConfigService");  
		
		this.startWebsocketInstantChat();
		this.startWebsocketOnlineManage();
	}

	/**
	 * 启动即时聊天服务
	 */
	public void startWebsocketInstantChat() {
		
		         
		WebSocketImpl.DEBUG = false;
		BgInstantChatServer s;
		try {
			String port =  (bgConfigService.
					findConfigByConfigType(Const.CONFIG_BG_INSTANTCHAT_OBJ)).getParam2();
			if(port != null && !"".equals(port)){
				s = new BgInstantChatServer(Integer.parseInt(port));
				s.start();
			}
			// System.out.println( "websocket服务器启动,端口" + s.getPort() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动在线管理服务
	 */
	public void startWebsocketOnlineManage() {
		WebSocketImpl.DEBUG = false;
		BgOnlineManageServer s;
		try {
			String port =  (bgConfigService.
					findConfigByConfigType(Const.CONFIG_BG_ONLINEMANAGE_OBJ)).getParam2();
			if(port != null && !"".equals(port)){
				s = new BgOnlineManageServer(Integer.parseInt(port));
				s.start();
			}
			// System.out.println( "websocket服务器启动,端口" + s.getPort() );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 计时器
	public void timer() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 9); // 控制时
		calendar.set(Calendar.MINUTE, 0); // 控制分
		calendar.set(Calendar.SECOND, 0); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {

				// PersonService personService = (PersonService)ApplicationContext.getBean("personService");

				// System.out.println("-------设定要指定任务--------");
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}
