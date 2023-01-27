
N = 90 ;
Fe = 1000 ; 
f0 = 1100 ;
Te = 1/Fe ;
Temps = 0 : Te :(N-1)*Te ;
M = cos (2*pi*f0*Temps) ;
xlabel(Temps) ;
ylabel(M) ;
N1 = 2^(nextpow2(N)+1) ;
Frequence = linspace(0, Fe, N1) ;
semilogy(Frequence,abs(fft(M,N1))) ;