package com.java.pitfall.environment.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sound {
	AudioPlayer MGP = AudioPlayer.player;
	AudioStream BGM;
	public Sound(){
		
	}
	
	//Efeitos sonoros
	public void song(String path){
		ContinuousAudioDataStream loop;

		try
		{
			InputStream test = new FileInputStream(path);
            BGM = new AudioInputStream(test);
            AudioInputStream s = (AudioInputStream) new AudioInputStream(BGM);
            AudioData audiodata = s.getData();
            loop = new ContinuousAudioDataStream(audiodata);
			AudioPlayer.player.start(loop);
		}
		catch(FileNotFoundException e){
			System.out.print(e.toString());
		}
	}
}