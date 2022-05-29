package com.luisfdalmolin.accountsdailysyncworker;

import com.luisfdalmolin.accountsdailysyncworker.controllers.AccountsDailySyncController;
import com.luisfdalmolin.accountsdailysyncworker.factories.AccountControllersFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

/**
 * @author Luís Felipe Dal Molin <luisfdalmolin@gmail.com>
 */
@SpringBootApplication
public class AccountsDailySyncWorker implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AccountsDailySyncWorker.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            if (args.length > 0) {
                String path = args[0];

                if (path != null && !path.isEmpty()) {
                    File file = new File(path);

                    AccountsDailySyncController controller = AccountControllersFactory.makeCSVAccountsDailySyncController();

                    controller.sync(file);
                }
            }
        } catch (Exception e) {
            System.err.println("Error while loading input file");
            e.printStackTrace();
        }
    }
}
