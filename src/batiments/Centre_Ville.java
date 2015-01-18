package batiments;

import org.newdawn.slick.geom.*;

import sharedRessources.Images;
import models.*;


public class Centre_Ville extends Batiment {

	//Data contient les champs:
	//sizeX, sizeY, defense, pvmax; tempsconstruction;champvision;listeproduction;

	

	public Centre_Ville(){
	}

	@Override
	public void initialiser(float x, float y, Joueur j, int identite, boolean b){
		this.initiate(identite, j);
		//copie des champs de data
		Centre_Ville centreville = (Centre_Ville) this.getData().getCentre_ville();
		this.terrain= this.getJoueur().getPartie().getTerrain();
		this.setDefense(centreville.getDefense());
		this.setChampVision(centreville.getChampVision());
		this.setMaxPV(centreville.getMaxPV());
		this.setTempsConstruction(centreville.getTempsConstruction());
		this.setSizeXY(centreville.getSizeX(), centreville.getSizeY());
		this.setListeProduction(centreville.getListeProduction());
		this.setNom(centreville.getNom());
		this.setDescription(centreville.getDescription());
		// Lacollision box du batiment est un rectangle
		this.setCollisionBox(new Rectangle(x,y,centreville.getCollisionRadius(),centreville.getCollisionRadius()));
		this.collisionBox.setCenterX(x);
		this.collisionBox.setCenterY(y);
		this.setImage(Images.get().getImage("Centre_Ville"+this.getJoueur().getNumeroJoueur()));
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

		// modifs flo
	
	public void produirePeon(){
		if(this.getJoueur().getRessources(1)>50 && this.getFileDattente().size()<2){
      
		this.getFileDattente().add(new Production(   this.getData().getPeon(),"models.Peon",1));
		//Ordre_Produire o = new Ordre_Produire();
		
		this.setOrdre(new Ordre_Produire());

		this.getJoueur().setRessources(1,this.getJoueur().getRessources(1)-50);
		}
	}

}
