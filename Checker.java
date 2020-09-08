
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Checker {
	


	public static void main(String[] args) throws IOException  {
		
		
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the file name: "); 
		
		String file = input.nextLine();
		
		FileReader fr =new FileReader(file); 
		
		String Sequence = " ";
		
		int x= 0;
		while ( (x = fr.read()) != -1	){
			Sequence += (char)x; // sets sequence equal to fill read in
		}

		
		System.out.println("Enter the start name: ");
		
		file = input.nextLine();
		
		fr =new FileReader(file); 
		String start = "";
		x= 0;
		while ( (x = fr.read()) != -1	){
			start += (char)x; // sets sequence equal to fill read in
		}
		
		System.out.println("Enter the end name: ");
		
		file = input.nextLine();
		
		fr =new FileReader(file); 
		String end = "";
		x= 0;
		while ( (x = fr.read()) != -1	){
			end += (char)x; // sets sequence equal to fill read in
		}
				
		
		ArrayList<String> Windows = new ArrayList<String>();
		start = "AAA" ; 
		end  = "ATA" ;
		String test = "ATTCGCAAATGTCCCTGACATTTGCATATGCTATTCGCAAATGTTGAATGCATAATTCGCAAATGTGTTGAACCGCATA";
		String comparison = "TGTCCTGAATTGC";
		String temp = "";
		String check= "";
		
		//STree st = new STree();

       // st.sTreeOf = test;

        //st.createTree();

        //System.out.println(st);
		
		int num =0;
		int counter = 0 ;
		
		while(test.length() > counter+1 ){
			
			 if( test.length() > counter + start.length()){
				 check = test.substring(counter, counter + start.length());
				 
				 if(start.equals(check)){
					 counter = counter + start.length();
				
					 while(test.length() > counter+1){
					
						 if(test.length() > counter + end.length()-1){
							check = test.substring(counter, counter + start.length());
						 	if(end.equals(check)){
						 		break;
						 	}
						 	else{
						 		temp = temp + test.charAt(counter);
							 	counter++;
						 	}
						 }
						 else{
						 	temp = temp + test.charAt(counter);
						 	counter++;
						 }
					 }
					 if(temp.length() > 0 ){
						 num++;
						 System.out.println(" string " + num + " " + temp );
						 Windows.add(temp);
						 temp = "";
				
					 }
				
				 }
			 }
			counter++;
		}
		
		compare(Windows, comparison);
	
	}
	
	class STree {

	    ArrayList<Node> rootNodes;

	    String sTreeOf;

	    Index endIndex;

	 

	    public STree() {

	        rootNodes = new ArrayList<Node>();

	        sTreeOf = "";

	    }

	 

	public void createTree(){

	           endIndex = new Index(0);

	    boolean rootNodeExists = false;

	    for (int i = 0; i < sTreeOf.length(); i++){

	        endIndex.setIndex(i);

	        for(Node n: rootNodes){

	            if (sTreeOf.charAt(n.startIndex.index) == sTreeOf.charAt(i)){

	                      //System.out.println(sTreeOf.charAt(i) +" is equal to " + sTreeOf.charAt(n.startIndex.index));

	                rootNodeExists = true;

	                break;

	            }



	            checkNSplit(i, n);

	            checkChildrenNodes(i,n);

	 

	        }

	        if (!rootNodeExists){

	                      //System.out.println(i+ " , " + endIndex.index);

	            rootNodes.add(new Node(new Index(i), endIndex,"root"));

	        }

	        else {

	           //System.out.println(sTreeOf.charAt(i));

	           rootNodeExists = false;

	        }

	    }

	}

	 

	private void checkChildrenNodes(int i, Node n) {

	    if(n.ChildrenNodes.size() > 0)

	           for(Node cN: n.ChildrenNodes) {

	                      //System.out.println("ChildNodeCheck");

	                      //if(cN.ChildrenNodes.size() > 0) checkChildrenNodes(i,cN);

	                      checkNSplit(i,cN);

	           }

	}

	 

	private void checkNSplit(int i, Node n) {

	    if (i>0 && n.startIndex.index+1<sTreeOf.length()){

	                      String check = Character.toString(sTreeOf.charAt(i-1));

	                      check += sTreeOf.charAt(i);

	                      String check2 = "";

	                      String tempString = sTreeOf.substring(n.startIndex.index, n.endIndex.index+1);

	                      int tempIndex = tempString.indexOf(sTreeOf.charAt(i-1));

	           if (tempIndex > -1) {

	                      int newIntNum = n.startIndex.index + tempIndex;

	                      check2 = Character.toString(sTreeOf.charAt(newIntNum));

	                      check2 += Character.toString(sTreeOf.charAt(newIntNum+1));

//	                  //check = sTreeOf.substring(n.startIndex.index, i+1);

	               if (!check.equals(check2) && (!check.equals(n.Check) && !check2.equals(n.Check)) && (sTreeOf.charAt(newIntNum) == sTreeOf.charAt(i-1)))

	                      //if (!check2.contains(check) && (sTreeOf.charAt(n.startIndex.index) == sTreeOf.charAt(i-1)))

	               {

	                      //System.out.println(check2 +" does not contain " + check);

	                      if (n.ChildrenNodes.isEmpty()) {

	                                 //System.out.println(n.startIndex.index+ " , " + n.endIndex.index );

	                                 n.ChildrenNodes.add(new Node(n, new Index(newIntNum+1), endIndex,check));

	                                 n.ChildrenNodes.add(new Node(n, new Index(i), endIndex,check));

	                                 n.endIndex = new Index(newIntNum);

//	                             n.ChildrenNodes.add(new Node(new Index(n.startIndex.index+1), endIndex));

//	                             n.ChildrenNodes.add(new Node(new Index(i), endIndex));

//	                             n.endIndex = new Index(n.startIndex.index);

	                      }

	                      else {

	                                 //

	                                 n.ChildrenNodes.add(new Node(n, new Index(newIntNum+1), endIndex,check));

	                                 n.endIndex = new Index(newIntNum);

	                      }

	               }

	    }

	  }

	}

	 

	
//    public String returnSequence(String start, String end){
//    	int s;
//    	int e;
//    	String pattern;
//    	for(Node n: rootNodes){
//    		if(sTreeOf.indexOf(start.charAt(0)) == n.startIndex.index){
//    			if (sTreeOf.substring(n.startIndex.index, n.endIndex.index).equals(start)){
//    				s = n.endIndex.index;
//    			}
//    			else if (n.ChildrenNodes.size() > 0){
//    				int newLen = 0 + sTreeOf.substring(n.startIndex.index, n.endIndex.index=1).length();
//    			}
//    		}
//    		else{
//    			continue;
//    		}
//    	}
//    	return sTreeOf.substring(s+1,e);
//    }
//    
//    public String returnSequenceFromChildren(String start, int startI, int len, Node nN){
//    	int s;
//    	int newLen = len + sTreeOf.substring(startI, nN.endIndex.index=1).length();
//    	for(Node n: nN.ChildrenNodes){
//    		if(sTreeOf.indexOf(start.charAt(0+len)) == n.startIndex.index){
//    			if (sTreeOf.substring(n.startIndex.index, n.endIndex.index).equals(start)){
//    				s = n.endIndex.index;
//    			}
//    			else if( n.ChildrenNodes.size() > 0){
//    				return returnSequenceFromChildren(start, startI, len + newLen, n);
//    			}
//    		}
//    		else{
//    			continue;
//    		}
//    	}
//    	return sTreeOf.substring(s+1,e);
//    }
//	 

	    @Override

	    public String toString() {

	        String returnString = "";

	        for (Node n : rootNodes) {

	            returnString += n.printChildren(sTreeOf, 0);

	        }

	        return returnString;

	    }

	}

	 

	class Node{

	    Node parentNode;

	    ArrayList<Node> ChildrenNodes;

	    Index startIndex;

	    Index endIndex;

	    String Check;

	 

	    public Node(){

	        this.ChildrenNodes = new ArrayList<Node>();

	        this.startIndex = new Index(0);

	        this.endIndex = new Index(0);

	        this.Check = "";

	    }

	   

	    public Node(Index s, Index e, String check){ 

	        this.ChildrenNodes = new ArrayList<Node>();

	        this.startIndex = s;

	        this.endIndex = e;

	       this.Check = check;

	    }

	 

	    public Node(Node n, Index s, Index e, String check){ 

	        this.parentNode = n;

	        this.ChildrenNodes = new ArrayList<Node>();

	        this.startIndex = s;

	        this.endIndex = e;

	        this.Check = check;

	    }

	 

	    public void addChildNode(Node n){

	        ChildrenNodes.add(n);

	    }

	 

	    public String toStringSegment(String s, int nodeNum){

	           return "Node "+ nodeNum +": " + getSuffix(s);

	    }

	   

	    public String getSuffix(String s) {

	           //return ChildrenNodes.size() > 0 ? Character.toString(s.charAt(startIndex.index)) : s.substring(startIndex.index, endIndex.index + 1);

	           return s.substring(startIndex.index, endIndex.index + 1);

	    }

	   

	    @Override

	    public String toString() {

	           return startIndex.toString() + "," + endIndex.toString();

	    }

	 

	    public String printChildren(String s, int i){

	        String returnString = "";

	        returnString += toStringSegment(s, i) + "\n";

	        if (ChildrenNodes.size() > 0)

	        for(Node n : ChildrenNodes){

	           i+=1;

	            returnString += ChildrenNodes.size() == 0 ? n.toStringSegment(s,1) + "\n" : n.printChildren(s, i);

	        }

	        return returnString;

	    }
	    


	}

	 

	class Index{

	    int index;

	    public Index(){

	        this.index = 0;

	    }

	    public Index(int index){

	        this.index = index;

	    }

	    public void setIndex(int index){

	        this.index = index;

	    }

	   

	    @Override

	    public String toString() {

	           return Integer.toString(index);

	    }

	}
	
	public static void compare(ArrayList<String> Windows, String comparison){
		
		int which = 0;
		String temp = "";
		int insertion = 0;
		int deletion = 0;
		int thing = temp.length();
		
		
		while(Windows.size() > which ){
			temp = Windows.get(which);
			String temp2 = comparison;
		
			for(int i = 0; i < temp.length()-1 || i < temp2.length()-1; i++){
				
				if( temp.length() > thing ){
					deletion = temp.length() - thing;
					thing = temp.length();
				}
				
				if(i < temp2.length() && i < temp.length() && temp2.charAt(i) != temp.charAt(i )){
					
					if(temp2.length() == temp.length()){
						for(int j =0; j < temp2.length(); j++ ){
							if(temp2.charAt(i+j) == temp.charAt(i+j)){
								System.out.println( " point replacement between " + i + " and " + (i + j));
								i = i + j;
								break;
							}
							if(temp.charAt(i+j)!= 'A' && temp.charAt(i+j)!= 'C' && temp.charAt(i+j)!= 'G' && temp.charAt(i+j)!= 'T'){
								int c = i+j;
								checkTempLetter(temp, temp2, c);
							}
							else if(temp2.charAt(i+j)!= 'A' && temp2.charAt(i+j)!= 'C' && temp2.charAt(i+j)!= 'G' && temp2.charAt(i+j)!= 'T'){
								int c = i+j;
								checkTemp2Letter(temp, temp2, i );
							}
								
							
						}
						
					}
					else if(temp2.length() < temp.length()){
						
						temp2 = checkInsertion(temp2, temp, i, insertion);
						
						
					}
					else if(temp2.length() > temp.length()){
						i = i + deletion;
						temp = checkDeletion(temp2, temp, i, deletion);
						
					}
						
					}
					else{
						
					}
					
					
					
				
			}
			System.out.println( " refrance string " + temp2);
			System.out.println( " test string     " + temp);
		
			deletion = 0;
			insertion = 0;
			which++;
		}
		
	}
	
	
	
	
	
	public static String checkInsertion(String temp2 , String temp , int i , int insertion){
		
		
		int num = i;
		int tmp = i;
		while(num < temp.length()-1 /*|| num < comparison.length()-1*/ ){
			
			if(temp.charAt(num)!= 'A' && temp.charAt(num)!= 'C' && temp.charAt(num)!= 'G' && temp.charAt(num)!= 'T'){
				checkTempLetter(temp, temp2, num );
			}
			else if(temp2.charAt(num)!= 'A' && temp2.charAt(num)!= 'C' && temp2.charAt(num)!= 'G' && temp2.charAt(num)!= 'T'){
				checkTemp2Letter(temp, temp2, num );
			}
			
			if(temp2.charAt(i) == temp.charAt(num+insertion)){
				break;
			}
			else{
				num++;
			}
			
		}
		
			
			
			if(temp2.charAt(i) != temp.charAt(i)){
				temp2 = temp2.substring(0, i) + "-" + temp2.substring(i, temp2.length());
			}
			
			while (temp.charAt(tmp) != temp2.charAt(tmp)){
				
				tmp++;
			}
			
			System.out.println( " insertion between " + i + " and " + tmp);
		
		return temp2;
		
	}
	
	
	
	public static String checkDeletion(String temp2 , String temp , int i , int deletion){
		
		int num = i;
		int tmp = i;
		
		
		while(num < temp.length()-1 || num < temp2.length()-1 ){
			
			if(temp2.charAt(num)!= 'A' && temp2.charAt(num)!= 'C' && temp2.charAt(num)!= 'G' && temp2.charAt(num)!= 'T'){
				checkTemp2Letter(temp, temp2, num );
			}
			
			else if(num < temp.length() && temp.charAt(num)!= 'A' && temp.charAt(num)!= 'C' && temp.charAt(num)!= 'G' && temp.charAt(num)!= 'T'){
				checkTempLetter(temp, temp2, num );
			}
			
			
			if(temp2.charAt(num) == temp.charAt(i-deletion)){
				break;
			}
			else{
				num++;
			}
			
		}
		
		if(temp2.charAt(i) != temp.charAt(i)){
			temp = temp.substring(0, i) + "-" + temp.substring(i, temp.length());
		}
		
		while (temp.charAt(tmp) != temp2.charAt(tmp)){
			
			tmp++;
		}
		System.out.println( " deletion between " + i + " and " + tmp);
			
		
		return temp;
		
	}
	
	public static String checkTempLetter(String temp2 , String temp, int c){
		StringBuilder thing = new StringBuilder(temp);
		
		if(temp.charAt(c) == 'R'){
			if(temp2.charAt(c) == 'A' || temp2.charAt(c) == 'G'){
				
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
				
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'Y'){
			if(temp2.charAt(c) == 'C' || temp2.charAt(c) == 'T'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'A'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'K'){
			if(temp2.charAt(c) == 'T' || temp2.charAt(c) == 'G'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'M'){
			if(temp2.charAt(c) == 'A' || temp2.charAt(c) == 'C'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'S'){
			if(temp2.charAt(c) == 'C' || temp2.charAt(c) == 'G'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'W'){
			if(temp2.charAt(c) == 'A' || temp2.charAt(c) == 'T'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'G'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'B'){
			if(temp2.charAt(c) != 'A'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'A'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'D'){
			if(temp2.charAt(c) != 'C'){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'H'){
			if(temp2.charAt(c) != 'G' ){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'G'); 
				temp = thing.toString();
			}
			
		}
		else if(temp.charAt(c) == 'V'){
			if(temp2.charAt(c) != 'T' ){
				thing.setCharAt(c, temp2.charAt(c)); 
				temp = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp = thing.toString();
			}
			
		}
		System.out.println(" temp " + temp);
		return temp;
	}
	
	
public static String checkTemp2Letter(String temp2 , String temp, int c){
	StringBuilder thing = new StringBuilder(temp2);
	
		if(temp2.charAt(c) == 'R'){
			if(temp.charAt(c) == 'A' || temp.charAt(c) == 'G'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
				
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp2 = thing.toString();
			}
			
		}
		
		else if(temp2.charAt(c) == 'Y'){
			if(temp.charAt(c) == 'C' || temp.charAt(c) == 'T'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'A'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'K'){
			if(temp.charAt(c) == 'T' || temp.charAt(c) == 'G'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'M'){
			if(temp.charAt(c) == 'A' || temp.charAt(c) == 'C'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'S'){
			if(temp.charAt(c) == 'C' || temp.charAt(c) == 'G'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'W'){
			if(temp.charAt(c) == 'A' || temp.charAt(c) == 'T'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'G'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'B'){
			if(temp.charAt(c) != 'A'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'A'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'D'){
			if(temp.charAt(c) != 'C'){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'C'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'H'){
			if(temp.charAt(c) != 'G' ){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'G'); 
				temp2 = thing.toString();
			}
			
		}
		else if(temp2.charAt(c) == 'V'){
			if(temp.charAt(c) != 'T' ){
				thing.setCharAt(c, temp.charAt(c)); 
				temp2 = thing.toString();
			}
			else{
				thing.setCharAt(c, 'T'); 
				temp2 = thing.toString();
			}
			
		}
		System.out.println(temp2);
		return temp2;
	}
}
