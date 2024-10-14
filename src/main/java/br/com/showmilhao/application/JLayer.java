package br.com.showmilhao.application;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import br.com.showmilhao.util.LogUtil;
import javazoom.jl.player.Player;

public class JLayer extends Thread {

	private File mp3;

	public void tocar(File mp3) {
		this.mp3 = mp3;
	}

	@Override
	public void run() {

		try {
			try (FileInputStream fileStream = new FileInputStream(mp3)) {
				BufferedInputStream bufferedStream = new BufferedInputStream(fileStream);
				Player player = new Player(bufferedStream);
				player.play();
				if (player.isComplete()) {
					player.close();
				}
			}

		} catch (Exception e) {
			LogUtil.getLogger(JLayer.class).error(e.getCause().toString());
		}

	}
}
