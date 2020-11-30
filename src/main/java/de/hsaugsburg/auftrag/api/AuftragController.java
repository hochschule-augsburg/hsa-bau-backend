package de.hsaugsburg.auftrag.api;


import de.hsaugsburg.auftrag.api.to.AuftragTO;
import de.hsaugsburg.auftrag.api.to.AuftragZuweisenTO;
import de.hsaugsburg.auftrag.api.to.statusAenderungTO;
import de.hsaugsburg.auftrag.api.to.NeuerAuftragTO;
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
@RequestMapping("/restapi/auftrag")
public class AuftragController {

    private final AuftragService auftragService;
    private final AuftragMapper auftragMapper;
    private final KundenService kundenService;

    @PostMapping("/{kundenId}")
    public ResponseEntity<AuftragTO> auftragEinplanen(@RequestBody @Valid final NeuerAuftragTO neuerAuftragTO, @PathVariable("kundenId") final String id) {
        val kunde = this.kundenService.kundeLaden(id);
        neuerAuftragTO.setKunde(kunde.getName());
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
    }

    @PostMapping("/zuweisen")
    public ResponseEntity<Void> auftragZuweisen(@RequestBody @Valid final AuftragZuweisenTO auftragZuweisenTO) {
        this.auftragService.auftragZuweisen(auftragZuweisenTO.getAuftragId(), auftragZuweisenTO.getMonteur(), auftragZuweisenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/abschliessen")
    public ResponseEntity<Void> lnAbschliessen(@RequestBody @Valid final statusAenderungTO statusAenderungTO) {
        this.auftragService.lnAbschliessen(statusAenderungTO.getAuftragId(), statusAenderungTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/genehmigen")
    public ResponseEntity<Void> lnGenehmigen(@RequestBody @Valid final statusAenderungTO statusAenderungTO) {
        this.auftragService.lnGenehmigen(statusAenderungTO.getAuftragId(), statusAenderungTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/pruefen")
    public ResponseEntity<Void> lnPruefen(@RequestBody @Valid final statusAenderungTO statusAenderungTO) {
        this.auftragService.lnPruefen(statusAenderungTO.getAuftragId(), statusAenderungTO.getTaskId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/freigeben")
    public ResponseEntity<Void> lnFreigeben(@RequestBody @Valid final statusAenderungTO statusAenderungTO) {
        this.auftragService.lnFreigeben(statusAenderungTO.getAuftragId(), statusAenderungTO.getTaskId());
        return ResponseEntity.ok().build();
    }


}
