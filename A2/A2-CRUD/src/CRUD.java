import java.sql.*;
import java.util.*;

public class CRUD {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager
					.getConnection("jdbc:mysql://localhost:3306/A2_1?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false", "root", "root");

			Statement stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TABLE IF NOT EXISTS student(roll_no INT PRIMARY KEY,name VARCHAR(255),college_name VARCHAR(255));");
			System.out.println("Table Created");

			Scanner sc = new Scanner(System.in);

			while (true) {
				System.out.print("		MENU\n1. Insert\n2. Update\n3. Read\n4.Delete\n");
				System.out.print("Enter your choice: ");
				int choice = Integer.parseInt(sc.nextLine());

				switch (choice) {
					case 1:
						PreparedStatement insertStmt = con.prepareStatement("INSERT INTO student VALUES(?,?,?);");
						System.out.println("Inserting New Data");
						System.out.print("Enter Roll No: ");
						int roll_no = Integer.parseInt(sc.nextLine());
						System.out.print("Enter Name: ");
						String name = sc.nextLine();
						System.out.print("Enter College Name: ");
						String college_name = sc.nextLine();
						insertStmt.setInt(1, roll_no);
						insertStmt.setString(2, name);
						insertStmt.setString(3, college_name);
						insertStmt.executeUpdate();
						System.out.println("New Student has been added");
						break;
					case 2:
						PreparedStatement updateStmt = con
								.prepareStatement("UPDATE student SET name=?, college_name=? WHERE roll_no=?");
						System.out.println("Inserting New Data");
						System.out.print("Enter Roll No of Student To Be Updated: ");
						roll_no = Integer.parseInt(sc.nextLine());
						System.out.print("Enter Updated Name: ");
						name = sc.nextLine();
						System.out.print("Enter Updated College Name: ");
						college_name = sc.nextLine();
						updateStmt.setInt(3, roll_no);
						updateStmt.setString(1, name);
						updateStmt.setString(2, college_name);
						if (updateStmt.executeUpdate() == 1) {
							System.out.println("Student has been updated");
						} else {
							System.out.println("Student not found.");
						}
						break;
					case 3:
						System.out.println("Enter 1(All Records) and 0(Search By One): ");
						choice = Integer.parseInt(sc.nextLine());
						ResultSet rs;
						if (choice == 1) {
							rs = stmt.executeQuery("SELECT * FROM student;");
							DisplayResult(rs);
						} else {
							System.out.println(
									" SELECT THE FIELD YOU WANT TO SELECT BY\n1. Roll No\n2. Name\n3. College Name");
							System.out.print("Enter your choice: ");
							choice = Integer.parseInt(sc.nextLine());
							switch (choice) {
								case 1:
									System.out.print("Enter Roll No: ");
									roll_no = Integer.parseInt(sc.nextLine());
									rs = stmt.executeQuery("SELECT * FROM student WHERE roll_no=" + roll_no + ";");
									DisplayResult(rs);
									break;
								case 2:
									System.out.print("Enter Name: ");
									name = sc.nextLine();
									rs = stmt.executeQuery("SELECT * FROM student WHERE name='" + name + "';");
									DisplayResult(rs);
									break;
								case 3:
									System.out.print("Enter College Name: ");
									college_name = sc.nextLine();
									rs = stmt.executeQuery(
											"SELECT * FROM student WHERE college_name='" + college_name + "';");
									DisplayResult(rs);
									break;
							}
						}
						break;
					case 4:
						System.out.print("Enter the Roll No To Be Deleted: ");
						roll_no = Integer.parseInt(sc.nextLine());
						if (stmt.executeUpdate("DELETE FROM student WHERE roll_no=" + roll_no + ";") == 1) {
							System.out.println("Deleted Record");
						} else {
							System.out.println("Record Not Found");
						}
						break;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void DisplayResult(ResultSet rs) {
		try {
			System.out.println("--------------------------------------------------------------------------------------------------------");
			while (rs.next()) {
				System.out.println("Roll No: " + rs.getInt(1) + " | Name: " + rs.getString(2) + " | College Name: " + rs.getString(3));
				System.out.println("----------------------------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
