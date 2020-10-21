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
  public void shouldExecuteHappyPath() {

    doNothing().when(testService).doSomething();

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_AngebotBearbeiten")
        .isNotAssigned();

    complete(task());

    assertThat(processInstance)
            .isWaitingAt("Task_BauvorhabenPlanen");

    complete(task(), withVariables("doIt", true));

    assertThat(processInstance)
            .isEnded();


    verify(testService, times(1)).doSomething();
  }


  @Test
  public void shouldDoNothing() {

    doNothing().when(testService).doSomething();

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

    assertThat(processInstance).isStarted()
            .task()
            .hasDefinitionKey("Task_AngebotBearbeiten")
            .isNotAssigned();

    complete(task());

    assertThat(processInstance)
            .isWaitingAt("Task_BauvorhabenPlanen");

    complete(task(), withVariables("doIt", false));

    assertThat(processInstance)
            .isEnded();


    assertThat(processInstance).hasNotPassed("Activity_1t205t6");
  }

}
