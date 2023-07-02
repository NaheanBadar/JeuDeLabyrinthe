package com.example.labyrinth.Labyrinthe;

import java.util.ArrayList;

public class Labyrinthe implements Directions{
	
	// Les variables d'instance
	
	ArrayList<Arete> arbre;
	int dim;
	int[][] tab;
	
	/*******************************************************
	 * Le constructeur.                                    *
	 * On a besoin d'instancier la classe graphe pour      *
	 * récupérer un arbre recouvrant de poids minimum de   *
	 * ce graphe.                                          *
	 * *****************************************************/

	public Labyrinthe(int d){
		this.dim = d;
		Graphe g = new Graphe(dim);
		arbre = g.getArbre();
		constructLab();
	}
	
	/*******************************************************
	 * La méthode suivante construit le tableau contenant  *
	 * toutes les informations du labyrinthe. Le premier   *
	 * indice donne le numéro de la case, le deuxième      *
	 * indice indique la direction. On récupère alors un   *
	 * entier donnant le numéro de la case vers laquelle   *
	 * on se déplace. S'il y a un mur, l'entier vaut -1    *
	 * *****************************************************/
	
	void constructLab(){
		tab = new int[dim*dim][4];
		// On met des murs partout
		for(int i=0;i<dim*dim;i++){
			tab[i][NORD] = -1;				
			tab[i][SUD] = -1;
			tab[i][EST] = -1;				
			tab[i][OUEST] = -1;
		}
		// Les ouvertures
		for(int i=0;i<arbre.size();i++){
			if(arbre.get(i).getS2()-arbre.get(i).getS1()==1){
				tab[arbre.get(i).getS1()][EST] = arbre.get(i).getS2();
				tab[arbre.get(i).getS2()][OUEST] = arbre.get(i).getS1();
			}
			else if(arbre.get(i).getS2()-arbre.get(i).getS1()==dim){
				tab[arbre.get(i).getS1()][SUD] = arbre.get(i).getS2();
				tab[arbre.get(i).getS2()][NORD] = arbre.get(i).getS1();
			}
		}
	}
	
	/********************************************************
	 * Cette méthode renvoie le tableau contenant           *
	 * toutes les données du labyrinthe.                    *
	 * Il est inutile de manipuler tout l'objet Labyrinthe. *
	 * Une variable contenant le résultat de cette méthode  *
	 * suffit.                                              *
	 * ******************************************************/
	  
	public int[][] getTab(){
		return tab;
	}
	
	/********************************************************
	 * Cette méthode affiche les données du labyrinthe      *
	 * au format texte dans la console.                     *
	 * ******************************************************/
	 
	 public void afficheTab(){
		for(int i=0;i<dim*dim;i++){
			System.out.println("Case n°"+i);
			System.out.println("\t NORD : "+tab[i][NORD]);
			System.out.println("\t SUD : "+tab[i][SUD]);
			System.out.println("\t EST : "+tab[i][EST]);
			System.out.println("\t OUEST : "+tab[i][OUEST]);
		}
	 }  

}
