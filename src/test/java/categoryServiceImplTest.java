import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.ICategoryService;
import cn.itcast.travel.service.Impl.categoryServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class categoryServiceImplTest {
    @Test
    public void findAllTest() throws IOException {
        ICategoryService iCategoryService = new categoryServiceImpl();
        List<Category> all = iCategoryService.findAll();
        for (Category category : all) {
            System.out.println(category);
        }
    }
}
