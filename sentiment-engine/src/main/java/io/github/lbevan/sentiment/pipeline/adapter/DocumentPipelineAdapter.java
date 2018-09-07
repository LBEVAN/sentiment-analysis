package io.github.lbevan.sentiment.pipeline.adapter;

import io.github.lbevan.sentiment.pipeline.Payload;
import io.github.lbevan.sentiment.repository.impl.DocumentRepository;
import io.github.lbevan.sentiment.service.SpringBeanUtil;
import io.github.lbevan.sentiment.service.domain.dto.DocumentAnalysisRequestDto;
import io.github.lbevan.sentiment.util.document.DocumentConversionException;
import io.github.lbevan.sentiment.util.document.DocumentConversionStrategy;
import io.github.lbevan.sentiment.util.document.DocumentConversionStrategyFactory;

import java.util.LinkedList;

/**
 * A {@link PipelineAdapter} implementation for a single document analysis request.
 */
public class DocumentPipelineAdapter implements PipelineAdapter {

    private DocumentAnalysisRequestDto request;
    private DocumentRepository documentRepository;

    /**
     * Constructor.
     *
     */
    public DocumentPipelineAdapter(DocumentAnalysisRequestDto request) {
        this.request = request;
        this.documentRepository = SpringBeanUtil.getBean(DocumentRepository.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Payload adapt() {
        // get the appropriate conversion strategy for the given document
        DocumentConversionStrategy conversionStrategy =
                DocumentConversionStrategyFactory.getConversionStrategy(request.getDocumentType());

        // retrieve the document and convert it to a usable form
        String documentContents = null;
        try {
            documentContents = conversionStrategy.convert(documentRepository.findById(request.getDocumentId()));
        } catch (DocumentConversionException e) {
            // todo: error handling
            e.printStackTrace();
        }

        LinkedList<String> payloadData = new LinkedList<>();
        payloadData.add(documentContents);

        return new Payload(request.getRequestId(), payloadData);
    }
}
