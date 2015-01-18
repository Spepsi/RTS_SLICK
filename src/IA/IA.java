package IA;
import batiments.Centre_Ville;
import models.*;
import batiments.*;
public class IA {
	int id;
	Partie p ;
	Joueur j;
	boolean hasPeon=true;
	boolean hasFantassin=true;



	public IA(int idJoueur,Partie p){
		id =idJoueur;
		this.p = p;
		this.j = p.getJoueur(idJoueur);


	}

	public void play(){


		produire();
		construire();
		recolter();
		observer();
		strategie();
		combattre();
	}

	private void construire() {
		if(this.j.getRessources(0)>200){
			if(Math.random()<0.5)
		construireCentre_Ville();
			else
				construireCaserne();
		}

	}

	private void produire() {
		produirePeon();
		produireFantassin();

	}


	private boolean placeCentre_Ville(int x, int y){
		Centre_Ville c = (Centre_Ville) this.p.getData(this.id).getCentre_ville();
		
		return placeDisponible(x,y,c.getSizeX(),c.getSizeY());

	}

	private void construireCentre_Ville(){
		Peon p = this.getAFreePeon();
//		if(p==null){
//			p=this.getAPeon();
//		}
		
		if(p!=null){
			int x = p.getX();
			int y = p.getY();
			int iteration = 0 ;
			while(iteration < 20){
				if(placeCentre_Ville(x,y)){
					System.out.println("ok");
					p.construireCentre_Ville(x, y);
					break;
				}
				x +=20 ;
				y += 20;
			}
		}
	}

	
	private void construireCaserne(){
		Peon p = this.getAFreePeon();
//		if(p==null){
//			p=this.getAPeon();
//		}
		
		if(p!=null){
			int x = p.getX();
			int y = p.getY();
			int iteration = 0 ;
			while(iteration < 10){
				if(placeCaserne(x,y)){
					System.out.println("ok");
					p.construireCaserne(x, y);
					break;
				}
				x +=20 ;
				y += 20;
			}
		}
	}
	private boolean placeCaserne(int x, int y) {
Caserne c = (Caserne) this.p.getData(this.id).getCaserne();
		
		return placeDisponible(x,y,c.getSizeX(),c.getSizeY());
	}

	private boolean placeDisponible(int x,int y, int xs , int ys){
		boolean test = true ;
		for(int i =x;i<x+xs;i++){
			for(int j=y;j<y+ys;j++){
				System.out.println(this.p.getTerrain().getCases(i,j).estOccupee());
				if(i>this.p.getTerrain().getSizeX() || j>this.p.getTerrain().getSizeY() || this.p.getTerrain().getCases(i, j).estOccupee()){
					test = false;
					break;
				}
				
			}
			if(!test){
				break;
			}
		}
		return test ;
	}

	private void produirePeon() {

		if(j.getRessources(1)>100){

			for(Element b: j.getBatiments()){
				if(b instanceof Centre_Ville){
					((Centre_Ville) b).produirePeon();
				}
			}
		}

	}
	
	private void produireFantassin() {

		if(j.getRessources(1)>100){

			for(Element b: j.getBatiments()){
				if(b instanceof Caserne){
					((Caserne) b).produireFantassin();
				}
			}
		}

	}



	private void chercherRessource(Peon p) {
		Ressource aRecolter = p.estAProximiteRessource(100);

		if(aRecolter !=null){
			p.setOrdre(new Ordre_Ramasser(aRecolter,this.p.getTerrain()));
		}

	}

	private void combattre() {
		for(Element u : j.getUnites()){
			if(u instanceof Fantassin && ((Fantassin)u).getOrdre().getOrdre()==TypeOrdre.NE_RIEN_FAIRE){
				chercherCombat((Fantassin) u);
			}
		}

	}

	private void chercherCombat(Fantassin u) {
		u.setOrdre(new Ordre_Attaquer(1,0,300,200));
		
	}

	private void strategie() {
		// TODO Auto-generated method stub

	}

	private void observer() {
		// TODO Auto-generated method stub

	}

	private Peon getAPeon(){
		for(Element u : j.getUnites()){
			if(u instanceof Peon ){
				return (Peon) u ;
			}
		}
		return null ;
	}

	private Peon getAFreePeon(){
		for(Element u : j.getUnites() ){
			if(u instanceof Peon && ((Peon)u).getOrdre().getOrdre()==TypeOrdre.NE_RIEN_FAIRE ){
				return (Peon) u ;
			}
		}
		return null ;
	}
	private void recolter() {


		for(Element u : j.getUnites()){
			if(u instanceof Peon && ((Peon)u).getOrdre().getOrdre()==TypeOrdre.NE_RIEN_FAIRE){
				chercherRessource((Peon) u);
			}
		}
		//		for(Element p : this.p.getPeonInoccupes()){
		//			
		//			if(p instanceof Peon){
		//			
		//			chercherRessource((Peon) p);
		//			}
		//		}

	}

}
