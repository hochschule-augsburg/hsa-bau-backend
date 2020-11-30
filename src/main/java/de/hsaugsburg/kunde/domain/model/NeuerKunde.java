package de.hsaugsburg.kunde.domain.model;

import lombok.*;

@Getter
@Builder
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class NeuerKunde {

  private String kundenId;

  private String name;

  public NeuerKunde(NeuerKunde neuerkunde) {
    this.kundenId = neuerkunde.getKundenId();
    this.name = neuerkunde.getName();
  }
}
