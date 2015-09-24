package com.example.main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;


public class mainFrame2 extends SwingWorker{
	
	
	public class client extends SwingWorker{

		public Scanner input;
		public PrintWriter output;
		public Socket mySocket = null;
		
		
		protected Void doInBackground() throws Exception {
		
			ListenFromServer listenFromServer = new ListenFromServer();
			
		    frame.addWindowListener(new WindowAdapter(){
		    	public void windowClosing(WindowEvent event){
		    		
		    		if(listenFromServer != null){listenFromServer.interrupt();}
		    		try {
						disconnect();
					} catch (IOException e) {
						e.printStackTrace();
					}
		    		
		    	
		    	}});
			
			
			
		    button2.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent event) {
		    		
		        	if(listenFromServer != null){listenFromServer.interrupt();}
		        try {disconnect();} 
		        	catch (IOException e) {e.printStackTrace();}
		    		      	
		        	}});
			
			
			try {
				mySocket = new Socket(text1.getText(), Integer.parseInt(text0.getText()));
			} catch (NumberFormatException | IOException e) {
				System.out.println("Connection failed");
			}
			
			System.out.println("Connected to server!");
			
			input = new Scanner (mySocket.getInputStream());
			output = new PrintWriter(mySocket.getOutputStream(), true);
			
			 listenFromServer.start();				
			
		    
			button3.addActionListener(new ActionListener(){
			    public void actionPerformed(ActionEvent e) {
		    	     
			    	sendMessage(name + ": " + text3.getText() + "\n");
			    	
					}});
			return null;
			
			
			
			
							
		}
		
				
		
		public void sendMessage(String msg){
			
			output.println(msg);
			System.out.println("Message sent!");
			}
		
		
		public void disconnect() throws IOException{
			 if(mySocket != null) mySocket.close();
			 report.append("Disconnected!");
	}
	
		
		
		
		
		public class ListenFromServer extends Thread{
			
			public void run(){
				
				while(!Thread.currentThread().isInterrupted()){
					String msg = input.nextLine();
					report.append(msg + "\n");
				}
			}	
		}
	}

	
	
	
	
	
	
	JFrame frame = new JFrame();	
	JButton button = new JButton("Connect to server");
	JButton button2 = new JButton("Close connection");
	JButton button3 = new JButton("Send message");
	JTextField text1 = new JTextField("Enter server adress");
	JTextField text0 = new JTextField("Enter server port");
	JTextField text2 = new JTextField("Enter your name");
	JTextField text3 = new JTextField("Write message");
	JTextArea report = new JTextArea(40,30);
	String name;
	String port;
	client client;
	
	
	
	protected Void doInBackground() throws Exception {
		
		
		frame.setSize(500, 700);
	    frame.setResizable(false);
	    frame.setVisible(true);
	      
	        
	    JPanel northPanel = new JPanel();
	    northPanel.add(text1);
	    northPanel.add(text0);
	    northPanel.add(text2);
	    northPanel.add(button);
	    frame.add(northPanel, BorderLayout.NORTH);
	
	    
	    JScrollPane  centerPanel = new JScrollPane(report);
	    centerPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    frame.add(centerPanel, BorderLayout.CENTER);
	 
	    	    
	    
	    JPanel southPanel = new JPanel();
	    southPanel.add(text3);
	    southPanel.add(button3);
	    southPanel.add(button2);
	    frame.add(southPanel, BorderLayout.SOUTH);
		
		
	    text0.setEditable(true);
	    text1.setEditable(true);
	    text2.setEditable(true);
	    text3.setEditable(true);
	    report.setEditable(false);
	    
        
	    
	    

	    
	    
	    
	    button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	        
            	name = text2.getText();
            	new client().execute();
            	
            	}});
		return null;
	    	    	    
		
	

    	    	    
	    	    
 }
}
	
	
