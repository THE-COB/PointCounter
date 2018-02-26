package com.example.rohan.pointcounter;

import android.app.FragmentManager;
import android.content.Context;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
		pFile = new File(getFilesDir(), "players.json");
		boolean isFile = false;
		try {
			isFile = pFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isFile){
			setNewNames();
		}
		useOldNames();
	}

	private File pFile;
	private android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
	private AddPlayer p1 = (AddPlayer)fm.findFragmentById(R.id.p1);
	private AddPlayer p2 = (AddPlayer)fm.findFragmentById(R.id.p2);

	private void setNewNames(){
		Button setNames = findViewById(R.id.setNames);
		setNames.setVisibility(View.VISIBLE);
		setNames.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				try {
					Player player1 = new Player(p1.getEText(), 1);
					Player player2 = new Player(p2.getEText(), 2);
					FileOutputStream fos = openFileOutput("players.json", MODE_PRIVATE);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					JSONObject jobj = new JSONObject();
					jobj.put(player1.getId(), player1.getName());
					jobj.put(player2.getId(), player2.getName());
					oos.writeObject(jobj.toString());
					oos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void useOldNames(){
		try {
			FileInputStream fis = openFileInput("players.json");
			ObjectInputStream ois = new ObjectInputStream(fis);
			JSONArray jArr = new JSONArray(ois.readObject());
			p1.setNText((String) jArr.get(0));
			p2.setNText((String) jArr.get(1));
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
