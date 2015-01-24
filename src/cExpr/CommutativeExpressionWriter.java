package cExpr;
import ds.BinTree;
import ds.BinTreeNode;
import ds.DefaultBinTree;
import ds.DefaultBinTreeNode;
import java.io.*;

// XML
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;  
import javax.xml.parsers.FactoryConfigurationError;  
import javax.xml.parsers.ParserConfigurationException;
 
import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  

import org.w3c.dom.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult; 

/**
 * CommutativeExpressionWriter writes commutative arithmetic
 * expression trees to an xml format.
 * @author Audrey Lee
 */
public class CommutativeExpressionWriter
{   
   /** 
    * Writes commutative expression binary tree object to file.
    */
   public static boolean writeCommutativeExpr( BinTree tree, String file )
   {
      return writeCommutativeExpr( tree, new File( file ) );
   }

   /** 
    * Writes BodyAndBarStructure object to file.
    */
   public static boolean writeCommutativeExpr( BinTree tree, File file )
   {
   
      // obtain default parser
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      
      try 
      {
         // get DocumentBuilder
         DocumentBuilder builder = factory.newDocumentBuilder();
         
         Document document = builder.newDocument();
         
         // create root node
         Element root = createBinTreeElement( tree, document );
         	 
         // add root node to document
         document.appendChild( root );
	 
         // write document
         output( document, new StreamResult( file ) );
	 
         return true;
	 
      }
      catch ( ParserConfigurationException pce )
      {
         pce.printStackTrace();
      }
      return false;
   }

   /**
    * outputs document.
    **/
   private static void output( Document document, StreamResult result )
   {
      try 
      {
         // Use a Transformer for output
         TransformerFactory tFactory =
            TransformerFactory.newInstance();
         Transformer transformer = tFactory.newTransformer();

         DOMSource source = new DOMSource(document);
         transformer.transform(source, result);       
      } 
      catch (TransformerConfigurationException tce) 
      {
         // Error generated by the parser
         System.out.println ("\n** Transformer Factory error");
         System.out.println("   " + tce.getMessage() );

         // Use the contained exception, if any
         Throwable x = tce;
         if (tce.getException() != null)
            x = tce.getException();
         x.printStackTrace();
      } 
      catch (TransformerException te) 
      {
         // Error generated by the parser
         System.out.println ("\n** Transformation error");
         System.out.println("   " + te.getMessage() );

         // Use the contained exception, if any
         Throwable x = te;
         if (te.getException() != null)
            x = te.getException();
         x.printStackTrace();
      }
   }

   /**
    * creates expression element corresponding to tree.
    * @return Element for struct.
    **/
   private static Element createBinTreeElement( 
       BinTree tree, Document document )
   {
     // create element with tag name expr
     return createExprNodeElement( tree.getRoot(), document );     
   }

   /**
    * creates element corresponding to expression node.
    * @return Element for node.
    **/
  private static Element createExprNodeElement( BinTreeNode node, Document document )
  {
      Element nodeElt = document.createElement( "expr" );
      
      // base case
      if ( node.isLeaf() )
      {
    	  nodeElt.setAttribute("type","answer");
        // create atom node
        Element atomElt = document.createElement( "answer" );

        // set value
        atomElt.setAttribute( "value", node.getData().toString() );
        
        // add as only child
        nodeElt.appendChild( atomElt );
      } 
      // recursive case
      else
      {
    	 nodeElt.setAttribute("type","binary");
        // create operator node
        Element opElt = document.createElement( "question" );
        
        // set value
        opElt.setAttribute( "value", node.getData().toString() );

        // add as child
        nodeElt.appendChild( opElt );

        // recursively add operands
        // NOTE: order is *not* preserved
        if ( node.getLeftChild() != null )
          nodeElt.appendChild( createExprNodeElement( node.getLeftChild(), document ) );
        if ( node.getRightChild() != null )
          nodeElt.appendChild( createExprNodeElement( node.getRightChild(), document ) );
      }
      return nodeElt;
   }

}
