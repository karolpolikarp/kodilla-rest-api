package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.trello.domain.Mail;
import com.crud.tasks.trello.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "ROZMOWCA 796 XXX 603 / zadwon+86 8876234232";

//    @Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 5000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String taskOrTasks = (size != 1) ? "tasks" : "task";
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "35.224.201.32, ul. Konstruktorska - Warszawa (52.1854999 x 20.9940467",
                null
        ));
    }
}
