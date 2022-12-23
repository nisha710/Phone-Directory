package phonebook;
import java.util.*;

	class Book {
		String name,number,email;
		int favorites,flag;
		Book lc;
		Book rc;
	}
	public class PhoneBook{
		Scanner scan=new Scanner(System.in);
		Book root;
		PhoneBook(){
			root=null;
		}
		String checknumber(String number) {
			while(number.length()!=10) { //setting of contact number size to 10 digit
				System.out.println("\nThe Contact Number Must be of 10 Digit! \n");
				System.out.println("PLEASE ENTER VALID NUMBER! :");
				number = scan.next();
			}
			return number;
		}
		String checkemail(String email) {
			char c;
			int flag=0;
			for(int i=0;i<email.length();i++) { // setting e mail address of the contact
				c=email.charAt(i);
				if(c=='@') {
					flag=1;
				}
			}
			if(flag==0) {
				System.out.println("\nPlease enter the valid email address\n");
				email = scan.next();}
			return email;
		}

		void create() {
			Book prev,ptr,cur;
			int Answer;
			System.out.print("\nENTER THE NAME: ");  //name of contact
			String Name=scan.next();
			System.out.print("\nENTER THE PHONE NUMBER: "); //number of contact
			String Number=scan.next();
			Number=checknumber(Number);
			System.out.print("\nENTER THE EMAIL ID: "); //e mail of contact
			String Email=scan.next();
			Email=checkemail(Email);
			System.out.println("\nDo you want to add this contact to Favorites??"); // to add more
			System.out.println("\nPRESS 1 to add");
			System.out.println("PRESS 0 to don't add");
			Answer=scan.nextInt();
			if(root==null) {
				ptr=new Book();  //create new Book
				ptr.name=Name;
				ptr.number=Number;
				ptr.email=Email;
				if(Answer==1) {  //add to favorites if Answer is 1
					ptr.favorites=1;
				}
				ptr.lc=null;
				ptr.rc=null;
				root=ptr;  //ptr is root
				System.out.println("\nCreated first phone contact!!!!!");
			}
			else {
				prev=null;  //creating new node when root is not null
				cur=root;
				while(cur!=null&&(!cur.name.equals(Name)||!cur.number.equals(Number))) { 
					prev=cur;
					if(cur.name.compareTo(Name)>0) {
						cur=cur.lc;
					}else {
						cur=cur.rc;
					}
				} 
				if(cur!=null&&cur.name.equals(Name)&&cur.number.equals(Number)) { // handling of duplicate contact
					System.out.println("\nThis name and phone number is already saved in the directory");
				}
				else {
					ptr=new Book();
					ptr.name=Name;
					ptr.number=Number;
					ptr.email=Email;
					if(Answer==1) {
						ptr.favorites=1;
					}
					ptr.lc=null;
					ptr.rc=null;
					if(prev.name.compareTo(Name)>0) {
						prev.lc=ptr;
					}
					else {
						prev.rc=ptr;
					}
					System.out.println("\nContact is successfully added in the directory");
				}
			}
		}
		
		void display() {
			//ino_nr
			Book cur;
			int top=-1;
			Book st[]=new Book[100];
			for(int i=0;i<100;i++){
				st[i]=new Book();
			}
			if(root==null){
				System.out.println("\nEmpty Directory");
			}else{
				cur=root;
				do{
					while(cur!=null){
						top++;   // push cur to top of the stack
						st[top]=cur;
						cur=cur.lc;
					}
					if(top!=-1){  // if stack not empty pop and print 
						cur=st[top--];
						System.out.println(cur.name+"    \t "+cur.number+"\t   "+cur.email);
						cur=cur.rc;
					}
				}while(cur!=null || top!=-1);
			}
		}

		void favorites() {
			Book cur;
			int flag=0;
			int top=-1;
			Book st[]=new Book[100];
			for(int i=0;i<100;i++){
				st[i]=new Book();
			}
			if(root==null){
				System.out.println("\nEmpty Directory");
			}else{
				System.out.println("\nYour Favorite Contacts are:");
				System.out.println();
				System.out.println("----------------------------------------------------");
				System.out.println("\nNAME\t | \tCONTACT NUMBER\t | \tEMAIL ID\n");
				System.out.println("----------------------------------------------------");
				cur=root;
				do{
					while(cur!=null){
						top++;
						st[top]=cur;
						cur=cur.lc;
					}
					if(top!=-1){
						cur=st[top--];
						if(cur.favorites==1) {
							flag++;
							System.out.println(cur.name+"    \t "+cur.number+"\t   "+cur.email);
						}
						cur=cur.rc;
					}
				}while(cur!=null || top!=-1);
			}
			if(flag==0) {
				System.out.println("    ----- NO FAVORITE CONTACTS FOUND -----    ");
				}
		}
		
		void search_contact(){
			String Name;
			Scanner scan=new Scanner(System.in);
			System.out.print("\nENTER THE NAME TO SERACH : ");
			Name=scan.nextLine();
			int flag=0;
			Book prev,ptr,cur;
			prev=null;
			cur=root;
			while(cur!=null) {
				prev=cur;
				if(Name.equals(cur.name)) {
					System.out.println("\n    -----  RECORD FOUND  -----    ");
					System.out.println();
					System.out.println("----------------------------------------------------");
					System.out.println("\nNAME\t | \tCONTACT NUMBER\t | \tEMAIL ID\n");
					System.out.println(cur.name+"    \t "+cur.number+"\t   "+cur.email);
					System.out.println("----------------------------------------------------");
					flag=1;
					break;
				}
				else {
					if(prev.name.compareTo(Name)>0) {
						cur=cur.lc;
					}else {
						cur=cur.rc;
					}
				}
			}
			if(flag==0) {
				System.out.println("\n    -----  RECORD NOT FOUND  -----    ");
			}
		}

		void delete_contact(){
			Book cur,prev;
			Scanner scan=new Scanner(System.in);
			String Name;
			System.out.print("\nENTER THE NAME TO DELETE CONTACT : ");
			Name=scan.nextLine();
			if(root==null){
				System.out.println("\n    -----  EMPTY DIRECTORY  -----    ");
			}else {
				prev=null;
				cur=root;
				while(cur!=null&&!cur.name.equals(Name)) {
					prev=cur;
					if(cur.name.compareTo(Name)>0) {
						cur=cur.lc;
					}
					else {
						cur=cur.rc;
					}
				}
				if(cur==null){
					System.out.println("\n    -----  RECORD NOT FOUND  -----    ");
				}
				else{
					//cur is node to delete
					System.out.println("\nDeleted following Contact successfully");
					System.out.println();
					System.out.println("----------------------------------------------------");
					System.out.println("\nNAME\t | \tCONTACT NUMBER\t | \tEMAIL ID\n");
					System.out.println(cur.name+"    \t "+cur.number+"\t   "+cur.email);
					System.out.println("----------------------------------------------------");
					if(cur.rc==null&&cur.lc==null){
						//leaf node to delete
						if(prev==null){
							//only node to delete
							root=null;
							cur=null;
						}else{
							if(prev.name.compareTo(Name)>0){
								prev.lc=null;
								cur=null;
							}else{
								prev.rc=null;
								cur=null;
							}
						}
					}
					else if(cur.lc!=null&&cur.rc==null){
						//node with only left subtree
						if(prev==null){  //root is only node with left sub tree
							root=cur.lc;
							cur.lc=null;
							cur=null;
						}else{
							if(prev.name.compareTo(Name)>0){
								prev.lc=cur.lc;
								cur.lc=null;
								cur=null;
							}
							else{
								prev.rc=cur.lc;
								cur.lc=null;
								cur=null;
							}
						}
					}
					else if(cur.lc==null&&cur.rc!=null){
						//node with only right subtree
						if(prev==null){ //root is only node with right subtree
							root=cur.rc;
							cur.rc=null;
							cur=null;
						}else{
							if(prev.name.compareTo(Name)>0){
								prev.lc=cur.rc;
								cur.rc=null;
								cur=null;
							}else{
								prev.rc=cur.rc;
								cur.rc=null;
								cur=null;
							}
						}
					}
					else if(cur.lc!=null&&cur.rc!=null){
						//node having both subtree
						Book temp=cur.lc;  //go one left and extreme right
						while(temp.rc!=null){
							temp=temp.rc;
						}
						temp.rc=cur.rc;
						cur.rc=null;
						if(prev==null){
							root=cur.lc;
							cur=null;
						}else{
							if(prev.name.compareTo(Name)>0){
								prev.lc=cur.lc;
								cur.lc=null;
								cur=null;
							}else{
								prev.rc=cur.lc;
								cur.lc=null;
								cur=null;
							}
						}
					}
				}
			}
		}

		void update_contact(){
			String new_number,Name;//new_name
			Scanner scan=new Scanner(System.in);
			System.out.print("\nENTER NAME TO UPDATE NUMBER : ");
			Name=scan.nextLine();
			int flag=0;
			Book prev,ptr,cur;
			prev=null;
			cur=root;
			while(cur!=null) {
				prev=cur;
				if(Name.equals(cur.name)) {
					System.out.print("\nENTER NEW NUMBER : ");
					new_number=scan.nextLine();
					cur.number=new_number;
					System.out.println("\nUPDATED CONTACT IS: ");
					System.out.println();
					System.out.println("----------------------------------------------------");
					System.out.println("\nNAME\t | \tCONTACT NUMBER\t | \tEMAIL ID\n");
					System.out.println(cur.name+"    \t "+cur.number+"\t   "+cur.email);
					System.out.println("----------------------------------------------------");
					flag=1;
					break;
				}else {
					if(prev.name.compareTo(Name)>0) {
						cur=cur.lc;
					}else {
						cur=cur.rc;
					}
				}
			}
			if(flag==0) {
				System.out.println("\n    -----  RECORD NOT FOUND  -----    ");
			}
		}

		public static void main(String[] args) {
			PhoneBook obj=new PhoneBook();
			int choice;
			int ans;
			Scanner scan=new Scanner(System.in);
			System.out.println();
			System.out.println("********  WELCOME TO PHONEBOOK  ********");
			System.out.println();
			System.out.println("\t1.CREATE NEW CONTACT ");
			System.out.println("\t2.ALL CONTACTS INFORMATION ");	
			System.out.println("\t3.SEARCH CONTACT");
			System.out.println("\t4.UPDATE CONTACT");
			System.out.println("\t5.DELETE CONTACT");
			System.out.println("\t6.FAVORITES");
			do {
				System.out.print("\nENTER YOUR CHOICE: ");
				choice=scan.nextInt();
				switch(choice) {
				case 1:
					System.out.println("\nCREATE A NEW CONTACT : ");
					obj.create();
					break;
				case 2:
					System.out.println("\nDISPLAY CONTACT : ");
					System.out.println();
					System.out.println("----------------------------------------------------");
					System.out.println("\nNAME\t | \tCONTACT NUMBER\t | \tEMAIL ID\n");
					System.out.println("----------------------------------------------------");
					obj.display() ;
					System.out.println("----------------------------------------------------");
					break;
				case 3:
					System.out.println("\nSEARCH CONTACT : ");
					obj.search_contact() ;
					break;
				case 4:
					System.out.println("\nUPDATE CONTACT : ");
					obj.update_contact();
					break;
				case 5:
					System.out.println("\nDELETE CONTACT : ");
					obj.delete_contact();
					break;
				case 6:
					System.out.println("\nFAVORITE CONTACT LIST : ");
					obj.favorites();
					System.out.println("----------------------------------------------------");
					break;
				default:
					System.out.println("\nINVALID CHOICE ");
					break;
				}
				System.out.println("\nDO YOU WANT TO CONTINUE ???");
				System.out.println("\nPRESS 1 : CONTINUE ");
				System.out.println("PRESS 0 : EXIT ");
				ans=scan.nextInt();
				if(ans==0) {
					System.out.println("\nThank you!!!!!!!!!");
				}
			}while(ans!=0);
		}
	}
