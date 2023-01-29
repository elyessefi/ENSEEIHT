function [Vd,Vg] = vactorisation_par_colonne(I);
Vd = I(1:size(I,2)-1);
Vg = I(2:size(I,2));
end

