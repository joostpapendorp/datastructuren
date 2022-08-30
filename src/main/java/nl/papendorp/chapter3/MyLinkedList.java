package nl.papendorp.chapter3;

public class MyLinkedList< TYPE >
		implements MyList< TYPE >
{
	private /*mut*/ Node< TYPE > first;
	private /*mut*/ Node< TYPE > last;
	private /*mut*/ int size;

	public MyLinkedList()
	{
		this.last = new Stop<>();
		this.first = last;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public TYPE first()
	{
		return elementOf( first );
	}

	@Override
	public TYPE last()
	{
		return elementOf( last );
	}

	private TYPE elementOf( final Node< TYPE > node )
	{
		return switch( node )
				{
					case Link< TYPE > link -> link.element();
					case Stop< ? > ignored -> throw new IndexOutOfBoundsException( "list is empty" );
				};
	}

	@Override
	public void addFirst( final TYPE element )
	{
		switch( first )
		{
			case Link< TYPE > link -> first = new Link<>( element, link );
			case Stop< TYPE > ignored -> insertIntoEmptyList( element );
		}
		size++;
	}

	/**
	 * This method breaks symmetry and is contra-intuitive. It requires Link to be mutable. If last were such a special
	 * case, then where is removeLast()?
	 * In regular implementations (e.g. in functional languages) access to the last element is never provided; in stead
	 * those implementations only rely on head and tail (being the list without the head)
	 *
	 * java.util.LinkedList *does* provide addLast(E), b/c it is implemented as a doubly linked list, and implements the
	 * Queue en Deque interfaces
	 */
	@Override
	public void addLast( final TYPE element )
	{
		switch( last )
		{
			case Link< TYPE > link ->
			{
				last = new Link<>( element, link.next() );
				link.updateNext( last ); // mutable op, breaks symmetry.
			}
			case Stop< TYPE > ignored -> insertIntoEmptyList( element );
		}
		size++;
	}

	private void insertIntoEmptyList( TYPE element )
	{
		first = new Link<>( element, last );
		last = first;
	}

	@Override
	public TYPE removeFirst()
	{
		return switch( first )
				{
					case Link< TYPE > node ->
					{
						first = node.next();
						size--;
						yield node.element();
					}

					case Stop< TYPE > ignored -> throw new IndexOutOfBoundsException( "list is empty" );
				};
	}
}

sealed interface Node< TYPE >{ }

/** mutable b/c of insertLast. Otherwise, it would've been a record*/
final class Link< TYPE >
		implements Node< TYPE >
{
	private final TYPE element;
	private /*mut*/ Node< TYPE > next;

	Link( final TYPE element, final Node< TYPE > next )
	{
		this.element = element;
		this.next = next;
	}

	TYPE element()
	{
		return element;
	}

	Node< TYPE > next()
	{
		return next;
	}

	void updateNext( final Node< TYPE > next )
	{
		this.next = next;
	}
}

record Stop< TYPE >( )
		implements Node< TYPE >{ }
