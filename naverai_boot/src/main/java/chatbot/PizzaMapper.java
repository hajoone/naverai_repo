package chatbot;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper //@mapperscan
@Repository // @componentscan
public interface PizzaMapper {
	int insertPizza(PizzaDTO dto);
}
