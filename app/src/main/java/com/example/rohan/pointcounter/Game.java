package com.example.rohan.pointcounter;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by rohan on 2/24/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Game {
	private ArrayList<Player> players;
	private ArrayList<Double> scores;
	private boolean finsihed = false;
	private Player winner;
	private boolean tie = false;
	private static ArrayList<Game> allGames;
	private LocalDate today = LocalDate.now();
	public Game(ArrayList<Player> p){
		players = p;
		scores = new ArrayList<>(p.size());
	}
	public void changeScore(String pId, double points){
		for(int i = 0; i<players.size(); i++){
			if(players.get(i).getId().equals(pId)){
				scores.set(i, scores.get(i)+points);
			}
		}
	}
	public void finish(){
		finsihed = true;
		double top = -1;
		boolean tie = false;
		for(int i = 0; i<players.size(); i++){
			if(scores.get(i) > top){
				tie = false;
				top = scores.get(i);
				winner = players.get(i);
			}
			if(scores.get(i) == top){
				tie = true;
			}
		}
		allGames.add(this);
	}
	public static ArrayList<Game> getAllGames(){
		return allGames;
	}
}
