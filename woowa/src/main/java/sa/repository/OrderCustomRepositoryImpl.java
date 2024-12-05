package sa.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sa.domain.Menu;
import sa.domain.QMenu;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static sa.domain.QMenu.menu;

@RequiredArgsConstructor
@Repository
public class OrderCustomRepositoryImpl implements OrderCustomRepository{

    private final JPAQueryFactory query;

    @Override
    public Map<Long, Menu> findMenuListById(List<Long> idList) {
        List<Menu> menuList = query.selectFrom(menu)
                .where(menu.id.in(idList))
                .fetch();

        return menuList.stream()
                .collect(Collectors.toMap(
                        (menu) -> menu.getId(),
                        (menu) -> menu
                ));
    }
}
