package project;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginPage 
{
	public static void main(String[] args) 
	{
		String url="jdbc:mysql://localhost:3306?user=root&password=12345";
		String cspsigin="call jsp.InsertUser(?,?,?,?,?,?,?,?)";
		String cspu="call jsp.UpdateUser(?,?)";
		String csplogin="call jsp.SelectUser(?,?)";
		try 
		{
			Connection con=DriverManager.getConnection(url);
			System.out.println("Establish the connection");
			boolean status=true;
			while(status)
			{
				Scanner sc=new Scanner(System.in);
				System.out.println("-----------------------------------");
				System.out.println("Enter 1 is Sigin the page");
				System.out.println("Enter 2 is Update the page");
				System.out.println("Enter 3 is Loginpage");
				System.out.println("Enter 4 is Exits the page");
				System.out.println("-----------------------------------");
				int no=sc.nextInt();
				switch (no) 
				{
				case 1:	
					CallableStatement cs1=con.prepareCall(cspsigin);
					System.out.println("WELCOME TO SIGIN PAGE");
					System.out.println("-----------------------------------");
					System.out.println("created the platform");
					System.out.println("establish the connection ");
					System.out.println("created platfrom");
					System.out.print("Enter the firstname : ");
					cs1.setString(1, sc.next());
					System.out.print("Enter the lastname : ");
					cs1.setString(2, sc.next());
					System.out.print("Enter date of birth : ");
					cs1.setString(3, sc.next());
					System.out.print("Enter the address : ");
					cs1.setString(4,sc.next());
					System.out.print("Enter the moblie : ");
					cs1.setString(5, sc.next());
					System.out.print("Enter the mail id : ");
					cs1.setString(6, sc.next());
					System.out.print("Enter Id : ");
					cs1.setInt(7, sc.nextInt());
					System.out.print("Enter pin : ");
					cs1.setInt(8, sc.nextInt());
					cs1.executeUpdate();
					System.out.println("The record is updated");	
					break;
				case 2:
					CallableStatement cs2=con.prepareCall(cspu);
					System.out.println("WELCOME TO UPDATE PAGE");
					System.out.println("-----------------------------------");
					System.out.println("cteated platform");
					System.out.print("Enter the password : ");
					cs2.setString(1, sc.next());
					System.out.print("Enter pin : ");
					cs2.setInt(2, sc.nextInt());
					int value =cs2.executeUpdate();
					if(value>0)
					{
						System.out.println("the record is update");
	
					}
					else 
					{
						System.out.println("Invalid Pin number");
						
					}
					break;
				case 3:
					CallableStatement cs3=con.prepareCall(csplogin);
					System.out.println("WELCOME LOGIN PAGE");
					System.out.println("-----------------------------------");
					System.out.println("Enter the Userid");
					cs3.setInt(1, sc.nextInt());
					System.out.println("Enter the Password");
					cs3.setString(2, sc.next());
					ResultSet rs=cs3.executeQuery();
					if(rs.last())
					{
						rs.beforeFirst();
						while(rs.next())
						{
							System.out.println("User firstName is : "+rs.getString("Firstname"));
							System.out.println("User Mobile nuber is : "+rs.getString("Mobile"));
							System.out.println("-----------------------------------");
						}
					}
					else
					{
						System.err.println("Invaid data found Enter the vaild user Id (or) Password");
					}
					break;
				case 4:
					System.out.println("Exits page sucessfully ✌️✌️✌️✌️✌️");
					con.close();
					System.out.println("Close the connection");
					status=false;
					break;
				default:
					System.out.println("Enter vaild options");
					break;
					
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

}
