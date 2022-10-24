package nl.papendorp.chapter12;

import java.util.Optional;

import static java.util.Optional.empty;

public abstract class PatternMatcher
{
	public Optional< Integer > find( final String text, final String pattern )
	{
		if( pattern.isEmpty() ) return Optional.of( 0 );

		if( text.length() < pattern.length() ) return empty();

		return findPattern( text.toCharArray(), pattern.toCharArray() );
	}
	protected abstract Optional< Integer > findPattern( final char[] text, final char[] pattern );
}
