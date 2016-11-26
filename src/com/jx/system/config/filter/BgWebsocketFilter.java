package com.jx.system.config.filter;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.java_websocket.WebSocketImpl;

import com.jx.system.config.BaseController;
import com.jx.system.config.Const;
import com.jx.system.config.chat.BgChatServer;
import com.jx.system.config.chat.BgOnlineChatServer;
import com.jx.system.util.Tools;

/**
 * 创建人：FH 创建时间：2014年2月17日
 * @version
 */
public class BgWebsocketFilter extends BaseController implements Filter {

	/**
	 * 初始化
	 */
	public void init(FilterConfig fc) throws ServletException {
		this.startWebsocketInstantMsg();
		this.startWebsocketOnline();
	}

	/**
	 * 启动即时聊天服务
	 */
	public void startWebsocketInstantMsg() {
		WebSocketImpl.DEBUG = false;
		BgChatServer s;
		try {
//			String strWebscoket = Tools.readTxtFile(Const.WEBSOCKET);// 读取WEBSOCKET配置,获取端口配置
			String strWebscoket = Const.WEBSOCKET;
			if (null != strWebscoket && !"".equals(strWebscoket)) {
				String strIW[] = strWebscoket.split(",fh,");
				if (strIW.length == 4) {
					s = new BgChatServer(Integer.parseInt(strIW[1]));
					s.start();
				}
			}
			// System.out.println( "websocket服务器启动,端口" + s.getPort() );
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动在线管理服务
	 */
	public void startWebsocketOnline() {
		WebSocketImpl.DEBUG = false;
		BgOnlineChatServer s;
		try {
//			String strWebscoket = Tools.readTxtFile(Const.WEBSOCKET);// 读取WEBSOCKET配置,获取端口配置
			String strWebscoket = Const.WEBSOCKET;
			if (null != strWebscoket && !"".equals(strWebscoket)) {
				String strIW[] = strWebscoket.split(",jx,");
				if (strIW.length == 4) {
					s = new BgOnlineChatServer(Integer.parseInt(strIW[3]));
					s.start();
				}
			}
			// System.out.println( "websocket服务器启动,端口" + s.getPort() );
		} catch (UnknownHostException e) {
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
