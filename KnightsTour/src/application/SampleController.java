package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import javax.swing.JOptionPane;

public class SampleController {
	
	public static Button[][] buttons;
	@FXML
	private GridPane gridPane;
	@FXML
	private MenuItem menuItemNew;
	@FXML
	private MenuItem menuItemExit;
	@FXML
	private MenuItem menuItemAbout;
	@FXML
	private Button btnAboutOkay;
	@FXML
	private Label lblScore;
	@FXML
	private Label lblGameOver;
	FXMLLoader loader;
	Parent root;
	Scene scene;
	Stage stage;
	private Moves moves;
	private final String RED_COLOR = "-fx-background-color: #F44336;";
	private final String GREEN_COLOR = "-fx-background-color: #4CAF50;";

	// Atın konumunu tutacak değişkenler
	private int prevX = -1;
	private int prevY = -1;
	private int score = 0;
	public static List<Integer> visitedCells = new ArrayList<>();
	
	//Program ilk çalıştırıldığında çalışacak kodlar
	public void initialize() {
	//	menuItemAbout.setOnAction(e -> handleMenuItemAboutAction(e));
		menuItemNew.setOnAction(e -> handleMenuItemNewAction(e));
		menuItemExit.setOnAction(e -> handleMenuItemExitAction(e));
		
		buttons = new Button[9][9];
		moves = new Moves();
		lblScore.setText("SCORE : " + score);
		lblGameOver.setText(" ");

		// Buton tasarımı
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Button button = new setButton();
				buttons[i][j] = button;
			}
		}

		// Butonu ızgaraya sığdırma ve tıklanma fonksiyonunu çağırma
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				buttons[i][j].setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
				gridPane.add(buttons[i][j], i, j);
				buttons[i][j].setOnAction(e -> handleButtonAction(e));

			}
		}	
	}
	@FXML
	private void handleMenuItemAboutAction(ActionEvent e){
		
		try {
			loader = new FXMLLoader(getClass().getResource("About.fxml"));
			loader.setController(this);
			root = loader.load();
			scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = new Stage();
			stage.setScene(scene);
			stage.initStyle(StageStyle.UTILITY);
			stage.show();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	@FXML
	private void handleAboutButtonAction(ActionEvent e) {
		stage.close();
	}
	// new game menu item tıklama fonksiyonu
	private void handleMenuItemNewAction(ActionEvent e) {
		newGame();
	}
	private void handleMenuItemExitAction(ActionEvent e) {
		Platform.exit();
	}
	private void moveUpdate(Button button,int x,int y) {
		
		moves.knightX = x;
		moves.knightY = y;
		visitedCells.add(x * 10 + y);
		markCellVisited(x, y);
		moves.isAnyValidMoveLeft(lblGameOver);
	}
	// butona tıklama foknsiyonu
	private void handleButtonAction(ActionEvent event) {
		Button button = (Button) event.getSource();
		int x = GridPane.getColumnIndex(button);
		int y = GridPane.getRowIndex(button);
		if (score == 0) {
			moveUpdate(button,x,y);
		} else if (score == 80) {
			if (moves.isKnightMoveValid(x, y)) {
				moveUpdate(button,x,y);
			}
		//	JOptionPane.showMessageDialog(null,"Tebrikler! En yüksek skora ulaştınız.","Oyun Bitti",JOptionPane.DEFAULT_OPTION);
			
		} else if (score > 0) {
			if (moves.isKnightMoveValid(x, y)) {
				moveUpdate(button,x,y);
			}
		}
	}
	
	//
	private void markCellVisited(int row, int col) {
		// Önceki butonun rengini eski haline çevir
		if (prevX != -1 && prevY != -1 && score != 0) {
			buttons[prevX][prevY].setStyle(RED_COLOR);
		}
		// gidilen butonun rengini değiştirir
		score++;
		buttons[row][col].setStyle(GREEN_COLOR);
		buttons[row][col].setText("" + score);
		lblScore.setText("SCORE : " + score);
		// Önceki butonun koordinatlarını kaydet
		prevX = row;
		prevY = col;
	}
	private void newGame() {
		score = 0;
		moves.restoreBorders();
		lblGameOver.setText("");
		lblScore.setText("SCORE : " + score);
		visitedCells.clear();
		for(Button[] btnArray : buttons) {
			for(Button btn : btnArray) {
				btn.setStyle("-fx-background-color: #F7EDB6;");
				btn.setText("");
				btn = new setButton();
			}
		}
		
	}
}
