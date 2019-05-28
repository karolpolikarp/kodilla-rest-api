package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {
    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardMail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending task to Trello");


        Context context = new Context();
        context.setVariable("preview", "Trello app - new card added");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://karolpolikarp.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        context.setVariable("company", companyConfig.getCompanyName() + ", " +  companyConfig.getCompanyGoal()
                + ", " + companyConfig.getCompanyEmail() + ", " + companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("goodbye", "Goodbye!");
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledMail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        long size = taskRepository.count();
        String taskOrTasks = size == 1 ? " task" : " tasks";
        message = "Currently in database you've got: " + size + taskOrTasks;

        Context context = new Context();
        context.setVariable("preview", "Trello app - your daily reminder");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://karolpolikarp.github.io/");
        context.setVariable("button", "See tasks");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("company", companyConfig.getCompanyName() + ", " +  companyConfig.getCompanyGoal()
                + ", " + companyConfig.getCompanyEmail() + ", " + companyConfig.getCompanyPhone());
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/scheduled-trello-mail", context);
    }
}