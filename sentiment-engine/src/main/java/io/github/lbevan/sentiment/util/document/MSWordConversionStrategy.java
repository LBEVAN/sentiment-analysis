package io.github.lbevan.sentiment.util.document;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.data.mongodb.gridfs.GridFsResource;

import java.io.IOException;
import java.util.List;

/**
 * Document conversion strategy for .docx documents.
 */
public class MSWordConversionStrategy implements DocumentConversionStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(GridFsResource resource) throws DocumentConversionException {
        try {
            XWPFDocument document = new XWPFDocument(resource.getInputStream());
            List<XWPFParagraph> paragraphs = document.getParagraphs();

            StringBuilder stringBuilder = new StringBuilder();
            for(XWPFParagraph paragraph : paragraphs) {
                stringBuilder.append(paragraph.getText());
            }

            document.close();

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new DocumentConversionException("Exception converting a word document to its String contents.", e);
        }
    }
}
