with SDA_Exceptions;         use SDA_Exceptions;
with Ada.Unchecked_Deallocation;

package body LCA is

	procedure Free is
		new Ada.Unchecked_Deallocation (Object => T_Cellule, Name => T_LCA);


	procedure Initialiser(Sda: out T_LCA) is
	begin
		Sda := Null ;
	end Initialiser;


	function Est_Vide (Sda : T_LCA) return Boolean is
	begin
		return (Sda=Null) ;
	end;


	function Taille (Sda : in T_LCA) return Integer is
	begin
		 if Sda = Null then
		   return 0 ;
		 else
		    return 1 + Taille(Sda.All.Suivant) ;
		 end if ;
	end Taille;


	procedure Enregistrer (Sda : in out T_LCA ; Cle : in T_Cle ; Donnee : in T_Donnee) is
	begin
		if Sda=Null then
		  Sda:= new T_Cellule ;
		  Sda.All.Cle := Cle ;
		  Sda.All.Donnee := Donnee ;
		  Sda.All.Suivant := Null ;
		elsif Sda.All.Cle = Cle then
		     Sda.All.Donnee := Donnee ;
		else
		     Enregistrer(Sda.All.Suivant , Cle , Donnee) ;
		end if ;
	end Enregistrer;


	function Cle_Presente (Sda : in T_LCA ; Cle : in T_Cle) return Boolean is
	begin
		 if Sda = Null then
		   return False ;
		 elsif Sda.All.Cle = Cle then
		   return True ;
		 else
		   return Cle_Presente(Sda.All.Suivant,Cle) ;
		 end if ;
    end Cle_Presente ;

    function s_Cle(Sda: in T_LCA) return T_Cle is
    begin
       if Sda = Null then
         raise Cle_Absente_Exception ;
       else
         return Sda.All.Cle ;
       end if ;
    end s_Cle ;

    function position_cle(Sda: in T_LCA; Cle : in T_Cle) return Integer is
    begin
       if not Cle_Presente(Sda,Cle) then
         return (-1) ;
       elsif Sda.All.Cle = Cle then
         return 0;
       else
         return 1 + position_cle(Sda.all.Suivant,Cle) ;
       end if ;
    end position_cle ;


	function La_Donnee (Sda : in T_LCA ; Cle : in T_Cle) return T_Donnee is
	begin
		 if Cle_Presente(Sda,Cle) then
		   if Sda.All.Cle = Cle then
		     return Sda.All.Donnee ;
		   else
		     return La_Donnee(Sda.All.Suivant,Cle) ;
		   end if ;
		 else
		   raise Cle_Absente_Exception ;
		 end if ;
	end La_Donnee;


	procedure Supprimer (Sda : in out T_LCA ; Cle : in T_Cle) is
	    Detruire : T_LCA ;
	begin
		 if not Cle_Presente(Sda,Cle) then
		   raise Cle_Absente_Exception ;
		 elsif Sda.All.Cle = Cle then
		   Detruire:=Sda;
		   Sda:= Sda.All.Suivant ;
		   Free(Detruire);
		 else
		   Supprimer (Sda.All.Suivant,Cle) ;
		 end if ;
	end Supprimer;


	procedure Vider (Sda : in out T_LCA) is
	begin
		 while Sda/=Null loop
		    Supprimer(Sda,Sda.All.Cle) ;
		 end loop ;
	end Vider;


	procedure Pour_Chaque (Sda : in T_LCA) is
	    dict : T_LCA ;
	begin
		if Sda=Null then
		   Null ;
		else
		   dict := Sda ;
		   while dict /= null loop
		       begin
		         Traiter(dict.All.Cle , dict.All.Donnee) ;
		       exception when others => Null ;
		       end ;
		       dict:=dict.All.Suivant ;
		   end loop ;
		end if ;
	end Pour_Chaque;



end LCA;
