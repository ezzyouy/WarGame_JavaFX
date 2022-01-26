package com.jeu.v1;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;

public class Balle extends GraphicObject {
	
	private Point2D direction=new Point2D(0,0);
	
	public Balle(Arme arme) {
		corps=new Circle(0,0,4,Color.BLUE);
		corps.setTranslateX(arme.getSortie().getTranslateX());
		corps.setTranslateY(arme.getSortie().getTranslateY());
		updateDirection(arme.getRotate());
	}
	
	private void updateDirection(double rotation) {
		Point2D p;
		double x=Math.cos(Math.toRadians(rotation));
		double y=Math.sin(Math.toRadians(rotation));
		p=new Point2D(x,y);
		direction=p.normalize().multiply(4);
	}
	
	public void update() {
		corps.setTranslateX(corps.getTranslateX()+direction.getX());
		corps.setTranslateY(corps.getTranslateY()+direction.getY());
	}
	
}
