package nl.papendorp.chapter2.exercises.c2_17.modular;

public class ModularAbsDiffProgression
		extends BiFunctionalProgression< Long >
{
	public ModularAbsDiffProgression( final long low, final long high )
	{
		super( low + high, low,
				( prev, curr ) -> Math.abs( curr - prev ) );

		if( low < 0 || high < 0 )
			throw new IllegalArgumentException( "Initial values must be positive" );
	}

	public ModularAbsDiffProgression()
	{
		this( 2, 200 );
	}
}
