package graphe.init;

import java.util.ArrayList;

public class Sommet {

	public ArrayList<Integer> voisins;

	public Sommet(){
		this.voisins = new ArrayList<Integer>();
	}

	/*public void ajouterVoisin(int sommet){
		int i = 0;
		while (voisins.get(i) < sommet){
			i++;
		}
		voisins.add(sommet, i);
	}*/
	
	public void ajouterVoisin(int sommet){  // liste rangée (voir : implementer par dichotomie ?)
		int i = 0;
		if (voisins.size()==0){
			voisins.add(sommet);
		}
		else{
			while ((i < voisins.size()) && (voisins.get(i) < sommet)){
				i++;
			}
			if (i == voisins.size()){
				voisins.add(sommet);
			}
			else{
				voisins.add(sommet,i);
			}
		}
	}

	public ArrayList<Integer> getVoisins(){
		return this.voisins;
	}
	
	public int nbVoisins(){
		return voisins.size();
	}

	public boolean isVoisin(int sommet){ // pas utilisé (recodé dans Sommet)
		return voisins.contains(sommet);
	}


}