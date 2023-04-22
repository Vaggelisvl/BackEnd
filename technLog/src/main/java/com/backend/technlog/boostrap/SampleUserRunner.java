package com.backend.technlog.boostrap;

import com.backend.technlog.base.BaseComponent;
import com.backend.technlog.domain.User;
import com.backend.technlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("sample-user")
@Component
@RequiredArgsConstructor
public class SampleUserRunner extends BaseComponent implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) {
        try {
            List<User> users = List.of(
                    User.builder().email("mymail1@gmail.com")
                            .firstName("Ivan")
                            .lastName("Moody")
                            .passWord("123123njhkjBjakd").build(),
                    User.builder().email("mymail2@gmail.com")
                            .firstName("Chris")
                            .lastName("Kael")
                            .passWord("123123njhkjBjakd").build(),
                    User.builder().email("mymail3@gmail.com")
                            .firstName("Jason")
                            .lastName("Hook")
                            .passWord("123123njhkjBjakd").build(),
                    User.builder().email("mymail4@gmail.com")
                            .firstName("Jeremy")
                            .lastName("Spencer")
                            .passWord("123123njhkjBjakd").build());
            logger.debug("Saving some users");
            userService.saveAll(users);
        } catch (DataIntegrityViolationException dive) {
            logger.warn("Unable to persist sample customer list as they probably already exist!");
        } catch (DataAccessException dae) {
            logger.error("Error occurred while interacting with underlying database, see the details", dae);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        logger.debug("Reporting all customers");
        userService.findAll().forEach(i->logger.debug("{}",i));
        logger.info("Does user exist? {}.", (userService.findByEmail("mymail1@gmail.com") != null));
        logger.info("Does user exist? {}.", (userService.findByEmail("nosuchemail@gmail.com") != null));

    }
}
