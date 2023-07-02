package com.example.labyrinth.Labyrinthe;

import java.util.ArrayList;

public class Graphe {

	// les variables d'instance

    int dim;
    ArrayList<Arete> aretes; // Les arêtes du graphe
    ArrayList<Integer> sommets; // Les sommets du graphe
    ArrayList<Arete> ouvertures; // Les arêtes conservées dans l'arbre recouvrant de poids minimum

	/********************************************************
	 * Le constructeur.                                     *
	 * On initialise les listes modélisant le graphe        *
	 * et l'arbre recouvrant minimum.                       *
	 * ******************************************************/

    Graphe (int d){
        this.dim = d;
        aretes = new ArrayList<Arete>(); // La liste des arêtes du graphe
        sommets = new ArrayList<Integer>(); // La liste des sommets manipulés dans l'algorithme de Prim
        ouvertures = new ArrayList<Arete>(); // La liste des aêtes de l'arbre recouvrant
    }

	/**********************************************************
	 * Cette méthode attribue une valeur aléatoire (un poids) *
	 *  à chaque arête du graphe.                             *
	 * ********************************************************/
	 
    void initAretes(){
        // arêtes horizontales
        for(int i=0;i<dim*dim;i++){
			if((i%dim)!=(dim-1))
				aretes.add(new Arete(new Integer(i),new Integer(i+1),(int)(Math.random()*dim*dim*10)));
        }
        // arêtes verticales
        for(int i=0;i<dim*(dim-1);i++){
            aretes.add(new Arete(new Integer(i),new Integer(i+dim),(int)(Math.random()*dim*dim*10)));
        }
    }

	/*******************************************************************
     * Cette méthode classe les arêtes du graphe par ordre croissant   *
     * de leur valeur (leur poids).                                    *
     * On utilise un tri à bulles, peu performant mais suffisant ici.  *
     * *****************************************************************/
     
    void triAretes(ArrayList<Arete> l){
        int j = l.size()-1;
        int i;
        while(j>0){
            i = 0;
            while(i<j){
                if(l.get(i).getValue()>l.get(i+1).getValue()){
                    Arete temp = l.get(i);
                    l.set(i,l.get(i+1));
                    l.set(i+1,temp);
                }
                else i++;
            }
            j--;
        }
    }

	/********************************************************************
    /* La méthode suivante sélectionne et met dans une liste toutes     *
     * les arêtes dont les sommets sont dans la liste sommets puis elle *
     * retourne cette liste.                                            *
     ********************************************************************/
     
    ArrayList<Arete> selectAretes(){
        ArrayList<Arete> selection = new ArrayList<Arete>();
        Arete a;
		for(int i=0;i<aretes.size();i++){
			a = aretes.get(i);
			if(sommets.contains(a.getS1())||sommets.contains(a.getS2())){
				selection.add(aretes.get(i));
			}
		}
        return selection;
    }

	/***********************************************************
	 * L'algorithme de Prim                                    *
	 * Il remplit la liste ouvertures de toutes les arêtes     *
	 * appartenant à l'arbre recouvrant de poids minimum       *
	 * extrait du graphe pondéré généré par la méthode         *
	 * initAretes                                              *
	 * *********************************************************/

	void algoPrim(){
		sommets.add(new Integer(0)); // On part du sommet 0
		for(int i=1;i<dim*dim;i++){
			ArrayList<Arete> s = selectAretes();
			triAretes(s);
			System.out.println();
			int j=0;
			Arete a=null;
			boolean flag = false;
			while(!flag){
				a = s.get(j);
				if(!sommets.contains(a.getS1())){
					sommets.add(a.getS1());
					flag = true;
				}
				else if(!sommets.contains(a.getS2())){
					sommets.add(a.getS2());
					flag = true;
				}
				else
					j++;
			}
			ouvertures.add(a);
			aretes.remove(a);
		}
	}

    /**********************************************************
     * Cette méthode retourne un arbre recouvrant de poids    *
     * minimum (la liste de ses arêtes) extrait à partir d'un *
     * graphe pondéré généré par la méthode initAretes        *
     * ********************************************************/
     
    ArrayList<Arete> getArbre(){
		initAretes();
		algoPrim();
		return ouvertures;
    }

}
