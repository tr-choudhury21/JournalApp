package com.journal.journalApp.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface SecurityConfigV2 {
    void configure(AuthenticationManagerBuilder auth) throws Exception;
}
