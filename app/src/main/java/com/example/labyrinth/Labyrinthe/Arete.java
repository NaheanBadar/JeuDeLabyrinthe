package com.example.labyrinth.Labyrinthe;

/************************************************************
 * Cette classe modélise une arête d'un graphe pondéré.     *
 * Les deux entiers s1 et s2 modélisent les deux sommets de *
 * l'arête. L'entier value modélise le poids de l'arête.    *
 * **********************************************************/

public class Arete {

	/*********************************************************
	 * Les variables d'instance.                             *
	 * On utilise le type Integer (objet) plutôt que le type *
	 * primitif int car les entiers vont être stockés dans   *
	 * une ArrayList qui manipule des objets.                *
	 * *******************************************************/ 

    Integer s1;
    Integer s2;
    int value;

	// Constructeur à 3 paramètres
    Arete(Integer a,Integer b,int v){
        this.s1 = a;
        this.s2 = b;
        this.value = v;
    }
    
    // Les accesseurs dont on on a besoin 
    
    Integer getS1(){
		return s1;
	}
	
	Integer getS2(){
		return s2;
	}

    int getValue(){
        return this.value;
    }
}
