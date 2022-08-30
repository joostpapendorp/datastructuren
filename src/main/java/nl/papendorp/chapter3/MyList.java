package nl.papendorp.chapter3;

public interface MyList< TYPE >
{
	int size();

	default boolean isEmpty()
	{
		return size() == 0;
	}

	TYPE first();

	TYPE last();

	void addFirst( final TYPE element );

	void addLast( final TYPE element );

	TYPE removeFirst();
}
