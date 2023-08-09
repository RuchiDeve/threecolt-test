package com.threecolts.hiring;

import java.util.logging.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class OkezieUruchiHiringTestImpl implements HiringTest{

    private static final Logger logger = Logger.getLogger(OkezieUruchiHiringTestImpl.class.getName());

    public int countUniqueUrls(List<String> urls) {
        Set<String> uniqueUrls = new HashSet<>();
        for (String url : urls) {
            try {
                URI uri = new URI(url);
                String normalizedUrl = getNormalizedUrl(uri);
                if (normalizedUrl != null) {
                    uniqueUrls.add(normalizedUrl);
                }
            } catch (URISyntaxException e) {
                logger.warning("Ignoring malformed URL: " + url);
            }
        }
        return uniqueUrls.size();
    }

    public Map<String, Integer> countUniqueUrlsPerTopLevelDomain(List<String> urls) {
        Map<String, Integer> uniqueUrlsPerTopLevelDomain = new HashMap<>();

        urls.stream()
                .map(this::getTopLevelDomain)
                .filter(Objects::nonNull)
                .forEach(tld -> uniqueUrlsPerTopLevelDomain.put(tld, uniqueUrlsPerTopLevelDomain.getOrDefault(tld, 0) + 1));

        return uniqueUrlsPerTopLevelDomain;
    }

    private String getNormalizedUrl(URI uri) {
        String host = uri.getHost();
        if (host != null) {
            String path = uri.getPath();
            if (path == null || path.isEmpty() || path.equals("/")) {
                path = "";
            }
            String query = uri.getQuery();
            if (query == null || query.isEmpty()) {
                query = "";
            } else {
                String delimiter = "&";
                List<String> queryParams = Arrays.stream(query.split(delimiter))
                        .sorted()
                        .collect(Collectors.toList());
                query = "?" + String.join(delimiter, queryParams);
            }
            return uri.getScheme() + "://" + host + path + query;
        }
        return null;
    }

    private String getTopLevelDomain(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            if (host != null) {
                String[] parts = host.split("\\.");
                int length = parts.length;
                if (length >= 2) {
                    return parts[length - 2] + "." + parts[length - 1];
                }
            }
        } catch (URISyntaxException e) {
            logger.warning("Ignoring malformed URL: " + url);
        }
        return null;
    }
}
