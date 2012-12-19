/*
 * Copyright (C) 2012 Tim Vaughan <tgvaughan@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package master.examples;

import master.Population;
import master.inheritance.InheritanceModel;
import master.inheritance.InheritanceReaction;
import master.inheritance.InheritanceTrajectory;
import master.inheritance.InheritanceTrajectorySpec;
import master.inheritance.LineageEndCondition;
import master.inheritance.NexusOutput;
import master.inheritance.Node;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates a tree using the Yule model of speciation.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class YuleTree {
    
    public static void main(String[] argv) throws FileNotFoundException {
        /*
         * Assemble model:
         */
    
        InheritanceModel model = new InheritanceModel();
    
        // Define populations:
        Population X = new Population("X");
        model.addPopulation(X);
        
        // Define reactions:
        
        // X -> 2X
        InheritanceReaction birth = new InheritanceReaction("Birth");
        Node Xparent = new Node(X);
        Node Xchild1 = new Node(X);
        Node Xchild2 = new Node(X);
        Xparent.addChild(Xchild1);
        Xparent.addChild(Xchild2);
        birth.setInheritanceReactantSchema(Xparent);
        birth.setInheritanceProductSchema(Xchild1, Xchild2);
        birth.setRate(1.0);
        model.addInheritanceReaction(birth);

        /*
         * Set initial state:
         */
        
        List<Node> initNodes = new ArrayList<Node>();
        initNodes.add(new Node(X));
        
        /*
         * Define simulation:
         */
        
        InheritanceTrajectorySpec spec = new InheritanceTrajectorySpec();

        spec.setModel(model);
        spec.setInitNodes(initNodes);
        spec.setUnevenSampling();
        spec.addLineageEndCondition(new LineageEndCondition(10, false));
        
        /*
         * Generate inheritance graph:
         */
        
        InheritanceTrajectory traj = new InheritanceTrajectory(spec);
        
        /*
         * Write results as a newick tree:
         */
        
        NexusOutput.write(traj, false, false, new PrintStream("out.tree"));
    }
    
}