package songplayer;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class CaughtMusic {
	//Class responsible for playing and stopping the caught pokemon music
	private static AudioStream audioStream = null;
	private static Boolean on = false;
	public static void play() {
		//Try to get the audio file
		try {
			FileInputStream is = new FileInputStream("music/caught.wav");
			audioStream = new AudioStream(is);
			AudioPlayer.player.start(audioStream);
			on = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void stop() {
		System.out.println("lol");
		if (audioStream != null) {
			AudioPlayer.player.stop(audioStream);
			on = false;
		}
	}
	public static Boolean on() {
		return on;
	}
}