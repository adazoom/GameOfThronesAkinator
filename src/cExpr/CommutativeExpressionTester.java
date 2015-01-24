package cExpr;
import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTree;
import ds.DefaultBinTreeNode;

/**
 * CommutativeExpressionTester.java
 * CS 211 
 * Audrey St. John
 **/

/**
 * Testing expression i/o.
 * @author Audrey Lee
 **/
public class CommutativeExpressionTester
{
  /**
   * Read in xml file in args[0], print tree, then write back to file at args[1].
   **/
  public static void main( String[] args )
  {
    BinTree exprTree = CommutativeExpressionReader.readCommutativeExpr( args[0] );
    
    //exprTree.printInorder();
    
    System.out.println("the root is "+exprTree.getRoot().getData().toString());
    System.out.println("the YES is "+ exprTree.getRoot().getLeftChild().getData().toString());
    System.out.println("the NO is "+ exprTree.getRoot().getRightChild().getData().toString());
    //CommutativeExpressionWriter.writeCommutativeExpr( exprTree, args[1] );
    
  }
}
