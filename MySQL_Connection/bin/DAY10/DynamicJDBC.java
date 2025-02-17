package DAY10;

import java.sql.*;
import java.util.*;
public class DynamicJDBC {
	
	static int ID;
	static String username, password;
	
	static Scanner s = new Scanner(System.in);
	
	public static Connection connect()  {
		
		
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3309/registeration","root","dbms");
			
			if(con!=null) {
				return con;
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	//Table Creation
	static void createTable() throws SQLException{
		
		String qry = "Create table signup (ID int , username varchar(30) , password varchar(20))";
		
		Connection con = connect();
		PreparedStatement pre = con.prepareStatement(qry);
		
		pre.executeUpdate();
		
		con.close();
		pre.close();
	}
	
	//Insertion 
	
	static void insertTable() throws SQLException{
		
		System.out.println("Enter Your ID :");
		ID = s.nextInt();
		
		System.out.println("Enter Your User Name :");
		username=s.next();
		
		System.out.println("Enter Your Password :");
		password=s.next();
		
		String qry = "Insert into signup(ID,username,password) values(?,?,?)";
		
		Connection con=connect();
		
		PreparedStatement ps = con.prepareStatement(qry);
		
		ps.setInt(1, ID);
		ps.setString(2, username);
		ps.setString(3, password);
		
		ps.executeUpdate();
		
		con.close();
		ps.close();
	}
	
	
	static void deleteTable() throws SQLException{
		System.out.println("Enter Your ID to be Delete :");
		ID = s.nextInt();
		
		String qry = "Delete from signup where ID=?";
		
		Connection con =connect();
		
		PreparedStatement ps = con.prepareStatement(qry);
		ps.setInt(1,ID);
		
		ps.executeUpdate();
		
		con.close();
		ps.close();
	}
	
	static void updateTable() throws SQLException{
		
		String qry = " Update signup set username=? ,password =? where id=?";
		
		System.out.println("Enter the ID to be Update :");
		ID=s.nextInt();
		
		System.out.println("Enter the New USER NAME :");
		username= s.next();
		
		System.out.println("Enetr the New PASSWORD :");
		password= s.next();
		
		Connection con = connect();
		
		PreparedStatement ps = con.prepareStatement(qry);
		
		ps.setString(1, username);
		ps.setString(2,password );
		ps.setInt(2, ID);
		
		ps.executeUpdate();
		
		con.close();
		ps.close();
		
	}
	
	static void displayTable() throws SQLException{
		
		String qry = "select * from signup;";
		
		Connection con = connect();
		
		PreparedStatement ps= con.prepareStatement(qry);
		
		ResultSet rs = ps.executeQuery();
		System.out.println();
		System.out.println("=================================================");
		while(rs.next()) {
			System.out.println(rs.getInt(1) +" | "+ rs.getString(2)+ " | "+rs.getString(3));
		}
		System.out.println("=================================================");
		System.out.println();
		con.close();
		ps.close();
	}
	
	static void loginPage() throws SQLException{
		
		System.out.println("ENTER YOUR USER NAME :");
		username =s.next();
		
		System.out.println("ENTER YOUR PASSWORD :");
		password =s.next();
		
		String qry="select * from signup where username=?";
		
		Connection con = connect();

		PreparedStatement ps = con.prepareStatement(qry);
		ps.setString(1, username);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			
			if((rs.getString(2).equals(username)) && rs.getString(3).equals(password)) {
				
				System.out.println("LOGIN SUCCESSFULLY");
				
				con.close();
				ps.close();
				
			}
			else {
				System.out.println("password incorrect");
				System.out.println("Reset Password : (YES / NO) ");
				String ans=s.next();
				if( ans.equals("YES")) {
					
					System.out.println("Enter NEW PASSWORD :");
					
					String nps = s.next();
					//check new
					System.out.println("Confirm NEW PASSWORD :");
					
					String chnps = s.next();
					
					if(nps.equals(chnps)) {
						String change = " update signup set password =? where username=?;";
					
						PreparedStatement changeps = con.prepareStatement(change);
						changeps.setString(1, nps);
						changeps.setString(2, username);
					
						changeps.executeUpdate();
						System.out.println("========== Changed Password Successfully==============");
						
						changeps.close();
						DynamicJDBC.loginPage();
					}
					else {
						System.out.println("PASSWORD not match ");
					}
					
				}
				
				else {
					System.out.println("=========THANK YOU ============= ");
				}
			}
			
		}
		else
			System.out.println("username incorrect");
		
		con.close();
		ps.close();
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println();

		
		int ch;
		do{
			System.out.println("***********************");
			System.out.println("*********** USER MANAGEMENT ************");
			System.out.println("1. CREATE (run only once)");
			System.out.println("2. INSERT");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. DISPLAY");
			System.out.println("6. LOGIN");
			System.out.println("7. EXIT");
			System.out.println("***********************");
			System.out.println("Enter your choice :");
			ch=s.nextInt();
			System.out.println("***********************");
			switch(ch) {
		
				case 1:
					DynamicJDBC.createTable();
					break;

				case 2:
					DynamicJDBC.insertTable();
					break;
					
				case 3:
					DynamicJDBC.updateTable();
					break;
					
				case 4:
					DynamicJDBC.deleteTable();
					
					break;
				
				case 5:
					DynamicJDBC.displayTable();
					break;
				
				case 6:
					DynamicJDBC.loginPage();
					break;
			}
		}while(ch<=6);
		System.out.println("********** THANK YOU *************");
	}
}
