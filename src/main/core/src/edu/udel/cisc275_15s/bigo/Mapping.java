package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.Gdx;

public class Mapping {
	public static int yScreenToText(int coord){
		return Gdx.graphics.getHeight()-coord;
	}
}
