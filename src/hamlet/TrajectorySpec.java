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
package hamlet;

import com.google.common.collect.Lists;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Basic specification for birth-death trajectory simulations.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class TrajectorySpec {
    
    // Birth-death model to simulate:
    Model model;

    // Seed for RNG (negative number means use default seed):
    long seed;
    
    // Initial state of system:
    PopulationState initPopulationState;
    
    // Verbosity level for reportage on progress of simulation:
    int verbosity;
    
    // Length of time to propagate for:
    double simulationTime;

    // Integrator to use:
    Stepper stepper;
    
    // Population size end conditions:
    List<PopulationEndCondition> populationEndConditions;
    
    // Whether to collect evenly spaced samples or let the state stepper
    // when to sample:
    boolean evenlySpacedSampling;

    // Number of evenly spaced samples times.  Must be >=2.
    int nSamples;
    
    /**
     * Constructor.
     */
    public TrajectorySpec() {
        
        // Default initial population sizes of zero:
        initPopulationState = new PopulationState();
        
        // Spec progress reportage off by default:
        this.verbosity = 0;

        // Use BEAST RNG seed unless specified:
        this.seed = -1;
        
        // Initialise lists:
        populationEndConditions = Lists.newArrayList();
    }
    
    public void setModel(Model model) {
        this.model = model;
    }
    
    public Model getModel() {
        return model;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }
    
    public void setInitPopulationState(PopulationState initState) {
        this.initPopulationState = initState;
    }

    @JsonIgnore
    public PopulationState getInitPopulationState() {
        return initPopulationState;
    }

    public void setVerbosity(int verbosity) {
        if (verbosity<0 || verbosity>3)
            throw new IllegalArgumentException("Verbosity number must be between 0 and 3.");

        this.verbosity = verbosity;
    }

    /**
     * Set maximum simulation time.
     * 
     * @param simulationTime 
     */
    public void setSimulationTime(double simulationTime) {
        this.simulationTime = simulationTime;
    }

    /**
     * Set state updating algorithm to use.
     * 
     * @param integrator 
     */
    public void setStepper(Stepper integrator) {
        this.stepper = integrator;
    }

    /**
     * Add a population size end condition.  Note that truncation end
     * conditions may play havoc with ensemble summaries.
     * 
     * @param endCondition 
     */
    public void addPopSizeEndCondition(PopulationEndCondition endCondition) {
        this.populationEndConditions.add(endCondition);
    }

    /**
     * Sample population sizes at evenly spaced times.
     * 
     * @param nSamples Number of samples to record. Must be >=2.
     */
    public void setEvenSampling(int nSamples) {
        if (nSamples>=2) {
            this.nSamples = nSamples;
            this.evenlySpacedSampling = true;
        } else
            throw new IllegalArgumentException("Number of sampling times"
                    + " must be >= 2");
    }
    
    /**
     * Sample the state following every simulation step.  Note that this
     * will still give evenly spaced samples when a finite time-step
     * integration algorithm is used as the stepper.
     */
    public void setUnevenSampling() {
        this.evenlySpacedSampling = false;
    }
    
    /**
     * Retrieve maximum simulation time.
     * 
     * @return simulationTime
     */
    public double getSimulationTime() {
        return simulationTime;
    }
   
    /**
     * Retrieve object representing a particular state incrementing algorithm.
     * 
     * @return Stepper object.
     */
    public Stepper getStepper() {
        return stepper;
    }
    
    /**
     * Get number of evenly spaced samples.
     * 
     * @return nSamples
     */
    public int getnSamples() {
        return nSamples;
    }
    
    /**
     * Determine whether evenly spaced sampling is to be used.
     * 
     * @return true if evenly spaced sampling is uesed.
     */
    public boolean isSamplingEvenlySpaced() {
        return evenlySpacedSampling;
    }

    /**
     * Get time between samples.
     *
     * @return Sampling period.
     */
    @JsonIgnore
    public double getSampleDt() {
        return simulationTime/(nSamples-1);
    }
    
    /**
     * Retrieve verbosity index.
     * 
     * @return verbosity
     */
    @JsonIgnore
    public int getVerbosity() {
        return verbosity;
    }
    
    /**
     * Retrieve list of population end conditions.
     * 
     * @return population end conditions.
     */
    public List<PopulationEndCondition> getPopulationEndConditions() {
        return populationEndConditions;
    }
}
