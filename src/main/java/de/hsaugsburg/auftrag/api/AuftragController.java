package de.hsaugsburg.auftrag.api;


import de.hsaugsburg.auftrag.api.to.*;
import de.hsaugsburg.auftrag.domain.AuftragMapper;
import de.hsaugsburg.auftrag.domain.AuftragService;
import de.hsaugsburg.kunde.domain.KundenService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restapi/auftrag")
public class AuftragController {

    private final AuftragService auftragService;
    private final AuftragMapper auftragMapper;

    @PostMapping
    public ResponseEntity<AuftragTO> auftragEinplanen(@RequestBody @Valid final NeuerAuftragTO neuerAuftragTO) {
        val model = this.auftragService.auftragEinplanen(this.auftragMapper.map(neuerAuftragTO));
        return ResponseEntity.ok(this.auftragMapper.map2TO(model));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuftragTO> auftragLaden(@PathVariable("id") final String id) {
        val model = this.auftragService.auftragLaden(id);
        return ResponseEntity.ok(this.auftragMapper.map2TO(model));
    }

    @GetMapping
    public ResponseEntity<List<AuftragTO>> alleAuftraegeLaden() {
        val model = this.auftragService.alleAuftraegeLaden();
        return ResponseEntity.ok(this.auftragMapper.map2TO(model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> auftragEntfernen(@PathVariable("id") final String id) {
        this.auftragService.auftragEntfernen(id);
        return ResponseEntity.ok("Auftrag gelöscht");

        // #TODO: Beim Löschen eines Auftrages muss auch die aktive Instanz gelöscht werden
    }

    @PostMapping("/zuweisen")
    public ResponseEntity<Void> auftragZuweisen(@RequestBody @Valid final AuftragZuweisenTO auftragZuweisenTO) {
        this.auftragService.auftragZuweisen(auftragZuweisenTO.getAuftragId(), auftragZuweisenTO.getMonteur(), auftragZuweisenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/abschliessen")
    public ResponseEntity<Void> auftragAbschliessen(@RequestBody @Valid final AuftragAbschliessenTO auftragAbschliessenTO) {
        this.auftragService.auftragAbschliessen(auftragAbschliessenTO.getAuftragId(), auftragAbschliessenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/genehmigen")
    public ResponseEntity<Void> auftragGenehmigen(@RequestBody @Valid final AuftragGenehmigenTO auftragGenehmigenTO) {
        this.auftragService.auftragGenehmigen(auftragGenehmigenTO.getAuftragId(), auftragGenehmigenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pruefen")
    public ResponseEntity<Void> auftragPruefen(@RequestBody @Valid final AuftragPruefenTO auftragPruefenTO) {
        this.auftragService.auftragPruefen(auftragPruefenTO.getAuftragId(), auftragPruefenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/freigeben")
    public ResponseEntity<Void> auftragFreigeben(@RequestBody @Valid final AuftragFreigebenTO auftragFreigebenTO) {
        this.auftragService.auftragFreigeben(auftragFreigebenTO.getAuftragId(), auftragFreigebenTO.getTaskId());
        return ResponseEntity.ok().build();
    }


}
