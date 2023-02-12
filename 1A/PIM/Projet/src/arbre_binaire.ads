package arbre_binaire is

    type T_Octet is mod 2**8;
    type T_Cellule;
    type T_Arbre is access T_Cellule;
    type T_Cellule is record
        Cle : T_Octet;
        Donnee : Integer;
        fils_gauche : T_Arbre;
        fils_droite : T_Arbre;
    end record;

    --Initialiser une arbre 
    procedure Initialiser(Arbre : out T_Arbre) with 
        Post => Est_Vide(Arbre);

    --Verifier si une arbre est vide
    function Est_Vide(Arbre : in T_Arbre) return Boolean;

    --Obtenir la cle d'une arbre
    function Cle (Arbre : in T_Arbre) return T_Octet;

    --Obtenir la donnee d'une arbre
    function Donnee(Arbre : in T_Arbre) return Integer;

    --Creer une Arbre
    function Creer(Cle : in T_Octet; Donnee : in Integer; Arbre_Gauche : in T_Arbre; Arbre_Droite : in T_Arbre) return T_Arbre with 
        Post => not Est_Vide(Creer'Result);

    --Verifier si une arbre est une feuille
    function Est_Feuille(Arbre : in T_Arbre) return Boolean;

    --Vider une arbre
    procedure Vider(Arbre : in out T_Arbre) with
        Post => Est_Vide(Arbre);

    --Obtenir le fils gauche d'une arbre
    function Fils_Gauche(Arbre : in T_Arbre) return T_Arbre with
           Pre => not Est_Vide(Arbre);

    --Obtenir le fils droit d'une arbre
    function Fils_Droite(Arbre : in T_Arbre) return T_Arbre with
        Pre => not Est_Vide(Arbre);

    --Supprimer le fils gauche d'une arbre
    procedure Supprimer_Fils_Gauche(Arbre : in T_Arbre) with
        Pre => not Est_Vide(Arbre);

    --Supprimer le fils droit d'une arbre
    procedure Supprimer_Fils_Droite(Arbre : in T_Arbre) with
        Pre => not Est_Vide(Arbre);

end arbre_binaire;
