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

  @Autowired
  private TestDelegate testDelegate;

  @MockBean
  private TestService testService;

  @Test
  public void shouldExecuteHappyPath() {

    doNothing().when(testService).doSomething();

    when(testService.erstelleObjekt())
            .thenReturn(
                    TestObjekt.builder()
                            .vorhanden(false)
                            .build()
            );

    String processDefinitionKey = "bauprozess";

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey);

    assertThat(processInstance).isStarted()
        .task()
        .hasDefinitionKey("Task_AngebotBearbeiten")
        .isNotAssigned();

    complete(task());

    assertThat(processInstance)
            .isWaitingAt("Task_BauvorhabenPlanen");

    complete(task(), withVariables("ok", false));

    assertThat(processInstance)
            .isEnded();


    verify(testService, times(1)).doSomething();
  }

}
