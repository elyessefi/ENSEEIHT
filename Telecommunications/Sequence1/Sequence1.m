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
M1 = 2;
Rs1 = Rb/log2(M1);
Ts1 = 1/Rs1;
Ns1=Ts1/Te;

%Nombre de bits générés
nb_bits=100;

%Génération de l’information binaire
bits=randi([0,1],1,nb_bits);

%Mapping binaires à moyenne nulle : 0->-1, 1->1
Symboles1=2*bits-1;

%Génération de la suite de Diracs pondérés par les symbols (suréchantillonnage)
Suite_diracs1=kron(Symboles1, [1 zeros(1,Ns1-1)]);

%Génération de la réponse impulsionnelle du filtre de mise en forme (NRZ)
h1 = ones(1,Ns1);

%Filtrage de mise en forme
x1 = filter(h1,1,Suite_diracs1);

%Affichage du signal généré
figure ;
plot(x1);
axis([0 nb_bits - 1 -1.5 1.5]);
title("Figure 1 : Signal généré x");
xlabel("temps en seconde");
ylabel("signal généré x");

% Calcul de la DSP du signal x
DSP_x1 = (1 / length(x1)) * abs(fft(x1, 2 ^ nextpow2(length(x1)))) .^ 2;

% Affichage de la DSP du signal 
figure;
plot(linspace(-Fe/2, Fe/2, length(DSP_x1)), fftshift(DSP_x1));
title('Figure 2 : DSP du signal ');
xlabel('Fréquences en Hz');
ylabel('S_{f}(x)');



%Durée symbole en nombre d’échantillons (Ts=NsTe)
M2 = 4;
Rs2 = Rb2/log2(M2);
Ts2 = 1/Rs2;
Ns2=Ts2/Te;

%Mapping 4-aires à moyenne nulle : 
Symboles2 = (2 * bi2de(reshape(bits, 2, length(bits)/2).') - 3).';
%Génération de la suite de Diracs pondérés par les symbols (suréchantillonnage)
Suite_diracs2=kron(Symboles2, [1 zeros(1,Ns2-1)]);
%Génération de la réponse impulsionnelle du filtre de mise en forme (NRZ)
h2=ones(1,Ns2);
%Filtrage de mise en forme
x2=filter(h2,1,Suite_diracs2);
%Affichage du signal généré
figure ; 
plot(x2);
axis([0 nb_bits-1 -1.5 1.5]);
title("Figure 3 : Signal généré x");
xlabel("temps en seconde");
ylabel("signal généré x");

% Calcul de la DSP du signal x
DSP_x2 = (1 / length(x2)) * abs(fft(x2, 2 ^ nextpow2(length(x2)))) .^ 2;

% Affichage de la DSP du signal 
figure;
plot(linspace(-Fe/2, Fe/2, length(DSP_x2)), fftshift(DSP_x2));
title('Figure 4 : DSP du signal ');
xlabel('Fréquences en Hz');
ylabel('S_{f}(x)');


%Durée symbole en nombre d’échantillons (Ts=NsTe)
M3 = 2;
Rs3 = Rb3/log(M3);
Ts3 = 1/Rs3;
Ns3=Ts3/Te;
L = 6;
alpha = 0.5;

%Mapping binaires à moyenne nulle : 0->-1, 1->1
Symboles3=2*bits-1;

%Génération de la suite de Diracs pondérés par les symbols (suréchantillonnage)
Suite_diracs3=kron(Symboles3, [1 zeros(1,Ns-1)]);

%Génération de la réponse impulsionnelle du filtre de mise en forme (NRZ)
h = rcosdesign(alpha, L, Ns3);

%Filtrage de mise en forme
x3 = filter(h3,1,Suite_diracs3);

%Affichage du signal généré
figure ;
plot(x3);
axis([0 nb_bits - 1 -1.5 1.5]);
title("Figure 5 : Signal généré x");
xlabel("temps en seconde");
ylabel("signal généré x");

% Calcul de la DSP du signal x
DSP_x3 = (1 / length(x3)) * abs(fft(x3, 2 ^ nextpow2(length(x3)))) .^ 2;

% Affichage de la DSP du signal 
figure;
plot(linspace(-Fe/2, Fe/2, length(DSP_x3)), fftshift(DSP_x3));
title('Figure 6 : DSP du signal ');
xlabel('Fréquences en Hz');
ylabel('S_{f}(x)');


%----- Etude des interferences entre symbole et du critere de Nyquist ------

%--------------------- Etude sans Canal de propagation -------------------
%---------------------Modulateur binaire a moyenne nulle-----------------
M = 2;
Rs2 = Rb2/log2(M2);
Ts2 = 1/Rs2;
Ns2=Ts2/Te;

% mapping binaire a moyenne null
Symboles = 2*bits-1 

