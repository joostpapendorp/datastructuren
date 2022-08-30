package nl.papendorp.chapter2.exercises.c2_17.original;

import nl.papendorp.chapter2.exercises.c2_17.original.Progression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbsoluteDifferenceProgressionSuite
{
	@Test
	@DisplayName( "prepare its initial state to produce the given values as its first two arguments" )
	void yield_arguments_in_succession()
	{
		// using convention: sut = Subject Under Test
		var sut = newSUT( 1L, 2L );

		var first = sut.nextValue();
		var second = sut.nextValue();

		assertThat( first, is( 1L ) );
		assertThat( second, is( 2L ) );
	}

	@Test
	@DisplayName( "yield subsequent values by subtracting the current value from the previous one" )
	void next_value_is_difference_between_previous_two()
	{
		final var sut = newSUT( 1L, 2L );
		assertThat( thirdValueFrom( sut ), is( 2L - 1L ) );
	}

	@Test
	@DisplayName( "flip negative results back to positive" )
	void flip_negatives()
	{
		final var sut = newSUT( 5L, 2L );
		assertThat( thirdValueFrom( sut ), is( 3L ) );
	}

	@Test
	@DisplayName( "disallow initial negatives" )
	void negative_starting_conditions_throw_exception()
	{
		var thrown = assertThrows( IllegalArgumentException.class, () -> newSUT( -1L, 0L ) );
		assertThrows( IllegalArgumentException.class, () -> newSUT( 0L, -1L ) );

		assertThat( thrown.getMessage(), is( "Initial values must be positive" ) );
	}

	@Test
	@DisplayName( "defaults to 2 and 200 as values" )
	void uses_stated_defaults()
	{
		final var sut = newSUT();

		assertThat( sut.current, is( 2L ) );
		sut.advance();
		assertThat( sut.current, is( 200L ) );
	}
	protected abstract Progression< Long > newSUT( long low, long high );
	protected abstract Progression<Long> newSUT();

	private < TYPE > TYPE thirdValueFrom( final Progression< TYPE > sut )
	{
		sut.nextValue();
		sut.nextValue();
		return sut.nextValue();
	}
}
