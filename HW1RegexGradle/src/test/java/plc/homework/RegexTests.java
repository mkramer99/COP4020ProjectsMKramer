package plc.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Contains JUnit tests for {@link Regex}. A framework of the test structure 
 * is provided, you will fill in the remaining pieces.
 *
 * To run tests, either click the run icon on the left margin, which can be used
 * to run all tests or only a specific test. You should make sure your tests are
 * run through IntelliJ (File > Settings > Build, Execution, Deployment > Build
 * Tools > Gradle > Run tests using <em>IntelliJ IDEA</em>). This ensures the
 * name and inputs for the tests are displayed correctly in the run window.
 */
public class RegexTests {

    /**
     * This is a parameterized test for the {@link Regex#EMAIL} regex. The
     * {@link ParameterizedTest} annotation defines this method as a
     * parameterized test, and {@link MethodSource} tells JUnit to look for the
     * static method {@link #testEmailRegex()}.
     *
     * For personal preference, I include a test name as the first parameter
     * which describes what that test should be testing - this is visible in
     * IntelliJ when running the tests (see above note if not working).
     */
    @ParameterizedTest
    @MethodSource
    public void testEmailRegex(String test, String input, boolean success) {
        test(input, Regex.EMAIL, success);
    }

    /**
     * This is the factory method providing test cases for the parameterized
     * test above - note that it is static, takes no arguments, and has the same
     * name as the test. The {@link Arguments} object contains the arguments for
     * each test to be passed to the function above.
     */
    public static Stream<Arguments> testEmailRegex() {
        return Stream.of(
                Arguments.of("Alphanumeric", "thelegend27@gmail.com", true),
                Arguments.of("UF Domain", "otherdomain@ufl.edu", true),
                Arguments.of("All Characters", "thelegend@gmail.com", true),
                Arguments.of("All digits", "27@gmail.com", true),
                Arguments.of("All Uppercase", "TIMMYJ@ufl.edu", true),
                Arguments.of("Missing Domain Dot", "missingdot@gmailcom", false),
                Arguments.of("Symbols", "symbols#$%@gmail.com", false),
                Arguments.of("Missing Username", "@gmail.com", false),
                Arguments.of("Missing Domain", "missingdomain@", false),
                Arguments.of("Missing @", "usernamedomain.com", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testEvenStringsRegex(String test, String input, boolean success) {
        test(input, Regex.EVEN_STRINGS, success);
    }

    public static Stream<Arguments> testEvenStringsRegex() {
        return Stream.of(
                //what has ten letters and starts with gas?
                Arguments.of("10 Characters", "automobile", true),
                Arguments.of("14 Characters", "i<3pancakes10!", true),
                Arguments.of("16 Characters", "whoisthis?hello?", true),
                Arguments.of("18 Characters", "nobodyknowstheclue", true),
                Arguments.of("20 Characters", "bananablueberrycakes", true),
                Arguments.of("2 Characters", "it", false),
                Arguments.of("6 Characters", "6chars", false),
                Arguments.of("13 Characters", "i<3pancakes9!", false),
                Arguments.of("15 Characters", "whatdidyousay??", false),
                Arguments.of("23 Characters", "joeboelikesboats8923458", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testIntegerListRegex(String test, String input, boolean success) {
        test(input, Regex.INTEGER_LIST, success);
    }

    public static Stream<Arguments> testIntegerListRegex() {
        return Stream.of(
                Arguments.of("Single Element", "[1]", true),
                Arguments.of("Multiple Elements", "[1,2,3]", true),
                Arguments.of("Many Elements", "[1,2,3,4,5,6,7,8,9]", true),
                Arguments.of("Repeating Elements", "[1,1,1,1,1,1]", true),
                Arguments.of("Double digit integers", "[11,12,13]", true),
                Arguments.of("No Element", "[]", false),
                Arguments.of("Leftover comma", "[1,2,3,]", false),
                Arguments.of("Missing Brackets", "1,2,3", false),
                Arguments.of("Missing Commas", "[1 2 3]", false),
                Arguments.of("Beginning comma", "[,2,3,4]", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testNumberRegex(String test, String input, boolean success) {
        test(input, Regex.NUMBER, success);
    }

    public static Stream<Arguments> testNumberRegex() {
        return Stream.of(
                Arguments.of("One digit", "1", true),
                Arguments.of("Many digits", "393730572", true),
                Arguments.of("Number with decimal", "000.222", true),
                Arguments.of("Negative number without decimal", "-1", true),
                Arguments.of("Positive number with decimal", "+393730.572", true),
                Arguments.of("Number with one digit after decimal", "39.0", true),
                Arguments.of("Number with leading decimal", ".000222", false),
                Arguments.of("Number with trailing decimal", "100.", false),
                Arguments.of("Negative number with trailing decimal", "-393730572.", false),
                Arguments.of("Postive number with leading decimal", "+.000222", false),
                Arguments.of("Leading decimal with one digit", ".5", false),
                Arguments.of("Trailing decimal with one digit", "5.", false)
                );
    }

    @ParameterizedTest
    @MethodSource
    public void testStringRegex(String test, String input, boolean success) {
        test(input, Regex.STRING, success);
    }

    public static Stream<Arguments> testStringRegex() {
        return Stream.of(
                Arguments.of("Empty String", "\"\"", true),
                Arguments.of("No Escape", "\"Hello, World!\"", true),
                Arguments.of("Escape \t", "\"1\\t2\"", true),
                Arguments.of("Escape \b", "\"Hello\b\"", true),
                Arguments.of("Escape \n", "\"Good Day\n\"", true),
                Arguments.of("Unterminated", "unterminated", false),
                Arguments.of("Invalid Escape", "invalid\\escape", false),
                Arguments.of("Missing End Quote", "\"Pancakes", false),
                Arguments.of("Invalid Escapes", "another\\\\invalid\\\\escape", false),
                Arguments.of("", "", false)
        );
    }

    /**
     * Asserts that the input matches the given pattern. This method doesn't do
     * much now, but you will see this concept in future assignments.
     */
    private static void test(String input, Pattern pattern, boolean success) {
        Assertions.assertEquals(success, pattern.matcher(input).matches());
    }

}
