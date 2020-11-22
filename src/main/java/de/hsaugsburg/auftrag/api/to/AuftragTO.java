package de.hsaugsburg.auftrag.api.to;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuftragTO {

    private String id;

    private String bauvorhaben;

    private String name;

    private String kunde;

    private String monteur;

    private String status;

}
