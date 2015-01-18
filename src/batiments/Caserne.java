package batiments;

import org.newdawn.slick.geom.*;

import sharedRessources.Images;
import models.*;

public class Caserne extends Batiment {
	public Caserne(){
	}

	@Override
	public void initialiser(float x, float y, Joueur j, int identite, boolean b){
		this.initiate(identite, j);
		//copie des champs de data
		Caserne caserne = (Caserne) this.getData().getCaserne();
		this.terrain= this.getJoueur().getPartie().getTerrain();
		this.setDefense(caserne.getDefense());
		this.setChampVision(caserne.getChampVision());
		this.setMaxPV(caserne.getMaxPV());
		this.setTempsConstruction(caserne.getTempsConstruction());
		this.setSizeXY(caserne.getSizeX(), caserne.getSizeY());
		this.setListeProduction(caserne.getListeProduction());
		this.setNom(caserne.getNom());
		this.setDescription(caserne.getDescription());
		this.setCollisionBox(new Rectangle(x,y,caserne.getCollisionRadius(),caserne.getCollisionRadius()));
		this.collisionBox.setCenterX(x);
		this.collisionBox.setCenterY(y);
		this.setImage(Images.get().getImage("Caserne"+this.getJoueur().getNumeroJoueur()));
		//initialisation
		this.setEnConstruction(!b);
		if(b)
			this.setPV(this.getMaxPV());
		else 
			this.setPV(1);
		this.setIdProduction(0);
		this.setP(new Point(x,y));
		this.setXY(x,y);
		this.setOrdre(new Ordre());
		this.setPointDeRalliement(null);
	}

	public void produireFantassin(){
		if(this.getJoueur().getRessources(1)>100){
		
		this.getFileDattente().add(new Production(this.getData().getFantassin(),"Fantassin",1));
		this.setOrdre(new Ordre_Produire());
		this.getJoueur().setRessources(1,this.getJoueur().getRessources(1)-100);
		}
	}

}
