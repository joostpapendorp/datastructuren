package nl.papendorp.chapter2.exercises.c2_17.original;

public class AbsoluteDifferenceProgression
		extends Progression< Long >
{
	private long previous;

	public AbsoluteDifferenceProgression()
	{
		this( 2L, 200L );
	}

	public AbsoluteDifferenceProgression( long low, long high )
	{
		super( low );
		if( low < 0 || high < 0 )
			throw new IllegalArgumentException( "Initial values must be positive" );
		previous = high + low;
	}

	@Override
	public void advance()
	{
		final long temp = previous;
		previous = current;
		current = Math.abs( temp - previous );
	}
}
