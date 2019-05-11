package piglatin;

import java.util.HashSet;
import java.util.Set;

import javax.swing.SpringLayout.Constraints;

public class PigLatin {

	private static String conStr = "bcdfghjklmnprstvwxyz";
	private static String vowStr = "aeiou";

	private static Set<Character> validCharacters;
	private static Set<Character> vowels;
	private static Set<Character> consonants;
	private static Set<Character> vowelsUp;
	private static Set<Character> consonantsUp;

	static {

		vowels = new HashSet<>();
		for (char letter : vowStr.toCharArray()) {
			vowels.add(letter);
		}

		vowelsUp = new HashSet<>();
		for (char letter : vowStr.toUpperCase().toCharArray()) {
			vowelsUp.add(letter);
		}

		consonants = new HashSet<>();
		for (char letter : conStr.toCharArray()) {
			consonants.add(letter);
		}

		consonantsUp = new HashSet<>();
		for (char letter : conStr.toUpperCase().toCharArray()) {
			consonantsUp.add(letter);
		}

		validCharacters = new HashSet<>();
		validCharacters.add(' ');
		validCharacters.addAll(vowels);
		validCharacters.addAll(consonants);
		validCharacters.addAll(vowelsUp);
		validCharacters.addAll(consonantsUp);

	}

	private String phrase;

	public PigLatin(String phrase) throws InvalidPhraseException {
		if (containsSubsequentSpaces(phrase) || startsOrEndsWithSpace(phrase) 
				|| containInvalidChar(phrase) || !validationWords(getWords(phrase))) {
			throw new InvalidPhraseException();
		}
		this.phrase = phrase;
	}

	private boolean containInvalidChar(String phrase) {
		for (int i = 0; i < phrase.length(); i++) {
			char character = phrase.charAt(i);
			if (!validCharacters.contains(character)) {
				return true;
			}
		}
		return false;
	}

	private boolean startsOrEndsWithSpace(String phrase) {
		return phrase.startsWith(" ") || phrase.endsWith(" ");
	}

	private boolean containsSubsequentSpaces(String phrase) {
		int count = 0;
		for (int i = 0; i < phrase.length(); i++) {
			char character = phrase.charAt(i);
			if (character == ' ') {
				++count;
				if (count > 1) {
					return true;
				}
			} else {
				count = 0;
			}
		}
		return false;
	}

	public String getPhrase() {
		return phrase;
	}

	public String translate() throws InvalidPhraseException {
		String[] words = getWords(phrase);
		StringBuilder builder = new StringBuilder();
		// StringBuilder. Rewrite this function with map
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			String translateWord = translateWord(word);
			builder.append(translateWord);
			if (i < words.length - 1) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}

	private String translateWord(String word) {
		if (startsWithAVowel(word) || startsWithXR(word)) {
			if (allTheLettersUpper(word))
				return word + "AY";
			return word + "ay";
		} else {
			return startsWithAFewConsonants(word);
		}
	}

	private boolean allTheLettersUpper(String word) {
		for (int i = 0; i < word.length(); i++) {
			char character = word.charAt(i);
			if (!vowelsUp.contains(character) && !consonantsUp.contains(character)) {
				return false;
			}
		}
		return true;
	}

	private String[] getWords(String phrase) throws InvalidPhraseException {
		return phrase.split("\\s");
	}

	private boolean validationWords(String[] words) {
		for (int i = 0; i < words.length; i++) {
			if (!validateWord(words[i]))
				return false;
		}
		return true;
	}

	private boolean validateWord(String word) {
		int startChar = 0;
		if (word.length() > 1) {
			if (charInUppercase(word.charAt(0))) {
				startChar = 1;
			}
			char fstChar = word.charAt(startChar);
			for (int i = 1; i < word.length(); i++) {
				if ((charInUppercase(fstChar) && !charInUppercase(word.charAt(i)))
						|| (!charInUppercase(fstChar) && charInUppercase(word.charAt(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean charInUppercase(char fstChar) {
		return vowelsUp.contains(fstChar) || consonantsUp.contains(fstChar);
	}

	private String startsWithAFewConsonants(String word) {
		String substringOfConsonants = "";
		String substr = null;
		String changedWord = null;
		for (int i = 0; i < word.length(); i++) {
			char nextLetter = word.charAt(i);
			if (consonants.contains(nextLetter) || consonantsUp.contains(nextLetter)) {
				substringOfConsonants = substringOfConsonants + nextLetter;
			} else {
				substr = word.substring(i);
				changedWord = substr + substringOfConsonants;
				break;
			}
		}
		if (onlyFirstLetterUpper(word)) {
			return makeFirstLetterUpper(changedWord) + "ay";
		} else if (allTheLettersUpper(word))
			changedWord = changedWord + "AY";
		else
			changedWord = changedWord + "ay";
		return changedWord;
	}

	private boolean onlyFirstLetterUpper(String word) {
		if (consonantsUp.contains(word.charAt(0))) {
			for (int i = 1; i < word.length(); i++) {
				char character = word.charAt(i);
				if (!vowels.contains(character) && !consonants.contains(character)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	private String makeFirstLetterUpper(String changedWord) {
		String firstChar = changedWord.substring(0, 1).toUpperCase();
		changedWord = firstChar + changedWord.substring(1).toLowerCase();
		return changedWord;
	}

	private boolean startsWithAVowel(String phrase) {
		char firstChar = phrase.charAt(0);
		return vowels.contains(firstChar) || vowelsUp.contains(firstChar);
	}

	private boolean startsWithXR(String word) {
		String xrStr = word.substring(0, 2);
		return xrStr.equals("xr") || xrStr.equals("XR") || xrStr.equals("Xr");
	}
}
