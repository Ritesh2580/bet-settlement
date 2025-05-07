package org.sporty.bet.settlement.service;

public interface Publisher <R,T>{
    R publish(T payload);
}