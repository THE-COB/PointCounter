package com.example.rohan.pointcounter;

/**
 * Created by rohan on 2/24/2018.
 */

public class Player {
	private String name;
	private String id;
	public Player(String name, int count){
		this.name = name;
		this.id = Integer.toHexString(count);
	}
	public String getName(){
		return name;
	}
	public String getId(){
		return id;
	}
}
