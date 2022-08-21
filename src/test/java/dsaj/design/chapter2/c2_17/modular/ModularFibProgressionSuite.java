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
package dsaj.design.chapter2.c2_17.modular;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName( "The modular version of the fibonacci progression should yield the same starting ten numbers as the original one" )
class ModularFibProgressionSuite
{
	@Test
	@DisplayName( "using the default configuration" )
	void default_configuration_yields_same_results_as_original()
	{
		final var sut = new ModularFibProgression();

		final var expectedValues = new Long[]{0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L};

		for( final Long expected : expectedValues )
		{
			assertThat( sut.nextValue(), is( expected ) );
		}
	}

	@Test
	@DisplayName( "using the start values 4 and 6" )
	void custom_configuration_yields_same_results_as_original()
	{
		final var sut = new ModularFibProgression(4, 6);

		final var expectedValues = new Long[]{4L, 6L, 10L, 16L, 26L, 42L, 68L, 110L};

		for( final Long expected : expectedValues )
		{
			assertThat( sut.nextValue(), is( expected ) );
		}
	}
}
