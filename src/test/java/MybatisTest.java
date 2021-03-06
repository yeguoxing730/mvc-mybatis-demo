import com.mvc.entity.Student;
import com.mvc.service.IStudentService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by yeguo on 2018/3/11.
 */
public class MybatisTest {
    private IStudentService studentService;

    @Before
    public void before() {
        //使用"spring.xml"和"spring-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        studentService = (IStudentService) ac.getBean("studentService");
    }

    @Test
    public void testAddUser() {
        studentService.selectByPrimaryKey(2);
        studentService.selectByPrimaryKey(4);
        int uid = 5;
        Student student = new Student();
        student.setAge(22);
        student.setClassid(0);
        student.setName("yeguoxing");
        student.setUid(uid);
//        studentService.insert(student);
        studentService.selectByPrimaryKey(uid);
        studentService.selectByPrimaryKey(uid);
        student.setName("yeguoxing-change");
        studentService.updateByPrimaryKeySelective(student);
        studentService.selectByPrimaryKey(uid);
        studentService.selectByPrimaryKey(uid);
    }

}
