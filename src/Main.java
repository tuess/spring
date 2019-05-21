import com.entity.AllCollectionType;
import com.entity.Course;
import com.entity.Student;
import com.service.StudentServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void springioc(){
//        com.student.Student student = new com.student.Student();
//        student.setAge(18);
//        student.setId(10001);
//        student.setName("zhangsan");
//        System.out.println(student);

        //spring上下文对象：context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }

    public static void learn(){
        Student student = new Student();
        student.learnHtml();
        student.learnJava();
    }

    public static void learnfactory(){
        Student student = new Student();
        student.learn("Java");
    }

    public static void learnioc(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc中获取学生
        Student student = (Student) context.getBean("student");
        student.learn("htmlCourse");
    }

    public static void testDi(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Course course = (Course) context.getBean("course");
        course.showInfo();
    }

    public static void collection(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AllCollectionType collectionType = (AllCollectionType) context.getBean("collection");
        System.out.println(collectionType);
    }

    public static void transaction(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从ioc中获取学生
        Student student = (Student) context.getBean("student");
        StudentServiceImpl studentService = (StudentServiceImpl) context.getBean("studentService");
        studentService.addStudent(student);
    }

    public static void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        StudentServiceImpl studentService = (StudentServiceImpl) context.getBean("studentService");
        student.setAge(23);
        student.setId(10001);
        student.setName("zhangsan");
        studentService.addStudent(student);
        studentService.deleteStudent();
    }

    public static void main(String[] args) {
//        springioc();
//        learn();
//        learnfactory();
//        learnioc();
//        testDi();
//        collection();
//        transaction();
        testAop();
    }
}
