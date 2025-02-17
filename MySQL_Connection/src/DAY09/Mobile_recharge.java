package DAY09;

import java.sql.*;
import java.util.*;

public class Mobile_recharge {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
				Scanner s = new Scanner(System.in);
				int choice;
				ResultSet rs;
				int id,age;
				String name;
				
				int i;
				Connection con;
				Statement st;
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				do {
	                System.out.println("==================================");
	                System.out.println("1. Register User and Initiate Recharge");
	                System.out.println("2. View Transaction History");
	                System.out.println("3. Modify Payment Method");
	                System.out.println("4. Delete User or Plan");
	                System.out.println("5. Exit");
	                System.out.println("==================================");

	                System.out.print("Enter your choice: ");
	                
					choice = s.nextInt();
	                 // Consume newline

	                PreparedStatement ps;
					switch (choice) {
	                    case 1:
	                        // Register User and Initiate Recharge
	                        System.out.print("Enter User Name: ");
	                        name =s .nextLine();
	                        System.out.print("Enter Phone Number: ");
	                        String phoneNumber =s .nextLine();
	                        System.out.print("Enster Email: ");
	                        String email = s.nextLine();
	                        System.out.print("Enter Payment Method: ");
	                        String paymentMethod = s.nextLine();
	                        System.out.print("Enter Plan ID: ");
	                        int planId = s.nextInt();
	                        s.nextLine();  // Consume newline
	                        System.out.print("Enter Plan Name: ");
	                        String planName = s.nextLine();
	                        System.out.print("Enter Amount: ");
	                        double amount = s.nextDouble();

	                        try {
	                            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/mobile_recharge", "root", "password");
	                            String insertQuery = "INSERT INTO MobileRechargeApp (userId, name, phoneNumber, email, paymentMethod, planId, planName, amount) " +
	                                                 "VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";
	                            ps = con.prepareStatement(insertQuery);
	                            ps.setString(1, name);
	                            ps.setString(2, phoneNumber);
	                            ps.setString(3, email);
	                            ps.setString(4, paymentMethod);
	                            ps.setInt(5, planId);
	                            ps.setString(6, planName);
	                            ps.setDouble(7, amount);

	                            int result = ps.executeUpdate();
	                            if (result > 0) {
	                                System.out.println("User registered and recharge initiated successfully!");
	                            }
	                        } catch (Exception e) {
	                            System.out.println("Error: " + e.getMessage());
	                        } 
	                        break;

	                    case 2:
	                        // View Transaction History
	                        System.out.print("Enter User ID to View Transaction History: ");
	                        int userId = s.nextInt();

	                        try {
	                            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/mobile_recharge", "root", "password");
	                            String selectQuery = "SELECT * FROM MobileRechargeApp WHERE userId = ?";
	                            ps = con.prepareStatement(selectQuery);
	                            ps.setInt(1, userId);
	                            rs = ps.executeQuery();

	                            while (rs.next()) {
	                                System.out.println("==================================");
	                                System.out.println("User Name: " + rs.getString("name"));
	                                System.out.println("Phone Number: " + rs.getString("phoneNumber"));
	                                System.out.println("Email: " + rs.getString("email"));
	                                System.out.println("Plan: " + rs.getString("planName"));
	                                System.out.println("Amount: " + rs.getDouble("amount"));
	                                System.out.println("Transaction Date: " + rs.getTimestamp("transactionDate"));
	                            }
	                        } catch (Exception e) {
	                            System.out.println("Error: " + e.getMessage());
	                        } 
	                       
	                        break;

	                    case 3:
	                        // Modify Payment Method
	                        System.out.print("Enter User ID to Modify Payment Method: ");
	                        int modifyUserId = s.nextInt();
	                        s.nextLine();  // Consume newline
	                        System.out.print("Enter New Payment Method: ");
	                        String newPaymentMethod = s.nextLine();

	                        try {
	                            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/mobile_recharge", "root", "password");
	                            String updateQuery = "UPDATE MobileRechargeApp SET paymentMethod = ? WHERE userId = ?";
	                            ps = con.prepareStatement(updateQuery);
	                            ps.setString(1, newPaymentMethod);
	                            ps.setInt(2, modifyUserId);

	                            int result = ps.executeUpdate();
	                            if (result > 0) {
	                                System.out.println("Payment method updated successfully!");
	                            }
	                        } catch (Exception e) {
	                            System.out.println("Error: " + e.getMessage());
	                        } 
	                        
	                        break;

	                    case  4:
	                       
	                        System.out.print("Enter User ID to Delete: ");
	                        int deleteUserId = s.nextInt();

	                        try {
	                            con = DriverManager.getConnection("jdbc:mysql://localhost:3309/mobile_recharge", "root", "password");
	                            String deleteQuery = "DELETE FROM MobileRechargeApp WHERE userId = ?";
	                            ps = con.prepareStatement(deleteQuery);
	                            ps.setInt(1, deleteUserId);

	                            int result = ps.executeUpdate();
	                            if (result > 0) {
	                                System.out.println("User account or plan deleted successfully!");
	                            }
	                        } catch (SQLException e) {
	                            System.out.println("Error: " + e.getMessage());
	                        } 
	                        break;

	                    case 5:
	                        System.out.println("Exiting... Thank you!");
	                        break;

	                    default:
	                        System.out.println("Invalid choice! Please try again.");
	                        break;
	                }
	            } while (choice != 5);
			}

	}


