package ua.slavik.carwash.VO;

import lombok.Data;
import java.util.List;

@Data

public class CreateServiceVO
{
    private long id;
    private String name;
    private int price;
    private List<Long> jobIds;
}
