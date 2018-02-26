package com.example.rohan.pointcounter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


public class AddPlayer extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_player, container, false);
	}
	public String getEText(){
		EditText eText = getView().findViewById(R.id.playerName);
		String text = eText.getText().toString();
		eText.setVisibility(View.GONE);
		return text;
	}
	public void setNText(String text){
		TextView nText = getView().findViewById(R.id.nameText);
		nText.setVisibility(View.VISIBLE);
		nText.setText(text);
	}
}
