package org.example.application;

import org.example.domain.SplitStrategy;
import org.example.domain.model.DocumentType;
import org.example.domain.model.SplitRange;
import org.example.infrastructure.pdf.PdfBoxSplitStrategy;
import org.example.infrastructure.word.DocxSplitStrategy;

import java.io.File;
import java.util.List;

public class SplitService {

    public void split(String inputFile, String rangesExpr) throws Exception {
        DocumentType type = detectType(inputFile);
        List<SplitRange> ranges = RangeParser.parse(rangesExpr);

        String outputDir = "output";
        new File(outputDir).mkdirs();

        SplitStrategy strategy = switch (type) {
            case PDF -> new PdfBoxSplitStrategy();
            case DOCX -> new DocxSplitStrategy();
        };

        strategy.split(inputFile, outputDir, ranges);
        System.out.println("🎯 Splitting completed!");
    }

    private DocumentType detectType(String filePath) {
        if (filePath.toLowerCase().endsWith(".pdf")) return DocumentType.PDF;
        if (filePath.toLowerCase().endsWith(".docx")) return DocumentType.DOCX;
        throw new IllegalArgumentException("Unsupported file type");
    }
}