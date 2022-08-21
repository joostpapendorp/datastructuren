package dsaj.design.chapter2.c2_17.modular;

import dsaj.design.chapter2.c2_17.original.Progression;

import java.util.function.BiFunction;

public class BiFunctionalProgression< TYPE >
		extends Progression< TYPE >
{
	private final BiFunction< TYPE, TYPE, TYPE > advancement;
	private /*mutable*/ TYPE previous;

	protected BiFunctionalProgression( final TYPE previous, final TYPE current,
	                                   final BiFunction< TYPE, TYPE, TYPE > advancement )
	{
		super( current );

		this.previous = previous;
		this.advancement = advancement;
	}

	@Override
	protected void advance()
	{
		final var next = advancement.apply( previous, current );
		previous = current;
		current = next;
	}
}
