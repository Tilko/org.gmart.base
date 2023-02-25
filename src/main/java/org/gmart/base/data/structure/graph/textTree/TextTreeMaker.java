package org.gmart.base.data.structure.graph.textTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.gmart.base.data.structure.graph.OwnIdentityAware;
import org.gmart.base.data.structure.graph.TargetsAwareDGNode;

public class TextTreeMaker {
	private static record Line(int depth, String id) {}
	public static NamedNode textToNode(String text) {
		HashMap<Integer, NamedNode> indentToParentNode = new HashMap<>();
		List<Line> lines = Stream.of(text.split("\\R")).map(s->new Line(countLeadingSpaces(s), s.trim())).toList();
		NamedNode value = new NamedNode("root");
		indentToParentNode.put(0, value);
		lines.forEach(l->{
			NamedNode node = new NamedNode(l.id);
			int depth = l.depth;
			indentToParentNode.get(depth).addChild(node);
			indentToParentNode.put(depth+1, node);
		});
		return value;
	}
	private static int countLeadingSpaces(String line) {
        int count = 0;
        while (count < line.length() && line.charAt(count) == ' ') {
            count++;
        }
        return count;
    }


	
	public static String nodeToText(NamedNode node) {
	    StringBuilder sb = new StringBuilder();
	    treeToStringHelper(node, sb, 0);
	    return sb.toString();
	}
	private static <Id, N extends IdentityAndTargetsAwareDGNode<Id, N>>void treeToStringHelper(N node, StringBuilder sb, int indentLevel) {
	    for (int i = 0; i < indentLevel; i++) {
	        sb.append(" ");
	    }
	    sb.append(node.getOwnId()).append("\n");
	    node.getTargetsStream().forEach(c -> treeToStringHelper(c, sb, indentLevel + 1));
	}
	
	interface IdentityAndTargetsAwareDGNode<Id, C extends IdentityAndTargetsAwareDGNode<Id,C>> 
			extends OwnIdentityAware<Id>, TargetsAwareDGNode<C> {
	}
	
	public static class NamedNode implements IdentityAndTargetsAwareDGNode<String, NamedNode>{
	    private String id;
	    private List<NamedNode> children;
	    public NamedNode(String id) {
	        this.id = id;
	        this.children = new ArrayList<>();
	    }
	    public void addChild(NamedNode child) {
	        this.children.add(child);
	    }
	    public List<NamedNode> getChildren() {
	        return children;
	    }
		@Override
		public Stream<NamedNode> getTargetsStream() {
			return children.stream();
		}
		@Override
		public String getOwnId() {
			return id;
		}
	}
}
