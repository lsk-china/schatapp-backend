package indi.midreamsheep.schatapp.backend;

import indi.midreamsheep.schatapp.backend.chat.ChatHandlerMapper;
import indi.midreamsheep.schatapp.backend.chat.scan.ChatScanConfiguration;
import indi.midreamsheep.schatapp.backend.netty.ChatServer;
import jakarta.annotation.Resource;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * 整个项目的启动类
 * */
/*启动类标识*/
@SpringBootApplication
/*扫描指定的聊天处理器*/
@ChatScanConfiguration("indi.midreamsheep.schatapp.backend.chat")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
/*扫描指定路径下的mapper*/
@MapperScan("indi.midreamsheep.schatapp.backend.dao.mysql")
public class ApplicationStarter implements CommandLineRunner {

    /**用于启动netty通讯服务*/
    @Resource
    private ChatServer chatServer;

    /**用于注册netty服务的处理器*/
    @Resource
    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }


    /**回调启动netty服务*/
    @Override
    public void run(String... args) throws Exception {
        ChatHandlerMapper.init(context);
        chatServer.run(7524);
    }
}

