package songplayer;

import java.io.FileInputStream;
import java.io.IOException;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MapMusic {
	//Class responsible for playing and stopping the map theme music
	private static AudioStream audioStream = null;
	public static void play() {
		//Try to get the audio file
		try {
			FileInputStream is = new FileInputStream("music/map.wav");
			audioStream = new AudioStream(is);
			AudioPlayer.player.start(audioStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void stop() {
		if (audioStream != null) {
			AudioPlayer.player.stop(audioStream);
		}
	}
}