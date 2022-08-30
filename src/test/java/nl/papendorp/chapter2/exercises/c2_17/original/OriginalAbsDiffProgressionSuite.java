package nl.papendorp.chapter2.exercises.c2_17.original;

import org.junit.jupiter.api.DisplayName;

@DisplayName( "The original progression of absolute differences should" )
public class OriginalAbsDiffProgressionSuite
		extends AbsoluteDifferenceProgressionSuite
{
	@Override
	protected Progression< Long > newSUT( long low, long high )
	{
		return new AbsoluteDifferenceProgression( low, high );
	}

	@Override
	protected Progression< Long > newSUT()
	{
		return new AbsoluteDifferenceProgression();
	}
}
