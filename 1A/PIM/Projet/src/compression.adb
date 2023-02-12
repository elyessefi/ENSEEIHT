with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with Ada.Text_IO ; use Ada.Text_IO ;
with Ada.Integer_Text_IO;   use Ada.Integer_Text_IO;
with Ada.Streams.Stream_IO ; use Ada.Streams.Stream_IO ;
with LCA ;
with arbre_binaire ; use arbre_binaire ;
with arbre_exceptions ; use arbre_exceptions;
with Ada.IO_Exceptions; use Ada.IO_Exceptions;

package body compression is

    use Oct_frequence;
    use T_arb;
    use Bi_LCA;
    use Code_LCA;



    procedure Codage_Huffman(Arbre: in T_Arbre; Bit : in out Bi_LCA.T_LCA; Code : in out Code_LCA.T_LCA) is
        tab1 : Bi_LCA.T_LCA ;

    begin
       Bi_LCA.Initialiser(tab1);
       if Est_Vide(Arbre) then
         raise arbre_vide_Exception;
       elsif Est_feuille(Arbre) then
         Code_LCA.Enregistrer(Code, Cle(Arbre), Bit);
       else
         Bi_LCA.Enregistrer(Bit, Taille(Bit) + 1, 0);
         tab1 := copie(Bit);
         Codage_Huffman(Arbre.all.fils_gauche,tab1 ,Code);
         Bi_LCA.Enregistrer(Bit, Taille(Bit), 1);
         tab1 := copie(Bit);
         Codage_Huffman(Arbre.all.fils_droite, tab1 ,Code);
       end if;
    end Codage_Huffman;

    function Copie(sda : in Bi_LCA.T_LCA) return Bi_LCA.T_LCA is
      copie : Bi_LCA.T_LCA;
    begin
       Initialiser(copie);
       if not Est_Vide(Sda) then
         for i in 1..Bi_LCA.Taille(Sda) loop
           Bi_LCA.Enregistrer(copie, i , Bi_LCA.La_Donnee(Sda,i));
         end loop;
       end if;
       return copie;
    end Copie;

    function Cle_Premier_Minimum(Sda : in T_arb.T_LCA) return Integer is
        Indice : Integer;
    begin
        Indice := 1;
        for i in 1..Taille(Sda) loop
           if Donnee(La_Donnee(Sda,i)) < Donnee(La_Donnee(Sda,Indice)) then
             Indice := i;
           end if;
        end loop;
        return Indice;
     end Cle_premier_Minimum;

     function Cle_Deuxiem_Minimum(Sda : in T_arb.T_LCA) return Integer is
        Indice : Integer;
     begin
        if Cle_Premier_Minimum(Sda) = 1 then
          Indice := 2;
        else
          Indice := 1;
        end if;
        for i in 1..Taille(Sda) loop
          if Donnee(La_Donnee(Sda,i)) < Donnee(La_Donnee(Sda,Indice)) and  (i /= Cle_Premier_Minimum(Sda)) then
            Indice := i;
          end if;
        end loop;
        return Indice;
     end Cle_Deuxiem_Minimum;

     function Creer_Arbre(Sda : in T_arb.T_LCA) return T_arb.T_LCA is
         tab : T_arb.T_LCA;
         I : Integer;
         Arbre : T_Arbre;
         somme_f : Integer;
      begin
         Initialiser(tab);
         Initialiser(Arbre);
         I := 0;
         for j in 1..Taille(Sda) loop
           if j /= Cle_Premier_Minimum(Sda) and j /= Cle_Deuxiem_Minimum(Sda) then
             I := I + 1 ;
             Enregistrer(tab,I,La_Donnee(Sda,j)) ;
           end if ;
         end loop ;
         somme_f := Donnee(La_Donnee(Sda,Cle_Premier_Minimum(Sda))) + Donnee(La_Donnee(Sda,Cle_Deuxiem_Minimum(Sda)));
         Arbre := Creer(0,somme_f,La_Donnee(Sda,Cle_Premier_Minimum(Sda)),La_Donnee(Sda,Cle_Deuxiem_Minimum(Sda)));
         Enregistrer(tab,I+1,Arbre);
         return tab;
      end Creer_Arbre;


      function Arbre_Huffman(Sda : in T_arb.T_LCA) return T_Arbre is
      begin
         if Taille(Sda) = 1 then
           return La_Donnee(Sda,1);
         else
           return Arbre_Huffman(Creer_Arbre(Sda));
         end if;
      end Arbre_Huffman;


    procedure Octet_Binaire(Sda : in Bi_LCA.T_LCA) is
        c:integer;
      begin
        for i in 1..Taille(Sda) loop
            c:=La_Donnee(Sda,i);
            Put(c,1);
	     end loop;
	  end Octet_Binaire;

	  procedure Parcours_Infixe(Arbre : in T_Arbre; Bit : in out Bi_LCA.T_LCA) is
	  begin
	    if Est_feuille(Arbre) then
	      Enregistrer(Bit, Taille(Bit) + 1 , 1) ;
	    else
	      Enregistrer(Bit, Taille(Bit) + 1 , 0) ;
	      Parcours_Infixe(Arbre.all.fils_gauche, Bit) ;
	      Parcours_Infixe(Arbre.all.fils_droite, Bit) ;
	    end if ;
      end parcours_infixe ;


	  procedure Afficher_Huffman (Arbre : in T_Arbre;   Bit : in out Bi_LCA.T_LCA ; c : Integer )is
        espace : Constant String(1..5) := "     " ;
        tab1 , tab2 : Bi_LCA.T_LCA ;
      begin
        Bi_LCA.Initialiser(tab1) ;
        Bi_LCA.Initialiser(tab2) ;
        if Taille(Bit) >= 1 then
          for i in 1..Taille(Bit) loop
            if La_Donnee(Bit,i) = 0 then
              Put(' ') ;
            else
              Put('|') ;
            end if ;
            Put(espace) ;
          end loop ;
        end if ;

        Put("\--"); Put(c,1); Put("--(") ;
        Put(Donnee(Arbre),1); Put(") ") ;
        if Est_feuille(Arbre) then
          if Cle(Arbre) = -1 then
            Put(" '\$' ") ;
          elsif Cle(Arbre) = 10 then
            Put(" '\n' ") ;
          else
            Put(" '" & Character'Val(Cle(Arbre)) & "'");
          end if ;
		  New_Line;
        else
          New_Line;
          Bi_LCA.Enregistrer(Bit, Taille(Bit) + 1 , 1 - c) ;
          tab1 := Copie(Bit) ;
          Afficher_Huffman(Arbre.all.fils_gauche, tab1 ,0);
          tab2 := Copie(Bit) ;
          Afficher_Huffman(Arbre.all.fils_droite, tab2 , 1) ;
        end if ;
      end Afficher_Huffman ;



   procedure Compresser(F_name : Unbounded_String ; B : boolean) is
    F , FHuff  : Ada.Streams.Stream_IO.File_Type;
    S , C : Stream_Access;
    Octet , oct : T_Octet;
    taille, d, e : Integer  ;
    Tab : Oct_frequence.T_LCA ;
    Arbre :  T_Arbre ;
    A: T_arb.T_LCA ;
    Bit , bit1 , bit2, Bitt1 , Bitt2 : Bi_LCA.T_LCA ;
    Code1 , Code2 : Code_LCA.T_LCA ;
   begin
    Open(F, In_File, To_String(F_Name));
    S  := stream(F);
    Initialiser(Tab) ;
    while not End_Of_File(F) loop
        Octet := T_Octet'Input(S);
        if not Cle_Presente(Tab , Octet) then
          Oct_frequence.Enregistrer(Tab,Octet,1) ;
        else
          Oct_frequence.Enregistrer(Tab,Octet,La_Donnee(Tab,Octet)+1) ;
        end if ;
    end loop ;
    Oct_frequence.Enregistrer(Tab, -1 , 0 ) ; -- Enregistrer le symbole “\$”
    Close(F) ;

    taille := 0 ;
    Initialiser(A) ;
    while not Oct_frequence.Est_Vide(Tab) loop
       taille := taille + 1 ;
       Octet := Oct_frequence.s_Cle(Tab) ;
       T_arb.Enregistrer(A,taille, Creer(Octet,Oct_frequence.La_Donnee(Tab,Octet),Null,Null)) ;
       Oct_frequence.Supprimer(Tab,Octet) ;
    end loop ;



     --construire l'arbre
     initialiser(Arbre) ;
     Arbre := Arbre_Huffman(A) ;

     --enregistrer chaque caractère avec son code huffman correspondant
     Bi_LCA.Initialiser(bit1) ;
     Code_LCA.Initialiser(Code1) ;
     Codage_Huffman(Arbre,bit1 , Code1) ;
     Bi_LCA.Initialiser(bit2) ;
     Code_LCA.Initialiser(Code2) ;
     Codage_Huffman(Arbre,bit2 , Code2) ;



     --obtention du fichier compressé
     Create (FHuff, Out_File, to_String(F_Name) & ".hff");
     C := Stream (FHuff);
     Open(F,In_File,to_String(F_Name)) ;
        S := Stream(F) ;


     T_Octet'Write(C, T_Octet(Code_LCA.position_cle(Code1,-1))) ;
     Supprimer(Code2, -1) ;
     while not Est_Vide(Code2) loop
        T_Octet'Write(C, s_Cle(Code2));
        if code_LCA.Taille(Code2) = 1  then
          T_Octet'Write(C, s_Cle(Code2));
        end if ;
        Supprimer(Code2, s_Cle(Code2)) ;
     end loop ;

     --effectuer le parcours infixe
     Bi_LCA.Initialiser(Bit) ;
     parcours_infixe(Arbre, Bit) ;
     oct := 0 ;
     e := 0 ;
     for k in 1..(Bi_LCA.Taille(Bit)) loop
        e := e + 1 ;
        oct := (oct*2) or T_Octet(Bi_LCA.La_Donnee(Bit,k)) ;
        if e = 8 then
          e := 0 ;
          T_Octet'Write(C, oct);
          oct := 0 ;
        end if ;
     end loop ;


     d := 0 ;
     while not End_Of_File(F) loop
        Octet := T_Octet'Input(S) ;
        while e < 8 and d < (Bi_LCA.Taille(Code_LCA.La_Donnee(Code1, Octet))) loop
                e := e + 1 ;
                d := d + 1 ;
               oct := (oct*2) or T_Octet(Bi_LCA.La_Donnee(Code_LCA.La_Donnee(Code1, Octet),d)) ;
        end loop ;
        if e = 8 then
          e := 0 ;
          T_Octet'Write(C, oct);
          oct := 0 ;
          if d < (Bi_LCA.Taille(La_Donnee(Code1, Octet))) then
            for i in (d + 1)..(Bi_LCA.Taille(Code_LCA.La_Donnee(Code1, Octet))) loop
               e := e + 1 ;
               oct := (oct*2) or T_Octet(Bi_LCA.La_Donnee(code_LCA.La_Donnee(Code1, Octet),i)) ;
            end loop ;
          end if ;
        end if ;
        d := 0 ;
     end loop ;
     while e < 8 and d < Bi_LCA.Taille(Code_LCA.La_Donnee(code1, -1))  loop    -- symbole $
            e := e + 1 ;
            d := d + 1 ;
            oct := (oct*2) or T_Octet(Bi_LCA.La_Donnee(code_LCA.La_Donnee(Code1, -1),d)) ;
        end loop ;


     if e = 8 then
       T_Octet'Write(C, oct);
       oct := 0 ; e := 0 ;
     else
       while e < 8 loop
                e := e + 1 ;
                oct := (oct*2) ;
       end loop ;
       T_Octet'Write(C, oct);
            e := 0 ;
            oct := 0 ;
     end if ;

     if d < Bi_LCA.Taille(Code_LCA.La_Donnee(Code1, -1)) then
       for i in (d + 1)..Bi_LCA.Taille(Code_LCA.La_Donnee(Code1, -1)) loop
          e := e + 1 ;
          oct := (oct * 2) or T_Octet(Bi_LCA.La_Donnee(Code_LCA.La_Donnee(Code1, -1),i)) ;
       end loop ;


        --Completer les octests non complets avec
       while e < 8 loop
                e := e + 1 ;
                oct := (oct*2) ;
       end loop ;
       T_Octet'Write(C, oct);
            e := 0 ;
            oct := 0 ;
       New_line ;
     end if ;

     Close(F) ;
     Close(FHuff) ;


        Initialiser(Bitt1) ;
        Initialiser(Bitt2) ;
     if B = True then
       --affichage de l'arbre de Huffman
       Put('('); Put(Donnee(Arbre),1); Put(')') ;
       New_Line ;
       afficher_huffman(Arbre.all.fils_gauche, Bitt1 , 0) ;
       afficher_huffman(Arbre.all.fils_droite , Bitt2 , 1) ;
       New_Line ;

       --afficher le caractère avec son conde huffman associé
       for i in 1..T_arb.Taille(A) loop
         Octet := Cle(La_Donnee(A,i)) ;
         if Octet = 10 then
            Put(" '\n'") ;
         elsif Octet = -1 then
            Put(" '\$' ") ;
         else
            Put(" '" & Character'Val(Octet) & "'");
         end if ;
         Put(" --> ") ;
         Octet_Binaire(Code_LCA.La_Donnee(Code1,Octet)) ;
         New_Line ;
       end loop ;
     end if ;
   end Compresser ;

end compression ;
