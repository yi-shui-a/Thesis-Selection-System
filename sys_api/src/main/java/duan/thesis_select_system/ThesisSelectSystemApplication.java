package duan.thesis_select_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("duan.thesis_select_system.mapper")
@SpringBootApplication
public class ThesisSelectSystemApplication {//

    public static void main(String[] args) {
        SpringApplication.run(ThesisSelectSystemApplication.class, args);
    }

}
