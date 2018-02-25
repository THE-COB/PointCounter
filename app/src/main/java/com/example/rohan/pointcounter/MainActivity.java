package com.example.rohan.pointcounter;

import android.content.Context;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		File gameFile = new File("data/data/com.example.rohan.pointcounter/allGames.dat");
		try {
			gameFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		final FrameLayout f1 = findViewById(R.id.p1);
		final FrameLayout f2 = findViewById(R.id.p2);
		final File playerFile = new File("data/data/com.example.rohan.pointcounter/players.dat");

		if(!playerFile.exists()){
			try {
				playerFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Button setPlayers = findViewById(R.id.setPlayers);
			setPlayers.setVisibility(View.VISIBLE);
			setPlayers.setOnClickListener(new View.OnClickListener(){
				public void onClick(View v){
					try {
						FileOutputStream fos = new FileOutputStream(getApplicationContext().getFilesDir()+"/players.dat");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						EditText n1 = null;
						EditText n2 = null;
						for(int i = 0; i<f1.getChildCount(); i++){
							View temp1 = f1.getChildAt(i);
							View temp2 = f2.getChildAt(i);
							if(temp1.getId() == R.id.playerName){
								n1 = (EditText) temp1;
							}
							if(temp2.getId() == R.id.playerName){
								n2 = (EditText) temp2;
							}
						}
						Player p1 = new Player(n1.getText().toString(), 0);
						Player p2 = new Player(n2.getText().toString(), 1);
						n1.setVisibility(GONE);
						n2.setVisibility(GONE);
						ArrayList<Player> players = new ArrayList<>(0);
						players.add(p1);
						players.add(p2);
						oos.writeObject(players);

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					setNames();
				}
			});
		}
		setNames();
	}

	private void setNames(){
		FrameLayout f1 = findViewById(R.id.p1);
		FrameLayout f2 = findViewById(R.id.p2);
		try {
			FileInputStream fis = new FileInputStream(getApplicationContext().getFilesDir()+"/players.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			ArrayList<Player> inPlayers = (ArrayList) ois.readObject();
			TextView nt1 = null;
			TextView nt2 = null;
			for(int i = 0; i<f1.getChildCount(); i++){
				View temp1 = f1.getChildAt(i);
				View temp2 = f2.getChildAt(i);
				if(temp1.getId() == R.id.playerName){
					nt1 = (TextView) temp1;
				}
				if(temp2.getId() == R.id.playerName){
					nt2 = (TextView) temp2;
				}
			}
			nt1.setText(inPlayers.get(0).getName());
			nt2.setText(inPlayers.get(1).getName());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
