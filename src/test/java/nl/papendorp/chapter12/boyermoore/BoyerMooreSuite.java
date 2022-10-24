package nl.papendorp.chapter12.boyermoore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName( "The Boyer Moore algorithm should" )
public class BoyerMooreSuite
{
	private BoyerMoore sut;

	@BeforeEach
	void setUp()
	{
		sut = new BoyerMoore();
	}

	@Test
	@DisplayName( "not find any pattern in an empty text" )
	void dont_find_patterns_in_empty_string()
	{
		dontFind( "", "a" );
	}

	@Test
	@DisplayName( "find empty pattern at index 0 in any text" )
	void empty_pattern_prefixes_any_text()
	{
		findAt( "", "", 0 );
	}

	@Test
	@DisplayName( "find full text at index 0" )
	void full_text_is_its_own_prefix()
	{
		findAt( "a", "a", 0 );
	}

	@Test
	@DisplayName( "find a prefix of the text at index 0" )
	void all_prefixes_start_at_zero()
	{
		findAt( "Xa", "X", 0 );
		findAt( "Xab", "X", 0 );
		findAt( "XYa", "XY", 0 );
		findAt( "XYab", "XY", 0 );
	}

	@Test
	@DisplayName( "find a suffix of the text at index at text length - pattern length" )
	void all_suffixes_start_at_the_end_of_the_text_minus_the_suffix_length()
	{
		findAt( "aX", "X", 1 );
		findAt( "aXY", "XY", 1 );
		findAt( "abX", "X", 2 );
		findAt( "abXY", "XY", 2 );
	}

	@Test
	@DisplayName( "not find a pattern that the text does not contain" )
	void dont_find_pattern_not_in_text()
	{
		dontFind( "abc", "X" );
		dontFind( "abc", "XY" );
		dontFind( "ab", "XYZ" );
	}

	@Test
	@DisplayName( "find patterns embedded in the text at their starting index" )
	void embedded_patterns_start_at_first_index()
	{
		findAt( "aXb", "X", 1 );
		findAt( "aXbc", "X", 1 );
		findAt( "abXc", "X", 2 );
		findAt( "aXYb", "XY", 1 );
		findAt( "aXXbc", "XX", 1 );
		findAt( "abXXc", "XX", 2 );
		findAt( "abcXYZdef", "XYZ", 3 );
	}

	@Test
	@DisplayName( "only find complete matches" )
	void only_find_complete_matches()
	{
		findAt( "XaXY", "XY", 2 );
		findAt( "aXYXYZ", "XYZ", 3 );
		findAt( "aZYXXYZ", "XYZ", 4 );
		findAt( "XYZabXY", "XYZ", 0 );
	}

	@Test
	@DisplayName( "find first occurrence of multiple matches" )
	void find_first_occurrence()
	{
		findAt( "XYZaXYZ", "XYZ", 0 );
		findAt( "abcXYZXYZ", "XYZ", 3 );
		findAt( "abcXXXXXXX", "XX", 3 );
	}

	private void findAt( final String text, final String pattern, final int index )
	{
		find( text, pattern, Optional.of( index ) );
	}

	private void dontFind( final String text, final String pattern )
	{
		find( text, pattern, empty() );
	}

	private void find( final String text, final String pattern, final Optional< Integer > expected )
	{
		assertThat( sut.find( text, pattern ), is( expected ) );
	}
}
