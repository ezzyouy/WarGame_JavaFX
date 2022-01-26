package com.jeu.v1;


import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class Program extends Application{
	
	private double widthWindow=800;
	private double heightWindow=600;
	private Pane container=new Pane();
	
	Line line = new Line(0,200,widthWindow,200);
	Zone zone1=new Zone(0,0,line.getEndX()-50,line.getEndY()-50);
	Zone zone2=new Zone(line.getStartX(),line.getStartY(),line.getEndX()-50,heightWindow-50);
	
	private Player player=new Player(zone2);
	private List<Monster> monsters=new ArrayList<>();
	private List<Balle> balles=new ArrayList<>();
	Arme arme=new Arme(player);
	
	EventHandler<KeyEvent> event=new EventHandler<KeyEvent>() {
		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			if(event.getCode()==KeyCode.X) {
				arme.rotateLeft();
			}
			if(event.getCode()==KeyCode.W) {
				arme.rotateRight();
			}
			if(event.getCode()==KeyCode.SPACE) {
				Balle balle=new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
			}
			if(event.getCode()==KeyCode.LEFT) {
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
				arme.attachToPlayer(player);
				
			}
			if(event.getCode()==KeyCode.RIGHT) {
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
				arme.attachToPlayer(player);
			}
			if(event.getCode()==KeyCode.UP) {
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
				arme.attachToPlayer(player);
			}
			if(event.getCode()==KeyCode.DOWN) {
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
				arme.attachToPlayer(player);
				
			}
		}
	};
	
	AnimationTimer anime=new AnimationTimer() {
		
		@Override
		public void handle(long now) {
			refreshContent();
		}
	};
	private void refreshContent() {
		for(Balle balle:balles) {
			for(Monster m:monsters) {
				if(balle.touch(m)) {
					container.getChildren().removeAll(balle.getCorps(),m.getCorps());
					balle.setAlive(false);
					m.setAlive(false);
					
				}
			}
		}
		
		monsters.removeIf(GraphicObject::isDead);
		balles.removeIf(GraphicObject::isDead);
		
		for(Balle balle:balles) {
			balle.update();
		}
		if(Math.random()<0.01) {
		Monster m=new Monster(zone1);
		container.getChildren().add(m.getCorps());
		monsters.add(m);
		}
	}
	public static void main(String[] args) {
		Application.launch(args);
	}
	private void createContent() {
		container.getChildren().add(line);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());
	}
	@Override
	public void start(Stage window) throws Exception {
		window.setWidth(widthWindow);
		window.setHeight(heightWindow);
		window.setTitle("jeu de guerre!");
		Scene scene=new Scene(container);
		createContent();
		window.setScene(scene);
		anime.start();
		scene.setOnKeyPressed(event);
		window.show();
	}
	
	

}
