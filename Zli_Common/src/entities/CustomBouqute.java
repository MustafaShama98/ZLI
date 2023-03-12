package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entities.users.Catalog;

public class CustomBouqute {
private Map<Catalog,Integer> customBouqute = new HashMap<Catalog,Integer>();

	public CustomBouqute() {
		
		// TODO Auto-generated constructor stub
	}
	public void AddtoBouqute(Catalog flower,int count) {
		customBouqute.put(flower, count);
	}
}
