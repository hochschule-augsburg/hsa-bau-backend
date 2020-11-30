package de.hsaugsburg.kunde.domain;

import de.hsaugsburg.kunde.domain.model.NeuerKunde;
import de.hsaugsburg.kunde.infrastructure.repos.KundenJpaRepository;
import de.hsaugsburg.shared.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KundenService {

  private final KundenJpaRepository kundenJpaRepository;
  private final KundenMapper kundenMapper;

  public NeuerKunde kundeAnlegen(final NeuerKunde Neuerkunde) {
    val neuerKunde = new NeuerKunde(Neuerkunde);
    return this.kundeSpeichern(neuerKunde);
  }

  private NeuerKunde kundeSpeichern(final NeuerKunde kunde) {
    val entity = this.kundenJpaRepository.save(this.kundenMapper.map(kunde));
    return this.kundenMapper.map(entity);
  }

  public void kundeEntfernen(final String id) {
    this.kundenJpaRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(String.format("Kunde mit der Id %s ist nicht vorhanden", id)));
    this.kundenJpaRepository.deleteById(id);
  }

  public NeuerKunde kundeLaden(final String id) {
    val kunde = this.kundenJpaRepository.findById(id)
        .orElseThrow(() -> new ObjectNotFoundException(String.format("Kunde mit der Id %s ist nicht vorhanden", id)));
    return this.kundenMapper.map(kunde);
  }

  public List<NeuerKunde> alleKundenLaden() {
    val kunden = this.kundenJpaRepository.findAll();
    return this.kundenMapper.map(kunden);
  }

}
