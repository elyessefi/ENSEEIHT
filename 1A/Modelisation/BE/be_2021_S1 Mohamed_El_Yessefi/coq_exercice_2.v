Require Import Naturelle.
Section Session1_2021_Logique_Exercice_2.

Variable A B C : Prop.

Theorem Exercice_2_Naturelle : ((A /\ B) -> C) -> ((A -> C) \/ (B -> C)).
Proof.
(* A COMPLETER *)
I_imp H.
E_ou A(~A).
TE.
I_imp H1.
I_ou_d.
I_imp H2.
E_imp (A/\B).
Hyp H.
I_et.
Hyp H1.
Hyp H2.
I_imp H1.
I_ou_g.
I_imp H2.
E_antiT.
I_antiT A.
Hyp H2.
Hyp H1.
Qed.

Theorem Exercice_2_Coq : ((A /\ B) -> C) -> ((A -> C) \/ (B -> C)).
Proof.
(* A COMPLETER *)
intros.
cut (A\/~A).
intros.
elim H0.
intro.
right.
intro.
cut (A/\B).
exact H.
split.
exact H1.
exact H2.
intro.
left.
intro.
absurd A.
exact H1.
exact H2.
apply(classic A).
Qed.

End Session1_2021_Logique_Exercice_2.

