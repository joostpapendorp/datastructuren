package nl.papendorp.chapter7.exercises;

import net.datastructures.LinkedPositionalList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName( "Exercises chapter seven: " )
class Exercises_Chapter_7_Suite
{
	@DisplayName( "7.5 a reverse the positional list using size, first, last, remove and addFirst" )
	@Test
	void reverse_7_5a()
	{
		var given = new LinkedPositionalList< Integer >();

		// able to 'reverse' empty lists
		assertTrue( Exercises_Chapter_7.reverse_7_5a( given ).isEmpty() );

		// able to reverse non-empty lists
		for( int i = 0; i < 10; i++ )
		{
			given.addLast( i );
		}

		var result = Exercises_Chapter_7.reverse_7_5a( given );

		var it = result.positions().iterator();
		for( var i = 9; i >= 0; i-- )
		{
			assertTrue( it.hasNext() );
			var nextPos = it.next();
			assertThat( nextPos.getElement(), is( i ) );
		}

		assertFalse( it.hasNext() );
	}
	@DisplayName( "7.5 b reverse the positional list using size, first, remove and addFirst" )
	@Test
	void reverse_7_5b()
	{
		var given = new LinkedPositionalList< Integer >();

		// able to 'reverse' empty lists
		assertTrue( Exercises_Chapter_7.reverse_7_5b( given ).isEmpty() );

		// able to reverse non-empty lists
		for( int i = 0; i < 10; i++ )
		{
			given.addLast( i );
		}

		var result = Exercises_Chapter_7.reverse_7_5b( given );

		var it = result.positions().iterator();
		for( var i = 9; i >= 0; i-- )
		{
			assertTrue( it.hasNext() );
			var nextPos = it.next();
			assertThat( nextPos.getElement(), is( i ) );
		}

		assertFalse( it.hasNext() );
	}
}
