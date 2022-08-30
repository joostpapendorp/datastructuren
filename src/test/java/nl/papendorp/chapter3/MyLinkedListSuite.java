package nl.papendorp.chapter3;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName( "the linked list should" )
public class MyLinkedListSuite
{
	@DisplayName( "contain the added element" )
	@Test
	void contains_added_element()
	{
		var sut = new MyLinkedList< Integer >();
		sut.addFirst( 1 );
		assertThat( sut.first(), is( 1 ) );
	}

	@DisplayName( "start out empty" )
	@Test
	void starts_empty()
	{
		var sut = new MyLinkedList< Integer >();
		assertThat( sut.isEmpty(), is( true ) );
		assertThat( sut.size(), is( 0 ) );
	}

	@DisplayName( "increase in size after adding elements" )
	@Test
	void increases_in_size()
	{
		var sut = new MyLinkedList< Integer >();
		sut.addFirst( 1 );
		sut.addLast( 2 );
		sut.addLast( 3 );
		sut.addFirst( 4 );
		assertThat( sut.size(), is( 4 ) );
	}

	@DisplayName( "fail when removing from empty list" )
	@Test
	void remove_from_empty_list_throws_exception()
	{
		var sut = new MyLinkedList< Integer >();

		var exception = assertThrows( IndexOutOfBoundsException.class, sut::removeFirst );
		assertThat( exception.getMessage(), is( "list is empty" ) );
	}

	@DisplayName( "be empty after removing all elements" )
	@Test
	void adding_then_removing_elements_yields_empty_list()
	{
		var sut = new MyLinkedList< Integer >();

		for( var i = 0; i < 4; i++ )
			sut.addFirst( i );

		for( var i = 0; i < 4; i++ )
			sut.removeFirst();

		assertThat( sut.isEmpty(), is( true ) );
	}

	@DisplayName( "act like a stack when adding first" )
	@Test
	void adding_elements_first_can_be_retrieved_in_reverse_order()
	{
		var sut = new MyLinkedList< Integer >();

		for( var i = 0; i < 4; i++ )
			sut.addFirst( i );

		for( var i = 3; i >= 0; i-- )
			assertThat( sut.removeFirst(), is( i ) );
	}

	@DisplayName( "act like queue when adding last" )
	@Test
	void adding_elements_last_can_be_retrieved_in_order()
	{
		var sut = new MyLinkedList< Integer >();

		for( var i = 0; i < 4; i++ )
			sut.addLast( i );

		for( var i = 0; i < 4; i++ )
			assertThat( sut.removeFirst(), is( i ) );
	}
}
