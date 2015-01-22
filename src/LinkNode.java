/*
 * LinkNode.java
 *
 */


public class LinkNode {
    
    private String empName;
    private String jobTitle;
    private int salary;
    private int yearsEmp;
    private LinkNode next;
    
  
    public LinkNode(String name, String title, int sal, int years) { 
        empName = name;
        jobTitle = title;
        salary = sal;
        yearsEmp = years;
        next = null;
    } // LinkNode()
  
    public void setNext(LinkNode nextPtr) {
        next = nextPtr;
    } 
  
    public LinkNode getNext() {
        return next;
    } 
    
    public String getInfo()
    {
        return empName + "\t" + jobTitle + "\t"+ salary + "\t" + yearsEmp;
    }
    
    // added method for access to year information
     public int getYears()
    {
        return yearsEmp;
    }
     public String getName()
     {
         return empName;
     }
     public int getSal(){
         return salary;
     }

     public String getTitle(){
         return jobTitle;
     }
    
    
}
