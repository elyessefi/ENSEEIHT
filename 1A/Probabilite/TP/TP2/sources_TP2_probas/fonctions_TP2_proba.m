
% TP2 de Probabilites : fonctions a completer et rendre sur Moodle
% Nom : EL YESSEFI
% Prenom : MOHAMED
% Groupe : 1SN-

function varargout = fonctions_TP2_proba(varargin)

    switch varargin{1}
        
        case {'calcul_frequences_caracteres','determination_langue','coeff_compression','gain_compression','partitionnement_frequences'}

            varargout{1} = feval(varargin{1},varargin{2:end});
            
        case {'recuperation_caracteres_presents','tri_decroissant_frequences','codage_arithmetique'}
            
            [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});
            
        case 'calcul_parametres_correlation'
            
            [varargout{1},varargout{2},varargout{3}] = feval(varargin{1},varargin{2:end});
            
    end

end

% Fonction calcul_frequences_caracteres (exercice_0.m) --------------------
function frequences = calcul_frequences_caracteres(texte,alphabet)
    % Note : initialiser le vecteur avec 'size(alphabet)' pour garder la bonne orientation
    frequences = zeros(length(alphabet),1);
    for j = 1:length(alphabet)
        s=0;
        for i = 1:length(texte)
            if texte(i) == alphabet(j) 
                s = s+1 ;
            end
        end
        frequences(j,1) = s/length(texte);
     end
end

% Fonction recuperation_caracteres_presents (exercice_0.m) ----------------
function [selection_frequences,selection_alphabet] = ...
                      recuperation_caracteres_presents(frequences,alphabet)
selection_frequences = zeros(size(find(frequences ~= 0)));
selection_alphabet = zeros(size (selection_frequences));
j = 1 ;
for i = 1:length(frequences)
   if frequences(i) ~= 0
        selection_frequences(j) = frequences(i) ;
        selection_alphabet(j) = alphabet(i) ;
        j = j+1 ;
    end
end


end

% Fonction tri_decremental_frequences (exercice_0.m) ----------------------
function [frequences_triees,indices_frequences_triees] = ...
                           tri_decroissant_frequences(selection_frequences)
    [frequences_triees, indices_frequences_triees] = sort(selection_frequences, 'descend');

end

% Fonction determination_langue (exercice_1.m) ----------------------------
function indice_langue = determination_langue(frequences_texte, frequences_langues, nom_norme)
    % Note : la variable nom_norme peut valoir 'L1', 'L2' ou 'Linf'.
    Matrice = abs(frequences_langues-frequences_texte') ;
    switch nom_norme
        case 'L1'
            Vecteur = sum(Matrice,2);
            [~, indice_langue] = min(Vecteur);
        case 'L2'
            Vecteur = sum(Matrice.*Matrice,2);
            [~, indice_langue] = min(Vecteur);
        case 'Linf'
            Vecteur = max(Matrice');
            [~, indice_langue] = min(Vecteur);
    end
end

% Fonction coeff_compression (exercice_2.m) -------------------------------
function coeff_comp = coeff_compression(signal_non_encode,signal_encode)
    coeff_comp = (length(signal_non_encode)-length(signal_encode))/length(signal_non_encode) ;
end

% Fonction coeff_compression (exercice_2bis.m) -------------------------------
function gain_comp = gain_compression(coeff_comp_avant,coeff_comp_apres)
gain_comp = (coeff_comp_avant - coeff_comp_apres)/coeff_comp_avant ;

end

% Fonction partitionnement_frequences (exercice_3.m) ----------------------
function bornes = partitionnement_frequences(selection_frequences)



end

% Fonction codage_arithmetique (exercice_3.m) -----------------------------
function [borne_inf,borne_sup] = ...
                       codage_arithmetique(texte,selection_alphabet,bornes)


    
end