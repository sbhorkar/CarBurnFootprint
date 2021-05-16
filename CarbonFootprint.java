package carbonFootprintTool;

import java.util.ArrayList;
import java.util.Scanner;

public class CarbonFootprint {
	
	double direct_mpg = 8.78;
	double indirect_mpg = 1.55;
	
	int mpg;
	int distance_year;
	static double total_emissions;
	
	CarbonFootprint(int init_mpg, int init_distance_year) {
		mpg = init_mpg;
		distance_year = init_distance_year;
	}
	
	public double f_to_m() {
		return 0;
	}
	
	public double f_to_t() {
		return 0;
	}
	
	public double f_to_use() {
		
		total_emissions = ((direct_mpg/mpg) + (indirect_mpg/mpg)) * distance_year;
		
		return total_emissions;
	}
	
	public double trees_needed() {
		
		double total_tons = (total_emissions/907); // this finds the total number of tons of CO2 released per year
		double total_trees = (total_tons * 64); // this finds the total number of trees needed to offset the CO2
		
		return total_trees;
		
	}
	
	public static void textInput() {
		int init_mpg = 0;
		int init_distance_year = 0;

		Scanner scanner = new Scanner(System.in);

		CarbonFootprint carbon;
		Car myCar;
		CarDatabase data = new CarDatabase();

		System.out.println("Choose a make from the following list: ");	
		ArrayList<Car> makes = data.getMakesListDB();
		int i = 0;
		for (Car c : makes) {
			System.out.println((++i)+ ". " + c.getMake());
		}
		int makeIndex = scanner.nextInt();
		scanner.nextLine();
		String make = makes.get(makeIndex-1).getMake();
		System.out.println("You selected " + make);

		System.out.println("Choose a model from the following list: ");
		ArrayList<Car> models = data.getModelsListDB(make);
		int j = 0;
		for (Car c: models) {
			System.out.println((++j)+". " + c.getModel());
		}
		int modelIndex = scanner.nextInt();
		scanner.nextLine();
		String model = models.get(modelIndex-1).getModel();
		System.out.println("You selected " + model);

		System.out.println("Choose a model from the following list: ");
		ArrayList<Car> years = data.getYearsListDB(make, model);
		int k = 0;
		for (Car c: years) {
			System.out.println((++k)+". " + c.getYear());
		}
		int yearIndex = scanner.nextInt();
		scanner.nextLine();
		int year = years.get(yearIndex-1).getYear();
		System.out.println("You selected " + year);

		init_mpg = data.returnMPG(make, model, year);

		System.out.println("About how many miles do you drive this car every year? ");
		init_distance_year = scanner.nextInt();
		scanner.nextLine();

		System.out.println("You selected " + make + ", " + model + ", " + year + ", your MPG is " + init_mpg + ", and you drive " + init_distance_year + " miles every year with this car.");

		carbon = new CarbonFootprint(init_mpg, init_distance_year);

		System.out.println("You emit " + carbon.f_to_use() + " kilograms of CO2 per year.");
		
		System.out.println(carbon.trees_needed() + " trees are needed to offset your carbon emissions.");
		
	}
	
	public static void main(String[] args) {
		
		textInput();
		
	}
	
}
