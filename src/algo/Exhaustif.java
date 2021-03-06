package algo;

import graphe.calcul.GraphePartition;
import graphe.init.ListeAdjacence;
import graphe.calcul.Solution;

public class Exhaustif extends Algorithme implements Runnable {
	private GraphePartition graphe;
	private int nbClasses;

	public Exhaustif(ListeAdjacence l, int nbClasses){
		this.graphe = new GraphePartition(l, nbClasses);
		this.nbClasses = nbClasses;
	}
	
	public void run(){
		
		long startTime = System.currentTimeMillis();
		
		GraphePartition g = this.graphe;
		//g.calculerEvaluation();
		int nbSommets = g.getNbSommets();
		Solution sOpt = g.setSolutionExhaustif();
		Solution sInitiale = sOpt;
		
		int evalOpt = g.getEval();
		
		boolean boucle = true;
		int sommetCourant;
		
		while (boucle){
			sommetCourant = 0;
			while (sommetCourant<nbSommets && // Tant que on est pas apres le dernier sommet
				   g.getClasse(sommetCourant) == this.nbClasses-1){ // et que la classe de ce sommet est la derniere classe
				g.pickNdrop(sommetCourant, 0); // On met le sommet dans la classe 0
				sommetCourant++; // Et on va au sommet suivant
			}
			if (sommetCourant<nbSommets){
				g.pickNdrop(sommetCourant, g.getClasse(sommetCourant)+1); // On met le sommet dans la classe suivante
				int evalG = g.getEval();
				Solution sol = g.getSolution();
				System.out.println("la solution : " + sol.toString() + " donne l'evaluation : " + evalG);				
				if ((evalG < evalOpt) && (evalG!=0)){
					sOpt = sol;
					evalOpt = sOpt.getEval();
				}
			}
			else //La precedente boucle a depasse le dernier sommet : on a tout teste
				boucle = false;
		}		
		long endTime = System.currentTimeMillis();
		super.afficherResultat(sInitiale, sOpt, endTime-startTime);
		this.solutionOpt = sOpt;
		this.evalOpt = evalOpt;
	}
}