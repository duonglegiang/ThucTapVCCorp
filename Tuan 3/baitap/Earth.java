package baitap;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.stream.*;
import java.sql.*;

public class Earth {
	public ArrayList<Country> listCountry;
//	list id cua luc dia
	public Set<String> list_id;
	
    public static String DB_URL = "jdbc:mysql://localhost:3306/earth";
    public static String USER_NAME = "root";
    public static String PASSWORD = "12345678";
    
	public void init1() {
		listCountry = new ArrayList<Country>();
		list_id = new HashSet<String>();
		
		City cap = new City(10, "Ha Noi", 200);
		
		ArrayList<City> listcity = new ArrayList<City>();
		
		City temp_city = new City(11, "Thanh Hoa", 30);
		listcity.add(temp_city);
		temp_city = new City(12, "Sai Gon", 70);
		listcity.add(temp_city);
		
		listcity.add(cap);
		Country temp = new Country("1", "Viet Nam", "A", 1000, 300, 200, cap, listcity);
		listCountry.add(temp);
		list_id.add("A");
		cap = new City(20, "Bac Kinh", 400);
		
		listcity = new ArrayList<City>();
		
		temp_city = new City(21, "Trung Khanh", 100);
		listcity.add(temp_city);
		temp_city = new City(22, "Thuong Hai", 100);
		listcity.add(temp_city);
		
		temp_city = new City(23, "Tu Xuyen", 50);
		listcity.add(temp_city);
//		add city
		listcity.add(cap);
		temp = new Country("2", "Trung Quoc", "Au", 1100, 650, 400, cap, listcity);
		listCountry.add(temp);
		list_id.add("Au");
		
//		in kq
//		for( Country i: listCountry) {
//			System.out.println("thanh pho la " + i.name);
//		}
		
	}
	
	public static Connection getConnection(String dbURL, String userName, String password) throws SQLException {
        Connection conn = null;
        
        try {
        	// Dang ky Driver    	
        	Class.forName("com.mysql.cj.jdbc.Driver");

        	// mo ket noi
            conn = DriverManager.getConnection(dbURL, userName, password);
//            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
	
	public void init() throws SQLException {
		listCountry = new ArrayList<Country>();
		list_id = new HashSet<String>();
		
		Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
    	
		String code;
        String name;
        String continent;
        double surfaceArea;
        int population_country;
        double gnp;
        int id_capital;
        
        City cap = new City(10, "Ha Noi", 200);
		
    	// tao truy van (select)
    	Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from country");

        while (rs.next()) {
        	ArrayList<City> listcity = new ArrayList<City>();
        	
        	code = rs.getString(1);
        	name = rs.getString(2);
        	continent = rs.getString(3);
        	surfaceArea = rs.getDouble(4);
        	population_country = rs.getInt(5);
        	gnp = rs.getDouble(6);
        	id_capital = rs.getInt(7);
        	
        	Statement stmt1 = conn.createStatement();
            ResultSet res = stmt1.executeQuery("select * from city where id = " + String.valueOf(id_capital));
            
            while(res.next()) {
            	cap = new City(res.getInt(1), res.getString(2), res.getInt(3));
            }
            
            Statement stmt2 = conn.createStatement();
//            String cc = "select * from city where country = '" + code + "'";
//            System.out.println(cc);
            ResultSet res2 = stmt2.executeQuery("select * from city where id_country = '" + code + "'");
            
            while(res2.next()) {
            	City temp_city = new City(res2.getInt(1), res2.getString(2), res2.getInt(3));
        		listcity.add(temp_city);
            }
            
            Country temp = new Country(code, name, continent, surfaceArea, population_country, gnp, cap, listcity);
    		listCountry.add(temp);
    		list_id.add(continent);
        }
        
//        in list id luc dia
        for(String i: list_id) {
        	System.out.print( i + " ");
        }
        System.out.println();
        
        conn.close();
	}
	
//	tim  thanh pho dong dan nhat cua moi quoc gia
	public void FindCity_Country() {
		for( Country c: listCountry) {
			City maxPopulationC = c.listCity.stream()
	                .max(Comparator.comparing(City::getPopulation))
	                .get(); 
			
			System.out.println("Thanh pho dong dan nhat cua quoc gia " + c.name + " la " + maxPopulationC.name);
		}
	}
	
//	tim thanh pho dong dan nhat cua moi luc dia 
	public void FindCity_Continent() {
		
		for( String id: list_id ) {
			ArrayList<City> listCity_all = new ArrayList<City>();
			
			City maxPopulationC = new City(0, "0", 0);
			for( Country c: listCountry) {
				if( c.continent.equals(id) == true ) {
//					System.out.print(c.name + " ");
					listCity_all.addAll(c.listCity);
				}
			}
			
			maxPopulationC = listCity_all.stream()
	                .max(Comparator.comparing(City::getPopulation))
	                .get();

			System.out.println("Thanh pho dong dan nhat cua luc dia " + id + " la " + maxPopulationC.name);
		}
	}
	
//	tim thanh pho la thu do dong dan nhat
	public void FindCapital() {
		Country maxPopulationCap = listCountry.stream()
                .max(Comparator.comparing(Country::getPopulationCap))
                .get(); 
		
		System.out.println("Thanh pho la thu do dong dan nhat la " + maxPopulationCap.capital_C.name);
	}
	
//	tim thanh pho la thu do dong dan nhat cua moi luc dia
	public void FindCap_Continent() {
		for( String id: list_id ) {
			
			Country maxPopulationCap = listCountry.get(0);
			ArrayList<Country> listCountry_id = new ArrayList<Country>();
			for( Country c: listCountry) {
				if( c.continent.equals(id) == true ) {
					listCountry_id.add(c);
				}
			}
			
			maxPopulationCap = listCountry_id.stream()
	                .max(Comparator.comparing(Country::getPopulationCap))
	                .get(); 
			
			System.out.println("Thanh pho la thu do dong dan nhat cua luc dia " + id + " la " + maxPopulationCap.capital_C.name);
		}
	}
	
	public void SortCountry() {
//		ArrayList<Country> listCountry_sort = new ArrayList<Country>();
//		listCountry_sort = (ArrayList<Country>) listCountry.clone();
		
		ArrayList<Country> listCountry_sort = (ArrayList<Country>) listCountry.stream() 
        	.sorted((s1, s2) -> s2.listCity.size() - s1.listCity.size())
			.collect(Collectors.toList());

		
		System.out.println("Cac quoc gia theo so luong thanh pho giam dan: ");
		for(Country i: listCountry_sort) {
			System.out.print(i.name + " ");
		}
		
		System.out.println();
	}
	
	public void SortCountry2() {
		ArrayList<Country> listCountry_sort = (ArrayList<Country>) listCountry.stream() 
	       	.sorted(Comparator.comparingDouble(Country::getMatdo).reversed())
			.collect(Collectors.toList());

		System.out.println("Cac quoc gia theo mat do dan so giam dan: ");
		for(Country i: listCountry_sort) {
			System.out.print(i.name + " ");
		}
			
		System.out.println();		
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Earth app = new Earth();
		app.init();
		app.FindCity_Country();
		System.out.println("-------------------------");
		app.FindCapital();
		System.out.println("-------------------------");
		app.FindCity_Continent();
		System.out.println("-------------------------");
		app.FindCap_Continent();
		System.out.println("-------------------------");
		app.SortCountry();
		System.out.println("-------------------------");
		app.SortCountry2();
	}

}
