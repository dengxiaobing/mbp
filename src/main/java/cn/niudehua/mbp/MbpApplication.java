package cn.niudehua.mbp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author deng
 */
@SpringBootApplication
@MapperScan("cn.niudehua.mbp.mapper")
public class MbpApplication {

    public static void main(String[] args) {
        SpringApplication.run(MbpApplication.class, args);
    }

}
