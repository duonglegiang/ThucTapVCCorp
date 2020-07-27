package baitap;

import java.util.*;
import java.io.*;
import java.lang.*;

public class City {
	public int id;
	public String name;
	public int population;
	
	public City(int id, String name, int population) {
		this.id = id;
		this.name = name;
		this.population = population;
	}
	
	public int getPopulation() {
		return population;
	}
}
