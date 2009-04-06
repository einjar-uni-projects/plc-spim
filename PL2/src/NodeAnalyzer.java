import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class NodeAnalyzer {
	
	private LinkedList<NodeAnalyzer> hijos;
	private LinkedList<NodeAnalyzer> hermanos;
	private String cad;
	private String states = "";
	private NodeType nodeType;
	private HashMap<NodeKeys, String> nodeAtributes;

	public NodeType getNodeType() {
		return nodeType;
	}
	
	public String getStrAtr(NodeKeys nk){
		return nodeAtributes.get(nk);
	}
	
	public Integer getIntAtr(NodeKeys nk){
		Integer ret = null;
		try{
			ret = new Integer(nodeAtributes.get(nk));
		}catch(NumberFormatException nfe){
			System.out.println("Error al parsear atr");
		}
		return ret;
	}
	
	public void setAtribute(NodeKeys nk, String value){
		nodeAtributes.put(nk, value);
	}

	public void setNodeType(NodeType nodeType) {
		this.nodeType = nodeType;
	}

	public void setStates(String nstates) {
		StringBuilder sb = new StringBuilder();
		sb.append(nstates);
		if (states.length() > 0) {
			sb.append(" ");
			sb.append(states);
		}
		this.states = sb.toString();
	}

	private boolean error;

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public NodeAnalyzer(String cad) {
		this.cad = cad;
		hijos = new LinkedList<NodeAnalyzer>();
		hermanos = new LinkedList<NodeAnalyzer>();
		nodeAtributes = new HashMap<NodeKeys, String>();
	}

	public void addLeftHijo(NodeAnalyzer hijo) {
		hijos.addFirst(hijo);
	}

	public void addRightHijo(NodeAnalyzer hijo) {
		hijos.addLast(hijo);
	}

	public void addLeftHermano(NodeAnalyzer hermano) {
		hermanos.addFirst(hermano);
	}

	public void addRightHermano(NodeAnalyzer hermano) {
		hermanos.addLast(hermano);
	}

	public void copyHijos(LinkedList<NodeAnalyzer> hijos) {
		this.hijos.addAll(hijos);
	}

	public LinkedList<NodeAnalyzer> getHijos() {
		return hijos;
	}

	public void print(int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			if(i == level-1)
				sb.append("\\_");
			else
				sb.append("  ");
		}
		sb.append(cad);
		sb.append('[');
		sb.append(states);
		sb.append(']');
		if(nodeType != null){
			sb.append('[');
			sb.append(nodeType);
			sb.append(']');
		}
		sb.append('[');
		sb.append(getStrAttr());
		sb.append(']');
		if(error)
			sb.append("error");
		System.out.println(sb);
		for (NodeAnalyzer hijo : hijos) {
			hijo.print(level + 1);
		}
		for (NodeAnalyzer hermano : hermanos) {
			hermano.print(level);
		}
	}

	private String getStrAttr() {
		StringBuilder sb = new StringBuilder();
		Set<NodeKeys> keys = nodeAtributes.keySet();
		for (NodeKeys key : keys) {
			sb.append(key.name());
			sb.append(":");
			sb.append(nodeAtributes.get(key));
			sb.append(" ");
		}
		return sb.toString();
	}

}