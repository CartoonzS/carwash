package ua.slavik.carwash.dto.customercarlink;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCustomerCarLinkDTO {
    private Long id;
    private Long customerId;
    private List<Long> carIds;
}
