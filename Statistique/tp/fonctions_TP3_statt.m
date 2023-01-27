
% TP3 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom : El Yessefi
% Prenom : Mohamed
% Groupe : 1SN-CM2-H

function varargout = fonctions_TP3_stat(varargin)

    [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});

end

% Fonction estimation_F (exercice_1.m) ------------------------------------
function [rho_F,theta_F,ecart_moyen] = estimation_F(rho,theta)
    n= length(rho);
    M = zeros(n,2);
    M(:,1) = cos(theta);
    M(:,2) = sin(theta);
    Y= M\rho;
    rho_F = sqrt(Y(1)^2 + Y(2)^2);
    theta_F = atan2(Y(2),Y(1));
    
    % A modifier lors de l'utilisation de l'algorithme RANSAC (exercice 2)
    ecart_moyen = Inf;

end

% Fonction RANSAC_2 (exercice_2.m) ----------------------------------------
function [rho_F_estime,theta_F_estime] = RANSAC_2(rho,theta,parametres)
    n = length(rho);
    residu_min = Inf;
    for i = 1:parametres(3)
        rnd = randperm(n,2);
        [rho_F, theta_F] = estimation_F(rho(rnd), theta(rnd));
        residu = abs(rho - rho_F * cos(theta - theta_F));
        Mat = residu <= parametres(1);
        if sum(Mat) / length(Mat) >= parametres(2)            
            residu = sum(1 / length(Mat) * abs(rho(Mat) - rho_F * cos(theta(Mat) - theta_F)));
        elseif residu <= residu_min
                residu_min = residu;
                rho_F_estime = rho_F;
                theta_F_estime = theta_F;
            end
        end
    end



% Fonction G_et_R_moyen (exercice_3.m, bonus, fonction du TP1) ------------
function [G, R_moyen, distances] = ...
         G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)



end

% Fonction estimation_C_et_R (exercice_3.m, bonus, fonction du TP1) -------
function [C_estime,R_estime,critere] = ...
         estimation_C_et_R(x_donnees_bruitees,y_donnees_bruitees,n_tests,C_tests,R_tests)
     
    % Attention : par rapport au TP1, le tirage des C_tests et R_tests est 
    %             considere comme etant deje effectue 
    %             (il doit etre fait au debut de la fonction RANSAC_3)



end

% Fonction RANSAC_3 (exercice_3, bonus) -----------------------------------
function [C_estime,R_estime] = ...
         RANSAC_3(x_donnees_bruitees,y_donnees_bruitees,parametres)
     
    % Attention : il faut faire les tirages de C_tests et R_tests ici



end
