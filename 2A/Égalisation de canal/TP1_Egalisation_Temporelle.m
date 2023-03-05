
% Script for computing the BER for BPSK/QPSK modulation in ISI Channels
% 
close all;
clear;

%% Simulation paramèters
% On décrit ci-après les paramètres généraux de la simulation

%Frame length
M=4; % 2:BPSK, 4: QPSK
N  = 1000000; % Number of transmitted bits or symbols
Es_N0_dB = [0:30]; % Eb/N0 values

%Multipath channel parameters
hc=[1 0.8*exp(1i*pi/3) 0.3*exp(1i*pi/6) ];%0.1*exp(1i*pi/12)];%ISI channel
%hc=[0.04, -0.05, 0.07, -0.21, -0.5, 0.72, 0.36, 0, 0.21, 0.03, 0.07];
%a=1.2;
%hc=[1 -a];
Lc=length(hc);%Channel length
ChannelDelay=0; %delay is equal to number of non causal taps


%Preallocations
nErr_zfinf=zeros(1,length(Es_N0_dB));
nErr_mmseinf=zeros(1,length(Es_N0_dB));
nErr_zf_ls=zeros(1,length(Es_N0_dB));
nErr_mmse_ls=zeros(1,length(Es_N0_dB));

% nErr_ml=zeros(1,length(Es_N0_dB));
const=qammod((0:M-1)',M); %reference Gray QPSK constellation
tblen=16; %Traceback depth
nsamp=1; %Oversampling rate
preamble=[];
postamble=[];
for ii = 1:length(Es_N0_dB)

   % BPSK symbol generations
%    bits = rand(1,N)>0.5; % generating 0,1 with equal probability
%    s = 1-2*bits; % BPSK modulation following: {0 -> +1; 1 -> -1} 
   
    % QPSK symbol generations
   bits = rand(2,N)>0.5; % generating 0,1 with equal probability
   s = 1/sqrt(2)*((1-2*bits(1,:))+1j*(1-2*bits(2,:))); % QPSK modulation following the BPSK rule for each quadatrure component: {0 -> +1; 1 -> -1} 
   sigs2=var(s);
   
   % Channel convolution: equivalent symbol based representation
   z = conv(hc,s);  
   
   %Generating noise
   sig2b=10^(-Es_N0_dB(ii)/10);
   %n = sqrt(sig2b)*randn(1,N+Lc-1); % white gaussian noise, BPSK Case
    n = sqrt(sig2b/2)*randn(1,N+Lc-1)+1j*sqrt(sig2b/2)*randn(1,N+Lc-1); % white gaussian noise, QPSK case
   
   % Adding Noise
   y = z + n; % additive white gaussian noise

   %% zero forcing equalization
   % We now study ZF equalization
   
   %Unconstrained ZF equalization, only if stable inverse filtering
   
   
   %%
   % 
   %  The unconstrained ZF equalizer, when existing is given by 
   % 
   % $w_{,\infty,zf}=\frac{1}{h(z)}$
   % 
   %%
   % 
   
%    s_zf=filter(1,hc,y);%if stable causal filter is existing
%     bhat_zf = zeros(2,length(bits));
%     bhat_zf(1,:)= real(s_zf(1:N)) < 0;
%     bhat_zf(2,:)= imag(s_zf(1:N)) < 0;
%     nErr_zfinfdirectimp(1,ii) = size(find([bits(:)- bhat_zf(:)]),1);

    %Otherwise, to handle the non causal case
    Nzf=200;
    [r, p, k]=residuez(1, hc);
    [w_zfinf]=ComputeRI( Nzf, r, p, k );
    s_zf=conv(w_zfinf,y);
    bhat_zf = zeros(2,length(bits));
    bhat_zf(1,:)= real(s_zf(Nzf:N+Nzf-1)) < 0;
    bhat_zf(2,:)= imag(s_zf(Nzf:N+Nzf-1)) < 0;
    
    nErr_zfinf(1,ii) = size(find([bits(:)- bhat_zf(:)]),1);
  
    % Implementation MMSE
    deltac = zeros(1, 2*Lc-1);
    deltac(Lc) = 1;
    Nmmse = 200;
    [r, p, k] = residuez(fliplr(conj(hc)), (conv(hc, fliplr(conj(hc)))+(sig2b/sigs2)*deltac));
    [w_mmseinf] = ComputeRI(Nmmse, r, p, k);
    s_mmse=conv(w_mmseinf,y);
    bhat_mmse = zeros(2,length(bits));
    bhat_mmse(1,:)= real(s_mmse(Nmmse:N+Nmmse-1)) < 0;
    bhat_mmse(2,:)= imag(s_zf(Nmmse:N+Nmmse-1)) < 0;
    
    nErr_mmseinf(1,ii) = size(find([bits(:)- bhat_mmse(:)]),1);

   % Structure RIF
    Nw = 100; % Paramtre de design
    d = 5;
    H = toeplitz([hc(1) zeros(1, Nw-1)]', [hc, zeros(1, Nw-1)]);
    % ZF
    Ry = conj(H)*H.';
    p = zeros(Nw+Lc-1, 1);
    P = H.'*inv(Ry)*conj(H);
    [~, dopt] = max(diag(P)); % alpha : le biais
    p(dopt) = 1;
    Gamma = conj(H)*p;
    w_zf_ls = (inv(Ry)*Gamma).';
    sig_e_opt=sigs2-conj(w_zf_ls)*Gamma;
    shat = conv(w_zf_ls, y);
    shat = shat(dopt:end);
    bHat = zeros(2, length(bits));
    bHat(1,:) = real(shat(1:N)) < 0;
    bHat(2,:) = imag(shat(1:N)) < 0;
    nErr_zf_ls(1,ii) = size(find([bits(:)- bHat(:)]),1);
    
    % MMSE
    q = sig2b/sigs2;
    Ry = (conj(H)*H.'+q*eye(length(conj(H)*H.')));
    p = zeros(Nw+Lc-1,1);
    P = H.'*inv(Ry)*conj(H);
    [alpha,dopt]=max(diag(abs(P)));
    p(dopt)=1;
    Gamma = conj(H)*p;
    w_mmse_ls = (inv(Ry)*Gamma).';
    sig_e_opt=sigs2-conj(w_mmse_ls)*Gamma;
    biais = 1-sig_e_opt/sigs2;
    sHat = conv(w_mmse_ls,y);
    sHat = sHat(dopt:end);
    bHat = zeros(2,length(bits));
    bHat(1,:) = real(sHat(1:N))<0;
    bHat(2,:) = imag(sHat(1:N))<0;
    nErr_mmse_ls(1,ii) = size(find([bits(:)- bHat(:)]),1);
    
    % Maximum likelihood
    s_ml = mlseeq(y,hc,const,tblen,'rst',nsamp,[ ],[ ])/sqrt(2) ;
    bits_ml = zeros(2,length(bits));
    bits_ml(1,:) = real(s_ml(1:N))<0;
    bits_ml(2,:) = imag(s_ml(1:N))<0;
    nErr_ml(1,ii) = size(find([bits(:)- bits_ml(:)]),1);
end

% plot

%% 2.1 Canal de transmission
% Visualisation des symboles reçus
scatterplot(z)
title("Les symboles reçus sans bruit")
scatterplot(y)
title("Les symboles reçus avec bruit")

% Visualisation des DSPs
figure

Fe = 1;
subplot(5,1,1)
[DSP_emission, freq]=pwelch(s,[],[],[],Fe,'twosided');
plot(freq, DSP_emission)
title("DSP à l'émission")

subplot(5,1,2)
[DSP_sb, freq]=pwelch(z,[],[],[],Fe,'twosided');
plot(freq, DSP_sb)
title("DSP sans bruit")

subplot(5,1,3)
[DSP_ab, freq] = pwelch(y,[],[],[],Fe,'twosided');
plot(freq, DSP_ab)
title("DSP avec bruit")

subplot(5,1,4)
plot(abs(fft(hc,1000)));
title("Module de la réponse fréquentielle")

subplot(5,1,5)
plot(angle(fft(hc,1000)));
title("Phase de la réponse fréquentielle")



%% Egaliseurs temporels à structure non contrainte
simBer_zfinf = nErr_zfinf/N/log2(M); % simulated ber
simBer_mmseinf = nErr_mmseinf/N/log2(M); % simulated ber

figure
semilogy(Es_N0_dB,simBer_zfinf(1,:),'ro-','Linewidth',2);
axis([0 50 10^-6 0.5])
grid on
legend('sim-zf-inf/direct');
xlabel('E_s/N_0, dB');
ylabel('Bit Error Rate');
title('Bit error probability curve for QPSK in ISI with ZF equalizers')


figure
semilogy(Es_N0_dB,simBer_mmseinf(1,:),'bx-','Linewidth',2);
axis([0 50 10^-6 0.5])
grid on
legend('sim-mmse-inf/direct');
xlabel('E_s/N_0, dB');
ylabel('Bit Error Rate');
title('Bit error probability curve for QPSK in ISI with MMSE equalizers')

figure
semilogy(Es_N0_dB,simBer_zfinf(1,:),'ro-','Linewidth',2);
axis([0 50 10^-6 0.5])
hold on
semilogy(Es_N0_dB,simBer_mmseinf(1,:),'bx-','Linewidth',2);
axis([0 50 10^-6 0.5])
grid on
legend('sim-zf-inf/direct','sim-mmse-inf/direct');
xlabel('E_s/N_0, dB');
ylabel('Bit Error Rate');
title('Comparaison between bit error probability curves for QPSK in ISI with ZF and MMSE equalizers')


figure
stem(real(w_zfinf))
hold on
stem(real(w_zfinf),'r-')
ylabel('Amplitude');
xlabel('time index')
title('Impulse response for ZF')

figure
stem(real(w_mmseinf))
hold on
stem(real(w_mmseinf),'b-')
ylabel('Amplitude');
xlabel('time index')
title('Impulse response for MMSE')

%% Egaliseurs temporels à structure RIF
simBer_zf_ls = nErr_zf_ls/N/log2(M); % simulated ber
simBer_mmse_ls = nErr_mmse_ls/N/log2(M); % simulated ber
figure
semilogy(Es_N0_dB,simBer_zf_ls(1,:),'go-','Linewidth',2);
axis([0 50 10^-6 0.5])
hold on
semilogy(Es_N0_dB,simBer_mmse_ls(1,:),'rx-','Linewidth',2);
axis([0 50 10^-6 0.5])
grid on
legend('RIF-zf','RIF-mmse');
xlabel('E_s/N_0, dB');
ylabel('Bit Error Rate');
title('Bit error probability curve for QPSK in ISI with RIF equalizers')

figure
title('Impulse response')
stem(real(w_zf_ls))
hold on
stem(real(w_zf_ls),'r-')
ylabel('Amplitude');
xlabel('time index')

%% Egaliseur Maximum de vraisemblance
simBer_ml = nErr_ml/N/log2(M); % simulated ber
figure
semilogy(Es_N0_dB,simBer_ml,'bo-','Linewidth',2);
xlabel('E_s/N_0, dB');
ylabel('Bit Error Rate');
title('Bit error probability curve for Maximum Likelihood Equalizer')


