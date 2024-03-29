package de.hsaugsburg.auftrag.api.to;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NeuerAuftragTO {

    @NotBlank
    private String kundenId;

    @NotBlank
    private String name;

    @NotBlank
    private String bauvorhaben;

    @NotBlank
    private String kundeId;

}