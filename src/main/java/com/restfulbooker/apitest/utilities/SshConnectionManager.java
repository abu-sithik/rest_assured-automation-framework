package com.restfulbooker.apitest.utilities;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;

public class SshConnectionManager {

		public static Session session;
		public static ChannelShell channel;
		public static String username = getProperties("UserName");
		public static String privateKey = getProperties("PrivateKey");
		public static String hostname = getProperties("HostName");
		public static String output = "" ;
		
		
		
		/*public static void main(String[] args){
		List<String> commands = new ArrayList<String>();
		commands.add("ls -l");
		
		executeCommands(commands);
		close();
		}
		*/
		
		public static Session getSession(){
		    if(session == null || !session.isConnected()){
		        session = connect(hostname,username,privateKey);
		    }
		    return session;
		}
		
		public static Channel getChannel(){
		    if(channel == null || !channel.isConnected()){
		        try{
		            channel = (ChannelShell)getSession().openChannel("shell");
		            channel.connect();
		
		        }catch(Exception e){
		            System.out.println("Error while opening channel: "+ e);
		        }
		    }
		    return channel;
		}
		
		public static Session connect(String hostname, String username, String password){
		
		    JSch jSch = new JSch();
		    
		    
		    try {
		    		jSch.addIdentity(privateKey);
		        session = jSch.getSession(username, hostname, 22);
		        Properties config = new Properties(); 
		        config.put("StrictHostKeyChecking", "no");
		        session.setConfig(config);
		
		        System.out.println("Connecting SSH to " + hostname + " - Please wait for few seconds... ");
		        session.connect();
		        System.out.println("Connected!");
		    }catch(Exception e){
		        System.out.println("An error occurred while connecting to "+hostname+": "+e);
		    }
		
		    return session;
		
		}
		
		public static void executeCommands(List<String> commands){
		
		    try{
		        Channel channel=getChannel();
		
		        System.out.println("Sending commands...");
		        sendCommands(channel, commands);
		
		        readChannelOutput(channel);
		        System.out.println("Finished sending commands!");
		
		    }catch(Exception e){
		        System.out.println("An error ocurred during executeCommands: "+e);
		    }
		}
		
		public static void sendCommands(Channel channel, List<String> commands){
		
		    try{
		        PrintStream out = new PrintStream(channel.getOutputStream(),true);
		        
		        for(String command : commands){
		            out.println(command);
		        }
		        out.println("exit");

		    }catch(Exception e){
		        System.out.println("Error while sending commands: "+ e);
		    }
		
		}
		
		public static void readChannelOutput(Channel channel){

		    byte[] buffer = new byte[1024];

		    try{
		        InputStream in = channel.getInputStream();
		        
		        while (true){
		            while (in.available() > 0) {
		                int i = in.read(buffer, 0, 1024);
		                if (i < 0) {
		                    break;
		                }
		                output = new String(buffer, 0, i);
		                System.out.println(output);
		            }

		            if(output.contains("logout")){
		                break;
		            }

		            if (channel.isClosed()){
		                break;
		            }
		            try {
		                Thread.sleep(1000);
		            } catch (Exception ee){}
		        }
		    }catch(Exception e){
		        System.out.println("Error while reading channel output: "+ e);
		    }

		}
	
		
		public static void close(){
		    channel.disconnect();
		    session.disconnect();
		    System.out.println("Disconnected channel and session");
		}
		
		public static String getProperties(String name) {
			
			String val = null;
			Helper getHelp = new Helper();
			getHelp.set_path("src/main/resources/Constants.properties");
			try {
				 val = getHelp.loadProperties(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return val;
		}
}