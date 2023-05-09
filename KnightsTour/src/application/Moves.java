package application;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;

public class Moves {

	public int knightX = 0;
	public int knightY = 0;
	//atın gidebileceği konumların mevcut konuma göre farkları
	private int[] rowOffsets = new int[] { 2, 2, -2, -2, 1, 1, -1, -1 };
	private int[] colOffsets = new int[] { 1, -1, 1, -1, 2, -2, 2, -2 };
	
	public Moves() {

	}
	
	//konum daha önce ziyaret edildi mi
	public boolean isKnightMoveValid(int row, int col) {
		
		int rowDiff = Math.abs(row - knightX);
		int colDiff = Math.abs(col - knightY);
		
		if ((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2)) {
			// Hücre daha önce ziyaret edilmediyse true döndür
			int cell = row * 10 + col;
			if (!SampleController.visitedCells.contains(cell)) {
				return true;
			}
		}
		return false;
	}
	//butonların çerçevelerini eski haline çevir
	public void restoreBorders()  {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				SampleController.buttons[i][j].setBorder(Border.stroke(Color.TRANSPARENT));
			}
		}
	}
	
	//atın gidebileceği konum var mı
	public boolean isAnyValidMoveLeft(Label lblGameOver) {
		// Tüm butonların çerçevesini eski haline çevir
		restoreBorders();
	
		boolean anyValidMovesLeft = false;

		for (int i = 0; i < 8; i++) {
			int newRow = knightX + rowOffsets[i];
			int newCol = knightY + colOffsets[i];

			if (newRow >= 0 && newRow < 9 && newCol >= 0 && newCol < 9) {
				if (isKnightMoveValid(newRow, newCol)) {
					// Atın gidebileceği konumlardaki butonların çerçevesini parlat
					SampleController.buttons[newRow][newCol].setBorder(Border.stroke(Color.BLUE));
					;
					anyValidMovesLeft = true;
				}
			}
		}

		// Atın mevcut konumunda kalması durumunda oyunun sonlandırılması sağlanır.
		if (!anyValidMovesLeft) {
	
			lblGameOver.setText("GAME OVER..");

		}
		return anyValidMovesLeft;
	}

}
