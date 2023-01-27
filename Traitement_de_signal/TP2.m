N = 100 ;
f1 = 1000 ;
f2 = 3000 ;
fc = 2000 ;
Fe = 10000 ;
Te = 1/Fe ;
Temps = 0 : Te : (N-1) * Te ;
M1 = cos (2*pi*f1*Temps) ;
M2 = cos (2*pi*f2*Temps) ;
M = M1 + M2 ;
xlabel(Temps) ;
ylabel(M) ;
plot(Temps,M) ;
N1 = 2^(nextpow2(N)+1) ;
Frequence = linspace (0, Fe, N) ;
semilogy(Frequence,abs(fft(M,N))) ;
h = 2*fc*sinc(2*pi*fc*[-5:1:5]) ;
S = filter(h,1,M) ;
plot(Temps,M1); % f1 = 1000 




