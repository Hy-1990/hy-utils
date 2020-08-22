package com.bigdata.hy_tools.stu;

import log.HyLogger;
import org.jivesoftware.smack.*;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.impl.JidCreate;

import java.io.IOException;

public class StuXMPP {
    public static void main(String[] args) throws Exception {
//        HyLogger.logger().info("haha {}","aa");
        XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
        configBuilder.setHost("192.168.3.128");
        configBuilder.setPort(5222);
        configBuilder.setUsernameAndPassword("huyi", "123456");
        configBuilder.setXmppDomain("192.168.3.128");
        configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        configBuilder.setConnectTimeout(Integer.MAX_VALUE);

        XMPPTCPConnection connection = new XMPPTCPConnection(configBuilder.build());

        connection.addConnectionListener(new ConnectionListener() {
            @Override
            public void connected(XMPPConnection xmppConnection) {

            }

            @Override
            public void authenticated(XMPPConnection xmppConnection, boolean b) {

            }

            @Override
            public void connectionClosed() {
                HyLogger.logger().info("connection close!");
                try {
                    connection.connect();
                    connection.login();
                } catch (Throwable throwable) {
                    HyLogger.logger().error("reconnect error", throwable);
                }
            }

            @Override
            public void connectionClosedOnError(Exception e) {
                HyLogger.logger().info("connections close on error!");
                try {
                    connection.connect();
                    connection.login();
                } catch (Throwable throwable) {
                    HyLogger.logger().error("reconnect error,", throwable);
                }
            }

            @Override
            public void reconnectionSuccessful() {

            }

            @Override
            public void reconnectingIn(int i) {

            }

            @Override
            public void reconnectionFailed(Exception e) {

            }
        });
        connection.connect();
        connection.login();
        connection.addAsyncStanzaListener(new HyListener(), stanza -> stanza instanceof Message);
        System.out.println("ok");
//        Message message = new Message();
//        message.setTo(JidCreate.from("admin@192.168.3.128"));
//        message.setFrom(JidCreate.from("huyi@192.168.3.128"));
//        message.setBody("hahahahahaha!");
//        message.setType(Message.Type.chat);
//        connection.sendStanza(message);
//        connection.disconnect();
        while (true){
            Thread.sleep(2000);
        }

    }
}
class HyListener implements StanzaListener{

    @Override
    public void processStanza(Stanza stanza) throws SmackException.NotConnectedException, InterruptedException {
        try{
            Message message = (Message) stanza;
//            if (message.getType() != Message.Type.chat) {
//                return;
//            }
            HyLogger.logger().info("receive message,from {}, type is {}, body is {}", message.getFrom(), message.getType(), message.getBody());

        }catch (Throwable throwable){
            HyLogger.logger().error(throwable);
        }
    }
}
