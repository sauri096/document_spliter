package org.example.domain.model;

public record SplitRange(int start, int end) {
    public static SplitRange of(int s, int e) {
        return new SplitRange(s, e);
    }
    public int endOrStart() {
        return end == 0 ? start : end;
    }
}