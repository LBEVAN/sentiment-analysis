package io.github.lbevan.sentiment.repository.impl;

import com.google.common.io.ByteSource;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import io.github.lbevan.sentiment.repository.document.conversion.DocumentConversionStrategy;
import io.github.lbevan.sentiment.repository.document.conversion.DocumentConversionStrategyFactory;
import io.github.lbevan.sentiment.service.domain.misc.DocumentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class DocumentRepository {

    private GridFsTemplate gridFsTemplate;
    private GridFSBucket gridFSBucket;

    @Autowired
    public DocumentRepository(GridFsTemplate gridFsTemplate, GridFSBucket gridFSBucket) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFSBucket = gridFSBucket;
    }

    /**
     * Save the specified document stream to the database.
     *
     * @param inputStream stream of the document
     * @param documentName document name
     * @param contentType document content
     * @return String saved id
     */
    public String save(final InputStream inputStream, final String documentName, final String contentType) {
        return gridFsTemplate.store(inputStream, documentName, contentType).toString();
    }

    /**
     * Find the document by its id.
     *
     * @param id id of the document
     * @return GridFSResource
     */
    public GridFsResource findById(final String id) {
        // find the file
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

        // download it from the store
        GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(file.getObjectId());
        return new GridFsResource(file, downloadStream);
    }

    /**
     * Get the conversion strategy for the particular document.
     *
     * @return DocumentConversionStrategy
     */
    public DocumentConversionStrategy getDocumentConversionStrategyForDocument(DocumentType documentType) {
        return DocumentConversionStrategyFactory.getConversionStrategy(documentType);
    }
}
