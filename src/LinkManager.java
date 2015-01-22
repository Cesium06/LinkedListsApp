/*
 * LinkManager.java
 *
*/

import java.lang.*;
import java.util.*;
import java.io.*;

public class LinkManager { 
    
    LinkNode head = null;
    LinkedApplication myApp;
    
    /** Creates a new instance of LinkManager */
    public LinkManager(LinkedApplication a) {
        myApp = a;
    }
    
    public boolean initialize(String fileName){
        try {
            BufferedReader inStream                       // Create and 
                = new BufferedReader (new FileReader(fileName)); // Open the stream
            String name = "";
            String title = "";
            int sal = 0;
            int years = 0;
            String line = inStream.readLine();            // Read one line

            while (line != null) {                        // While more text
                
               name = line;

               line = inStream.readLine();               // Read next line
	       title = line;

               line = inStream.readLine();               // Read next line
               sal = Integer.parseInt(line);

               line = inStream.readLine();               // Read next line
               years = Integer.parseInt(line);
               
               LinkNode tempNode = new LinkNode(name, title, sal, years); 
               insert (tempNode);
               
               line = inStream.readLine();            // Read one line
        }
            inStream.close();   // Close the stream
            return true;
        }
    
        catch (java.io.FileNotFoundException e) {
            myApp.displayText("IOERROR: File NOT Found: " + fileName + "\n");
            return false; // invalid operation
            //e.printStackTrace();
        } 
        catch (java.io.IOException e) {
            myApp.displayText("IOERROR: " + e.getMessage() + "\n");
            return false; // invalid operation
            //e.printStackTrace();
        }
    }
    
    public boolean isEmpty()  
    {
        return head == null;
    }
    
    public void insert(LinkNode newNode) { 
    if (isEmpty()) 
        head = newNode;                   // Insert at head of list
    else {
        LinkNode current = head;   // Start traversal at head
        while (current.getNext() != null) // While not at the last node
            current = current.getNext();  //   go to the next node
        current.setNext( newNode );     // Do the insertion
    }      
} // insert()

    public String print() { 
    
    if (isEmpty()) 
        return "List is empty";
    
    String listContents = "";
    LinkNode current = head;          // Start traversal at head
    
    while (current != null) {                     // While not at end of list
        listContents = listContents + current.getInfo() + "\n"; //   append node's data to string
        current = current.getNext();              //   go to the next node
    }       
    return listContents;
    
} // print()

     // This method will traverse the existing list, referenced by head, and for each node, 
    // will remove the node from the current list and place it in the propoer location
    // of the new list which is sorted by year.
   
    public void sortByYears()
    {
        LinkNode sortedYearHead = null; // temp variable for start of new list
        LinkNode current = head; // pointer to current node (to be added)
        LinkNode temp; // will keep location of the next node to be placed from the current list
        LinkNode prevNode; // will be used in new list to keep track of node before next
        LinkNode nextNode; // will be used in new list to keep track of node after previous
        
        while (current!= null) // visit each node in the original list
        {
            if (sortedYearHead == null) // if there are no nodes in new list
            {
                sortedYearHead = current; // set location to new node
                head = current; // assign head of list to current node
                temp = current.getNext(); //Remember first node in the rest of the original list
                current.setNext(null); // remove link to current list
                current = temp; // set current back to next node to be placed (and to keep while loop active)
                

            } // end if
           
            else if (current.getYears() < head.getYears()) //Place node at front
            {
                 temp = current.getNext(); //Keep the rest of original list
                 head = current; // place new node at the head of the list
                 head.setNext(sortedYearHead); // attach rest of new list
                 sortedYearHead = head; // reassign new sorted Year Head
                 current = temp; // set current back to next node to be placed (and to keep while loop active)
            } //end else if
            
            else // node needs to be placed in middle or end of the list
            {
                temp = current.getNext();  //Keep the rest of original list
                prevNode = sortedYearHead; // set prev to head of list
                nextNode = sortedYearHead.getNext(); // set next to node after head
                
                // walk through new list to find location where new node should be placed
                // We need to use two pointers (prevNode and nextNode) if node will be placed
                // between two nodes.  If we do not use two pointers we will drop nodes from the 
                // list being built.
                 
                while ((nextNode != null) && (current.getYears() > nextNode.getYears()))
                {
                    prevNode = nextNode;
                    nextNode = nextNode.getNext();
                } //end  while
                
                // Since the above is a compound statement, we need to determine which condition
                // caused the loop to end.  If we reached the end (nextNode == null, then we know
                // that the new node needs to be placed at the end of the list.  If not, then it needs
                // to be placed between prevNode and nextNode.
                if (nextNode == null) //add to end
                {
                    prevNode.setNext(current); // at the end of the list, so add to end
                    current.setNext(null); // set next pointer to null
                    current= temp; // reset current back to next node to be placed in original list
                } //end if
                
                else //add to middle (place new node in between prevNode and nextNode)
                {
                    current.setNext(nextNode); // attached remainder of new list to current
                    prevNode.setNext(current); // link current node to rest of the list
                    current= temp;  // reset current back to next node to be placed in original list
                }   
             }// end else
        } //end while
    } // end sortByYears

     public void sortByName()
    {
        LinkNode sortedNameHead = null; // temp variable for start of new list
        LinkNode current = head; // pointer to current node (to be added)
        LinkNode temp; // will keep location of the next node to be placed from the current list
        LinkNode prevNode; // will be used in new list to keep track of node before next
        LinkNode nextNode; // will be used in new list to keep track of node after previous
        
        while (current!= null) // visit each node in the original list
        {
            if (sortedNameHead == null) // if there are no nodes in new list
            {
                sortedNameHead = current; // set location to new node
                head = current; // assign head of list to current node
                temp = current.getNext(); //Remember first node in the rest of the original list
                current.setNext(null); // remove link to current list, sortedNameHead is the same as current
                current = temp; // set current back to next node to be placed (and to keep while loop active)
                

            } // end if
           
            else if (current.getName().compareTo(head.getName()) < 0) //Place node at front
            {
                 temp = current.getNext(); //Keep the rest of original list
                 head = current; // place new node at the head of the list
                 head.setNext(sortedNameHead); // attach rest of new list
                 sortedNameHead = head; // reassign new sorted Year Head
                 current = temp; // set current back to next node to be placed (and to keep while loop active)
            } //end else if
            
            else // node needs to be placed in middle or end of the list
            {
                temp = current.getNext();  //Keep the rest of original list
                prevNode = sortedNameHead; // set prev to head of list
                nextNode = sortedNameHead.getNext(); // set next to node after head
                
                // walk through new list to find location where new node should be placed
                // We need to use two pointers (prevNode and nextNode) if node will be placed
                // between two nodes.  If we do not use two pointers we will drop nodes from the 
                // list being built.
                 
                while ((nextNode != null) && (current.getName().compareTo(nextNode.getName()) > 0))
                {
                    prevNode = nextNode;
                    nextNode = nextNode.getNext();
                } //end  while
                
                // Since the above is a compound statement, we need to determine which condition
                // caused the loop to end.  If we reached the end (nextNode == null, then we know
                // that the new node needs to be placed at the end of the list.  If not, then it needs
                // to be placed between prevNode and nextNode.
                if (nextNode == null) //add to end
                {
                    prevNode.setNext(current); // at the end of the list, so add to end
                    current.setNext(null); // set next pointer to null
                    current= temp; // reset current back to next node to be placed in original list
                } //end if
                
                else //add to middle (place new node in between prevNode and nextNode)
                {
                    current.setNext(nextNode); // attached remainder of new list to current
                    prevNode.setNext(current); // link current node to rest of the list
                    current= temp;  // reset current back to next node to be placed in original list
                }   
             }// end else
        } //end while
    } // end sortByName

     public void sortByTitleSal()
    {
        LinkNode sortedTitle = null; // temp variable for start of new list
        LinkNode current = head; // pointer to current node (to be added)
        LinkNode temp; // will keep location of the next node to be placed from the current list
        LinkNode prevNode; // will be used in new list to keep track of node before next
        LinkNode nextNode; // will be used in new list to keep track of node after previous
        
        while (current!= null) // visit each node in the original list
        {
            if (sortedTitle == null) // if there are no nodes in new list
            {
                sortedTitle = current; // set location to new node
                head = current; // assign head of list to current node
                temp = current.getNext(); //Remember first node in the rest of the original list
                current.setNext(null); // remove link to current list, sortedNameHead is the same as current
                current = temp; // set current back to next node to be placed (and to keep while loop active)
                

            } // end if
           
            else if ((current.getTitle().compareTo(head.getTitle()) < 0) && (current.getSal() > head.getSal()))//Place node at front  
            {
                 temp = current.getNext(); //Keep the rest of original list
                 head = current; // place new node at the head of the list
                 head.setNext(sortedTitle); // attach rest of new list
                 sortedTitle = head; // reassign new sorted Year Head
                 current = temp; // set current back to next node to be placed (and to keep while loop active)
            } //end else if
            
            else // node needs to be placed in middle or end of the list
            {
                temp = current.getNext();  //Keep the rest of original list
                prevNode = sortedTitle; // set prev to head of list
                nextNode = sortedTitle.getNext(); // set next to node after head
                
                // walk through new list to find location where new node should be placed
                // We need to use two pointers (prevNode and nextNode) if node will be placed
                // between two nodes.  If we do not use two pointers we will drop nodes from the 
                // list being built.
                 
                //This is where my issue is!! 
                while (((nextNode != null) && ((current.getTitle().compareTo(nextNode.getTitle()) > 0))) && (current.getSal() > nextNode.getSal()))
                {
                    prevNode = nextNode;
                    nextNode = nextNode.getNext();
                } //end  while
                
                // Since the above is a compound statement, we need to determine which condition
                // caused the loop to end.  If we reached the end (nextNode == null, then we know
                // that the new node needs to be placed at the end of the list.  If not, then it needs
                // to be placed between prevNode and nextNode.
                if (nextNode == null) //add to end
                {
                    prevNode.setNext(current); // at the end of the list, so add to end
                    current.setNext(null); // set next pointer to null
                    current= temp; // reset current back to next node to be placed in original list
                } //end if
                
                else //add to middle (place new node in between prevNode and nextNode)
                {
                    current.setNext(nextNode); // attached remainder of new list to current
                    prevNode.setNext(current); // link current node to rest of the list
                    current= temp;  // reset current back to next node to be placed in original list
                }   
             }// end else
        } //end while
    } // end sortByName
}
