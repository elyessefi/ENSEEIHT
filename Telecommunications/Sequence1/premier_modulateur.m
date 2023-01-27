% Initialisation
clear;
clear all;
clc;

%Initialisation des parametres

Fe = 24000;
Te = 1/Fe;
Rb = 3000;
Tb = 1/Rb;

%Durée symbole en nombre d’échantillons (Ts=NsTe)
m = 2;
Rs = Rb/log2(m);
Ts = 1/Rs;
Ns=floor(Ts/Te);

%Nombre de bits générés
nb_bits=100;

%Génération de l’information binaire
bits=randi([0,1],1,nb_bits);

%Mapping binaires à moyenne nulle : 0->-1, 1->1
Symboles=2*bits-1;

%Génération de la suite de Diracs pondérés par les symbols (suréchantillonnage)
Suite_diracs=kron(Symboles, [1 zeros(1,Ns-1)]);

%Génération de la réponse impulsionnelle du filtre de mise en forme (NRZ)
h = ones(1,Ns);

%Filtrage de mise en forme
x = filter(h,1,Suite_diracs);

%Affichage du signal généré
figure ;
plot(x);
axis([0 nb_bits - 1 -1.5 1.5]);
title("Figure 1 : Signal généré x");
xlabel("temps en seconde");
ylabel("signal généré x");

% Calcul de la DSP du signal x
%DSP_x = (1 / length(x)) * abs(fft(x, 2 ^ nextpow2(length(x)))) .^ 2;
DSP_x = abs(fft(x)).^2;
F = linspace(-Fe/2, Fe/2, length(DSP_x));

% Affichage de la DSP du signal 
figure;
semilogy(F, fftshift(DSP_x));
title('Figure 2 : DSP du signal');
xlabel('Fréquences en Hz');
ylabel('S2_{f}(x)');