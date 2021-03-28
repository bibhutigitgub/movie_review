package com.crejo.dao;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
private static DataStore  dataStore=new DataStore();
private Map<String, UserDetails> mapOfUsers=new HashMap<String, UserDetails>();
private   Map<String, MovieDetails> mapOfmovies=new HashMap<String, MovieDetails>();

	
public Map<String, UserDetails> getMapOfUsers() {
	return mapOfUsers;
}

public void setMapOfUsers(Map<String, UserDetails> mapOfUsers) {
	this.mapOfUsers = mapOfUsers;
}

public Map<String, MovieDetails> getMapOfmovies() {
	return mapOfmovies;
}

public void setMapOfmovies(Map<String, MovieDetails> mapOfmovies) {
	this.mapOfmovies = mapOfmovies;
}


private DataStore() {
	// TODO Auto-generated constructor stub
}

public static DataStore getDataStore() {
	return dataStore;
}
}
