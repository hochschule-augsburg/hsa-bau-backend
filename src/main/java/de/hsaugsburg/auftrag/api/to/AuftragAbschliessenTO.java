package de.hsaugsburg.auftrag.api.to;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AuftragAbschliessenTO {

    @NotBlank
    private String auftragId;

    @NotBlank
    private String taskId;
}
