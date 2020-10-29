package de.hsaugsburg;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class PacklisteDelegate implements JavaDelegate {

//    private final TestService testService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        String itemspackinglist = (String) delegateExecution.getVariable("packinglist");
        System.out.println("In der Packliste befinden sich die folgenden Gegenstände: " + itemspackinglist);

    }
}