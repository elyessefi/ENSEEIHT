# include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <assert.h>

/**
 * \brief Obtenir la fréquence d'un chiffre dans un nombre.
 * Exemples : la fréquence de 5 dans 1515 est 2. La fréquence de 3 dans 123 est 1.
 * La fréquence de 0 dans 412 est 0.
 * \param[in] chiffre dont ont veut calculer la fréquence
 * \param[in] nombre pour lequel on veut calculer la fréquence de chiffre
 * \return la fréquence de chiffre dans nombre
 * \pre chiffre est un vrai chiffre : 0 <= chiffre <= 9
 */
int frequence(int nombre, int chiffre)
{
    assert(chiffre >= 0);
    assert(chiffre <= 9);
    int f = 0 ;
    int div = 10 ;
    int nchiffres = 0 ;
    int quotient ;
    // TODO: Donner le bon code !
    
    do {
        quotient = nombre / div ;
        div = div * 10 ;
        nchiffres = nchiffres + 1 ;
    }
    while ( quotient >= 1);
    div = div / 100 ;
    do {
        quotient = nombre / div ;
        if (quotient == chiffre){
            f = f+1;
        }
        nombre = nombre - quotient * div ;
        div = div / 10;
        }
    while (div >= 1 );
    return f;
}


////////////////////////////////////////////////////////////////////////////////
//                                                                            //
//                    NE PAS MODIFIER CE QUI SUIT...                          //
//                                                                            //
////////////////////////////////////////////////////////////////////////////////


void test_frequence(void) {
    assert(2 == frequence(1515, 5));
    assert(1 == frequence(123, 3));
    assert(0 == frequence(421, 0));
    assert(3 == frequence(444, 4));
    assert(1 == frequence(0, 0));
    printf("%s", "frequence... ok\n");
}


int main(void) {
    test_frequence();
    printf("%s", "Bravo ! Tous les tests passent.\n");
    return EXIT_SUCCESS;
}
