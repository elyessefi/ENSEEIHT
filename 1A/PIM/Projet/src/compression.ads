with Ada.Strings.Unbounded; use Ada.Strings.Unbounded;
with LCA ;
with arbre_binaire ; use arbre_binaire ;


package compression is

    -- instanciation d'une LCA pour pouvoir créer une structure qui associe chaque caractère à sa fréquence dans le texte
    package Oct_frequence is new LCA(T_Cle => T_Octet ,T_Donnee => Integer );
    use Oct_frequence;
    -- instanciation de LCA dont la donnée est un arbre
    package T_arb is new LCA(T_Cle=>Integer , T_Donnee=> T_Arbre);
    use T_arb;
   --instanciation de LCA dont la clé est un entier
    package Bi_LCA is new LCA(T_Cle => Integer , T_Donnee=> Integer);
    use Bi_LCA;
    --Instanciatttion de LCA pour créer une structure qui associe chaque caractère à son code huffman associé
    package Code_LCA is new LCA(T_Cle => T_Octet , T_Donnee => Bi_LCA.T_LCA);
    use Code_LCA;



   --Calculer le codage de huffman de chaque caractère et enregistrer ce dans une sda avec son caractère associé
   procedure Codage_Huffman(Arbre : in T_Arbre ; Bit : in out Bi_LCA.T_LCA; Code : in out Code_LCA.T_LCA );

    --Obtenir la clé du premier minimum des données de la sda
   function Cle_Premier_Minimum(Sda : in T_arb.T_LCA) return Integer with
            Pre => not T_arb.Est_Vide(Sda);

     --Obtenir la clé du deuxieme minimum des données de la sda
   function Cle_Deuxiem_Minimum(Sda : in T_arb.T_LCA) return Integer with
            Pre => not T_arb.Est_Vide(Sda) ;

   -- obtenir un nv arbre suivant l'algo de huffman
   function Creer_Arbre(Sda : in T_arb.T_LCA) return T_arb.T_LCA;

   -- Construire l'arbre de huffman par récursivité
   function Arbre_Huffman(Sda : in T_arb.T_LCA) return T_Arbre with
            Pre => not T_arb.Est_Vide(Sda);

   --Afficher l'octet en base binaire
   procedure Octet_Binaire(Sda : in  Bi_LCA.T_LCA) with
            Pre => not Bi_LCA.Est_Vide(Sda) ;

   --Affectuer un parcours infixe de l'arbre et génerer le code de huffman
    procedure Parcours_Infixe (Arbre : in T_Arbre; Bit : in out Bi_LCA.T_LCA) ;

   --Afficher l'arbre de huffman
    procedure Afficher_Huffman(Arbre : in T_Arbre;   Bit : in out Bi_LCA.T_LCA ; c : Integer ) ;

   --Compresser un fichier texte
    procedure Compresser(F_name : Unbounded_String ; B : boolean) ;

    --Récuperer une copie de sda
   function Copie(sda : in Bi_LCA.T_LCA) return Bi_LCA.T_LCA ;

end compression ;
