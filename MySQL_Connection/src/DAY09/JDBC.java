package DAY09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class JDBC {

	public static void main(String[] args) throws ClassNotFoundException,  SQLException{
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		int ch;
		ResultSet rs;
		int id,age;
		String name;
		
		int i;
		Connection con;
		Statement st;
		Class.forName("com.mysql.cj.jdbc.Driver");
			
			do {
				System.out.println("==================================");
				System.out.println("1.CREATE");
				System.out.println("2.ADD");
				System.out.println("3.MODIFY");
				System.out.println("4.DISPLAY");
				System.out.println("5.DELETE");
				System.out.println("6.EXIT");
				
				System.out.println("Enter Querry:");
				
				ch=s.nextInt();
				
				switch (ch) {
				
				case 1:
					System.out.println("Creation");
					
					try {
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3309/training","root","dbms");
						 st = con.createStatement();
						
						st.executeUpdate("create table Freshers(empId int , name varchar(25), age int );");
						System.out.println("Table created");
						
						st.close();
						con.close();
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 2:
					System.out.println("Add");
					System.out.println("");
					
					System.out.println("ID :");
					 id=s.nextInt();
					 
					 System.out.println(" Name :");
					 name=s.next();
					 
					 System.out.println("Age :");
					 age=s.nextInt();
					
					 try {
						 con = DriverManager.getConnection("jdbc:mysql://localhost:3309/training","root","dbms");
						 
						 st = con.createStatement();
						 st.executeUpdate("insert into freshers value("+ id+",'"+name+"',"+age+")");
						 
						 st.close();
						 con.close();
						 
						 System.out.println(" Value Updated");
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
					break;
					
				case 3:
					System.out.println("==================================");
					System.out.println("Modification");

					System.out.println("Emp ID :");
					id=s.nextInt();
					
					System.out.println("Change Name :");
					name=s.next();
					
					System.out.println("Change Age :");
					age=s.nextInt();
					
					try {
						con=DriverManager.getConnection("jdbc:mysql://localhost:3309/training","root","dbms");
						
						st=con.createStatement();
						
						i=st.executeUpdate("update freshers set name='"+name+"',age="+age+" where empId="+id);
					
						if(i==1) {
							System.out.println("RECORD MODIFIED SUCCESSFULLY ");
						}
						
						st.close();
						con.close();
					}
					catch(Exception e) {
						System.out.println(e);
					}
					
					break;
					
				case 4:
					System.out.println("==================================");
					System.out.println("Display");

					try {
						con=DriverManager.getConnection("jdbc:mysql://localhost:3309/training","root","dbms");
						
						st= con.createStatement();
						
						rs=st.executeQuery("select * from freshers");
						
						int j=1;
						while(rs.next()) {
							System.out.println("==================================");
							System.out.println("Record : "+j);
							System.out.println("EmpID : "+rs.getInt(1));
							System.out.println("NAME : "+rs.getString(2));
							System.out.println("AGE : "+rs.getString(3));
						}
						con.close();
						st.close();
						System.out.println("==================================");
					}
					catch(Exception e) {
						System.out.println(e);
					}
					
					break;
					
				case 5:
					System.out.println("==================================");
					System.out.println("Deletion");

					System.out.println("EmpId to be delete :");
					id=s.nextInt();
					
					try {
						System.out.println("==================================");
						con=DriverManager.getConnection("jdbc:mysql://localhost:3309/training","root","dbms");
						
						st= con.createStatement();
						
						st.executeUpdate("delete from freshers where empId="+id);
						
						con.close();
						st.close();
						System.out.println("==================================");
					}
					catch(Exception e) {
						System.out.println(e);
					}
					
					break;
					
					
				}
			}while(ch!=6);
			System.out.println("! THANK YOU !");
			
	}
		
}

