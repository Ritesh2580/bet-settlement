package org.sporty.bet.settlement;

import org.springframework.boot.SpringApplication;

public class TestSportyAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.from(SportyAssignmentApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
