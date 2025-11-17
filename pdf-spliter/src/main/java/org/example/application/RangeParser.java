package org.example.application;

import org.example.domain.model.SplitRange;

import java.util.*;

public class RangeParser {
    public static List<SplitRange> parse(String input) {
        List<SplitRange> out = new ArrayList<>();
        for (String token : input.split(",")) {
            token = token.trim();
            if (token.isEmpty()) continue;
            if (token.contains("-")) {
                String[] parts = token.split("-");
                out.add(SplitRange.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            } else {
                int v = Integer.parseInt(token);
                out.add(SplitRange.of(v, v));
            }
        }
        return out;
    }
}
