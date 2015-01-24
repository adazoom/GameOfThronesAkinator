package gui;
import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTree;
import ds.DefaultBinTreeNode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sun.audio.*;
import java.io.*;


public class RestrictedGUI extends JPanel{
/// instance variables
	 private JLabel question;
	 private BinTree exprTree;
	 private ImageIcon image;
	 //images
	 private JLabel labelImage;
	 private ImageIcon imageChar;
	 private ImageIcon imageYes;
	 private ImageIcon imageNo;
	 //current node
	 private BinTreeNode current;
	/**
	 * Constructor, invoked for a new instance
	 * Create GUI components.
	 * returns the created main panel
	 **/
	public  RestrictedGUI( BinTree exprTree)
	{
		this.exprTree=exprTree;
		setLayout (new BorderLayout());
		setBackground(Color.BLACK);
		//deal with the question label
		question=new JLabel();
		question.setOpaque (true);
		question.setHorizontalAlignment(SwingConstants.CENTER);
		
		//start the game
		start();
		//create panels to display question and create buttons
		add(createButtonPanel(), BorderLayout.SOUTH);
		add( question, BorderLayout.CENTER);
		add( labelImage, BorderLayout.NORTH);
	}
	/**
	 * Start method
	 */
	public void start(){
		System.out.println("game started");
		//set the question to the root first
		current=exprTree.getRoot();
		setQuestion(current.getData().toString());
		images();
	}
	/**
	 * Download images
	 */
	public void images(){
		//download the image of icon
		image = new ImageIcon("image.jpg");
		labelImage = new JLabel("", image, JLabel.CENTER);
		//download the image of charachters
		imageChar = new ImageIcon("char.jpg");
		//dowload the img of YES
		imageYes= new ImageIcon("yes.jpg");
		//dowload the img of NO
		imageNo= new ImageIcon("no.jpg");
	}
	/**
	 * Create Panel for buttons
	 */
	public JPanel createButtonPanel(){
		//create main panel to hold all buttons
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.BLACK);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		//create flow layoit panel to deal with YES/NO
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(createYesButton());
        buttonPanel.add(createNoButton());
        mainPanel.add(createCharactersButton(),BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        return mainPanel;
	}
	/**
	 * Create and return yes button.
	 **/
	public JButton createYesButton()
	{
		// create a button
	    JButton yesButton = new JButton( imageYes );
	    //yesButton.setPreferredSize(new Dimension(80, 50));
	    yesButton.setBackground(Color.BLACK);
	    yesButton.setOpaque(true);
	    yesButton.setBorderPainted(false);
	    // add an action listener for button's action (click)
	    yesButton.addActionListener(
	        new ActionListener()
	        {
	          /**
	           * Invoked when associated action is performed.
	           **/
	          public void actionPerformed( ActionEvent e )
	          {
	            // this is where you put the code you want
	            // executed when the button is pressed
	            // here, we call a method to update the display     
	        	  updateQuestion(1);
	        	
	          }
	        }
	        );
	    return yesButton;
	}
	/**
	 * Create and return no button.
	 **/
	public JButton createNoButton()
	{
		// create a button
	    JButton noButton = new JButton( imageNo );
	    noButton.setBackground(Color.BLACK);
	    noButton.setOpaque(true);
	    noButton.setBorderPainted(false);
	    // add an action listener for button's action (click)
	    noButton.addActionListener(
	        new ActionListener()
	        {
	          /**
	           * Invoked when associated action is performed.
	           **/
	          public void actionPerformed( ActionEvent e )
	          {
	            // this is where you put the code you want
	            // executed when the button is pressed
	            // here, we call a method to update the display     
	        	  updateQuestion(2);
	        	
	          }
	        }
	        );
	    return noButton;
	}
	/**
	 * Create and return show charachters button.
	 **/
	public JButton createCharactersButton()
	{
		// create a button
	    JButton charButton = new JButton( "Show available charachters", imageChar);
	    charButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	    charButton.setHorizontalTextPosition(SwingConstants.CENTER);
	    charButton.setBackground(Color.BLACK);
	    charButton.setForeground(new Color(100,230,250));
	    charButton.setOpaque(true);
	    charButton.setBorderPainted(false);
	    // add an action listener for button's action (click)
	    charButton.addActionListener(
	        new ActionListener()
	        {
	          /**
	           * Invoked when associated action is performed.
	           **/
	          public void actionPerformed( ActionEvent e )
	          {
	            // this is where you put the code you want
	            // executed when the button is pressed
	            // here, we call a method to update the display     
	        	  showChars();
	        	
	          }
	        }
	        );
	    return charButton;
	}
	
	
	/**
	 * Methos to list available chrachters
	 */
	public void showChars(){
		JOptionPane.showMessageDialog (null, "Daenerys Targaryen \nJoffrey Baratheon \nArya Stark \n" +
				"Jon Snow \nKhal Drogo \nBran Stark \nTheon Greyjoy \nSansa Stark \nJaime Lannister \n" +
				"Eddard Stark \nStannis Baratheon \nRenly Baratheon \nTyrion Lannister \nRobb Stark \nCersei Lannister \nMargaery Tyrell \n" +
				"Sandor Clegane(The Hound) \nPetyr Baelish \nYgritte \nCatelyn Stark \nJorah Mormont \nTywin Lannister \nRobert Baratheon \nVarys"
				, "You can choose to be...", JOptionPane.INFORMATION_MESSAGE);
	}
	 /**
	  * Update the label of question
	  * @return
	  */
	public JLabel setQuestion(String text)
	  {
	  	 question.setText(text);
	  	 question.setBackground(Color.BLACK);
	  	 Font myFont = new Font("Herculanum",Font.BOLD,15);
	  	 question.setFont(myFont);
	  	 question.setForeground(new Color(100,230,250));
	  	 question.setOpaque(true);
	  	
	  	  return question;
	  }

		/**
		 * Update the question
		 */
		public void updateQuestion(int answer){
			try{
			//if the answer is yes go to left child
			if(answer==1){
				setQuestion(current.getLeftChild().getData().toString());
				//update the root
				current=current.getLeftChild();
			}
			//if the answer is no go to right child
			if(answer==2){
				setQuestion(current.getRightChild().getData().toString());
				current=current.getRightChild();
			}}catch(NullPointerException e)
			{
				int restart= JOptionPane.YES_NO_OPTION;
				restart = JOptionPane.showConfirmDialog(null, "The Game is over. Do you want to restart the current chosen Game?", "Restart?", restart);
				if(restart == JOptionPane.YES_OPTION){
				    // yes option
					//set the root to the very first one again
					start();
				} else {
				    // no option
					System.exit(0);
				}
			}
		}

}
