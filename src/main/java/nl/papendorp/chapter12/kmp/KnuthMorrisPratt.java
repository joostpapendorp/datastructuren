package nl.papendorp.chapter12.kmp;

import nl.papendorp.chapter12.PatternMatcher;

import java.util.Optional;

import static java.util.Optional.empty;

public class KnuthMorrisPratt
		extends PatternMatcher
{
	@Override
	protected Optional< Integer > findPattern( final char[] text, final char[] pattern )
	{
		final var fallback = fallbackIndicesOnFailure( pattern );

		var textIndex = 0;
		var patternIndex = 0;

		while( textIndex < text.length )
		{
			if( text[ textIndex ] == pattern[ patternIndex ] )
			{
				/* the run of matching characters continues (or starts) */

				/* Check for a match.
				 * In the book, this is (again!) unnecessarily complicated (see cf 12.3 line 10).
				 * With m = pattern.length, k = patternIndex and j = textIndex:
				 * if( k == m -1 ) return j - m + 1 <=>
				 * if( k == m -1 ) return j - (m - 1) <=>
				 * if( k == m -1 ) return j - k <=>
				 */
				if( patternIndex == pattern.length - 1 )
					return Optional.of( textIndex - patternIndex );

				textIndex++;
				patternIndex++;
			}

			else if( patternIndex > 0 )
			{
			/* We have a broken run, so we (recursively) check our fallback to continue matching from a previous run.
			 * Key reason why this works is the fact that fallback runs *always* start at the beginning of the pattern and
			 * are a subset of this run. This means that all the characters from the fallback run that we are skipping
			 * are *identical to this run* and have just been checked. See fig 12.5 and ex 12.2
			 */
				patternIndex = fallback[ patternIndex - 1 ];
			}
			else // start over at next char.
				textIndex++;
		}

		return empty();
	}

	/* See cf. 12.4 */
	private int[] fallbackIndicesOnFailure( final char[] pattern )
	{
		final var fallback = new int[ pattern.length ];

		var currentIndex = 1; // 'j' in cf 12.4, poor 'i' got left out. Wh-i? we shall never know.
		var fallBackToIndex = 0; // 'k' in cf 12.4. o-k, sure.

		while( currentIndex < pattern.length )
		{
			/* if the current character matches the current fallback character, the run of matches continues, thus
			 * incrementing the index of the character to fall back on. Essentially, consecutive matches count upwards.
			 *
			 * Note the +1, again. This is not indexing off by one, but instead purposeful.
			 * It means that the run *starts* at 1, i.e. we fall back to the first character *after* the last match.
			 * See ex. 12.2
			 *
			 * While we *could* use a counter per run (run length), the next character in the run is implicit in the index of
			 * the previous position to fall back on. This, of course, saves us a whole integer *and* nicely obscures the
			 * readability! 'Who wouldn't want that?' the book seems to think.
			 */
			if( pattern[ currentIndex ] == pattern[ fallBackToIndex ] )
			{
				final var runCounter = fallback[ currentIndex - 1 ] + 1;
				fallback[ currentIndex ] = runCounter;
				currentIndex++;
				fallBackToIndex++;
			}

			/* else the run is broken, but we might want to fall back to continue a previous run */
			else if( fallBackToIndex > 0 )
			{
				/* this clause always follows the if clause above (or itself) in the next while iteration.
				 * we have post incremented or fallen back, so we are still at a first mismatched position. That means that the
				 * index at fallback[ fallbackIndex -1 ] still is the last of the matches for this run. It neatly points to the
				 * character *following* its fallback run, i.e. the character that should match *this* current character.
				 */
				fallBackToIndex = fallback[ fallBackToIndex - 1 ];
			}
			else // no match and fallBackToIndex == 0.
			{
				/*  We are back to the start of the matching string and must fall back to 0, which is the default value */
				currentIndex++;
			}
		}

		return fallback;
	}
}
