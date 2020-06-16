package Model;


import javax.sound.sampled.*;

public class GameMusic{
	
	private Clip clip;
	
	// Change file name to match yours, of course
	
	public static GameMusic GameAudio = new GameMusic("Sound/GameMusic.wav");//Game Music
	public static GameMusic MenuMusic = new GameMusic("Sound/GameMenu.wav");//Menu Music
	public static GameMusic GameOver = new GameMusic("Sound/GameOver.wav");//Game Over Sound
	public static GameMusic Eating = new GameMusic("Sound/AppleBite.wav");//Eating Sound
	public static GameMusic Apple = new GameMusic("Sound/WeedApple.wav");//Music for The Weed Apple
	
	public GameMusic (String fileName) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(GameMusic.class.getClassLoader().getResource(fileName));
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.start();
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stop(){
		if(clip == null) return;
		clip.stop();
	}
	
	public void loop() {
		try {
			if (clip != null) {
				new Thread() {
					public void run() {
						synchronized (clip) {
							clip.stop();
							clip.setFramePosition(0);
							clip.loop(Clip.LOOP_CONTINUOUSLY);
						}
					}
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isActive(){
		return clip.isActive();
	}
}