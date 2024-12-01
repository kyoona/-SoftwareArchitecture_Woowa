package sa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sa.domain.Location;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderAddDto {

    private String phoneNumber;
    private List<OrderMenuDto> menuList;
    private Location location;
}
