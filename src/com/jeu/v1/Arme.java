package com.jeu.v1;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {

	private Rectangle corps=new Rectangle(-5,0,10,50);
	private Circle sortie=new Circle(0,0,5);
	
	public Rectangle getCorps() {
		return corps;
	}
	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}
	public Circle getSortie() {
		return sortie;
	}
	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}
	public Arme(GraphicObject player) {
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		corps.setFill(Color.DARKGREEN);
		sortie.setFill(Color.BROWN);
		updateSortie();
	}
	
	public void updateSortie() {
		sortie.setTranslateX(corps.getTranslateX());
		sortie.setTranslateY(corps.getTranslateY()+25);
	}
	public void rotateRight() {
		corps.setRotate(corps.getRotate()-5);
	}
	
	public void rotateLeft() {
		corps.setRotate(corps.getRotate()+5);
	}
	public double getRotate() {
		return corps.getRotate()-90;
	}
	public void attachToPlayer(Player player) {
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		updateSortie();
	}
}
