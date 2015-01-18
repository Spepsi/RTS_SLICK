package models;

import org.newdawn.slick.geom.*;

public class Soldat extends Unite{

	//variables de l'unit�




	/* ORDRE : ATTAQUER
	 * 
	 * pseudo-code:
	 * si l'�l�ment A est � port�e de coup, l'�l�ment frappe
	 * sinon si l'�l�ment A est visible, l'�l�ment se dirige vers lui
	 *       sinon si l'�l�ment est proche de la zone d�finie par la position
	 *                  l'�l�ment cherche un ennemi dans la zone
	 *                  si il y a un ennemi dans la zone
	 *                       on r�d�finit l'identit� de l'�l�ment A
	 *                  sinon l'�l�ment attend
	 *             sinon l'�l�ment va dans la zone d�finie par les coordonn�es 
	 */



	public void attaquer(boolean secondaire) throws InterruptedException{
		// fonction qui g�re l'ordre principal attaque si secondaire=false
		//       et qui g�re l'ordre secondaire attaque si secondaire=true
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
	 * si l'�l�ment n'est pas proche de la position, il y va.
	 * si l'�l�ment est proche de la position: deux options
	 *    si il y a un ennemi dans la zone l'�l�ment va l'attaquer
	 *    sinon il reste � attendre au point d�fini par les variables d'ordre.
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
				System.out.println("d�fense");
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
		//renvoie 'true' si la position d�finie dans les variables d'ordre 
		//est dans un carr� de demi c�t� 'distance'
		//autour de la position de l'�l�ment.
		//
		//si le boolean defendre vaut true, les variables d'ordre concern�es sont ordre_defendre_X et _Y
		//si le boolean defendre vaut false, les variables d'ordre concern�es sont ordre_attaquer_X et _Y
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
