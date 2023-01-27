
% TP1 de Statistiques : fonctions a completer et rendre sur Moodle
% Nom : EL YESSEFI
% Prénom : Mohamed
% Groupe : 1SN-CM2-Groupe GH

function varargout = fonctions_TP1_stat(varargin)

    [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});

end

% Fonction G_et_R_moyen (exercice_1.m) ----------------------------------
function [G, R_moyen, distances] = ...
         G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
moyenne_x = mean(x_donnees_bruitees);
moyenne_y = mean(y_donnees_bruitees);
G = [moyenne_x moyenne_y];
distances = sqrt ((x_donnees_bruitees-moyenne_x).^2 + (y_donnees_bruitees-moyenne_y).^2);
R_moyen = mean(distances);

     
     
end

% Fonction estimation_C_uniforme (exercice_1.m) ---------------------------
function [C_estime, R_moyen] = ...
         estimation_C_uniforme(x_donnees_bruitees,y_donnees_bruitees,n_tests)

[G, R_moyen, distances] = G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
C = rand(n_tests,2) + G;
C_x = repmat(C(:,1),1,length(x_donnees_bruitees));
C_y = repmat(C(:,2),1,length(y_donnees_bruitees));
X = repmat(x_donnees_bruitees,n_tests,1);
Y = repmat(y_donnees_bruitees,n_tests,1);
dist = sqrt((X-C_x).^2 + (Y-C_y).^2);
[minimum indice] = min(sum(dist - R_moyen,2).^2);
C_estime = C(indice,:);
     

end

% Fonction estimation_C_et_R_uniforme (exercice_2.m) ----------------------
function [C_estime, R_estime] = ...
         estimation_C_et_R_uniforme(x_donnees_bruitees,y_donnees_bruitees,n_tests)
[G, R_moyen, distances] = G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
C = (rand(n_tests,2)-0.5)*R_moyen + G;
R = rand(n_tests,1)-0.5 + R_moyen;
C_x = repmat(C(:,1),1,length(x_donnees_bruitees));
C_y = repmat(C(:,2),1,length(y_donnees_bruitees));
X = repmat(x_donnees_bruitees,n_tests,1);
Y = repmat(y_donnees_bruitees,n_tests,1);
dist = sqrt((X-C_x).^2 + (Y-C_y).^2);
[minimum indice] = min(sum(dist - R,2).^2);
C_estime = C(indice,:);
R_estime = R(indice,:);

end

% Fonction occultation_donnees (donnees_occultees.m) ----------------------
function [x_donnees_bruitees, y_donnees_bruitees] = ...
         occultation_donnees(x_donnees_bruitees, y_donnees_bruitees, theta_donnees_bruitees)
theta = rand(1,2)*2*pi;
% Cela prend en consideration tous les cas
theta_donnee = theta_donnees_bruitees>=theta(1,1) & theta_donnees_bruitees<=theta(1,2);

x_donnees_bruitees(theta_donnee == 0) = [];
y_donnees_bruitees(theta_donnee == 0) = [];

end

% Fonction estimation_C_et_R_normale (exercice_4.m, bonus) ----------------
function [C_estime, R_estime] = ...
         estimation_C_et_R_normale(x_donnees_bruitees,y_donnees_bruitees,n_tests)
[G, R_moyen, distances] = G_et_R_moyen(x_donnees_bruitees,y_donnees_bruitees)
C = randn(n_tests,2)*2*R_moyen + G;
R = (randn(n_tests,1)*0.5)*R_moyen + R_moyen;
C_x = repmat(C(:,1),1,length(x_donnees_bruitees));
C_y = repmat(C(:,2),1,length(y_donnees_bruitees));
X = repmat(x_donnees_bruitees,n_tests,1);
Y = repmat(y_donnees_bruitees,n_tests,1);
dist = sqrt((X-C_x).^2 + (Y-C_y).^2);
[minimum indice] = min(sum(dist - R,2).^2);
C_estime = C(indice,:);
R_estime = R(indice,:);

end