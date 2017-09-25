package com.example.demo;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.security.Permission;

@Component
public class ByteManRunner implements CommandLineRunner {

    String pid;

    @Override
    public void run(String... strings) throws Exception {



        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

        org.jboss.byteman.agent.install.Install.main(new String[]{"-b", "-Dorg.jboss.byteman.transform.all", pid});
        System.out.println(pid);
        System.out.println(new File(".").getAbsolutePath());
        //org.jboss.byteman.agent.submit.Submit.main(new String[]{"D:\\codehaus\\demoH2\\src\\main\\resources\\params.btm"});


        File folder = new File("D:\\codehaus\\demoH2\\btm");
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor =
                new FileAlterationMonitor(5000);
        FileAlterationListener listener = new BtmScriptMonitor();
        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();

    }

    public static class SystemExitControl {

        public static class ExitTrappedException extends SecurityException {
        }

        public static void forbidSystemExitCall() {
            final SecurityManager securityManager = new SecurityManager() {



                @Override
                public void checkPermission(Permission permission) {
                    if (permission.getName().contains("exitVM")) {
                        throw new ExitTrappedException();
                    }
                }
            };
            System.setSecurityManager(securityManager);
        }

        public static void enableSystemExitCall() {
            System.setSecurityManager(null);
        }
    }

}
