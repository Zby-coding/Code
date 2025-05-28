import com.zby.config.SpringConfig;
import com.zby.domain.Book2;
import com.zby.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)

public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    public void testGetById() {
        Book2 book = bookService.getById(1);
        System.out.println(book);
    }

    @Test
    public void testGetAll() {
        List<Book2> all = bookService.getAll();
        System.out.println(all);
    }
}