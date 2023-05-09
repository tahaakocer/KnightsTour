package application;

import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class setButton extends Button{
	
	private int shadowHeight = 5;
	private DropShadow shadow;
	private BlendMode blendMode;
	private String style = "-fx-background-color: #F7EDB6;";
	
	setButton(){
		// tasarım
		getStyleClass().add("my-button");
		shadow = new DropShadow();
		shadow.setColor(Color.BLUE);
		shadow.setHeight(shadowHeight);
		setBlendMode(blendMode);
		setStyle(style);
		setText("");
		
		//hover efekti
		setOnMouseEntered(e -> {
			setEffect(shadow);
		});
		setOnMouseExited(e->{
			setEffect(null);
		});
	}
}
