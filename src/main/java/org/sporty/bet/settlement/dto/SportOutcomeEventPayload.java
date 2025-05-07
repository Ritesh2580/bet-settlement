package org.sporty.bet.settlement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportOutcomeEventPayload {
    @NotBlank
    private String eventId; //can be UUID
    @NotBlank
    private String eventName;
    @NotBlank
    private String eventWinnerId; //can be UUID
}
