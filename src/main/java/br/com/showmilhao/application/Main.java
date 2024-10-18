package br.com.showmilhao.application;
	
import java.sql.Connection;
import java.sql.PreparedStatement;

import br.com.showmilhao.connection.ConnectionFactory;
import br.com.showmilhao.util.LogUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static final String FILE_MUSIC = "src/main/resouces/songs/som-abertura-2.mp3";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Janela JavaFX!");
			LogUtil.getLogger(Main.class).info(primaryStage.getTitle());;
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			ContinuosReprodution reprodution = new ContinuosReprodution(FILE_MUSIC, true);
			reprodution.start();
			
//			JLayer layer = new JLayer();
//			File mp3 = new File("src/main/resouces/songs/tire-a-carta-do-baralho-voice.mp3");
//			layer.tocar(mp3);
//			layer.start();
			
			
//			Connection connection = ConnectionFactory.getConnection();
//			String sql = "INSERT INTO jogador (id,nome,pontuacao) VALUES ($next_id, ?, ?)";
//			try(PreparedStatement statement = connection.prepareStatement(sql)){
//				statement.setString(2, "Pedro");
//				statement.setInt(3, 200);
//				statement.execute();
//				connection.commit();
//			}
//			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
