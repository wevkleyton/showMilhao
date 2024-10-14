package br.com.showmilhao.application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import br.com.showmilhao.util.LogUtil;
import javazoom.jl.player.Player;

public class ContinuosReprodution extends Thread {

	private String fileMusic;
	private boolean loop;
	
	public ContinuosReprodution(String fileMusic, boolean loop) {
		this.fileMusic = fileMusic;
		this.loop = loop;
	}
	
	@Override
	public void run() {
		try {
			do {
				new Player(new BufferedInputStream(new FileInputStream(fileMusic))).play();
			} while (loop);
			
		} catch (Exception e) {
			LogUtil.getLogger(ContinuosReprodution.class).error(e.getCause().toString());
		}
	}
}
