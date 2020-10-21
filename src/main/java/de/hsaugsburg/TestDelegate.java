package de.hsaugsburg;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDelegate  implements JavaDelegate {

    private final TestService testService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        //inputs


        //processing
        testService.doSomething();


        //outputs
    }
}
