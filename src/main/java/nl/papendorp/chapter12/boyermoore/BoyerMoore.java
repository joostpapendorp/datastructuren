package nl.papendorp.chapter12.boyermoore;

import java.util.HashMap;
import java.util.Optional;

import static java.util.Optional.empty;

public class BoyerMoore
{
	private static final int ABSENT_FROM_PATTERN = -1;

	public Optional< Integer > find( final String text, final String pattern )
	{
		if( pattern.isEmpty() ) return Optional.of( 0 );

		if( text.length() < pattern.length() ) return empty();

		return findPattern( text.toCharArray(), pattern.toCharArray() );
	}

	private Optional< Integer > findPattern( final char[] text, final char[] pattern )
	{
		final var lastOccurrenceInPattern = new HashMap< Character, Integer >( pattern.length );
		/* The book also walks through the *entire* text here to add sentinels for characters that occur in the text, but
		 * don't occur in the pattern. Let's... not do that.
		 */
		for( var i = 0; i < pattern.length; i++ )
			lastOccurrenceInPattern.put( pattern[ i ], i );

		final var lastIndexOfPattern = pattern.length - 1;
		var patternIndex = lastIndexOfPattern;
		var textIndex = lastIndexOfPattern;

		while( textIndex < text.length )
		{
			if( text[ textIndex ] == pattern[ patternIndex ] )
			{
				if( patternIndex == 0 )
					return Optional.of( textIndex );

				patternIndex--;
				textIndex--;
			}
			else /* mismatch occurred; compute the next text index from which to continue matching:*/
			{
				/* if the mismatched text character does *not* occur in the pattern, then realign the start of the pattern *after*
				 * the current mismatch (aligns pattern[ 0 ] with text[ textIndex +1 ]) and restart matching at the end of the pattern.
				 * i.e. the text index shifts the full length of the pattern. See fig 12.2
				 */
				if( !lastOccurrenceInPattern.containsKey( text[ textIndex ] ) )
					textIndex += pattern.length;

				else /* we consider two cases, depending on where the mismatched character last occurs in the pattern */
				{
					/* rightmost occurrence of the mismatched character is called 'j' in fig 12.3, because of... reasons unknown. */
					final var lastOccurrenceOfMismatchInPattern = lastOccurrenceInPattern.get( text[ textIndex ] );

					/* if the mismatched character occurs in the pattern *right* of the current index in the pattern,
					 * then we've already passed it by during this match.
					 * We can't be sure it does not occur in the rest of the pattern, so we shift the number of steps taken
					 * during our last attempt, aligning the pattern character that mismatched with the text character *after*
					 * the mismatch. See fig 12.3b. The text index shifts the number of steps in this attempt +1:
					 *
					 * NOTE: the above +1 is *not* due to indices starting at 0, but because we start AFTER the mismatch!
					 *
					 * stepsInLastAttempt = lastIndexOfPattern - patternIndex
					 * textIndex += stepsInLastAttempt + 1
					 *
					 * but, since lastIndexOfPattern = pattern.length -1, this contracts to (-1 *is* due to indices vs length):
					 *
					 * textIndex += pattern.length - patternIndex;
					 *
					 * pattern.length and patternIndex are 'm' and 'k' respectively in the shrtspk o/t stpd bk.
					 */
					if( lastOccurrenceOfMismatchInPattern > patternIndex )
						textIndex += pattern.length - patternIndex;

					else /* the mismatched character occurs *left* of the current index into the pattern. */
					{
						/* We can realign the mismatched character with its rightmost position in the pattern
						 * where it *does* match. NOTE no +1 here, we start *at* the position, not *after*.
						 *
						 * This means we shift the amount of steps equal to the position of the last occurrence *from the right*,
						 * i.e. last index in pattern - index of rightmost occurrence:
						 *
						 * See fig 12.3a. However, the book trips over its own confusion between using the length and the indexes,
						 * thus translating above to m- (j+1) with 'm' as pattern.length and 'j' again as the last occurrence of the
						 * mismatch. We don't need that +1 crap due to index vs. length, because:
						 *
						 * m - (j+1) <=> m -1 -j <=> (m-1) - j <=>
						 */
						textIndex += lastIndexOfPattern - lastOccurrenceOfMismatchInPattern;
					}

					/* Finally, the book contracts the above into incomprehensibility, in a joyous feast full of +1s and -1s,
					 * given that it *also* uses -1 as a sentinel in the map of rightmost occurrences.
					 * This yields line 20 of cf. 12.2:
					 *
					 * i += m - Math.min( k, 1 + last.get( text[ i ] ) );
					 *
					 * equivalent to
					 *
					 * lastOccurrenceOfMismatchInPattern = lastOccurrenceInPattern.getOrDefault( text[ textIndex ], -1 );
					 * textIndex += pattern.length - Math.min( patternIndex, lastOccurrenceOfMismatchInPattern );
					 */
				}
				patternIndex = lastIndexOfPattern;
			}
		}

		return empty();
	}
}
