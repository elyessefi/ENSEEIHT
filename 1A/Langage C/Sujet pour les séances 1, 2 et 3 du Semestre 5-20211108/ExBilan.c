#include <stdio.h>
#include <stdlib.h>

int main()
{
    const float Unepouce = 2.54 ;
    float valeur, lg_cm, lg_pc ;
    char unite ;
    char rep ;
    do {
    
        /* Saisir la longueur */
        printf ( "entrer une longueur (valeur + unité) : \n") ;
        scanf ("%f %c", &valeur,&unite);

        /* Calculer la longueur en pouces et en centimètres */
        switch(unite) {
            case 'c' :
            case 'C' :
                lg_cm = valeur;
                lg_pc = valeur / Unepouce ;
                break ;
            case 'p' :
            case 'P' :
                lg_cm = valeur * Unepouce ;
                lg_pc = valeur ;
                break ;
            case 'm' :
            case 'M' :
                lg_cm = valeur * 100 ;
                lg_pc = lg_cm / Unepouce ;
                break;
            default :
                lg_cm = 0 ;
                lg_pc = 0 ;
        }

        /* Afficher la longueur en pouces et en centimètres */
        
        printf ("\n");
        printf ("%1.2f p = %1.2f cm \n", lg_pc , lg_cm) ;
        printf ("\nVoulez vous recommencer : (o/n) \n");
        scanf ("%c", &rep) ;
    }
    while ( rep == 'o') ;
    
    

}
