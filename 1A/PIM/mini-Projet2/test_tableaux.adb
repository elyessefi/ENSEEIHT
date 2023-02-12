with Ada.Text_IO;           use Ada.Text_IO;

with tableaux;

procedure test_tableaux is

	package Tableau_Reels is
		new tableaux (T_Element => Float, Capacite => 40);
	use Tableau_Reels;

procedure Double(Element : in out Float) is
    begin
        Element := Element + Element;

    end Double;

    procedure Double is
        new Appliquer_Sur_Chaque(Double);

    tab : Tableau_Reels.T_Tableau;
begin
    -- Inistialiser le tableau
    Initialiser(tab);

    -- test Insérer 
    Inserer(tab, 1,8.4);
    pragma Assert (Est_Present(tab, 8.4));
    pragma Assert (Element(tab, 1) = 8.4);
    Inserer(tab, 2, 4.7);
    pragma Assert (Element(tab, 2) = 4.7);
    Inserer(tab, 2, 8.4);
    pragma Assert (Element(tab, 2) = 8.4);
    pragma Assert (Element(tab, 3) = 4.7);
    Put_Line("test Inserer : Ok");

    -- test Ajouter
    Ajouter(tab,5.5);
    pragma Assert (Element(tab, 4) = 5.5);
    pragma Assert (Taille(tab) = 4);
    Put_Line("test Ajouter : Ok");

    -- test Supprimer
    Supprimer(tab, 4);
    pragma Assert (not Est_Present(tab, 5.5));
    pragma Assert (Taille(tab) = 3);
    Put_Line("test Supprimer : Ok");

    -- test Supprimer toutes les occurrences d’un élément
    Supprimer_Occurences(tab, 8.4);
    pragma Assert (not Est_Present(tab, 2.5));
    pragma Assert (Taille(tab) = 1);
    Inserer(tab, 2, 1.2);
    Put_Line("test Supprimer les occurences : Ok");

    -- Dounler tous les elements du tableau 
    Double(tab);
    pragma Assert (Element(tab,1) = 9.4);
    pragma Assert (Element(tab,2) = 2.4);
    Put_Line("test Appliquer sur tous : Ok");

    -- Les tests sont bien passés
    Put_Line("OK");
end test_tableaux;
