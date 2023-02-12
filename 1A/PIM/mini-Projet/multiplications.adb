--------------------------------------------------------------------------------
--  Auteur   : Mohamed El Yessefi
--  Objectif : 
--------------------------------------------------------------------------------

with Ada.Text_IO;           use Ada.Text_IO;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with Ada.Calendar;          use Ada.Calendar;
With Alea;

procedure Multiplications is

    package Mon_Alea is
		new Alea (0, 10);  -- générateur de nombre dans l'intervalle [0, 10]
	use Mon_Alea;
	
    Table : Integer := 11;
    Aleatoire : Integer;
    Reponse : Integer;
    Reponse_Correcte : Integer;
    Nombre_Reponses_Incorrectes : Integer := 0;
    Debut : Time;
    Fin : Time;
    Delai_Operation_Actuelle :  Duration;
    Delai_Maximal : Duration;
    Delai_Moyen : Duration;
    Delai_Moyen_Un : Duration;
    Table_A_Reviser : Integer;
    Continuer : Boolean := True;
    Continuer_Reponse : Character;
    Reponse_Juste : Boolean;
begin
    while Continuer loop
        Delai_Operation_Actuelle := 0.0;
        Delai_Maximal := 0.0;
        Delai_Moyen := 0.0;
        Nombre_Reponses_Incorrectes := 0;
        Reponse_Juste := false;
        Table := 11;
        -- Demander la table a l'utilisateur
        while Table < 0 or Table > 10 loop
            Put_Line("");
            Put ("table a reviser ? ");
            Get (Table);
        end loop;
        for I in 1..10 loop
            -- récupérer l'heure (heure de début)
            Debut := Clock;
            -- réaliser l'opération
		    Put("(M"); Put(I,0); Put(") "); Put(Table,0); Put(" * ");		    
		    Get_Random_Number(Aleatoire);
		    Reponse_Correcte := Table * Aleatoire;
		    Put (Aleatoire,0);
		    Put(" ? ");
		    Get(Reponse);
		    if Reponse = Reponse_Correcte then
		        Put_Line("Bravo !");
		    else
		        Put_Line("Mauvaise réponse");
		        Nombre_Reponses_Incorrectes := Nombre_Reponses_Incorrectes + 1 ;
		    end if;
		    Put_Line("");
		    -- récupérer l'heure (heure de fin)
		    Fin := Clock;
		    
		    -- calculer la durée de l'opération
		    Delai_Operation_Actuelle := Fin-Debut;
		    Delai_Moyen := Delai_Moyen + Delai_Operation_Actuelle;
		    if Delai_Maximal < Delai_Operation_Actuelle then
		        Delai_Maximal := Delai_Operation_Actuelle;
		        Table_A_Reviser := Aleatoire;
		    end if;
	    end loop;
	    --==========================<<Evaluation globale sur le test>>=============================--
	    case Nombre_Reponses_Incorrectes is 
	        when 0 => Put_Line ("Aucune erreur. Excellent!");
            when 1 => Put_Line ("Une seule erreur. Trés bien.");	
	        when 2 | 3 | 4 | 5 => Put (Nombre_Reponses_Incorrectes,1); Put(" erreurs. Il faut encore travailler la table de "); Put(Table,0);Put_Line(".");
	        when 6 | 7 | 8 | 9 => Put("Seulement "); Put (10-Nombre_Reponses_Incorrectes, 0); Put(" bonnes réponses. Il faut apprendre la table de "); Put(Table,0); Put_Line(" !");
	        when others => Put_Line ("Tout est faux! Volontaire ?");
	    end case;
	    --===================================================================--
	    Delai_Moyen := (Delai_Moyen/10);
	    Delai_Moyen_Un := Delai_Moyen + 1.0;
	    if Delai_Maximal > Delai_Moyen_Un then
	        Put_Line("");
	        Put ("Des hésitations sur la table de "); 
	        Put(Table_A_Reviser, 0);
	        Put(" : ");
	        Put(Duration'Image(Delai_Maximal));
	        Put(" seconds contre ");
	        Put(Duration'Image(Delai_Moyen));
	        Put_Line(" en moyenne. Il faut certainement la réviser.");
	        Put_Line("");
	    end if;
        While Reponse_Juste = false loop
            Put_Line("");
            Put("On continue ? (O/N) ");
            Get(Continuer_Reponse);
            if Continuer_Reponse = 'O' or Continuer_Reponse = 'o' then
                Continuer := true;
                Reponse_Juste := true;
            elsif Continuer_Reponse = 'N' or Continuer_Reponse = 'n' then
                Continuer := false;
                Reponse_Juste := true;
            else
                Put("essayez de repondre avec O ou o pour oui, N ou n pour Non.");
                Put_Line("");
            end if;
        end loop;
    end loop;
end Multiplications;
