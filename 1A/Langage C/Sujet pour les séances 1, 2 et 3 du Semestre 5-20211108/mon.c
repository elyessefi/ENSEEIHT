#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <assert.h>
#define taille 5
// definition du type monnaie
struct monnaie {
    float valeur ;
    char devise ;
};
typedef struct monnaie monnaie ;

// fct d'initialisation d'une monnaie 
void initialiser (monnaie t_monnaie,float valeur, char devise){
    t_monnaie.valeur = valeur ;
    t_monnaie.devise = devise ;
}
// fct d'ajout 
bool ajout_monnaie (monnaie m1, monnaie m2) {
    if (m1.devise == m2.devise){
        m2.valeur += m1.valeur ;
        return true;
    }
    else {
        return false ;
    }
}
// fct de verification
void veifier(void){
    monnaie m1, m2, m3 ;
    initialiser (m1, 3, 'e');
    assert (m1.valeur == 3) ;
    assert (m1.devise == 'e') ;
    initialiser (m2, 5, 'e');
    initialiser (m3, 3, '$');
    assert (true == ajout_monnaie (m1, m2) ) ;
    assert (false == ajout_monnaie (m1, m3) ) ;
}
int main(){
    monnaie porte_monnaie[taille] ;
    monnaie m ;
    char choix ;
    float somme ;
    int i = 0 ;
    float val ;
    char dev ;
    for (i = 0; i<=taille-1 ;i++){
        printf ("entrez la valeur de la %i eme monnaie : \n", i+1);
        scanf ("%f", &val);
        printf ("entrez le devise de la %i eme monnaie : \n", i+1);
        scanf("%c", &dev);
        initialiser(m, val, dev) ;
        porte_monnaie[i] =  m;
    }
    printf ("entrez le divise que vous voulez : \n") ;
    scanf("%c", choix) ;
    for (i = 0; i<=taille-1 ;i++){
        if (choix == porte_monnaie->devise){
            somme += porte_monnaie->valeur ;
        }
    }
    printf ("la somme est : %f1.2", somme);
    
    return EXIT_SUCCESS ;
}
