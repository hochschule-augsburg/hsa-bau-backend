package de.hsaugsburg.kunde.domain;


import de.hsaugsburg.auftrag.api.to.AuftragTO;
import de.hsaugsburg.auftrag.domain.model.Auftrag;
import de.hsaugsburg.auftrag.infrastructure.AuftragEntity;
import de.hsaugsburg.kunde.api.to.NeuerKundeTO;
import de.hsaugsburg.kunde.domain.model.NeuerKunde;
import de.hsaugsburg.kunde.infrastructure.KundeEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KundenMapper {

  public NeuerKunde map(final NeuerKundeTO to) {
    return NeuerKunde.builder()
        .name(to.getName())
        .build();
  }

  public KundeEntity map(final NeuerKunde model) {
    return KundeEntity.builder()
        .kundenId(model.getKundenId())
        .name(model.getName())
        .build();
  }

  public NeuerKunde map(final KundeEntity entity) {
    return NeuerKunde.builder()
        .kundenId(entity.getKundenId())
        .name(entity.getName())
        .build();
  }

  public NeuerKundeTO map2TO(final NeuerKunde model) {
    return NeuerKundeTO.builder()
        .kundenId(model.getKundenId())
        .name(model.getName())
        .build();
  }

  public List<NeuerKunde> map(final List<KundeEntity> entities) {
    return entities.stream()
        .map(this::map)
        .collect(Collectors.toList());
  }

  public List<NeuerKundeTO> map2TO(final List<NeuerKunde> models) {
    return models.stream()
        .map(this::map2TO)
        .collect(Collectors.toList());
  }

}
