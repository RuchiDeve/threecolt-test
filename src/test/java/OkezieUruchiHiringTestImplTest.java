import com.threecolts.hiring.OkezieUruchiHiringTestImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class OkezieUruchiHiringTestImplTest {

    private final OkezieUruchiHiringTestImpl hiringTest = new OkezieUruchiHiringTestImpl();

    @ParameterizedTest
    @MethodSource("testDataForCountUniqueUrls")
    public void testCountUniqueUrls(List<String> urls, int expectedUniqueUrlsCount) {
        int actualUniqueUrlsCount = hiringTest.countUniqueUrls(urls);
        Assertions.assertEquals(expectedUniqueUrlsCount, actualUniqueUrlsCount, "countUniqueUrls() should return the correct number of unique normalized URLs.");
    }

    @ParameterizedTest
    @MethodSource("testDataForCountUniqueUrlsPerTopLevelDomain")
    public void testCountUniqueUrlsPerTopLevelDomain(List<String> urls, Map<String, Integer> expectedMapResult) {
        Map<String, Integer> actualMapResult = hiringTest.countUniqueUrlsPerTopLevelDomain(urls);
        Assertions.assertEquals(expectedMapResult, actualMapResult, "expected and actual Maps do not match");
    }

    private static Stream<Object[]> testDataForCountUniqueUrls() {
        return Stream.of(
            // Test cases with input URLs and expected unique count
            new Object[]{Collections.singletonList("https://example.com"), 1},
            new Object[]{Arrays.asList("https://example.com", "https://example.com/"), 1},
            new Object[]{Arrays.asList("https://example.com", "http://example.com"), 2},
            new Object[]{Arrays.asList("https://example.com?", "https://example.com"), 1},
            new Object[]{Arrays.asList("https://example.com?a=1&b=2", "https://example.com?b=2&a=1"), 1}
        );
    }

    private static Stream<Object[]> testDataForCountUniqueUrlsPerTopLevelDomain() {
        Map<String, Integer> testMap1 = new HashMap<>();
        testMap1.put("example.com", 1);

        Map<String, Integer> testMap2 = new HashMap<>();
        testMap2.put("example.com", 2);

        return Stream.of(
                // Test cases with input URLs and expected map
                new Object[]{Collections.singletonList("https://example.com"), testMap1},
                new Object[]{Arrays.asList("https://example.com", "https://subdomain.example.com"), testMap2}
        );
    }
}
