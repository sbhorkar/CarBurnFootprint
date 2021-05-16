package carbonFootprintTool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CarDatabase {

	ArrayList<Car> database;
	private final String mySQLConnect = "jdbc:mysql://localhost:3306/vehicles_db?characterEncoding=utf8";
	private final String tableName = "vehicles";
	
	private void initArrayDatabase() {
		database = new ArrayList<Car>();

		String line = "";  
		String splitBy = ",";  
		try {  
			//parsing a CSV file into BufferedReader class constructor  
			BufferedReader br = new BufferedReader(new FileReader("/Users/sanaabhorkar/Documents/ScienceProject/vehicles.csv"));  
			br.readLine(); // Skip the header
			while ((line = br.readLine()) != null) {   //returns a Boolean value  
				String[] carInfo = line.split(splitBy);    // use comma as separator  
				System.out.println(carInfo[46] + carInfo[45] + carInfo[63]);
//				Car newCar = new Car(carInfo[46], carInfo[47], carInfo[62], Integer.parseInt(carInfo[63]), Integer.parseInt(carInfo[15]));
//				database.add(newCar);
			}  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
	}
	
	private void printArrayDatabase() {
		int i = 0;
		for (Car c : database) {
			System.out.println(c.getMake() + " " + c.getVClass());
			System.out.println(i++);
		}
	}
	
	private Connection getMySQLDBConnect() {
		try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		mySQLConnect, "root", "mysqlroot");  
		return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	private void initMySQLDatabase() {
		
	}
	
	private void printMySQLDatabase() {
		try {  

			Connection con=getMySQLDBConnect();
			if (con==null) {
				return;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from " + tableName);  
			while(rs.next())  
			System.out.println(rs.getString(47) + " " + rs.getString(48) + " " + rs.getInt(64));  
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
			}  
		
	}  

	CarDatabase() {
		initMySQLDatabase();
	}

	public void printCarDatabase() {
		printMySQLDatabase();
	}
	
	public ArrayList<Car> getMakesListDB() {
		
		ArrayList<Car> carDB = new ArrayList<Car>();
		
		try {  
			Connection con=getMySQLDBConnect(); 
			
			if (con==null) {
				return carDB;
			}
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct make from " + tableName + " order by make");  
			while(rs.next()) {  
				Car newCar = new Car(rs.getString(1));
				carDB.add(newCar);
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public ArrayList<String> getMakesStringListDB() {
		
		ArrayList<String> carDB = new ArrayList<String>();
		
		try {  
			Connection con=getMySQLDBConnect(); 
			
			if (con==null) {
				return carDB;
			}
			
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct make from " + tableName + " order by make");  
			while(rs.next()) {  
				carDB.add(rs.getString(1));
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public ArrayList<Car> getModelsListDB(String make) {
		
		ArrayList<Car> carDB = new ArrayList<Car>();
		
		try {  
			Connection con=getMySQLDBConnect();
			if (con==null) {
				return carDB;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct model from " + tableName + " where make='" + make + "' order by model");  
			while(rs.next()) {  
				Car newCar = new Car(make, rs.getString(1));
				carDB.add(newCar);
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public ArrayList<String> getModelsStringListDB(String make) {
		
		ArrayList<String> carDB = new ArrayList<String>();
		
		try {  
			Connection con=getMySQLDBConnect();
			if (con==null) {
				return carDB;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct model from " + tableName + " where make='" + make + "' order by model");  
			while(rs.next()) {  
				carDB.add(rs.getString(1));
			}
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public ArrayList<Car> getYearsListDB(String make, String model) {
		
		ArrayList<Car> carDB = new ArrayList<Car>();
		
		try {  
			Connection con=getMySQLDBConnect();  
			if (con==null) {
				return carDB;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct year from " + tableName + " where make='" + make + "' and model='" + model + "' order by year");  
			while(rs.next()) {  
				Car newCar = new Car(make, model, rs.getInt(1));
				carDB.add(newCar);
			}
			
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public ArrayList<Integer> getYearsIntListDB(String make, String model) {
		
		ArrayList<Integer> carDB = new ArrayList<Integer>();
		
		try {  
			Connection con=getMySQLDBConnect();  
			if (con==null) {
				return carDB;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct year from " + tableName + " where make='" + make + "' and model='" + model + "' order by year");  
			while(rs.next()) {  
				carDB.add(rs.getInt(1));
			}
			
			con.close();  
		} catch(Exception e){ 
			System.out.println(e);
		}  
		
		return carDB;
		
	}
	
	public int returnMPG(String make, String model, int year) {
		
		try {  
			Connection con=getMySQLDBConnect();
			if (con==null) {
				return 0;
			}
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select distinct comb08 from " + tableName + " where make='" + make + "' and model='" + model + "' and year='" + year + "'");    
			rs.next();
			int mpg = rs.getInt(1);
			con.close();
			return mpg;
		} catch(Exception e){ 
			System.out.println(e);
			return 0;
		}  
	
		
	}

}
