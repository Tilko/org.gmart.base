package org.gmart.base.functionFromAcyclicGraphCodeGen;

import java.util.function.Supplier;

public class AbstractComponentDynType {
	//pouvoir définir/créer un circuit acyclique d'objet Java qui crée un bloc combinatoire:
	//une fois formé, le circuit forme un composant combinatoire qui possèdent des entrées (celles qui n'ont pas été connectées à une sortie d'un des sous-blocs),
	//et des sorties qui peuvent être la sortie de n'importe quel sous bloc
	//un tel bloc peut être utilisé comme une fonction en fournissant des arguments et en demandant la valeur qui est sur telle ou telle sortie
	//il peut être réutilisable, les données en entrées peuvent considérées immutables pour optimiser la mémoïsation,
	//lorsqu'une valeur est demandée en sortie, seulement les données nécessaires sur les entrées peuvent être présentes, en cas d'abscence d'une donnée en entrée:
	//soit elle est optionnel et il existe une valeur par défaut ou la valeur n'est pas nécessaire, soit une erreur est levé, l'erreur spécifie que telle entrée obligatoire est absente.
	//Un bloc combinatoire peut avoir un état interne, mais celui-ci doit être ré-initialisé si une valeur d'entrée dont il dépend change.
	//
	//certain bloc combinatoire peut être générique: un nombre d'entrée quelconque (un additionneur par exemple), 
	//
	
	
	
	//in: e0, e1;
	// 
	//
	//out rez: Adder(e0, e1, 4)
	
	//le circuit doit être "compilé" en un objet "circuit_graphe" qui n'est pas exécutable, qui contient les données nécessaires à la construction du circuit exécutable.
	//Ces données doivent permettre de générer le code de création/connexion des bloc éxécutable, si ce code compile alors pas d'erreur de typage.
	//ce code doit surement ressembler à:
	
	public static class Adder {
		Supplier<Integer> in0;
		Supplier<Integer> in1;
		public int getOut() {
			return in0.get() + in1.get();
		}

		public void setIn0(Supplier<Integer> in0) {
			this.in0 = in0;
		}
		public void setIn1(Supplier<Integer> in1) {
			this.in1 = in1;
		}
		public void setIn0(int in0) {
			this.in0 = ()->in0;
		}
		public void setIn1(int in1) {
			this.in1 = ()->in1;
		}
		 
	}
	//memoization:
	//si les entrées ne changent pas alors je renvoie la valeur précédemment calculée.
	//code source de la génération du code suivant:
	//CompExample {
	//    in(0, 1)
	//    Adder adder0;
	//    adder0.in0 = in0
	//    adder0.in1 = 5
	//    ou:
	//    adder0(in0, 5)
	//    ou:
	//    Adder adder0(in0, 5);
	//    
	//    Adder adder1(adder0, in1);
	//    out adder1;
	//    ou:
	//    out Adder adder1(Adder(in0, 5), in1);
	//    ou:
	//    out Adder(Adder(in0, 5), in1);
	//    
	//}
	public static class CompExample {
		Adder adder0;
		Adder adder1;
		CompExample(){
			adder0 = new Adder();
			adder1 = new Adder();
			adder0.setIn1(5);
			adder1.setIn0(()->adder0.getOut());
		}
		
		
		Supplier<Integer> in0;
		Supplier<Integer> in1;
		public void setIn0(Supplier<Integer> in0) {
			this.in0 = in0;
			adder0.setIn0(in0);
		}
		public void setIn1(Supplier<Integer> in1) {
			this.in1 = in1;
			adder1.setIn1(in1);
		}
		public void setIn0(int in0) {
			setIn0(()->in0);
		}
		public void setIn1(int in1) {
			setIn1(()->in1);
		}
		Integer in0Memo;
		Integer in1Memo;
		Integer outMemo;
		public Integer getOut() {
			Integer in0Val = in0.get();
			Integer in1Val = in1.get();
			if(in0Memo == in0Val && in1Memo == in1Val) {
				System.out.println("eee");
				return outMemo;				
			}
			int out = adder1.getOut();
			this.outMemo = out;
			this.in0Memo = in0Val;
			this.in1Memo = in1Val;
			
			return out;
		}
	}
	public static void main(String[] args) {
		CompExample comp = new CompExample();
		comp.setIn0(4);
		comp.setIn1(2);
		System.out.println(comp.getOut());
		System.out.println(comp.getOut());
	}
	
	
	
}


//un composant peut posséder un état interne "state",
	//les connexions entre les composants doivent pouvoir être spécifié par une entrée externe qui représente les différentes connexions,
	//une connexion peut se définir par une référence à une sortie et à une entrée, par exemple: <nom_composant>.<nom_sortie|position> -> <nom_composant>.<nom_entrée|position>, 
	//<nom_sortie|position> et <nom_entrée|position> peuvent être omis si il n'y a qu'un seul entrée/sortie,
	//
	//
	//
	//
	//Le typage doit être vérifié par la compilation du code généré:
	//