package nl.papendorp.chapter7.exercises;


import net.datastructures.LinkedPositionalList;
import net.datastructures.PositionalList;

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
}
