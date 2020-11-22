package de.hsaugsburg.auftrag.domain.model;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Auftrag {

    private String id;

    private final String bauvorhaben;

    private final String name;

    private final String kunde;

    private String monteur;

    private String status;

    public Auftrag(final NeuerAuftrag neuerAuftrag) {
        this.bauvorhaben = neuerAuftrag.getBauvorhaben();
        this.name = neuerAuftrag.getName();
        this.kunde = neuerAuftrag.getKunde();
    }

    public void zuweisen(final String monteur) {
        this.monteur = monteur;
    }

    public void statusAendern(final String status) {
        this.status = status;
    }

}
