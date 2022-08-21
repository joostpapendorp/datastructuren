package dsaj.design.chapter2.c2_17.modular;

public class ModularFibProgression
		extends BiFunctionalProgression< Long >
{
	public ModularFibProgression()
	{
		this( 0, 1 );
	}

	public ModularFibProgression( final long first, final long second )
	{
		super( second - first, first, Long::sum );
	}
}
