package edu.udel.cisc275_15s.bigo;

import com.badlogic.gdx.Gdx;

//mapping class because sprite coordinates and global coordinates are different
public class Mapping {
	//maps the y Screen coordinate to correspond with the y Texture coordinate
	public static int yScreenToText(int coord){
		return Gdx.graphics.getHeight()-coord;
	}
}
