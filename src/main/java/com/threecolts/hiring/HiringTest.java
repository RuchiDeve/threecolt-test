package com.threecolts.hiring;

import java.util.List;
import java.util.Map;

public interface HiringTest {
    /**
     * This function counts how many unique normalized valid URLs were passed to the function
     * <p>
     * Accepts a list of URLs
     * <p>
     * Example:
     * <p>
     * input: ['https://example.com']
     * output: 1
     * <p>
     * Notes:
     * - assume none of the URLs have authentication information (username, password).
     * <p>
     * Normalized URL:
     * - process in which a URL is modified and standardized: https://en.wikipedia.org/wiki/URL_normalization
     * <p>
     * For example.
     * These 2 urls are the same:
     * input: ["https://example.com", "https://example.com/"]
     * output: 1
     * <p>
     * These 2 are not the same:
     * input: ["https://example.com", "http://example.com"]
     * output 2
     * <p>
     * These 2 are the same:
     * input: ["https://example.com?", "https://example.com"]
     * output: 1
     * <p>
     * These 2 are the same:
     * input: ["https://example.com?a=1&b=2", "https://example.com?b=2&a=1"]
     * output: 1
     */
    int countUniqueUrls(List<String> urls);

    /**
     * This function counts how many unique normalized valid URLs were passed to the function per top level domain
     * <p>
     * A top level domain is a domain in the form of example.com. Assume all top level domains end in .com
     * subdomain.example.com is not a top level domain.
     * <p>
     * Accepts a list of URLs
     * <p>
     * Example:
     * <p>
     * input: ["https://example.com"]
     * output: Hash["example.com" => 1]
     * <p>
     * input: ["https://example.com", "https://subdomain.example.com"]
     * output: Hash["example.com" => 2]
     */
    Map<String, Integer> countUniqueUrlsPerTopLevelDomain(List<String> urls);
}
