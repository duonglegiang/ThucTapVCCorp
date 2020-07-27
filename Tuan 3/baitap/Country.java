package baitap;

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.stream.*;

public class Country {
	public String code;
	public String name;
	public String continent;
	public double surfaceArea;
	public int population_country;
	public double gnp;
	public int capital;
	City capital_C;
	ArrayList<City> listCity;
	public double matdo;
	
	public Country(String code, String name, String continent, double surfaceArea, int population_country, double gnp, City capital_C, ArrayList<City> listCity) {
		this.code = code;
		this.name = name;
		this.capital = capital_C.id;
		this.capital_C = capital_C;
		this.continent = continent;
		this.gnp = gnp;
		this.listCity = listCity;
		this.population_country = population_country;
		this.surfaceArea = surfaceArea;
		this.matdo = population_country/surfaceArea;
	}
	
	public int getPopulationCap() {
		return this.capital_C.population;
	}
	
	public double getMatdo() {
		return this.matdo;
	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
