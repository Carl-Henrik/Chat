package com.example.main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class clientHandler {
	JFrame frame = new JFrame();
	JButton button = new JButton("start new client!");
	
	
	public clientHandler(){
		frame.setLayout(new GridLayout());
		frame.add(button);
		
		frame.setSize(500, 70);
	    frame.setResizable(false);
	    frame.setVisible(true);
	    

	    frame.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent event){
	    	

	    		System.exit(0);
	    	
	    	}});
	    
	    
	    button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	        
            	
            	new mainFrame2().execute();
            	
            	}});
	    	    	    
}
}
