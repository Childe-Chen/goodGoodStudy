package com.cxd.stateless;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Machine {

    final static Logger log = LoggerFactory.getLogger(Machine.class);

    public static void main(String[] args) {
        StateMachineConfig<State, Trigger> phoneCallConfig = new StateMachineConfig<>();

        phoneCallConfig.configure(State.OffHook)
                .permit(Trigger.CallDialed, State.Ringing);

        phoneCallConfig.configure(State.Ringing)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.CallConnected, State.Connected);

        phoneCallConfig.configure(State.Connected)
                .onEntry(Machine::startCallTimer)
                .onExit(Machine::stopCallTimer)
                .permit(Trigger.LeftMessage, State.OffHook)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.PlacedOnHold, State.OnHold);

        // ...

        StateMachine<State, Trigger> phoneCall = new StateMachine<>(State.OffHook, phoneCallConfig);

        log.info("before fire {}",phoneCall.getState());
        log.warn("can fire {} ", phoneCall.canFire(Trigger.LeftMessage));
//        phoneCall.fire(Trigger.HungUp);
        log.info("after fire {}",phoneCall.getState());

    }

    private static void stopCallTimer() {
        // ...
        log.info("stopCallTimer");
    }

    private static void startCallTimer() {
        // ...
        log.info("startCallTimer");
    }


    private enum State {
        Ringing, Connected, OnHold, OffHook

    }
    private enum Trigger {
        CallDialed, CallConnected, PlacedOnHold, LeftMessage, HungUp

    }
}
