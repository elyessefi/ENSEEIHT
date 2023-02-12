generic 
    type T_Element is private;
    Capacite : Integer;

package tableaux is 
    type T_Cellule ;
    type T_Tableau is access T_Cellule ;
    type T_Cellule is record
        Element : T_Element ;
        Taille : Integer;
        Suivant : T_Tableau ;
    end record ;

    -- Initialiser un tableau
    procedure Initialiser (Tableau : out T_Tableau) with
        Post => Tableau = Null and Taille(Tableau) = 0;

    -- Obtenir la taille d'un tableau
    function Taille (Tableau : in T_Tableau) return integer with 
        Post => Taille'result >= 0 and Taille'result <= Capacite;

    function Indice_Valide (Tableau: in T_Tableau; Indice: in Integer) return Boolean;

    -- Obtenir l’élément à un indice valide du tableau
    function Element (Tableau : in T_Tableau; Indice : in integer) return T_Element with
        Pre => Indice_Valide(Tableau, Indice); 

    -- Changer un élément du tableau avec un autre à un indice valide du tableau
    procedure Changer (Tableau : in out T_Tableau; Indice : in Integer; Nouvel_Element : in T_Element) with 
        Pre => Indice_Valide(Tableau, Indice),
        Post => Element (Tableau, Indice) = Nouvel_Element;  

    -- Ajouter un élément au tableau 
    procedure Ajouter (Tableau : in out T_Tableau; Nouvel_Element : in T_Element) with 
        Pre => Taille(Tableau) < Capacite,
        Post => Element (Tableau, Taille(Tableau)) = Nouvel_Element;

    -- Verifier si un element existant dans un tableau
    function Est_Present (Tableau : in T_Tableau; Element : in T_Element) return Boolean;

    -- Insérer un élément à un indice donné
    procedure Inserer (Tableau : in out T_Tableau; Indice : in Integer; Nouvel_Element : in T_Element) with
        Pre => Taille(Tableau) < Capacite;
        --Post => Element (Tableau, Indice) = Nouvel_Element and Taille(Tableau)'Old = Taille(Tableau) + 1;

    -- Supprimer un élément à un indice valide donné
    procedure Supprimer (Tableau : in out T_Tableau; Indice : in Integer) with
        Post => Taille(Tableau) = Taille(Tableau)'Old -1;

    -- Supprimer les occurrences d’un élément dans un tableau
    procedure Supprimer_Occurences (Tableau : in out T_Tableau; Element : in T_Element) with
        Post => not Est_Present(Tableau, Element);

    -- Appliquer une opération sur tous les éléments du tableau
    generic
        with procedure Traiter(Element : in out T_Element);
    procedure Appliquer_Sur_Chaque(Tableau : in T_Tableau); 

end tableaux;
