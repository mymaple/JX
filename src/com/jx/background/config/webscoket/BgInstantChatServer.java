package com.jx.background.config.webscoket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import net.sf.json.JSONObject;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

/**
 * 即时通讯
 * @author FH QQ 123456789 2015-5-16
 */
public class BgInstantChatServer extends WebSocketServer {

	public BgInstantChatServer(int port) throws UnknownHostException {
		super(new InetSocketAddress(port));
	}

	public BgInstantChatServer(InetSocketAddress address) {
		super(address);
	}

	/**
	 * 触发连接事件
	 */
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		// this.sendToAll( "new connection: " + handshake.getResourceDescriptor() );
		// System.out.println("===" + conn.getRemoteSocketAddress().getAddress().getHostAddress());
	}

	/**
	 * 触发关闭事件
	 */
	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		userLeave(conn);
	}

	/**
	 * 客户端发送消息到服务器时触发事件
	 */
	@Override
	public void onMessage(WebSocket conn, String message) {
		message = message.toString();
		if (null != message && message.startsWith("[InstantChatJoin]")) {
			this.userJoin(message.replaceFirst("\\[InstantChatJoin\\]", ""), conn);
		}
		if (null != message && message.startsWith("[InstantChatLeave]")) {
			this.userLeave(conn);
		}
		if (null != message && message.contains("[InstantChatContent]")) {
			String toUser = message.substring(message.indexOf("[InstantChatContent]") + 10, message.indexOf("[InstantChatContent]"));
			message = message.substring(0, message.indexOf("[InstantChatContent]")) + "[私信]  " + message.substring(message.indexOf("[InstantChatContent]") + 12, message.length());
			BgInstantChatServerPool.sendMessageToUser(BgInstantChatServerPool.getWebSocketByUser(toUser), message);// 向所某用户发送消息
			BgInstantChatServerPool.sendMessageToUser(conn, message);// 同时向本人发送消息
		} else {
			BgInstantChatServerPool.sendMessage(message.toString());// 向所有在线用户发送消息
		}
	}

	public void onFragment(WebSocket conn, Framedata fragment) {
	}

	/**
	 * 触发异常事件
	 */
	@Override
	public void onError(WebSocket conn, Exception ex) {
		ex.printStackTrace();
		if (conn != null) {
			// some errors like port binding failed may not be assignable to a specific websocket
		}
	}

	/**
	 * 用户加入处理
	 * @param user
	 */
	public void userJoin(String user, WebSocket conn) {
		JSONObject result = new JSONObject();
		result.element("type", "user_join");
		result.element("user", "<a onclick=\"toUserMsg('" + user + "');\">" + user + "</a>");
		BgInstantChatServerPool.sendMessage(result.toString()); // 把当前用户加入到所有在线用户列表中
		String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + user + "上线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
		BgInstantChatServerPool.sendMessage(joinMsg); // 向所有在线用户推送当前用户上线的消息
		result = new JSONObject();
		result.element("type", "get_online_user");
		BgInstantChatServerPool.addUser(user, conn); // 向连接池添加当前的连接对象
		result.element("list", BgInstantChatServerPool.getOnlineUser());
		BgInstantChatServerPool.sendMessageToUser(conn, result.toString()); // 向当前连接发送当前在线用户的列表
	}

	/**
	 * 用户下线处理
	 * @param user
	 */
	public void userLeave(WebSocket conn) {
		String user = BgInstantChatServerPool.getUserByKey(conn);
		boolean b = BgInstantChatServerPool.removeUser(conn); // 在连接池中移除连接
		if (b) {
			JSONObject result = new JSONObject();
			result.element("type", "user_leave");
			result.element("user", "<a onclick=\"toUserMsg('" + user + "');\">" + user + "</a>");
			BgInstantChatServerPool.sendMessage(result.toString()); // 把当前用户从所有在线用户列表中删除
			String joinMsg = "{\"from\":\"[系统]\",\"content\":\"" + user + "下线了\",\"timestamp\":" + new Date().getTime() + ",\"type\":\"message\"}";
			BgInstantChatServerPool.sendMessage(joinMsg); // 向在线用户发送当前用户退出的消息
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		WebSocketImpl.DEBUG = false;
		int port = 8887; // 端口
		BgInstantChatServer s = new BgInstantChatServer(port);
		s.start();
		// System.out.println( "服务器的端口" + s.getPort() );
	}

}
