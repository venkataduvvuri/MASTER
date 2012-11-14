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

/**
 * A Moment is a special MomentGroup containing just one moment schema.
 *
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class Moment extends MomentGroup {
    
    /**
     * Create a single factorial moment.
     * 
     * @param name moment name
     * @param pops populations whose sizes will be factored into moment
     */
    public Moment(String name, Population ... pops) {
        super(name);        
        addSchema(pops);
    }
    
    /**
     * Create a single (possibly non-factorial) moment.
     * 
     * @param name moment name
     * @param fact whether this is a factorial moment
     * @param pops populations whose sizes will be factored into moment
     */
    public Moment(String name, boolean fact, Population ... pops) {
        super(name, fact);
        addSchema(pops);
    }
}
