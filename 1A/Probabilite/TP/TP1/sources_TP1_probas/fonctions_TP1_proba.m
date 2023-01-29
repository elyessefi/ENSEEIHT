
% TP1 de Probabilites : fonctions a completer et rendre sur Moodle
% Nom : EL YESSEFI
% Prénom : MOHAMED
% Groupe : 1SN-CM2-Groupe H

function varargout = fonctions_TP1_proba(varargin)

    switch varargin{1}     
        case 'ecriture_RVB'
            varargout{1} = feval(varargin{1},varargin{2:end});
        case {'vectorisation_par_colonne','decorrelation_colonnes'}
            [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});
        case 'calcul_parametres_correlation'
            [varargout{8},varargout{2},varargout{3}] = feval(varargin{1},varargin{2:end}); 
    end

end

% Fonction ecriture_RVB (exercice_0.m) ------------------------------------
% (Copiez le corps de la fonction ecriture_RVB du fichier du meme nom)
function image_RVB = ecriture_RVB(image_originale)


dims = size(image_originale) ;
nb_lignes = dims(1)/2 ;
nb_colonnes =  dims(2)/2 ;
image_RVB = zeros(nb_lignes,nb_colonnes, 3);
image_RVB(:,:,2) = (image_originale(:,:,1)+image_originale(:,:,3))/2 ;
image_RVB(:,:,1) = image_originale(:,:,4);


end



% Fonction vectorisation_par_colonne (exercice_1.m) -----------------------
function [Vd,Vg] = vectorisation_par_colonne(I)
nb_colonnes = size(I,2);
Vd1 = I(:,2:end);
Vg2 = I(:,1:nb_colonnes-1);
Vd = Vd1(:);
Vg = Vg2(:);
  
end

% Fonction calcul_parametres_correlation (exercice_1.m) -------------------
function [r,a,b] = calcul_parametres_correlation(Vd,Vg)
    n  = size (Vd,1); 
    moyenned = (1/n)*(ones(1,n)*Vd);
    moyenneg = (1/n)*(ones(1,n)*Vg);
    varianced = (1/n)*(Vd-moyenned)'*(Vd-moyenned);
    varianceg = (1/n)*(Vg-moyenneg)'*(Vg-moyenneg);
    ecartd = sqrt (varianced);
    ecartg = sqrt (varianceg);
    Covariance = (1/n)*((Vg-moyenneg)'*(Vd-moyenned));
    r = Covariance/(ecartg*ecartd);
    a = Covariance/varianceg ;
    b =  (-a*moyenneg)+moyenned ;

end

% Fonction decorrelation_colonnes (exercice_2.m) --------------------------
function [I_decorrelee,I_min] = decorrelation_colonnes(I,I_max)
    I_decorrelee = I ;
    n=size(I,2);
    for i = 2:n
        I_decorrelee(:,i)= I(:,i)-I(:,i-1);
    end
        I_min = min(I_decorrelee(:));

end



