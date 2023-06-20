package io.github.tundeadetunji;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.github.tundeadetunji.General.stringToList;


/**
 * The type Checked difference.
 *
 * Example:
 * String a = "abc";
 * String b = "ade";
 * CheckedDifference checked = new CheckedDifference(a, b);
 * Terminology revolves around "but" and "and", so
 * start typing checked.a will generate (by intellisense) all methods with and
 * likewise checked.b will generate all methods with but
 */
public final class CheckedDifference {
    private final String first;
    private final String second;

    private String common = "";
    private String restOfFirst = "";
    private String restOfSecond = "";

    private List<Integer> linesCommon = new ArrayList<>();

    /**
     * Instantiates a new Checked difference.
     *
     * @param first  the initial string
     * @param second the string to compare with first
     * @throws AssertionError if first and/or second are/is not supplied
     */
    public CheckedDifference(String first, String second) {
        if ((first.isBlank() || first.isEmpty()) || (second.isBlank() || second.isEmpty()))
            throw new AssertionError("The string or the one to be compared with it not supplied!");

        this.first = first;
        this.second = second;
    }

    private boolean alreadyCalledGetCharacterDifference = false;

    private void getCharacterDifference() {
        if (andTheyAreTheSame()) return;

        StringBuilder commonBuilder = new StringBuilder();
        String thisIsShorter = butFirstIsLonger() && !butSecondIsLonger() ? second : first;

        for (int i = 0; i < thisIsShorter.length(); i++) {
            if (first.charAt(i) == second.charAt(i)) {
                commonBuilder.append(first.charAt(i));
            } else if (i == thisIsShorter.length()) {
                common = commonBuilder.toString();
                if (butFirstIsLonger()) {
                    restOfFirst = first.substring(i);
                } else {
                    restOfSecond = second.substring(i);
                }
                break;
            } else {
                common = commonBuilder.toString();
                restOfFirst = first.substring(i);
                restOfSecond = second.substring(i);
                break;
            }
        }
        common = commonBuilder.toString();
        alreadyCalledGetCharacterDifference = true;
    }

    private boolean alreadyCalledGetLineDifference = false;

    private void getLineDifference() {
        if (andTheyAreTheSame()) return;

        List<String> linesInFirst = stringToList(first);
        List<String> linesInSecond = stringToList(second);

        List<String> thisIsShorter = linesInFirst.size() > linesInSecond.size() ? linesInSecond : linesInFirst;

        for (int i = 0; i < thisIsShorter.size(); i++) {
            if (linesInFirst.get(i).equalsIgnoreCase(linesInSecond.get(i))) {
                linesCommon.add(i + 1);
            }
        }
        alreadyCalledGetLineDifference = true;
    }

    public boolean andTheyAreTheSame() {
        return first.equalsIgnoreCase(second);
    }

    public boolean butFirstIsLonger() {
        return first.length() > second.length();
    }

    public boolean butSecondIsLonger() {
        return second.length() > first.length();
    }

    /**
     * Checks if any text is common and captures the corresponding line numbers.
     *
     * @return the list of line numbers that contain common text
     */
    public List<Integer> andFoundTheseLinesInCommon() {
        if (!alreadyCalledGetLineDifference) getLineDifference();
        return Collections.unmodifiableList(linesCommon);
    }

    public boolean butFoundNothingInCommon() {
        if (andTheyAreTheSame()) return false;
        if (!alreadyCalledGetCharacterDifference) getCharacterDifference();
        return common.length() < 1;
    }

    /**
     * Finds common text, starting from the beginning.
     * If at any point it sees difference, it concludes.
     *
     * @return text that is found common
     */
    public String andFoundThisInCommon() {
        if (!alreadyCalledGetCharacterDifference) getCharacterDifference();
        return new StringBuilder().append(common).toString();
    }

    public String andFoundThisUniqueToFirst() {
        if (!alreadyCalledGetCharacterDifference) getCharacterDifference();
        return new StringBuilder().append(restOfFirst).toString();
    }

    public String andFoundThisUniqueToSecond() {
        if (!alreadyCalledGetCharacterDifference) getCharacterDifference();
        return new StringBuilder().append(restOfSecond).toString();
    }


    @Override
    protected void finalize() throws Throwable {
        throw new AssertionError();
    }
}
