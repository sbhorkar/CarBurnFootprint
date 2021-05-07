package carbonFootprintTool;

public class Car {

	String make; // 47
	String model; // 48
	String trany; // 58
	String vClass; // 63
	int year; // 64
	int mpg; // combu8: 16
	
	Car(String init_make) {
		make = init_make;
	}
	
	Car(String init_make, String init_model) {
		make = init_make;
		model = init_model;
	}
	
	Car(String init_make, String init_model, int init_year) {
		make = init_make;
		model = init_model;
		year = init_year;		
	}
	
	Car(String init_make, String init_model, String init_trany, String init_vClass, int init_year, int init_mpg) {
		make = init_make;
		model = init_model;
		trany = init_trany;
		vClass = init_vClass;
		year = init_year;
		mpg = init_mpg;
	}
	
	public void setMake(String set_make) {
		make = set_make;
	}
	
	public void setModel(String set_model) {
		model = set_model;
	}
	
	public void setYear(int set_year) {
		year = set_year;
	}
	
	public int getMpg() {
		return 25;
	}
	
	public String getMake() {
		return make;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getTrany() {
		return trany;
	}
	
	public String getVClass() {
		return vClass;
	}
	
	public int getYear() {
		return year;
	}

}
