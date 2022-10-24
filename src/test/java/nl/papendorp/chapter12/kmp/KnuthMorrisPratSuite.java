package nl.papendorp.chapter12.kmp;

import nl.papendorp.chapter12.PatternMatcher;
import nl.papendorp.chapter12.PatternMatchingSuite;

public class KnuthMorrisPratSuite extends PatternMatchingSuite
{
	@Override
	protected PatternMatcher sut()
	{
		return new KnuthMorrisPratt();
	}
}
