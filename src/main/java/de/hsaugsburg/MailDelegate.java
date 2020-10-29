package de.hsaugsburg;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class MailDelegate implements JavaDelegate {

//    private final TestService testService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("Erinnerungsmail wurde versendet");

    }
}