package dsaj.design.chapter2.c2_17.original;

public class Count extends Progression<Long>
{
	public Count( final long start )
	{
		super( start );
	}

	@Override
	public void advance()
	{
		current++;
	}
}
