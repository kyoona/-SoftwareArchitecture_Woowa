package sa.repository;

import sa.domain.Menu;

import java.util.List;
import java.util.Map;

public interface OrderCustomRepository {

    Map<Long, Menu> findMenuListById(List<Long> idList);
}
