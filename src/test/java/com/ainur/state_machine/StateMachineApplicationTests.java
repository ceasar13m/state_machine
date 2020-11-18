package com.ainur.state_machine;

import com.ainur.state_machine.statemachine.Events;
import com.ainur.state_machine.statemachine.States;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class StateMachineApplicationTests {
    @Autowired
    private StateMachine<States, Events> stateMachine;

    @Test
    public void testInit() {
        Assertions.assertThat(stateMachine).isNotNull();
        Assertions.assertThat(stateMachine.getState().getId())
                .isEqualTo(States.BACKLOG);
    }

    @Test
    public void testGreenWay() {
        stateMachine.sendEvent(Events.START_FEATURE);
        stateMachine.sendEvent(Events.FINISH_FEATURE);
        stateMachine.sendEvent(Events.QA_TEAM_APPROVE);
        Assertions.assertThat(stateMachine.getState().getId()).isEqualTo(States.DONE);
    }
}
