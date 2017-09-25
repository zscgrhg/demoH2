package com.example.demo;

import java.security.Permission;
import java.util.Arrays;

public class MySecurityManager extends SecurityManager {
    private SecurityManager baseSecurityManager;

    public MySecurityManager(SecurityManager baseSecurityManager) {
        this.baseSecurityManager = baseSecurityManager;
    }

    @Override
    public void checkPermission(Permission permission) {
        if (permission.getName().startsWith("exitVM")) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
           if(Arrays.asList(stackTrace).stream().anyMatch(s->s.getClassName().startsWith("org.jboss.byteman.agent"))){
               throw new SecurityException("System exit not allowed");
           }else {
               for (StackTraceElement stackTraceElement : stackTrace) {
                   System.out.println(stackTraceElement.getClassName());
               }
           }
        }
        if (baseSecurityManager != null) {
            baseSecurityManager.checkPermission(permission);
        } else {
            return;
        }
    }
}
