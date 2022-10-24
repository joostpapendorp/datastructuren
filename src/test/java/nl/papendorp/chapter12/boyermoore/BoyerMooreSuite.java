package nl.papendorp.chapter12.boyermoore;

import nl.papendorp.chapter12.PatternMatcher;
import nl.papendorp.chapter12.PatternMatchingSuite;
import org.junit.jupiter.api.DisplayName;

@DisplayName( "The Boyer Moore algorithm should" )
public class BoyerMooreSuite
		extends PatternMatchingSuite
{
	@Override
	protected PatternMatcher sut()
	{
		return new BoyerMoore();
	}
}
