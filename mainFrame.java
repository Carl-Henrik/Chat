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


public class mainFrame{
		
	
	
	
	public class server extends SwingWorker<Integer, Integer>{
		
		ArrayList<connection> connections;
		ServerSocket mySocket;
		int myPort;
		public int uniqueId;
		
		
		
		public Integer doInBackground() throws IOException{
			
			
		    button2.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e) {
	            	        
	            	try {cancel(true);} 
	            	catch (Exception e1) {e1.printStackTrace();}
	            	
	            	for (int i = connections.size(); --i >= 0;){
			        	  
			        	  connection temp = connections.get(i);
			        	  if (temp.mySocket != null) {
			        		  try {temp.mySocket.close();} 
			        		  catch (IOException e1) {report.append("Couldn't close socket.\n");}
			        		  }
	            	}}});
			
			
			
			connections = new ArrayList<connection>();
			myPort = Integer.parseInt(text.getText());
			
			
			try {mySocket = new ServerSocket(myPort);}
			catch (IOException ioEx){report.append("Port error\n");}
			
					
			while (!isCancelled()){
				
				report.append("Waiting for connection on port " + myPort + "\n");
				Socket client = mySocket.accept();
				if(isCancelled()) break;
				
				
				
				connection connection = new connection(client);
			
				connections.add(connection);
				connection.start();
			}
			
			report.append("Closing connection\n");
			return 1;
				
			}

	

	
		
	
		public class connection extends Thread{
			
			public Scanner input;
			public PrintWriter output;
			String message;
			Socket mySocket;
						
			public connection(Socket socket) throws IOException{
				 
				 mySocket = socket;
				 input = new Scanner(mySocket.getInputStream());
				 output = new PrintWriter(mySocket.getOutputStream(), true);
				 report.append("new user connected!\n");

			}
			
			
			public void run(){
				
		        boolean keepGoing = true;
		        while (keepGoing){
		            
		        	report.append("Server listening for messages!\n");
		            message = input.nextLine();
		            report.append("message recieved!\n");
		            String msg = message + "\n";
		            broadcast(msg);
				}
			}
				
				
			
			public synchronized void broadcast(String message) {
		    	
		          for (int i = connections.size(); --i >= 0;){
		        	  
		        	  connection temp = connections.get(i);
		        	  temp.write(message);
		        	}
		    	}
		    
		    
			private void write(String message) {
				
				output.println(message);
				report.append("message sent!\n");
		    }
			}
		}

	
	
	
	JButton button = new JButton("Start server!");
	JButton button2 = new JButton("Stop server!");
	JTextField text = new JTextField("Enter listening port");
	JTextArea report = new JTextArea(40,30);
	server server;
	
	
	
	
	public mainFrame(){
		
		JFrame frame = new JFrame();
		frame.setSize(400, 600);
	    frame.setResizable(false);
	    frame.setVisible(true);
	   
	    
	    JPanel northPanel = new JPanel();
	    northPanel.add(text);
	    frame.add(northPanel, BorderLayout.NORTH);
	    text.setEditable(true);
	
	    JScrollPane  centerPanel = new JScrollPane(report);
	    centerPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    frame.add(centerPanel, BorderLayout.CENTER);
	    report.setEditable(false);
	    	    
	    JPanel southPanel = new JPanel();
	    southPanel.add(button);
	    southPanel.add(button2);
	    frame.add(southPanel, BorderLayout.SOUTH);
		
	    
		    
	        
        
	    frame.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent event){
	    	
	    		System.exit(0);
	    	
	    	}});
	    
	    
	    
	    button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	
            	report.append("Starting Server!\n");
            	new server().execute(); 
            	
            	}});
        
       

        
        }
	   }
	
