package models;

import org.newdawn.slick.geom.*;

import sharedRessources.Images;

public class Peon extends Unite{

	private int vitesse_travail;
	//Data contient les champs: SizeXY;Defense;ChampVision;MaxPV;TempsCreation;Vitesse;VitesseTravail;listeproduction

	public Peon(){
	}

	
	public void initialiser(float x, float y, Joueur j, int identite, boolean b){
		this.initiate(identite, j);
		Peon p = (Peon) this.getData().getPeon();
		this.terrain= this.getJoueur().getPartie().getTerrain();
		//copie des champs de Data
		this.setSizeXY(p.getSizeX(), p.getSizeY());
		this.setDefense(p.getDefense());
		this.setChampVision(p.getChampVision());
		this.setMaxPV(p.getMaxPV());
		this.setTempsCreation(p.getTempsCreation());
		
		this.setVitesse(p.getVitesse());
		this.setVitesseTravail(p.getVitesseTravail());
		this.setListeProduction(p.getListeProduction());
		this.setNom(p.getNom());
		this.setDescription(p.getDescription());
		//assignation de la position et de l'ordre
		this.setPV(this.getMaxPV());
		this.setCollisionBox(new Circle(x,y,10));
		this.setP(new Point(x,y));
		this.setXY(x,y);
		this.setOrdre(new Ordre());
		
		this.setImage(Images.get().getImage("Peon"+this.getJoueur().getNumeroJoueur()));
	}

	public int getVitesseTravail(){
		return this.vitesse_travail;
	}
	public void setVitesseTravail(int v){
		this.vitesse_travail = v;
	}

	/*ORDRE: RAMASSER
	 * 
	 * ramasser est l'ordre assigné à un péon lorsque l'utilisateur clique droit sur une ressource
	 * en ayant sélectionné un péon au préalable
	 * 
	 * Note:
	 * 
	 * l'ordre s'arrête si le péon est à proximité de l'objectif et qu'il n'y a plus de ressources à proximité
	 * 
	 * pseudo-code
	 * si le péon n'est pas à distance 5 de l'objectif
	 * 		il avance vers l'objectif
	 * sinon
	 * 		si le péon est à côté d'une case contenant la ressource
	 * 			il mine
	 * 		sinon
	 * 			si le péon est à côté de l'objectif
	 * 				si il n'y a pas de ressources à cinq cases à la ronde
	 * 					l'ordre s'arrête
	 * 				sinon
	 *                  on rédéfinit l'objectif sur cette ressource
	 * 			sinon 
	 * 				le péon avance vers l'objectif
	 * 		
	 */
	public void ramasser() throws InterruptedException{
		float ordre_X = this.getOrdre().getOrdreRamasserX();
		float ordre_Y = this.getOrdre().getOrdreRamasserY();
		if(!this.estProcheObjectif(10f, ordre_X, ordre_Y)){
			System.out.println("le péon est loin: il avance");
			// il avance
			this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(ordre_X, ordre_Y));
			this.deplacer(true);
		} else if(this.getOrdre().getRessource()==null){
			Ressource r = this.estAProximiteRessource(50);
			if(r==null){
				System.out.println("le péon ne fait plus rien");
				// l'ordre s'arrête
				this.setOrdre(new Ordre());
			} else {

				System.out.println("le péon a detecté une nouvelle ressource");
				// il resset son ordre sur la nouvelle ressource détectée
				this.setOrdre(new Ordre_Ramasser(r, this.getTerrain()));
			}
		} else if (this.estACoteRessource(this.getOrdre().getRessource())){
			System.out.println("le péon peut ramasser");
			Ressource r = this.getOrdre().getRessource();
			int ressourcesGagnees = 0;
			int ressourcesRamassees = 0;
			if(this.getJoueur().getTech().vitesse_travail_peon_1.isOn()){
				ressourcesRamassees = 2;
			} else {
				ressourcesRamassees = 1;
			}
			if(r.getRessourcesRestantes()>ressourcesRamassees){
				ressourcesGagnees = ressourcesRamassees;
				r.setRessourcesRestantes(r.getRessourcesRestantes()-2);
			} else {
				ressourcesGagnees = r.getRessourcesRestantes();
				r.setRessourcesRestantes(0);
				this.getJoueur().getPartie().enleverRessource(r);
			}
			switch(r.getTypeRessource()){
			case BOIS: this.getJoueur().addRessources(0, ressourcesGagnees);break;
			case OR:this.getJoueur().addRessources(1, ressourcesGagnees);break;
			case FOI: this.getJoueur().addRessources(2, ressourcesGagnees);break;
			default:
				break;
			}
			System.out.println("votre stock de " + r.getTypeRessource() + " vaut " + this.getJoueur().getRessources(0));
			return;
		} else if (this.isAccessible(ordre_X, ordre_Y)){
			System.out.println("le péon n'est plus très loin");
			// il avance
			this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(ordre_X, ordre_Y));
			this.deplacer(true);
		} else {
			System.out.println("oh oh ");
			this.setOrdre(new Ordre_Ramasser(this.getOrdre().getRessource(), this.getTerrain()));

		}
	}
// FONCTION RAMASSER A REFAIRE
	
//JE SAIS PAS SI J'AI BIEN COMPRIS CETTE FONCTION ??? A REVOIR	
private boolean estProcheObjectif(float distance, float ordre_X, float ordre_Y) {
		
		return (ordre_X-this.getX())*(ordre_X-this.getX())+(ordre_Y-this.getY())*(ordre_Y-this.getY())<distance*distance;
	}

		public void ramasser2() throws InterruptedException{
		Ressource r = this.getOrdre().getRessource();
		if(r==null){
			Ressource resSuiv = this.estAProximiteRessourceLibre(50);
			if(resSuiv!=null){
				this.setOrdre(new Ordre_Ramasser(resSuiv,this.getTerrain()));
				return;
			} else {
				this.setOrdre(new Ordre());
				return;
			}
		}
		float x = r.getEmplacement().getX();
		float y = r.getEmplacement().getY();
		if(!this.estProcheObjectif(30f, x, y)){
			this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x,y));
			this.deplacer(true);
		} else if(this.estACoteRessource(this.getOrdre().getRessource())) {
			int ressourcesGagnees = 0;
			int ressourcesRamassees = 0;
			if(this.getJoueur().getTech().vitesse_travail_peon_1.isOn()){
				ressourcesRamassees = 2;
			} else {
				ressourcesRamassees = 1;
			}
			if(r.getRessourcesRestantes()>ressourcesRamassees){
				ressourcesGagnees = ressourcesRamassees;
				r.setRessourcesRestantes(r.getRessourcesRestantes()-2);
			} else {
				ressourcesGagnees = r.getRessourcesRestantes();
				r.setRessourcesRestantes(0);
				this.getJoueur().getPartie().enleverRessource(r);
			}
			switch(r.getTypeRessource()){
			case BOIS: this.getJoueur().addRessources(0, ressourcesGagnees);break;
			case OR:this.getJoueur().addRessources(1, ressourcesGagnees);break;
			case FOI: this.getJoueur().addRessources(2, ressourcesGagnees);break;
			default:
				break;
			}
			return;
		} else {
			if(r.isLibreHaut()){
				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x,y-this.def));
				this.deplacer(true);
				return;
			} else if (r.isLibreBas()){
				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x,y+this.def));
				this.deplacer(true);
				return;
			} else if (r.isLibreDroite()){
				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x+this.def,y));
				this.deplacer(true);
				return;
			} else if (r.isLibreGauche()){	
				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x-this.def,y));
				this.deplacer(true);
				return;
			} else {
				Ressource resSuiv = this.estAProximiteRessourceLibre(50);
				if(resSuiv!=null){
					this.setOrdre(new Ordre_Ramasser(resSuiv,this.getTerrain()));
					return;
				} else {
					if(this.getX()==x && this.getY()==y){
						this.setOrdre(new Ordre());
						return;
					}						
					this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(x,y));
					this.deplacer(true);
				}
			}
		} 
	}
	/*ORDRE: CONSTRUIRE
	 * si le boolean est en construction est false, créer le batiment
	 * sinon si le peon est loin, aller sur le batiment
	 * sinon construire le batiment
	 * lorsque la construction a fini
	 *    soit le péon voit autre chose à construire
	 *    soit il passe sur ne rien faire
	 */
	public void construire() throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException{
		Ordre o = this.getOrdre();
		if(o.getBatiment()==null){
			Element e = getTerrain().getBatiment(new Point(o.getX(),o.getY()));
			if(e!=null && e.isEnConstruction()){
				o.setBatiment((Batiment) e);
			} else {
				o.setBatiment(this.getJoueur().getPartie().creerBatiments(o.getIdBatiment(), o.getX(), o.getY(), this.getNumeroJoueur(),false));
				o.setDebutConstruction(true);
			}
		} else{
			o.setBatiment(getTerrain().getBatiment(new Point(o.getX(),o.getY())));
			if (!this.estProcheObjectif(2+this.getSizeX()/2+ o.getBatiment().getSizeX(), o.getX(), o.getY())){

				this.getOrdre().setOrdreSecondaire(new Ordre_SeDeplacer(o.getX(),o.getY()));
				this.deplacer(true);
			} else if (o.getBatiment().getTempsConstruction()>0){
				o.getBatiment().setTempsConstruction(o.getBatiment().getTempsConstruction()-this.vitesse_travail);
				int i = 0;
				if(o.getBatiment().getTempsConstruction()<=0)
					o.getBatiment().setEnConstruction(false);
				else
					i = (int) Math.max(1,o.getBatiment().getMaxPV()/o.getBatiment().getTempsConstruction());
				o.getBatiment().ajouterPointsDeVie(i);
			}else{
				o.getBatiment().setPV(o.getBatiment().getMaxPV());;
				Batiment b = isBatimentAConstruireProche(50);
				if(b!=null){
					this.setOrdre(new Ordre_Construire(b.getX(),b.getY(),b.getNom()));
				} else {
					this.setOrdre(new Ordre());
				}

			} 
		}

	}

	// Renvoie le batiment à construire si il est proche ???????
	// PROBABLEMENT A REVOIR
	public Batiment isBatimentAConstruireProche(int distance){
		if(((this.getOrdre().getBatiment().getCollisionBox().getCenterX()-
				this.getX())*
				(this.getOrdre().getBatiment().getCollisionBox().getCenterX()-this.getX())+
				(this.getOrdre().getBatiment().getCollisionBox().getCenterY()-
						this.getY())*
						(this.getOrdre().getBatiment().getCollisionBox().getCenterY()-this.getY())
						<distance*distance + this.getOrdre().getBatiment().getCollisionBox().getBoundingCircleRadius())){
			return this.getOrdre().getBatiment();
			
		}

		return null;
	}
	public boolean estACoteRessource(Ressource res){
		boolean b = false;
		float x = res.getEmplacement().getX();
		float y = res.getEmplacement().getY();
		float gx = this.getX();
		float gy = this.getY();
		int sx = this.getSizeX(), sy = this.getSizeY();
		b = b || (gx<=x-10+sx/2 && gx >= x-10-sx/2 && gy<=y+sy/2 && gy >= y-sy/2);
		b = b || (gx<=x+10+sx/2 && gx >= x+10-sx/2 && gy<=y+sy/2 && gy >= y-sy/2);
		b = b || (gx<=x+sx/2 && gx >= x-sx/2 && gy<=y-10+sy/2 && gy >= y-10-sy/2);
		b = b || (gx<=x+sx/2 && gx >= x-sx/2 && gy<=y+10+sy/2 && gy >= y+10-sy/2);
		return b;

	}
	public Ressource estAProximiteRessource( int distance){

		
		
		return getTerrain().getRessourcePlusProche(this);
		
		
	}
	public Ressource estAProximiteRessourceLibre( int distance){
		float xmin = Math.max(this.getX()-distance, 0);
		float xmax = Math.min(this.getX()+distance, this.getTerrainXsize()-1);
		float ymin = Math.max(this.getY()-distance, 0);
		float ymax = Math.min(this.getY()+distance, this.getTerrainYsize()-1);
		int distanceMax=3*distance;
		Ressource ressourceMax = null;
		// A RECODER AVEC TERRAIN
//		int dis;
//		//System.out.println(xmin +" "+ xmax +" "+ ymin+ " "+ ymax);
//		for(int ix=xmin; ix<=xmax; ix++){
//			for(int iy=ymin; iy<=ymax; iy++){
//				Ressource r = this.getTerrain().getCases(ix, iy).getRessource();
//				if(!r.getTypeRessource().equals(Terrain_Ressources.VIDE) && r.isLibre()){
//					//System.out.println("nouvelle ressource " + ix + " " + iy);
//					dis = Math.abs(ix-this.getX())+Math.abs(iy-this.getY());
//					if(dis<distanceMax){
//						distanceMax = dis;
//						ressourceMax = this.getTerrain().getCases(ix,iy).getRessource();
//					}
//				}
//			}
//		}
		return ressourceMax;
	}

	public int getTempsCreation(){
		return this.temps_creation;
	}
	public void construireCentre_Ville(int x , int y){
		if(this.getJoueur().getRessources(0)>200){
		String s = "batiments.Centre_Ville";	
		this.setOrdre(new Ordre_Construire(x,y,s));
		this.getJoueur().setRessources(0, this.getJoueur().getRessources(0)-200);
		
		}

	}

	public void construireCaserne(int x, int y) {
		if(this.getJoueur().getRessources(0)>200){
			String s = "batiments.Caserne";	
			this.setOrdre(new Ordre_Construire(x,y,s));
			this.getJoueur().setRessources(0, this.getJoueur().getRessources(0)-200);
			
			}
		
	}

}
