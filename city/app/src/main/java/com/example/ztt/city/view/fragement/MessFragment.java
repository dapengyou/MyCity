package com.example.ztt.city.view.fragement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztt.city.R;

/**
 * 食堂Fragment
 */
public class MessFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactsLayout = inflater.inflate(R.layout.fragment_mess,
				container, false);
		return contactsLayout;
	}

}
