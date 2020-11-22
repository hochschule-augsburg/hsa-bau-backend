package de.hsaugsburg.auftrag.api;


import de.hsaugsburg.auftrag.api.to.AuftragTO;
import de.hsaugsburg.auftrag.api.to.AuftragZuweisenTO;
import de.hsaugsburg.auftrag.api.to.NeuerAuftragTO;
import de.hsaugsburg.auftrag.domain.AuftragMapper;
import de.hsaugsburg.auftrag.domain.AuftragService;
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
    public ResponseEntity<List<AuftragTO>> alleAuftraegeLaden(final String id) {
        val model = this.auftragService.alleAuftraegeLaden();
        return ResponseEntity.ok(this.auftragMapper.map2TO(model));
    }

    @PostMapping("/zuweisen")
    public ResponseEntity<Void> auftragZuweisen(@RequestBody @Valid final AuftragZuweisenTO auftragZuweisenTO) {
        this.auftragService.auftragZuweisen(auftragZuweisenTO.getAuftragId(), auftragZuweisenTO.getMonteur(), auftragZuweisenTO.getTaskId());
        return ResponseEntity.ok().build();
    }

}
