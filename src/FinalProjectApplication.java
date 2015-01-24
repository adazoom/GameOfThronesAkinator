import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTree;
import ds.DefaultBinTreeNode;
import gui.RestrictedGUI;
import gui.UnrestrictedGUI;

import java.awt.BorderLayout;
//import all packages

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cExpr.CommutativeExpressionReader;

import sun.audio.*;
import java.io.*;

/**
 * Main class that combines both parts to tun an ice cream shop
 * @author zhuma22a
 *
 */
public class FinalProjectApplication extends JFrame {
	//instance variables
	private static JLabel mainImg;
	private ImageIcon imageS;
	private ImageIcon imageUn;
	private ImageIcon imageBanner;
	//the frame to hold everything
	private static BinTree exprTree;
	private BorderLayout layout;
	private static JPanel mainPanel;
	/**
	 * main method starts the program
	 **/
	public static void main( String[] args )
	{
		exprTree = CommutativeExpressionReader.readCommutativeExpr( args[0] );
		FinalProjectApplication app= new FinalProjectApplication();	
		// create a new JFrame to hold IceCreamPanel
		JFrame questionsFrame = new JFrame();
		// set size
		questionsFrame.setSize( 700, 1200);
		questionsFrame.add(mainPanel);
		// show frame
		questionsFrame.show( true );
		// exit normally on closing the window
		questionsFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}
	/**
	 * Constructor to set everything up
	 */
	public FinalProjectApplication(){
		//create main panel to hold all buttons
				mainPanel = new JPanel(new BorderLayout());
				mainPanel.setBackground(Color.BLACK);
				//set up images and add everything
				images();
				mainPanel.add( mainImg, BorderLayout.CENTER);
				mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
				mainPanel.add(createMusicButton(), BorderLayout.NORTH);
				//get the layout to remove components later
				layout = (BorderLayout) mainPanel.getLayout();		
	}
	/**
	 * Images
	 */
	public void images(){
		//download the image of icon
				ImageIcon image = new ImageIcon("mainImg.jpg");
				mainImg = new JLabel("", image, JLabel.CENTER);
				imageS = new ImageIcon("shieldS.jpg");
				imageUn = new ImageIcon("shieldUn.jpg");
	}
	/**
	 * Create Panel for buttons
	 */
	public JPanel createButtonPanel(){
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.BLACK);
		//create flow layoit panel to deal with YES/NO
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(createRestrictedButton());
        buttonPanel.add(createUnrestrictedButton());
      
        return buttonPanel;
	}
	/**
	 * Create and return r button.
	 **/
	public JButton createRestrictedButton()
	{
		// create a button
	    JButton rButton = new JButton( imageS);
	    rButton.setBackground(Color.BLACK);
	    rButton.setOpaque(true);
	    rButton.setBorderPainted(false);
	    // add an action listener for button's action (click)
	    rButton.addActionListener(
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
	        	  //remove and change the panel
	        	mainPanel.remove(mainImg);
	        	mainPanel.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
	        	mainPanel.add( new RestrictedGUI( exprTree), BorderLayout.CENTER);
	        	mainPanel.revalidate();
	        	mainPanel.repaint();
	        	
	          }
	        }
	        );
	    return rButton;
	}
	/**
	 * Create and return unr button.
	 **/
	public JButton createUnrestrictedButton()
	{
		// create a button
	    JButton rButton = new JButton( imageUn);
	    rButton.setBackground(Color.BLACK);
	    rButton.setOpaque(true);
	    rButton.setBorderPainted(false);
	    // add an action listener for button's action (click)
	    rButton.addActionListener(
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
	        	  mainPanel.remove(mainImg);
	        	  mainPanel.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
	        	  mainPanel.add( new UnrestrictedGUI( exprTree), BorderLayout.CENTER);
	        	  mainPanel.revalidate();
	        	  mainPanel.repaint();
	          }
	        }
	        );
	    return rButton;
	}
	/**
	 * Create and return yes button.
	 **/
	public JButton createMusicButton()
	{
		// create a button
		//dowload the img of YES
		imageBanner= new ImageIcon("banner.jpg");
	    JButton mButton = new JButton( imageBanner );
	    mButton.setPreferredSize(new Dimension(800, 80));
	    mButton.setBackground(Color.BLACK);
	    mButton.setOpaque(true);
	    mButton.setBorderPainted(false);
	    mButton.setForeground(Color.WHITE);
	    // add an action listener for button's action (click)
	    mButton.addActionListener(new AL());
	    return mButton;
	}
	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e) {
			 music();
			 }
		}
	public static void music(){
		AudioPlayer MGP= AudioPlayer.player;
		AudioStream BGM;
		AudioData MD;
		ContinuousAudioDataStream loop=null;
		
		try {
			 InputStream test = new FileInputStream("myzuka.wav");
			//open a file
			BGM=new AudioStream(test);
			AudioPlayer.player.start(BGM);
			//place the data
			//MD= BGM.getData();
			//loop audio
			//loop= new ContinuousAudioDataStream (MD); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MGP.start(loop);
	}
}