% ~gergaud/ENS/Automatique/TP19-20/TP_Etudiants/simu_pendule_etu/simu_pendule_inv_capteur_etu.m
%
%
% Auteur : Gergaud Joseph
% Date : october 2019
% Institution : Université de Toulouse, INP-ENSEEIHT
%               Département Sciences du Numérique
%               Informatique, Mathématiques appliquées, Réseaux et Télécommunications
%               Computer Science department
%
%-----------------------------------------------------------------------------------------
%
% Code Matlab de test pour la simulation du pendule inversé contrôlé. 
%
%-----------------------------------------------------------------------------------------


% Remarque : On ne fait pas de sous programme car Simulink utilise
% l'environnement Matlab
%
clear all; close all;
addpath('./Ressources');
fich_simulink = './pendule_inv_capteur_etu'
% Pour une figure avec onglet
set(0,  'defaultaxesfontsize'   ,  12     , ...
   'DefaultTextVerticalAlignment'  , 'bottom', ...
   'DefaultTextHorizontalAlignment', 'left'  , ...
   'DefaultTextFontSize'           ,  12     , ...
   'DefaultFigureWindowStyle','docked');
%
% Initialisations
% ---------------
t0 = 0;             % temps initial
g = 9.81; l = 10;   % constantes
xe = [0 0]';         % (x_e, u_e) point de fonctionnement
ue = 0;             %

% Cas 1
% -----
x0 = [pi/20 0]';       % initial point

% Cas 
fich = 'cas1';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode45';
pas = 10 ;
RelTol = '1e-3';

simu_pendule_inv_capteur;

% Cas 
fich = 'cas2';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode45';
pas = 10 ;

RelTol = '1e-10';

simu_pendule_inv_capteur;

% Cas 
fich = 'cas3';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode45';
pas = 10 ;

RelTol = 0.001;

simu_pendule_inv_capteur;

% Cas 
fich = 'cas4';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode1';
pas = 10;
RelTol = 1;

simu_pendule_inv_capteur;


% Cas 
fich = 'cas5';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode1';
pas = 10;
RelTol = 2;

simu_pendule_inv_capteur;  
% Cas 
fich = 'cas6';

tf = 100;              % temps final
K = [10 1];
algorithme = 'ode1';
pas = 10 ;
RelTol = 5;

simu_pendule_inv_capteur;




