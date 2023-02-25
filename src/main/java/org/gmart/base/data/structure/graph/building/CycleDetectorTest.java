package org.gmart.base.data.structure.graph.building;

import java.io.FileNotFoundException;

import org.gmart.base.data.structure.graph.textTree.TextTreeMaker;
import org.gmart.base.data.structure.graph.textTree.TextTreeMaker.NamedNode;

public class CycleDetectorTest {
	
	public static void main(String[] args) throws FileNotFoundException {
		String test = """
				a
				 b
				 c 
				  d
				   1
				   c
				  e
				  f
				 d
				  h
				  l 
				""";
		NamedNode fromText = TextTreeMaker.textToNode(test);
		String k = TextTreeMaker.nodeToText(fromText);
		System.out.println(k);
		String cyclic = new CycleDetector<NamedNode,String>(s->s.getOwnId(), s->s.getChildren().stream())
				.isCyclic(fromText);
		System.out.println("cyclic:" + cyclic);
	}
	
}
