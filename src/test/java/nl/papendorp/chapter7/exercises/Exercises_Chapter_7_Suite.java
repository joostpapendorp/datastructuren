package nl.papendorp.chapter7.exercises;

import net.datastructures.LinkedPositionalList;
import net.datastructures.PositionalList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nl.papendorp.chapter7.exercises.Exercises_Chapter_7.shuffleIndexed_7_6;
import static nl.papendorp.chapter7.exercises.Exercises_Chapter_7.shufflePositional_7_6;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
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

	@DisplayName( "7.6 shuffle an indexed list" )
	@Test
	void shuffle_indexed_list_7_6()
	{
		var given = new java.util.ArrayList< Integer >( 10 );

		// able to reverse non-empty lists
		for( int i = 0; i < 10; i++ )
		{
			given.add( i );
		}

		final java.util.List< Integer > result = shuffleIndexed_7_6( given );

		for( var next : result )
			System.out.print( next + " " );
		System.out.println();

		assertThat( result, containsInAnyOrder( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ) );

	}

	@DisplayName( "7.6 shuffle a positional list" )
	@Test
	void shuffle_positional_list_7_6()
	{
		var given = new LinkedPositionalList< Integer >();

		// able to reverse non-empty lists
		for( int i = 0; i < 10; i++ )
		{
			given.addLast( i );
		}

		final PositionalList< Integer > result = shufflePositional_7_6( given );

		for( var next : result )
			System.out.print( next + " " );
		System.out.println();

		assertThat( result, containsInAnyOrder( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ) );
	}
}
