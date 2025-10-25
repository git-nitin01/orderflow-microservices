package com.orderflow.common.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    @NotBlank private String sku;
    @Min(1) private Integer quantity;
    @Min(0) private Long amountCents;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
}
