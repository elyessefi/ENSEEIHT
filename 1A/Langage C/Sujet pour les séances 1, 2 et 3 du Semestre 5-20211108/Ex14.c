#include <stdlib.h> 
#include <stdio.h>
#include <assert.h>
#include <math.h>
// Definition du type Point 
struct Point {
    int x ;
    int y ;
};


int main(){
    // Déclarer deux variables ptA et ptB de types Point
    struct Point A, B ;
    
    // Initialiser ptA à (0,0)
    A.x = 0 ;
    A.y = 0 ;
    // Initialiser ptB à (10,10)
    B.x = 10 ;
    B.y = 10 ;
    // Calculer la distance entre ptA et ptB.
    float distance = 0;
    distance = pow((pow((A.x-B.x),2)+(pow((A.y-B.y),2))),1/2) ;
    assert( (int)(distance*distance) == 200);
    
    return EXIT_SUCCESS;
}
