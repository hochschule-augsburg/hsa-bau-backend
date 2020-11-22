package de.hsaugsburg.auftrag.process;

import de.hsaugsburg.auftrag.domain.AuftragService;
import io.holunda.camunda.bpm.data.factory.VariableFactory;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import static io.holunda.camunda.bpm.data.CamundaBpmData.stringVariable;

@Component
@RequiredArgsConstructor
public class StatusAendernDelegate implements JavaDelegate {

    public static final VariableFactory<String> NEUER_STATUS = stringVariable("neuer_status");

    private final AuftragService auftragService;

    @Override
    public void execute(final DelegateExecution delegateExecution) throws Exception {
        //input
        val neuerStatus = NEUER_STATUS.from(delegateExecution).getLocal();
        val auftragId = delegateExecution.getProcessBusinessKey();

        //processing
        this.auftragService.auftragStatusAendern(auftragId, neuerStatus);

        //output
        NEUER_STATUS.on(delegateExecution).set(neuerStatus);
    }
}
