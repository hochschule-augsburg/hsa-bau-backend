package de.hsaugsburg.auftrag.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NeuerAuftrag {

    private final String bauvorhaben;

    private final String name;

    private final String kunde;

    private final String status;

}
