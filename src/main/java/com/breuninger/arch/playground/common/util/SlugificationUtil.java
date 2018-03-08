package com.breuninger.arch.playground.common.util;

import static java.util.Locale.ENGLISH;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public final class SlugificationUtil {

  private static final Map<Character, String> CHAR_MAP_UMLAUTE;
  private static final Map<Character, String> CHAR_MAP_FOREIGN_CHARS;

  static {
    CHAR_MAP_UMLAUTE = ImmutableMap.<Character, String>builder()
      .put('ä', "ae")
      .put('ö', "oe")
      .put('ü', "ue")
      .put('Ä', "Ae")
      .put('Ö', "Oe")
      .put('Ü', "Ue")
      .put('ß', "ss")
      .build();
    CHAR_MAP_FOREIGN_CHARS = ImmutableMap.<Character, String>builder()
      .put('à', "a")
      .put('á', "a")
      .put('â', "a")
      .put('ã', "a")
      .put('ä', "a")
      .put('å', "a")
      .put('ā', "a")
      .put('ą', "a")
      .put('ă', "a")
      .put('ç', "c")
      .put('ć', "c")
      .put('č', "c")
      .put('ĉ', "c")
      .put('ċ', "c")
      .put('ď', "d")
      .put('đ', "d")
      .put('ð', "d")
      .put('è', "e")
      .put('é', "e")
      .put('ê', "e")
      .put('ë', "e")
      .put('ē', "e")
      .put('ę', "e")
      .put('ě', "e")
      .put('ĕ', "e")
      .put('ė', "e")
      .put('ƒ', "f")
      .put('ſ', "f")
      .put('ĝ', "g")
      .put('ğ', "g")
      .put('ġ', "g")
      .put('ģ', "g")
      .put('ĥ', "h")
      .put('ħ', "h")
      .put('ì', "i")
      .put('í', "i")
      .put('î', "i")
      .put('ï', "i")
      .put('ī', "i")
      .put('ĩ', "i")
      .put('ĭ', "i")
      .put('į', "i")
      .put('ı', "i")
      .put('ķ', "k")
      .put('ĸ', "k")
      .put('ł', "l")
      .put('ľ', "l")
      .put('ĺ', "l")
      .put('ļ', "l")
      .put('ŀ', "l")
      .put('ñ', "n")
      .put('ń', "n")
      .put('ň', "n")
      .put('ņ', "n")
      .put('ŉ', "n")
      .put('ŋ', "n")
      .put('ò', "o")
      .put('ó', "o")
      .put('ô', "o")
      .put('õ', "o")
      .put('ö', "o")
      .put('ø', "o")
      .put('ō', "o")
      .put('ő', "o")
      .put('ŏ', "o")
      .put('œ', "o")
      .put('Þ', "p")
      .put('þ', "p")
      .put('ŕ', "r")
      .put('ř', "r")
      .put('ŗ', "r")
      .put('ś', "s")
      .put('š', "s")
      .put('ş', "s")
      .put('ŝ', "s")
      .put('ș', "s")
      .put('ť', "t")
      .put('ţ', "t")
      .put('ŧ', "t")
      .put('ț', "t")
      .put('ù', "u")
      .put('ú', "u")
      .put('û', "u")
      .put('ü', "u")
      .put('ū', "u")
      .put('ů', "u")
      .put('ű', "u")
      .put('ŭ', "u")
      .put('ũ', "u")
      .put('ų', "u")
      .put('ŵ', "w")
      .put('ý', "y")
      .put('ÿ', "y")
      .put('ŷ', "y")
      .put('ž', "z")
      .put('ż', "z")
      .put('ź', "z")
      .put('æ', "ae")
      .put('À', "A")
      .put('Á', "A")
      .put('Â', "A")
      .put('Ã', "A")
      .put('Ä', "A")
      .put('Å', "A")
      .put('Ā', "A")
      .put('Ą', "A")
      .put('Ă', "A")
      .put('Ç', "C")
      .put('Ć', "C")
      .put('Č', "C")
      .put('Ĉ', "C")
      .put('Ċ', "C")
      .put('Ď', "D")
      .put('Đ', "D")
      .put('Ð', "D")
      .put('È', "E")
      .put('É', "E")
      .put('Ê', "E")
      .put('Ë', "E")
      .put('Ē', "E")
      .put('Ę', "E")
      .put('Ě', "E")
      .put('Ĕ', "E")
      .put('Ė', "E")
      .put('Ĝ', "G")
      .put('Ğ', "G")
      .put('Ġ', "G")
      .put('Ģ', "G")
      .put('Ĥ', "H")
      .put('Ħ', "H")
      .put('Ì', "I")
      .put('Í', "I")
      .put('Î', "I")
      .put('Ï', "I")
      .put('Ī', "I")
      .put('Ĩ', "I")
      .put('Ĭ', "I")
      .put('Į', "I")
      .put('İ', "I")
      .put('Ĵ', "J")
      .put('Ķ', "K")
      .put('Ł', "L")
      .put('Ľ', "L")
      .put('Ĺ', "L")
      .put('Ļ', "L")
      .put('Ŀ', "L")
      .put('Ñ', "N")
      .put('Ń', "N")
      .put('Ň', "N")
      .put('Ņ', "N")
      .put('Ŋ', "N")
      .put('Ò', "O")
      .put('Ó', "O")
      .put('Ô', "O")
      .put('Õ', "O")
      .put('Ö', "O")
      .put('Ø', "O")
      .put('Ō', "O")
      .put('Ő', "O")
      .put('Ŏ', "O")
      .put('Ŕ', "R")
      .put('Ř', "R")
      .put('Ŗ', "R")
      .put('Ś', "S")
      .put('Š', "S")
      .put('Ş', "S")
      .put('Ŝ', "S")
      .put('Ș', "S")
      .put('Ù', "U")
      .put('Ú', "U")
      .put('Û', "U")
      .put('Ü', "U")
      .put('Ū', "U")
      .put('Ů', "U")
      .put('Ű', "U")
      .put('Ŭ', "U")
      .put('Ũ', "U")
      .put('Ų', "U")
      .put('Ŵ', "W")
      .put('Ý', "Y")
      .put('Ŷ', "Y")
      .put('Ÿ', "Y")
      .put('Ź', "Z")
      .put('Ž', "Z")
      .put('Ż', "Z")
      // special characters
      .put('+', "plus")
      .build();
  }

  private static String noAccents(final String string) {
    final StringBuilder builder = new StringBuilder(string.length());
    for (final char letter : Normalizer.normalize(string, Form.NFKC).toCharArray()) {
      if (CHAR_MAP_UMLAUTE.containsKey(letter)) {
        builder.append(CHAR_MAP_UMLAUTE.get(letter));
      } else if (CHAR_MAP_FOREIGN_CHARS.containsKey(letter)) {
        builder.append(CHAR_MAP_FOREIGN_CHARS.get(letter));
      } else {
        builder.append(letter);
      }
    }
    return builder.toString();
  }

  public static String slugify(final String string) {
    if (string == null) {
      return null;
    }
    return noAccents(string)
      // Get rid of apostrophes
      .replaceAll("([a-z])'s([^a-z])", "$1s$2")
      .replaceAll("[^\\w]", "-")
      .replaceAll("-{2,}", "-")
      // Get rid of any "-" at the start and end
      .replaceAll("-$", "")
      .replaceAll("^-", "")
      .toLowerCase(ENGLISH);
  }
}
