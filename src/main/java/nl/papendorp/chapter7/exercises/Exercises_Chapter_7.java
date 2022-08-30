package nl.papendorp.chapter7.exercises;


import net.datastructures.LinkedPositionalList;
import net.datastructures.PositionalList;

import java.util.Random;
import java.util.function.Function;

public class Exercises_Chapter_7
{
	public static < TYPE > PositionalList< TYPE > reverse_7_5a( final PositionalList< TYPE > list )
	{
		// 7.5 a reverse the positional list using size, first, last, remove and addFirst

		if( list.first() != list.last() )
		{
			var lastElement = list.remove( list.last() );
			reverse_7_5a( list );
			list.addFirst( lastElement );
		}

		return list;
	}

	public static < TYPE > PositionalList< TYPE > reverse_7_5b( final PositionalList< TYPE > list )
	{
		//7.5 b reverse the positional list using size, first, remove and addFirst
		var list2 = new LinkedPositionalList< TYPE >();
		var list3 = new LinkedPositionalList< TYPE >();

		final var size = list.size();
		for( var i = 0; i < size; i++ )
		{
			list2.addFirst( list.remove( list.first() ) );
		}

		for( var i = 0; i < size; i++ )
		{
			list3.addFirst( list2.remove( list2.first() ) );
		}

		for( var i = 0; i < size; i++ )
		{
			list.addFirst( list3.remove( list3.first() ) );
		}

		return list;
	}

	public static < TYPE > java.util.List< TYPE > shuffleIndexed_7_6( final java.util.List< TYPE > list )
	{
		// gegeven:  randomInteger( k ) = x, met x <- random( 0..k-1 ), zoals:
		final Function< Integer, Integer > randomInteger = new Random( System.currentTimeMillis() )::nextInt;

		// gevraagd: shuffle een list met length = size
		final var size = list.size();

		// i = index of next element to swap with random element from remainder of the list
		// last element needs no swapping
		for( var i = 0; i < size - 1; i++ )
		{
			// size of list remainder. element can remain constant (i.e. swapped with itself)
			final var remaining = size - i;

			// choose random index from the remainder: i..length (exclusive)
			var nextIndex = i + randomInteger.apply( remaining );

			//swap
			final var temp = list.get( i );
			list.set( i, list.get( nextIndex ) );
			list.set( nextIndex, temp );
		}

		return list;
	}

	public static < TYPE > PositionalList< TYPE > shufflePositional_7_6( final PositionalList< TYPE > list )
	{
		// given:  randomInteger( k ) = x, for x <- random( 0..k-1 ), zoals:
		final Function< Integer, Integer > randomInteger = new Random( System.currentTimeMillis() )::nextInt;

		// required: shuffle a list with length = size
		final var size = list.size();

		// elements left to swap
		// last element needs no swapping
		var current = list.first();
		for( var remaining = size; remaining >= 1; remaining-- )
		{
			// choose the amount of steps from the current position to swap with: 0..remaining (exclusive
			var steps = randomInteger.apply( remaining );

			// find the position to swap with, possibly current (steps == 0)
			var swapPosition = current;
			for( int j = 0; j < steps; j++ )
			{
				swapPosition = list.after( swapPosition );
				if( swapPosition == null )
					throw new IllegalStateException( remaining + " " + steps );
			}

			//insert swapped element before current, or skip current
			if( current != swapPosition )
				list.addBefore( current, list.remove( swapPosition ) );
			else
				current = list.after( current );
		}

		return list;
	}

	public static < TYPE extends Comparable< TYPE > > PositionalList< TYPE > bubbleSort( final PositionalList< TYPE > list )
	{
		final var size = list.size();
		for( var i = 0; i < size - 1; i++ )
		{
			var current = list.first();
			for( var j = 1; j < size - i; j++ )
			{
				var next = list.after( current );

				if( current.getElement().compareTo( next.getElement() ) > 0 )
				{
					var temp = current.getElement();
					list.set( current, next.getElement() );
					list.set( next, temp );
				}
				current = next;
			}
		}

		return list;
	}
}
