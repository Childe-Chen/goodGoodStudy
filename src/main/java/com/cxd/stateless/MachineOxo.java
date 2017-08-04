package com.cxd.stateless;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MachineOxo {

    final static Logger log = LoggerFactory.getLogger(MachineOxo.class);

    public static void main(String[] args) {
        StateMachineConfig<State, Trigger> phoneCallConfig = new StateMachineConfig<>();

        phoneCallConfig.configure(State.OffHook)
                .permit(Trigger.CallDialed, State.Ringing);

        phoneCallConfig.configure(State.Ringing)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.CallConnected, State.Connected);

        phoneCallConfig.configure(State.Connected)
                .onEntry(MachineOxo::startCallTimer)
                .onExit(MachineOxo::stopCallTimer)
                .permit(Trigger.LeftMessage, State.OffHook)
                .permit(Trigger.HungUp, State.OffHook)
                .permit(Trigger.PlacedOnHold, State.OnHold);

        phoneCallConfig.configure(State.OnHold)
                .permitDynamic(Trigger.BOON, () -> State.OffHook);

        // ...

        StateMachine<State, Trigger> phoneCall = new StateMachine<>(State.OffHook, phoneCallConfig);

        phoneCall.onUnhandledTrigger((State state,Trigger trigger) -> {
            System.out.println(state.name() + "--" + trigger.name());
        });

        log.info("before fire {}",phoneCall.getState());
        log.warn("can fire {} ", phoneCall.canFire(Trigger.BOON));
        phoneCall.fire(Trigger.BOON);
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
        CallDialed, CallConnected, PlacedOnHold, LeftMessage, HungUp, BOON

    }
}
