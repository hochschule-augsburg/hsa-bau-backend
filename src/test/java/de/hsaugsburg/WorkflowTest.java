package de.hsaugsburg;


import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WorkflowTest extends AbstractProcessEngineRuleTest {

  @Autowired
  public RuntimeService runtimeService;

  @MockBean
  private TestService testService;

  @Test
  public void shouldExecuteOrderSignedPath() {
    /*
    **************************************
    * Path: with order and signed report
    * ************************************
    * */

    //  doNothing().when(testService).doSomething();

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, withVariables("offerRequired", true));

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_AngebotBearbeiten")
        .isNotAssigned();
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

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, withVariables("offerRequired", true));

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_AngebotBearbeiten")
        .isNotAssigned();
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
        .hasPassed("StartEvent", "Task_AngebotBearbeiten", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKlaeren");

  }

  @Test
  public void shouldExecuteNoOrderSignedPath() {
    /*
    **************************************
    * Path: with no order and signed report
    * ************************************
    * */

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, withVariables("offerRequired", false));

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_BauvorhabenPlanen")
        .isNotAssigned();
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
        .hasPassed("StartEvent", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKontrollieren");

  }

  @Test
  public void shouldExecuteNoOrderUnSignedPath() {
    /*
    **************************************
    * Path: with no order and unsigned report
    * ************************************
    * */

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, withVariables("offerRequired", false));

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_BauvorhabenPlanen")
        .isNotAssigned();
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
        .hasPassed("StartEvent", "Task_BauvorhabenPlanen", "Task_AuftragBestaetigen", "Task_LnAbschliessen", "Task_LnKlaeren");

  }

}
