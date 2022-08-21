/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
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
package dsaj.design.chapter2.c2_17.original;

/**
 * Generates a simple progression. By default: 0, 1, 2, ...
 */
public abstract class Progression< TYPE >
{
	// instance variable
	protected TYPE current;

	/**
	 * Constructs a progression with given start value.
	 */
	public Progression( final TYPE start ){ current = start; }

	/**
	 * Returns the next value of the progression.
	 */
	public TYPE nextValue()
	{
		TYPE answer = current;
		advance();    // this protected call is responsible for advancing the current value
		return answer;
	}

	/**
	 * Advances the current value to the next value of the progression.
	 */
	protected abstract void advance();

	/**
	 * Prints the next n values of the progression, separated by spaces.
	 */
	public void printProgression( final int n )
	{
		System.out.print( nextValue() );          // print first value without leading space
		for( int j = 1; j < n; j++ )
			System.out.print( " " + nextValue() );  // print leading space before others
		System.out.println();                   // end the line
	}
}