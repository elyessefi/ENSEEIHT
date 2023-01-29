
% TP3 de Probabilites : fonctions a completer et rendre sur Moodle
% Nom : EL YESSEFI
% Prénom : Mohamed
% Groupe : H

function varargout = fonctions_TP3_proba(varargin)

    switch varargin{1}
        
        case 'matrice_inertie'
            
            [varargout{1},varargout{2}] = feval(varargin{1},varargin{2:end});
            
        case {'ensemble_E_recursif','calcul_proba'}
            
            [varargout{1},varargout{2},varargout{3}] = feval(varargin{1},varargin{2:end});
    
    end
end

% Fonction ensemble_E_recursif (exercie_1.m) ------------------------------
function [E,contour,G_somme] = ...
    ensemble_E_recursif(E,contour,G_somme,i,j,voisins,G_x,G_y,card_max,cos_alpha)
    contour(i,j)=0;
    e=1;
    while (length(E) < card_max && e <= length(voisins))
            vx=i+voisins(e,1);
            vy=j+voisins(e,2);
            if contour(vx,vy)
                Gx_i_voisin=G_x(vx,vy);
                Gy_j_voisin=G_y(vx,vy);
                
                V=[Gx_i_voisin,Gy_j_voisin];
               
            
                    if  (V*G_somme')/(norm(V)*norm(G_somme)) >= cos_alpha 
                        E=cat(1, E,[vx vy]);
                        G_somme=G_somme+V;
                        [E,contour,G_somme] = ...
    ensemble_E_recursif(E,contour,G_somme,vx,vy,voisins,G_x,G_y,card_max,cos_alpha);
                    

                    end
            end
       
        e=e+1;
    end

    
end

% Fonction matrice_inertie (exercice_2.m) ---------------------------------
function [M_inertie,C] = matrice_inertie(E,G_norme_E)
    E_flip = fliplr(E);
    
    M_inertie=zeros(2);
    p=sum(G_norme_E);
    a=sum(G_norme_E.*E_flip(:,1))/p;
    b=sum(G_norme_E.*E_flip(:,2))/p;
    M_inertie(1,1)=sum(G_norme_E.*((E_flip(:,1)-a).^2))/p;
    M_inertie(1,2)=sum(G_norme_E.*((E_flip(:,1)-a).*(E_flip(:,2)-b)))/p;
    M_inertie(2,1)=M_inertie(1,2);
    M_inertie(2,2)=sum(G_norme_E.*((E_flip(:,2)-b).^2))/p;

    C = [a,b];
 


end

% Fonction calcul_proba (exercice_2.m) ------------------------------------
function [x_min,x_max,probabilite] = calcul_proba(E_nouveau_repere,p)
x_min = min(E_nouveau_repere(:,1));
    x_max = max(E_nouveau_repere(:,1));
    y_min = min(E_nouveau_repere(:,2));
    y_max = max(E_nouveau_repere(:,2));
    
    N=round((x_max-x_min)*(y_max-y_min));
    [n,m]=size(E_nouveau_repere);
    probabilite=1-binocdf(n-1,N,p);

    
end
