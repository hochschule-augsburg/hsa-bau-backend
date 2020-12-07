package de.hsaugsburg.kunde.api;

import de.hsaugsburg.kunde.api.to.NeuerKundeTO;
import de.hsaugsburg.kunde.domain.KundenMapper;
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
@RequestMapping("/restapi/kunde")
public class KundenController {

  private final KundenService kundenService;
  private final KundenMapper kundenMapper;

  @PostMapping
  public ResponseEntity<NeuerKundeTO> kundeAnlegen(@RequestBody @Valid final NeuerKundeTO neuerKundeTO) {
    val model = this.kundenService.kundeAnlegen(this.kundenMapper.map(neuerKundeTO));
    return ResponseEntity.ok(this.kundenMapper.map2TO(model));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> kundeEntfernen(@PathVariable("id") final String id) {
    this.kundenService.kundeEntfernen(id);
    return ResponseEntity.ok("Kunde gelöscht");
  }

  @GetMapping("/{id}")
  public ResponseEntity<NeuerKundeTO> kundeLaden(@PathVariable("id") final String id) {
    val model = this.kundenService.kundeLaden(id);
    return ResponseEntity.ok(this.kundenMapper.map2TO(model));
  }

  @GetMapping
  public ResponseEntity<List<NeuerKundeTO>> alleKundenLaden() {
    val model = this.kundenService.alleKundenLaden();
    return ResponseEntity.ok(this.kundenMapper.map2TO(model));
  }

}
