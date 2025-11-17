package org.example.infrastructure.pdf;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.example.domain.SplitStrategy;
import org.example.domain.model.SplitRange;
import java.io.*;
import java.util.List;

public class PdfBoxSplitStrategy implements SplitStrategy {
    @Override
    public void split(String inputFile, String outputDir, List<SplitRange> ranges) throws IOException {
        PDDocument doc = PDDocument.load(new File(inputFile));
        try {
            int total = doc.getNumberOfPages();
            System.out.println("Total pages: " + total);

            for (int i = 0; i < ranges.size(); i++) {
                SplitRange r = ranges.get(i);
                int s = Math.max(1, r.start());
                int e = Math.min(total, r.endOrStart());
                PDDocument out = new PDDocument();

                for (int p = s; p <= e; p++) {
                    out.addPage(doc.getPage(p - 1));
                }

                String outputFileName = outputDir + "/split_" + (i + 1) + "_pages_" + s + "-" + e + ".pdf";
                out.save(outputFileName);
                out.close();
                System.out.println("✅ Created: " + outputFileName);
            }
        } finally {
            doc.close();
        }
    }
}
