package de.hsaugsburg.auftrag.domain;

import de.hsaugsburg.auftrag.domain.model.Auftrag;
import de.hsaugsburg.auftrag.domain.model.NeuerAuftrag;
import de.hsaugsburg.auftrag.infrastructure.repos.AuftragJpaRepository;
import de.hsaugsburg.shared.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuftragService {

    private final AuftragJpaRepository auftragJpaRepository;
    private final AuftragMapper auftragMapper;

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    public Auftrag auftragEinplanen(final NeuerAuftrag neuerAuftrag) {
        val auftrag = new Auftrag(neuerAuftrag);
        val savedAuftrag = this.auftragSpeichern(auftrag);
        this.runtimeService.startProcessInstanceByKey("bauprozess", savedAuftrag.getId());
        return savedAuftrag;
    }

    public Auftrag auftragLaden(final String id) {
        val auftrag = this.auftragJpaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("Auftrag mit der Id %s ist nicht vorhanden", id)));
        return this.auftragMapper.map(auftrag);
    }

    public List<Auftrag> alleAuftraegeLaden() {
        val auftraege = this.auftragJpaRepository.findAll();
        return this.auftragMapper.map(auftraege);
    }

    public void auftragEntfernen(final String id) {
        this.auftragJpaRepository.findById(id)
            .orElseThrow(() -> new ObjectNotFoundException(String.format("Auftrag mit der Id %s ist nicht vorhanden", id)));
        this.auftragJpaRepository.deleteById(id);
    }

    public void auftragZuweisen(final String id, final String monteur, final String taskId) {
        val auftrag = this.auftragLaden(id);
        auftrag.zuweisen(monteur);
        this.auftragSpeichern(auftrag);
        this.taskService.complete(taskId);
    }

    public void lnAbschliessen(final String id, final String taskId) {
        this.taskService.complete(taskId);
    }

    public void lnGenehmigen(final String id, final String taskId) {
        this.taskService.complete(taskId);
    }

    public void lnPruefen(final String id, final String taskId) {
        this.taskService.complete(taskId);
    }

    public void lnFreigeben(final String id, final String taskId) {
        this.taskService.complete(taskId);
    }

    public void auftragStatusAendern(final String auftragId, final String status) {
        val auftrag = this.auftragLaden(auftragId);
        auftrag.statusAendern(status);
        this.auftragSpeichern(auftrag);
    }

    private Auftrag auftragSpeichern(final Auftrag auftrag) {
        val entity = this.auftragJpaRepository.save(this.auftragMapper.map(auftrag));
        return this.auftragMapper.map(entity);
    }

}
