package gui;
import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTree;
import ds.DefaultBinTreeNode;import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class UnrestrictedGUI extends JPanel{
	/// instance variables
		 private JLabel question;
		 private BinTree exprTree;
		 private ImageIcon image;
		 //images
		 private JLabel labelImage;
		 private ImageIcon imageYes;
		 private ImageIcon imageNo;
		 //current node and its parent
		 private BinTreeNode current;
		 private BinTreeNode parent;
		 //string of user's own answer
		 private String myAnswer;
		 private String myQuestion;
		 private int yesNo;
		/**
		 * Constructor, invoked for a new instance
		 * Create GUI components.
		 * returns the created main panel
		 **/
		public  UnrestrictedGUI( BinTree exprTree)
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
			image = new ImageIcon("Unimage.jpg");
			labelImage = new JLabel("", image, JLabel.CENTER);
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
				if(!(current.isLeaf())){
				//if the answer is yes go to left child
				if(answer==1){
					setQuestion(current.getLeftChild().getData().toString());
					//update the current
					parent=current;
					current=current.getLeftChild();
				}
				//if the answer is no go to right child
				if(answer==2){
					setQuestion(current.getRightChild().getData().toString());
					parent=current;
					current=current.getRightChild();
				}}
				//ask if I guessed right
				if( current.isLeaf()){
					int ask= JOptionPane.YES_NO_OPTION;
					ask = JOptionPane.showConfirmDialog(null, "Is my guess right?", "Am I right?", ask);
					if(ask == JOptionPane.YES_OPTION){
					    // yes option
						restart("I won!Copmuters will soon take over the World, but in the meantime do you want to restart the current chosen Game?");
					} else {
					    // no option
						//get users answer and question for it
						myAnswer= JOptionPane.showInputDialog("Please type in your answer. Please put in *You are * before your answer");
						//check whether the new answer is in the tree and if yes learn
						if(!(checkAnswer(exprTree.getRoot(), false))){
							//ask for question
							myQuestion= JOptionPane.showInputDialog("Please type in a question that would distinguish between "+myAnswer+" and my guess.");
							yesNo= JOptionPane.YES_NO_OPTION;
							yesNo = JOptionPane.showConfirmDialog(null, "Choose your answer for * "+myQuestion+ " * to get *"+myAnswer+" *", "Yes or No?", yesNo);
							if(yesNo == JOptionPane.YES_OPTION){
							    // yes option
								//set the root to the very first one again
								learn(1);
							} else {
							    // no option
								learn(2);
							}
							//restart the game
							restart("I have learned that. Thank you, m'Lord. Do you want to play again now?");
						}//if the answer is in the tree tell user he did something wrong
						else{
							JOptionPane.showMessageDialog (null, "You probably didn't answer some questions right, because I have your answer as one of my options. Go watch some more of The Game of Thrones!", "You don't know the Game well enough", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
			/**
			 * Method to restart the game
			 */
			public void restart(String text){
				int restart= JOptionPane.YES_NO_OPTION;
				restart = JOptionPane.showConfirmDialog(null, text, "Restart?", restart);
				if(restart == JOptionPane.YES_OPTION){
				    // yes option
					//set the root to the very first one again
					start();
				} else {
				    // no option
					System.exit(0);
				}
			}
			/**
			 * Method to check if the answer already is in the tree (recursive). Start at the root
			 */
			public boolean checkAnswer(BinTreeNode node, boolean check){
				if(!check){
				//check if the node and the answer match
				if(node.getData().toString().toLowerCase().contains(myAnswer.toLowerCase())){
					return true;
				}
				//pass the children nodes to check their values with the answer
				else{
					//if recursion made it to the leaf the answer is uniquie
					if(node.isLeaf()){
						return false;
					}
					//if node is not a leaf and doesnt equal to myAnswer check its children
					check=checkAnswer(node.getLeftChild(),check);
					check=checkAnswer(node.getRightChild(),check);
				}
				}
				return check;
			}
			/**
			 * Method to learn the new question and answer
			 */
			public void learn(int yesNo){
				//cfeate new question and new answer
				//replace the current node with the answer
				BinTreeNode newQ=new DefaultBinTreeNode(myQuestion);
				BinTreeNode newA=new DefaultBinTreeNode(myAnswer);
				//if the answer to new question was yes
				if(yesNo==1){
					//set the new answer as a left child and the right child a current
					newQ.setLeftChild(newA);
					newQ.setRightChild(current);
					//swap the new question with the current
					if(parent.getLeftChild().equals(current)){
						parent.setLeftChild(newQ);
					}
					else{
						parent.setRightChild(newQ);
					}
				}
				else{
					//set the new answer as a left child and the right child a current
					newQ.setLeftChild(current);
					newQ.setRightChild(newA);
					//swap the new question with the current
					if(parent.getLeftChild().equals(current)){
						parent.setLeftChild(newQ);
					}
					else{
						parent.setRightChild(newQ);
					}
				}
			}
}
