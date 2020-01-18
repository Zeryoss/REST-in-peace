package fr.univlorraine.gheintz.RESTinpeace.dao;

import com.google.common.collect.Lists;
import fr.univlorraine.gheintz.RESTinpeace.entity.Grave;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraveRepositorySearchImpl implements GraveRepositorySearch {

    private static final int SCORE_NO_MATCH = -2;
    private static final int SCORE_WHITE_SPACE = -1;
    private static final int SCORE_IDENTICAL_BUT_CAPITAL_AND_ACCENTED = 1;
    private static final int SCORE_IDENTICAL_BUT_ACCENTED = 2;
    private static final int SCORE_IDENTICAL_BUT_CAPITAL = 3;
    private static final int SCORE_IDENTICAL = 4;

    private static final Pattern DIACRITICS_AND_FRIENDS = Pattern.compile("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+");

    private final GraveRepositoryBasic graveRepositoryBasic;

    public GraveRepositorySearchImpl(GraveRepositoryBasic graveRepositoryBasic) {
        this.graveRepositoryBasic = graveRepositoryBasic;
    }


    @Override
    public List<Grave> search(String searchString) {

        List<Grave> allGraves = Lists.newArrayList(graveRepositoryBasic.findAll());

        if (StringUtils.isBlank(searchString) || allGraves.isEmpty()) {
            return new ArrayList<>();
        }

        List<Map.Entry<Grave, Integer>> graveByScore = new ArrayList<>();

        for (Grave grave : allGraves) {
            int score = getScore(grave, searchString);
            if (score != SCORE_NO_MATCH) {
                graveByScore.add(new AbstractMap.SimpleImmutableEntry<>(grave, score));
            }
        }

        graveByScore.sort(Collections.reverseOrder(Comparator.comparing(Map.Entry::getValue)));

        return graveByScore
                .stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public int getScore(Grave grave, String searchString) {

        int max = SCORE_NO_MATCH;
        String graveStr = grave.toString();
        if (StringUtils.isBlank(searchString)) {
            return SCORE_NO_MATCH;
        }
        while (graveStr.length() >= searchString.length()) {
            int compare = searchWordInString(graveStr, searchString);
            max = Math.max(compare, max);
            graveStr = graveStr.substring(1);
        }
        return max;
    }

    /**
     * @param haystack the string in which we are looking for the needle
     * @param needle the word we are looking for
     * @return the search's score
     */
    private static int searchWordInString(String haystack, String needle) {
        return searchWordInString(haystack, needle, 0);
    }

    /**
     * @param haystack the string in which we are looking for the needle
     * @param needle the word we are looking for
     * @param score the current score
     * @return the search's score
     */
    private static int searchWordInString(String haystack, String needle, int score) {
        if ((StringUtils.isBlank(haystack) || StringUtils.isBlank(needle)) && score == 0) {
            return -2;
        }
        if (needle.length() == 0) {
            return score;
        } else {
            int compare = compareTwoLetters(haystack.charAt(0), needle.charAt(0));
            return  compare == -2 ? -2 : searchWordInString(haystack.substring(1), needle.substring(compare == -1 ? 0 : 1), score + compare);
        }
    }

    /**
     * @param referenceLetter the reference letter
     * @param searchLetter the letter to compare with the reference
     * @return score
     */
    private static int compareTwoLetters(char referenceLetter, char searchLetter) {
        if (referenceLetter == ' ') {
            return SCORE_WHITE_SPACE;
        } else if (searchLetter == referenceLetter) {
            return SCORE_IDENTICAL;
        } else if (searchLetter == Character.toLowerCase(referenceLetter)) {
            return SCORE_IDENTICAL_BUT_CAPITAL;
        } else if (searchLetter == normalize(referenceLetter+"").charAt(0)) {
            return SCORE_IDENTICAL_BUT_ACCENTED;
        } else if (searchLetter == normalize(referenceLetter+"").toLowerCase().charAt(0))  {
            return SCORE_IDENTICAL_BUT_CAPITAL_AND_ACCENTED;
        }

        return SCORE_NO_MATCH;
    }

    /**
     * @param str string containing accents
     * @return normalized string
     */
    private static String normalize(String str) {
        if (!StringUtils.isBlank(str)) {
            str = Normalizer.normalize(str, Normalizer.Form.NFD);
            str = DIACRITICS_AND_FRIENDS.matcher(str).replaceAll("");
        }
        return str;
    }
}
