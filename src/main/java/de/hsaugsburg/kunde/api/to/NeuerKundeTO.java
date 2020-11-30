package de.hsaugsburg.kunde.api.to;


import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class NeuerKundeTO {

  private String kundenId;

  @NotBlank
  private String name;

}
