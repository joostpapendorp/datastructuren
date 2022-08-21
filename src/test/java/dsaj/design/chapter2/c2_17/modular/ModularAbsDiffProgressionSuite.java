package dsaj.design.chapter2.c2_17.modular;

import dsaj.design.chapter2.c2_17.original.AbsoluteDifferenceProgressionSuite;
import dsaj.design.chapter2.c2_17.original.Progression;
import org.junit.jupiter.api.DisplayName;

@DisplayName( "The modular progression of absolute differences should" )
public class ModularAbsDiffProgressionSuite
		extends AbsoluteDifferenceProgressionSuite
{
	@Override
	protected Progression< Long > newSUT( long low, long high )
	{
		return new ModularAbsDiffProgression( low, high );
	}

	@Override
	protected Progression< Long > newSUT()
	{
		return new ModularAbsDiffProgression();
	}
}
