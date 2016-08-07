package com.hisivasankar.xmppdemo;

import android.os.AsyncTask;
import android.util.Log;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.DomainBareJid;
import org.jxmpp.jid.EntityJid;
import org.jxmpp.jid.impl.JidCreate;
import org.jxmpp.stringprep.XmppStringprepException;

import java.io.IOException;

public class XMPPDemo {
    public static final String LOG_TAG = XMPPDemo.class.getSimpleName();
    private AbstractXMPPConnection connection;
    private XMPPTCPConnectionConfiguration config;

    private String userName = "a", userNameWithDomain = "a@windows-PC", password = "zaxscd", domain = "192.168.1.13",
            serviceName = "windows-PC";
    private int port = 5222;

    public void start() {
        Log.d(LOG_TAG, "Starting Connection");
        new AsyncTask<String, String, Void>(){
            @Override
            protected Void doInBackground(String... params) {
                try {
                    connect();
                } catch (XmppStringprepException e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.execute();

    }

    private void connect() throws XmppStringprepException {

        try {
            DomainBareJid service = JidCreate.domainBareFrom(serviceName);
            config = XMPPTCPConnectionConfiguration.builder().setUsernameAndPassword(userName, password).setHost(domain)
                    .setSecurityMode(SecurityMode.disabled).setServiceName(JidCreate.domainBareFrom(serviceName)).setPort(port).build();

            connection = new XMPPTCPConnection(config);
            connection.connect();
            connection.login(userName, password);
            Log.d(LOG_TAG, "Connected to :" + connection.getHost());
            Log.d(LOG_TAG, "Connected as :" + connection.getUser());
        } catch (SmackException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMPPException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chatWithUser(String username, String message) {
        try {
            EntityJid toUser = JidCreate.entityBareFrom(username);
            Chat chat = ChatManager.getInstanceFor(connection).createChat(toUser, new ChatMessageListener() {
                public void processMessage(Chat chat, Message msg) {
                    // TODO Auto-generated method stub
                    Log.d(LOG_TAG, "Message Received : " + msg.getBody());
                }
            });
            chat.sendMessage("Hello there! " + message);
            Log.d(LOG_TAG, "Message sent.");
        } catch (NotConnectedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (XmppStringprepException e) {
            e.printStackTrace();
        }
    }

    private void listRosters() {
        new AsyncTask<String, String, Void>(){
            @Override
            protected Void doInBackground(String... params) {
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };
    }


}
