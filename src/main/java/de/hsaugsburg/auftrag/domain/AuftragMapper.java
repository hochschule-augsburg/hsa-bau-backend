package de.hsaugsburg.auftrag.domain;


import de.hsaugsburg.auftrag.api.to.AuftragTO;
import de.hsaugsburg.auftrag.api.to.NeuerAuftragTO;
import de.hsaugsburg.auftrag.domain.model.Auftrag;
import de.hsaugsburg.auftrag.domain.model.NeuerAuftrag;
import de.hsaugsburg.auftrag.infrastructure.AuftragEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuftragMapper {

    public NeuerAuftrag map(final NeuerAuftragTO to) {
        return NeuerAuftrag.builder()
                .bauvorhaben(to.getBauvorhaben())
                .kundeId(to.getKundeId())
                .name(to.getName())
                .build();
    }

    public AuftragEntity map(final Auftrag model) {
        return AuftragEntity.builder()
                .id(model.getId())
                .bauvorhaben(model.getBauvorhaben())
                .kunde(model.getKunde())
                .name(model.getName())
                .monteur(model.getMonteur())
                .status(model.getStatus())
                .build();
    }

    public Auftrag map(final AuftragEntity entity) {
        return Auftrag.builder()
                .id(entity.getId())
                .bauvorhaben(entity.getBauvorhaben())
                .kunde(entity.getKunde())
                .name(entity.getName())
                .monteur(entity.getMonteur())
                .status(entity.getStatus())
                .build();
    }

    public AuftragTO map2TO(final Auftrag model) {
        return AuftragTO.builder()
                .id(model.getId())
                .bauvorhaben(model.getBauvorhaben())
                .kunde(model.getKunde())
                .name(model.getName())
                .monteur(model.getMonteur())
                .status(model.getStatus())
                .build();
    }

    public List<Auftrag> map(final List<AuftragEntity> entities) {
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }


    public List<AuftragTO> map2TO(final List<Auftrag> models) {
        return models.stream()
                .map(this::map2TO)
                .collect(Collectors.toList());
    }
}
