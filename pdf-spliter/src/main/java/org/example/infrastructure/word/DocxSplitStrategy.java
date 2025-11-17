package org.example.infrastructure.word;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.example.domain.SplitStrategy;
import org.example.domain.model.SplitRange;
import java.io.*;
import java.util.*;

public class DocxSplitStrategy implements SplitStrategy {

    @Override
    public void split(String inputFile, String outputDir, List<SplitRange> ranges) throws IOException {
        try (FileInputStream fis = new FileInputStream(inputFile);
             XWPFDocument doc = new XWPFDocument(fis)) {

            List<Integer> pageBreaks = new ArrayList<>();
            List<XWPFParagraph> paragraphs = doc.getParagraphs();

            // Detect page breaks
            for (int i = 0; i < paragraphs.size(); i++) {
                if (paragraphs.get(i).isPageBreak()) {
                    pageBreaks.add(i);
                }
            }
            pageBreaks.add(paragraphs.size());

            int startIdx = 0;
            int part = 1;
            for (Integer endIdx : pageBreaks) {
                XWPFDocument newDoc = new XWPFDocument();
                for (int i = startIdx; i < endIdx; i++) {
                    XWPFParagraph p = newDoc.createParagraph();
                    p.getCTP().set(paragraphs.get(i).getCTP().copy());
                }

                String outputFile = outputDir + "/split_" + part++ + ".docx";
                try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                    newDoc.write(fos);
                }
                startIdx = endIdx + 1;
                System.out.println("✅ Created: " + outputFile);
            }
        }
    }
}
