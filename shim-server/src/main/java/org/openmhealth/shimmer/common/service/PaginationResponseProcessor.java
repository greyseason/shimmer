/*
 * Copyright 2015 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.shimmer.common.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.openmhealth.shimmer.common.configuration.PaginationSettings;
import org.openmhealth.shimmer.common.decoder.PaginationResponseDecoder;
import org.openmhealth.shimmer.common.domain.pagination.PaginationStatus;
import org.openmhealth.shimmer.common.extractor.PaginationResponseExtractor;
import org.springframework.http.ResponseEntity;

import java.util.Optional;


/**
 * Processes a response from a third-party API to identify if more data points are available via pagination and to
 * extract the information necessary to traverse the pagination to retrieve those data points.
 *
 * @author Chris Schaefbauer
 */
public abstract class PaginationResponseProcessor<T extends PaginationSettings> {

    PaginationResponseExtractor responseExtractor;

    PaginationResponseDecoder responseDecoder;

    /**
     * Processes the pagination content in the response and loads the object to respond to requests for information
     * about pagination.
     */
    public abstract PaginationStatus processPaginationResponse(T paginationResponseProperties,
            ResponseEntity<JsonNode> responseEntity);

    public PaginationResponseExtractor getPaginationResponseExtractor() {
        return responseExtractor; // This needs to get injected somehow, maybe from Shim or selected based on settings?
    }

    public void setPaginationResponseExtractor(PaginationResponseExtractor responseExtractor) {
        this.responseExtractor = responseExtractor;
    }

    /**
     * Used to decode pagination response information that is encoded in some form.
     * @return The decoder to use in processing the pagination response information, if one exists.
     */
    public Optional<PaginationResponseDecoder> getPaginationResponseDecoder() {
        return Optional.ofNullable(responseDecoder);
    }

    public void setResponseDecoder(PaginationResponseDecoder responseDecoder) {
        this.responseDecoder = responseDecoder;
    }


}
