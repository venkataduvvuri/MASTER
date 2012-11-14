package hamlet;

import java.util.*;

/**
 * Node in inheritance tree generated from birth-death model.
 *
 * @author Tim Vaughan
 */
public class Node {

    // Parent of this node.  (Null if root.)
    Node parent;
    
    double time;
    
    List<Node> children;
    
    Population subPopulation;

    /**
     * Constructor.
     *
     * @param population	Population to which node belongs.
     * @param subPopOffset Sub-population to which node belongs.
     */
    Node(Population subPopulation, double time) {

        this.subPopulation = subPopulation;
        this.time = time;

        parent = null;
        children = new ArrayList<Node>();
    }
    
    /**
     * Constructor for inheritance map node. (No sub-population specification.)
     * 
     * @param population Population to which node belongs.
     */
    Node(Population subPopulation) {
        this.subPopulation = subPopulation;
        this.time = -1;
        
        parent = null;
        children = new ArrayList<Node>();
    }
    
    /**
     * Add a child node to the list of children.  Returns this node to
     * allow method chaining.
     * 
     * @param child Child to add.
     * @return this 
     */
    Node addChild(Node child) {
        children.add(child);
        
        return this;
    }

    /**
     * Determine whether node is root.
     *
     * @return True if node has no parent.
     */
    boolean isRoot() {
        return (parent == null);
    }
}
