package org.airgraft.cityautocomplete.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Suggestion {
    private String name;
    private String latitude;
    private String longitude;
    private double score;
}
