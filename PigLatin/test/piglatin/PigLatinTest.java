package piglatin;

import static org.junit.Assert.*;

import org.junit.Test;

public class PigLatinTest {

	@Test
	public void testPhrase() throws Exception {
		PigLatin pigLatin = new PigLatin("a yellow bird");
		String phrase = pigLatin.getPhrase();
		assertEquals("a yellow bird", phrase);
	}

	@Test
	public void testPhraseWithSingleWord() throws Exception {
		PigLatin pigLatin = new PigLatin("yellow");
		String phrase = pigLatin.getPhrase();
		assertEquals("yellow", phrase);
	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithDoubleSpaces() throws Exception {
		new PigLatin("a  yellow bird");
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithWithSpaceInBeginning() throws Exception {
		new PigLatin(" a yellow bird");
	}

	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithWithSpaceAtTheEnd() throws Exception {
		new PigLatin("a yellow bird ");
	}
	
	//not finished => containInvalidChar()
	@Test(expected = InvalidPhraseException.class)
	public void testPhraseWithWithInvalidChar() throws Exception {
		new PigLatin("a, yellow bird");
	}
	
	@Test
	public void testTranslateWordsStartingWithA() throws Exception {
		PigLatin pigLatin = new PigLatin("apple");
		String translation = pigLatin.translate();
		assertEquals("appleay", translation);
	}
	
	@Test
	public void testTranslateWordsStartingWithU() throws Exception {
		PigLatin pigLatin = new PigLatin("umbrella");
		String translation = pigLatin.translate();
		assertEquals("umbrellaay", translation);
	}
	
	// other vowels
	
	//then
	@Test
	public void testTranslateWordsStartingWithB() throws Exception {
		PigLatin pigLatin = new PigLatin("bird");
		String translation = pigLatin.translate();
		assertEquals("irdbay", translation);
	}
	
	@Test
	public void testTranslateWordsStartingWithC() throws Exception {
		PigLatin pigLatin = new PigLatin("curl");
		String translation = pigLatin.translate();
		assertEquals("urlcay", translation);
	}
	
	@Test
	public void testTranslateWordsStartingWithG() throws Exception {
		PigLatin pigLatin = new PigLatin("game");
		String translation = pigLatin.translate();
		assertEquals("amegay", translation);
	}
	
	@Test
	public void testTranslateWordsStartingWithZ() throws Exception {
		PigLatin pigLatin = new PigLatin("zoom");
		String translation = pigLatin.translate();
		assertEquals("oomzay", translation);
	}
	
	
	@Test
	public void testTranslatePhrase() throws Exception {
		PigLatin pigLatin = new PigLatin("a yellow bird");
		String translation = pigLatin.translate();
		assertEquals("aay ellowyay irdbay", translation);
	}
	
	@Test
	public void testTranslateWordWithTHR() throws Exception {
		PigLatin pigLatin = new PigLatin("throw");
		String translation = pigLatin.translate();
		assertEquals("owthray", translation);
	}
	
	@Test
	public void testTranslateWordWithCH() throws Exception {
		PigLatin pigLatin = new PigLatin("chair");
		String translation = pigLatin.translate();
		assertEquals("airchay", translation);
	}
	
	@Test
	public void testTranslateWordStartingWith_xr() throws Exception {
		PigLatin pigLatin = new PigLatin("xray");
		String translation = pigLatin.translate();
		assertEquals("xrayay", translation);
	}
	
	@Test
	public void testTranslateUppercaseWordStartsWithA() throws Exception {
		PigLatin pigLatin = new PigLatin("APPLE");
		String translation = pigLatin.translate();
		assertEquals("APPLEAY", translation);
	}
	
	@Test
	public void testTranslateUppercaseWordWithC() throws Exception {
		PigLatin pigLatin = new PigLatin("CAREER");
		String translation = pigLatin.translate();
		assertEquals("AREERCAY", translation);
	}
	
	@Test
	public void testTranslateUppercaseWordWithTR() throws Exception {
		PigLatin pigLatin = new PigLatin("TRACK");
		String translation = pigLatin.translate();
		assertEquals("ACKTRAY", translation);
	}
	
	@Test
	public void testTranslateUppercaseWordWithXR() throws Exception {
		PigLatin pigLatin = new PigLatin("XRAM");
		String translation = pigLatin.translate();
		assertEquals("XRAMAY", translation);
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testTranslateUppercaseWordWithXRError() throws Exception {
		new PigLatin("XRinm");
	}
	
	@Test
	public void testTranslateUppercaseWordWithXr() throws Exception {
		PigLatin pigLatin = new PigLatin("Xram");
		String translation = pigLatin.translate();
		assertEquals("Xramay", translation);
	}
	
	@Test
	public void testTranslateFirstLetterBInUppercase() throws Exception {
		PigLatin pigLatin = new PigLatin("Bird");
		String translation = pigLatin.translate();
		assertEquals("Irdbay", translation);
	}
	
	@Test
	public void testTranslateFirstLetterIsUpper() throws Exception {
		PigLatin pigLatin = new PigLatin("Bird");
		String translation = pigLatin.translate();
		assertEquals("Irdbay", translation);
	}
	
	@Test
	public void testTranslateOneLetter() throws Exception {
		PigLatin pigLatin = new PigLatin("A");
		String translation = pigLatin.translate();
		assertEquals("AAY", translation);
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testTranslateDifferentLettersInTheWord01() throws Exception {
		new PigLatin("birD");
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testTranslateDifferentLettersInTheWord02() throws Exception {
		new PigLatin("BiRd");
	}
	
	@Test
	public void testTranslatePhrase01() throws Exception {
		PigLatin pigLatin = new PigLatin("A marko");
		String translation = pigLatin.translate();
		assertEquals("AAY arkomay", translation);
	}
	
	@Test
	public void testTranslatePhrase02() throws Exception {
		PigLatin pigLatin = new PigLatin("A yellow Bird");
		String translation = pigLatin.translate();
		assertEquals("AAY ellowyay Irdbay", translation);
	}

	@Test
	public void testTranslatePhrase03() throws Exception {
		PigLatin pigLatin = new PigLatin("A YELLOW Bird");
		String translation = pigLatin.translate();
		assertEquals("AAY ELLOWYAY Irdbay", translation);
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testTranslatePhrase04() throws Exception {
		new PigLatin("A yelLLow Bird");
	}
	
	@Test(expected = InvalidPhraseException.class)
	public void testTranslatePhrase05() throws Exception {
		new PigLatin("THRow");
	}

}
