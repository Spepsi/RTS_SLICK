package models;

import org.newdawn.slick.geom.*;

public class Soldat extends Unite{

	//variables de l'unité




	/* ORDRE : ATTAQUER
	 * 
	 * pseudo-code:
	 * si l'élément A est à portée de coup, l'élément frappe
	 * sinon si l'élément A est visible, l'élément se dirige vers lui
	 *       sinon si l'élément est proche de la zone définie par la position
	 *                  l'élément cherche un ennemi dans la zone
	 *                  si il y a un ennemi dans la zone
	 *                       on rédéfinit l'identité de l'élément A
	 *                  sinon l'élément attend
	 *             sinon l'élément va dans la zone définie par les coordonnées 
	 */



	public void attaquer(boolean secondaire) throws InterruptedException{
		// fonction qui gère l'ordre principal attaque si secondaire=false
		//       et qui gère l'ordre secondaire attaque si secondaire=true
		int idCible;
		Point p;
		if(secondaire){
			idCible = this.getOrdre().getOrdreSecondaire().getOrdreAttaquerIdentite();
		} else {
			idCible = this.getOrdre().getOrdreAttaquerIdentite();		
		}

		Element ennemiEnplus = this.existeEnnemiProche(this.getJoueur().getNumeroJoueur(), this.getPortee());
		if(ennemiEnplus!=null){
			int att = this.getAttaque();
			int def = ennemiEnplus.getDefense();
			this.getJoueur().getPartie().getJoueur(ennemiEnplus.getJoueur().getNumeroJoueur()).getElementsParId(ennemiEnplus.getId()).ajouterPointsDeVie(-Math.max(1,att-def));
			if(ennemiEnplus.getPV()<=0){
				this.getJoueur().getPartie().enleverElement(ennemiEnplus);
			}
		} else if(!secondaire){
			p = this.coordonneesObjectif(idCible, this.getChampVision());
			if(p!=null){
				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(p.getX(),p.getY()));
			} else if(!secondaire){
				if(this.estProcheObjectif(false, 15)){
					this.setOrdre(new Ordre_Defendre(this.getX(), this.getY()));
				} else {
					this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(this.getOrdre().getOrdreAttaquerX(), this.getOrdre().getOrdreAttaquerY()));
				}
			} else {
				System.out.println("chou blanc: soldat.java l.75");
			}
		}
		switch(this.getOrdre().getOrdreSecondaire().getOrdre()){
		case SE_DEPLACER: this.deplacer(true); break;
		case NE_RIEN_FAIRE: break;
		default:
			break;
		}


	}

	private Point coordonneesObjectif(int idCible, float champVision) {
		// Si dans le champs de vision attaquer la cible, sinon prendre cible la plus proche
		// JE SAIS PAS CE QUE C'EST IDCIBLE ???????????????????? ( J'ai lu ordre attaquer)
		Element e = getTerrain().getEnnemiPlusProche(this);
		return new Point(e.getX(),e.getY());
	}

	/* ORDRE: DEFENDRE
	 * pseudo-code:
	 * si l'élément n'est pas proche de la position, il y va.
	 * si l'élément est proche de la position: deux options
	 *    si il y a un ennemi dans la zone l'élément va l'attaquer
	 *    sinon il reste à attendre au point défini par les variables d'ordre.
	 *     
	 */
	public void defendre(boolean b) throws InterruptedException{
		if(!b){
			Element ennemi = this.existeEnnemiProche(this.getNumeroJoueur(), 50);
			if(ennemi==null){
				if(this.estProcheObjectif(10, this.getOrdre().getOrdreDefendreX(), this.getOrdre().getOrdreDefendreY()))
					this.getOrdre().setOrdreSecondaire(new Ordre());
				else
					this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(this.getOrdre().getOrdreDefendreX(), this.getOrdre().getOrdreDefendreY()));

			}
			else{
				System.out.println("défense");
				int att = this.getAttaque();
				int def = ennemi.getDefense();
				this.getJoueur().getPartie().getJoueur(ennemi.getJoueur().getNumeroJoueur()).getElementsParId(ennemi.getId()).ajouterPointsDeVie(-Math.max(1,att-def));
				if(ennemi.getPV()<=0){
					this.getJoueur().getPartie().enleverElement(ennemi);
				}
			}
			switch(this.getOrdre().getOrdreSecondaire().getOrdre()){
			case ATTAQUER: this.attaquer(true); break;
			case SE_DEPLACER: this.deplacer(true); break;
			case NE_RIEN_FAIRE: break;
			default:
				break;
			}
		}

	}

	public boolean estProcheObjectif(boolean defendre, int distance){
		//renvoie 'true' si la position définie dans les variables d'ordre 
		//est dans un carré de demi côté 'distance'
		//autour de la position de l'élément.
		//
		//si le boolean defendre vaut true, les variables d'ordre concernées sont ordre_defendre_X et _Y
		//si le boolean defendre vaut false, les variables d'ordre concernées sont ordre_attaquer_X et _Y
		//
		float ordreX;
		float ordreY;
		if(defendre){
			ordreX = this.getOrdre().getOrdreDefendreX();
			ordreY = this.getOrdre().getOrdreDefendreY();
		} else {
			ordreX = this.getOrdre().getOrdreAttaquerX();
			ordreY = this.getOrdre().getOrdreAttaquerY();
		}
		if(distance==0){
			return this.getX()==ordreX && this.getY()==ordreY;
		}
		if(this.getX()-ordreX<=distance){
			if(this.getX()-ordreX>=-distance){
				if(this.getY()-ordreY<=distance){
					if(this.getY()-ordreY>=-distance){
						return true;
					}
				}
			}
		}
		return false;

	}

}
