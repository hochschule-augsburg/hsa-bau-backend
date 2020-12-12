package de.hsaugsburg;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowTest extends AbstractProcessEngineRuleTest {

    @Autowired
    public RuntimeService runtimeService;

    @Test
    public void shouldExecuteOrderSignedPath() {
        /*
         **************************************
         * Path: with order and signed report
         * ************************************
         * */

        //  doNothing().when(testService).doSomething();

        final String processDefinitionKey = "bauprozess";

        final ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
                .isWaitingAt("Task_AufAngebotPruefen");
        complete(task(), withVariables("offerRequired", true));

        assertThat(processInstance)
                .isWaitingAt("Task_AngebotBearbeiten");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_BauvorhabenPlanen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_AuftragBestaetigen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_LnAbschliessen");
        complete(task(), withVariables("reportSigned", true));

        assertThat(processInstance)
                .isWaitingAt("Task_LnKontrollieren");
        complete(task());

        assertThat(processInstance)
                .isEnded()
                .hasPassed("StartEvent", "Task_AngebotBearbeiten", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKontrollieren");

        //  verify(testService, times(1)).doSomething();

    }

    @Test
    public void shouldExecuteOrderUnSignedPath() {
        /*
         **************************************
         * Path: with order and unsigned report
         * ************************************
         * */

        final String processDefinitionKey = "bauprozess";

        final ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
                .isWaitingAt("Task_AufAngebotPruefen");
        complete(task(), withVariables("offerRequired", true));

        assertThat(processInstance)
                .isWaitingAt("Task_AngebotBearbeiten");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_BauvorhabenPlanen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_AuftragBestaetigen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_LnAbschliessen");
        complete(task(), withVariables("reportSigned", false));

        assertThat(processInstance)
                .isWaitingAt("Task_LnKlaeren");
        complete(task());

        assertThat(processInstance)
                .isEnded()
                .hasPassed("StartEvent", "Task_AufAngebotPruefen", "Task_AngebotBearbeiten", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKlaeren");

    }

    @Test
    public void shouldExecuteNoOrderSignedPath() {
        /*
         **************************************
         * Path: with no order and signed report
         * ************************************
         * */

        final String processDefinitionKey = "bauprozess";

        final ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
                .isWaitingAt("Task_AufAngebotPruefen");
        complete(task(), withVariables("offerRequired", false));

        assertThat(processInstance)
                .isWaitingAt("Task_BauvorhabenPlanen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_AuftragBestaetigen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_LnAbschliessen");
        complete(task(), withVariables("reportSigned", true));

        assertThat(processInstance)
                .isWaitingAt("Task_LnKontrollieren");
        complete(task());

        assertThat(processInstance)
                .isEnded()
                .hasPassed("StartEvent", "Task_AufAngebotPruefen", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKontrollieren");

    }

    @Test
    public void shouldExecuteNoOrderUnSignedPath() {
        /*
         **************************************
         * Path: with no order and unsigned report
         * ************************************
         * */

        final String processDefinitionKey = "bauprozess";

        final ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(processDefinitionKey);

        assertThat(processInstance)
                .isWaitingAt("Task_AufAngebotPruefen");
        complete(task(), withVariables("offerRequired", false));

        assertThat(processInstance)
                .isWaitingAt("Task_BauvorhabenPlanen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_AuftragBestaetigen");
        complete(task());

        assertThat(processInstance)
                .isWaitingAt("Task_LnAbschliessen");
        complete(task(), withVariables("reportSigned", false));

        assertThat(processInstance)
                .isWaitingAt("Task_LnKlaeren");
        complete(task());

        assertThat(processInstance)
                .isEnded()
                .hasPassed("StartEvent", "Task_AufAngebotPruefen", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKlaeren");

    }

}

