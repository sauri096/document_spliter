package org.example.domain;

import org.example.domain.model.SplitRange;

import java.io.IOException;
import java.util.List;

public interface SplitStrategy {

    void split(String inputFile, String outputDir, List<SplitRange> ranges) throws IOException;

}