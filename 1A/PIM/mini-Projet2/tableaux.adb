with Ada.Text_IO;            use Ada.Text_IO;
package body tableaux is 

    procedure Initialiser(Tableau : out T_Tableau) is 
    begin 
        Tableau := null;
    end Initialiser;

    function Taille (Tableau : in T_Tableau) return integer is 
        begin
            if Tableau = null then
                return 0;
            else
                return 1 + Taille(Tableau.All.Suivant);
            end if;
        end Taille;

    function Indice_Valide(Tableau: in T_Tableau; Indice: in Integer) return Boolean is
    begin
        return (Indice > 0 and Indice <= Taille(Tableau));
    end Indice_Valide;

    function Element (Tableau : in T_Tableau; Indice : in integer) return T_Element is
        i : Integer;
        tab : T_Tableau;
    begin
        if Indice_Valide(Tableau, Indice) then
            i := 1;
            tab := Tableau;
            while i /= indice loop
                tab := tab.All.Suivant;
                i:=i+1;
            end loop;
        else
            Put_Line("essayez d'entrer un indice valide");
        end if;
        return tab.All.Element;
     end Element;

    procedure Changer (Tableau : in out T_Tableau; Indice : in integer; Nouvel_Element : in T_Element) is
        tab : T_Tableau;
    begin
        if Indice_Valide(Tableau, Indice) and Indice /= 1 then
            Changer (Tableau.All.Suivant,Indice-1,  Nouvel_Element);
        elsif Indice = 1 then
            tab := new T_Cellule;
            tab.All.Element := Nouvel_Element;
            tab.All.Taille := Taille(Tableau) ;
            if Tableau = Null then
                tab.All.Suivant := Null;
            else
                tab.All.Suivant := Tableau.All.Suivant;
            end if;
            Tableau := tab;
        else
            Put_Line("essayez d'entrer un indice valide");
        end if;
    end Changer;

    procedure Inserer (Tableau : in out T_Tableau; Indice : in Integer; Nouvel_Element : in T_Element) is
    tab : T_Tableau;
    begin
        if Indice_Valide(Tableau, Indice-1) and Indice /= 1 then
            if Tableau /= Null then
                Tableau.All.Taille := Taille(Tableau) +1;
            end if;
            Inserer(Tableau.All.Suivant,Indice - 1, Nouvel_Element);
        elsif Indice = 1 then
            tab := new T_Cellule;
            tab.All.Element := Nouvel_Element;
            tab.All.Taille := Taille(Tableau) +1;
            if Tableau = Null then
                tab.All.Suivant := Null;
            else
                tab.All.Suivant := Tableau;
            end if;
            Tableau := tab;
        else
            Put_Line("essayez d'entrer un indice valide");
        end if;
    end Inserer;
    procedure Ajouter (Tableau : in out T_Tableau; Nouvel_Element : in T_Element) is
        begin
            Inserer(Tableau,Taille(Tableau)+1, Nouvel_Element);
        end Ajouter;

     function Est_Present (Tableau : in T_Tableau; element : in T_Element) return Boolean is
        begin
            if Tableau = Null then
                return False;
            elsif Tableau.All.Element = element then
                return True;
            else
                return Est_Present(Tableau.All.Suivant, element);
            end if;
        end Est_Present;

      procedure Supprimer (Tableau : in out T_Tableau; Indice : in Integer) is 
      begin
        if Indice_Valide(Tableau,Indice) and Indice /= 1 then
            if Tableau /= Null then
                Tableau.All.Taille := Taille(Tableau) -1;
            end if;
            Supprimer(Tableau.All.Suivant, Indice - 1);
        elsif Indice = 1 then
            Tableau := Tableau.All.Suivant;
        else
            Put_Line("essayez d'entrer un indice valide");
        end if;
      end Supprimer;

    procedure Supprimer_Occurences (Tableau : in out T_Tableau; element : in T_Element) is
        begin
            if Tableau = Null then
                Null;
            else
                if Tableau.All.Element = element then
                    Supprimer(Tableau,1);
                    Supprimer_Occurences(TAbleau, element);
                else
                    Supprimer_Occurences(Tableau.All.Suivant, element);
                    if Taille(Tableau) > Taille(Tableau.All.Suivant)+1 then
                        Tableau.All.Taille := Taille(Tableau.All.Suivant)+1;
                    end if;
                end if;
            end if;
        end Supprimer_Occurences;

    procedure Appliquer_Sur_Chaque (Tableau : in T_Tableau) is
        begin
            if Tableau = Null then
                Null;
            else
                Traiter(Tableau.All.Element);
                Appliquer_Sur_Chaque(Tableau.All.Suivant);
            end if;
	    end Appliquer_Sur_Chaque;

end tableaux;
