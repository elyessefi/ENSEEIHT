with Ada.Unchecked_Deallocation;
with Ada.Text_IO ; use Ada.Text_IO ;
with Ada.Integer_Text_IO ; use Ada.Integer_Text_IO ;
with arbre_exceptions ; use arbre_exceptions;

package body arbre_binaire is

    procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_Arbre);

    procedure Initialiser(Arbre : out T_Arbre) is
        begin
            Arbre := null;
        end Initialiser;

    function Est_Vide(Arbre : in T_Arbre) return Boolean is
        begin
            return Arbre = null;
        end Est_Vide;

    function Cle(Arbre : in T_Arbre) return T_Octet is
        begin
            if Est_Vide(Arbre) then
                raise arbre_vide_Exception;
            else
                return Arbre.all.Cle;
            end if ;
        end Cle;

    function Donnee(Arbre : in T_Arbre) return Integer is
      begin
      if Est_Vide(Arbre) then
        raise arbre_vide_Exception ;
      else
        return Arbre.all.Donnee ;
      end if ;
    end Donnee;

    function Creer(Cle : in T_Octet; Donnee : in Integer; Arbre_Gauche : in T_Arbre; Arbre_Droite : in T_Arbre) return T_Arbre is
        Arbre : T_Arbre;
        begin
          Initialiser(Arbre);
          Arbre := New T_Cellule;
          Arbre.all.Cle := Cle;
          Arbre.all.Donnee := Donnee;
          Arbre.all.fils_gauche := Arbre_Gauche;
          Arbre.all.fils_droite := Arbre_Droite;
          return Arbre;
        end Creer ;

    function Est_Feuille(Arbre : in T_Arbre) return Boolean is
        begin
            return (Est_Vide(Arbre.all.fils_gauche) and Est_Vide(Arbre.all.fils_droite));
        end Est_Feuille;

    procedure Vider(Arbre : in out T_Arbre) is
    begin
         if  Est_Vide(Arbre) then
            Null;
        else
            Vider(Arbre.all.fils_gauche);
            Vider(Arbre.all.fils_droite);
            Free(Arbre);
        end if;
    end Vider;

    function Fils_Gauche(Arbre : in T_Arbre) return T_Arbre is
    begin
       if Est_Vide(Arbre) then
         return  Null;
       else
          return Arbre.all.fils_gauche;
       end if;
     end Fils_Gauche;

    function Fils_Droite(Arbre : in T_Arbre) return T_Arbre is
    begin
       if Est_Vide(Arbre) then
         return  Null;
       else
          return Arbre.all.fils_droite;
       end if;
     end Fils_Droite;

    procedure Supprimer_Fils_Gauche(Arbre : in T_Arbre) is
    begin
       if not Est_Vide(Arbre.all.fils_gauche) then
          Vider(Arbre.all.fils_gauche);
        end if;
    end Supprimer_Fils_Gauche;

    procedure Supprimer_Fils_Droite(Arbre : in T_Arbre) is
    begin
       if not Est_Vide(Arbre.all.fils_droite) then
          Vider(Arbre.all.fils_droite);
        end if;
    end Supprimer_Fils_Droite;
end arbre_binaire;
