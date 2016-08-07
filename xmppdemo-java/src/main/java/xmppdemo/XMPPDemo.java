package xmppdemo;

import java.io.IOException;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

public class XMPPDemo {
	private AbstractXMPPConnection connection;
	private XMPPTCPConnectionConfiguration config;

	private String userName = "a", userNameWithDomain = "a@windows-PC", password = "zaxscd", domain = "192.168.1.13",
			serviceName = "windows-PC";
	private int port = 5222;

	public void start() {
		System.out.println("Starting Connection");
		connect();
	}

	private void connect() {
		// connection = new XMPPTCPConnection(userName, password, domain);
		config = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(userName, password).setHost(domain)
				.setSecurityMode(SecurityMode.disabled).setServiceName(serviceName).setPort(port).build();

		connection = new XMPPTCPConnection(config);
		try {
			connection.connect();
			connection.login(userName, password);
			
			System.out.println("Connected to :" + connection.getHost());
			System.out.println("Connected as :" + connection.getUser());
			chatWithUser("b@windows-PC");
		} catch (SmackException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
	}

	private void chatWithUser(String username) {
		Chat chat = ChatManager.getInstanceFor(connection).createChat(username, new ChatMessageListener() {
			
			public void processMessage(Chat chat, Message msg) {
				// TODO Auto-generated method stub			
				System.out.println("Message Received : " + msg.getBody());
			}
		});
		
		try {			
			chat.sendMessage("Hello there!");
			System.out.println("Message sent.");
		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
	}

	private void listRosters() {

	}
}
