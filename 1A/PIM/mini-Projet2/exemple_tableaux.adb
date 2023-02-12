with tableaux;
with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;  use Ada.Integer_Text_IO;

procedure exemple_tableaux is

    package Tableau_Integer is new tableaux(T_Element => Integer, CAPACITE => 8);
    use Tableau_Integer;

    -- afficher le tableau
    procedure toString(tab : in Tableau_Integer.T_Tableau) is
        n : Integer;
        tab1 :Tableau_Integer.T_Tableau;
    begin
        tab1 := tab;
        Put("[");

        for i in 1..Taille(tab1) loop
            n := Element(tab1, i);
            Put(n,1);
            if i/=Taille(tab1) then
                Put(", ");
            end if;
        end loop;
        Put_Line("]");
    end toString;

    procedure Double(Element : in out Integer) is
    begin
        Element := Element + Element;
    end Double;

    procedure Double is
        new Appliquer_Sur_Chaque(Double);

    tab : Tableau_Integer.T_Tableau;
begin
    -- On Inistialise le tableau
    Initialiser(tab);
    
    --Insertion des elements
    Put_Line("Insertion de 2 a la premiere position");
    Inserer(tab, 1, 2);
    toString(tab);
    Put_Line("Insertion de 5 a la deuxieme position");
    Inserer(tab, 2,5);
    toString(tab);
    New_Line;

    -- Ajouter trois elements
    Put_Line("On ajoute 8 à la fin du tableau");
    Ajouter(tab,8);
    toString(tab);
    Put_Line("On ajoute 5 à la fin du tableau");
    Ajouter(tab, 5);
    toString(tab);
    New_Line;
    Put_Line("On ajoute 5 à la fin du tableau");
    Ajouter(tab, 5);
    toString(tab);
    New_Line;

    -- suppression d'un element
    Put_Line("On supprime l'élement d'indice 2");
    Supprimer(tab, 2);
    toString(tab);

    -- suppression de tous les 2 du tableau
    Put_Line("On Supprime toutes les entiers 5");
    Supprimer_Occurences(tab,5);
    toString(tab);

    -- mettre au carré tous les éléments du Tableau
    Put_Line("Doubler tous les elements");
    Double(tab);
    toString(tab);
end exemple_tableaux;
