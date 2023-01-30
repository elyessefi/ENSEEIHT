#include <stdlib.h> 
#include <stdio.h>
#include <assert.h>
#include <stdbool.h>
#define taille 5

// Definition du type monnaie
// TODO 
struct monnaie {
    float valeur ;
    char devise ;
};
typedef struct monnaie monnaie ;
/**
 * \brief Initialiser une monnaie 
 * \param[]
 * \pre 
 * // TODO
 */ 
void initialiser(monnaie t_monnaie, float val, char dev){
    // TODO
    t_monnaie.valeur = val ;
    t_monnaie.devise = dev ;
}


/**
 * \brief Ajouter une monnaie m2 à une monnaie m1 
 * \param[]
 * // TODO
 */ 
bool ajouter(monnaie m1, monnaie m2){
    // TODO
    if (m1.devise == m2.devise){
        m2.valeur += m1.valeur ;
        return true;
    }
    else {
        return false ;
    }
}


/**
 * \brief Tester Initialiser 
 * \param[]
 * // TODO
 */ 
void tester_initialiser(){
    // TODO
    monnaie m1 ;
    initialiser (m1, 3, "e");
    assert (m1.valeur == 3) ;
    assert (m1.devise == "e") ;

}

/**
 * \brief Tester Ajouter 
 * \param[]
 * // TODO
 */ 
void tester_ajouter(){
    // TODO
    monnaie m1, m2, m3 ;
    initialiser (m1, 3, "e");
    initialiser (m2, 5, "e");
    initialiser (m3, 3, "$");
    assert (true == ajouter (m1, m2) ) ;
    assert (false == ajouter (m1, m3) ) ;
}



int main(void){
    // Un tableau de 5 monnaies
    // TODO
    monnaie porte_monnaie[taille] ;

    //Initialiser les monnaies
    // TODO
    int i ;
    monnaie m ;
    float val ;
    char dev ;
    float somme = 0 ;
    for (i = 0; i<=taille-1 ;i++){
        printf ("entrez la valeur de la %i eme monnaie : \n", i+1);
        scanf ("%f", &val);
        printf ("\nentrez le devise de la %i eme monnaie : \n", i+1);
        scanf("%c", &dev);
        initialiser(m, val, dev) ;
        porte_monnaie[i] =  m;
}
 
    // Afficher la somme des toutes les monnaies qui sont dans une devise entrée par l'utilisateur.
    // TODO
    printf ("\nentrez le divise que vous voulez : \n") ;
    scanf("%c", dev) ;
    for (i = 0; i<=taille-1 ;i++){
        if (dev == porte_monnaie->devise){
            somme += porte_monnaie->valeur ;
        }
    }
    printf ("la somme est : %f1.2", somme);
    return EXIT_SUCCESS;
}
