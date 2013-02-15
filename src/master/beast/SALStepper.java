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
package master.beast;

import beast.core.Description;
import beast.core.Input;
import beast.core.Input.Validate;
import beast.core.Plugin;

/**
 * Plugin for specifying use of the SAL algorithm.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
@Description("Step anticipation tau-leaping stochastic integration algorithm.")
public class SALStepper extends Plugin implements Stepper {
    
    public Input<Double> stepSizeInput = new Input<Double>("stepSize",
            "Length of integration time step.", Validate.REQUIRED);

    double stepSize;
    
    public SALStepper() { }
    
    @Override
    public void initAndValidate() {
        stepSize = stepSizeInput.get();
    }
    
    @Override
    public master.Stepper getStepperObject() {
        return new master.SALStepper(stepSize);
    }
    
}
