package com.example.demo;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.management.ManagementFactory;

@Component
public class ByteManRunner implements CommandLineRunner {

    String pid;

    @Autowired
    BtmProps props;
    @Autowired
    BtmScriptListener listener;

    @Override
    public void run(String... strings) throws Exception {

        SecurityManager sm = new MySecurityManager( System.getSecurityManager() );
        System.setSecurityManager(sm);

        String pid = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

        org.jboss.byteman.agent.install.Install.main(new String[]{"-b", "-Dorg.jboss.byteman.transform.all", pid});



        File folder = new File(props.watchedDir);
        FileAlterationObserver observer = new FileAlterationObserver(folder);
        FileAlterationMonitor monitor =
                new FileAlterationMonitor(props.pollingInterval);


        listener.installAll();
        observer.addListener(listener);
        monitor.addObserver(observer);
        monitor.start();

    }
}
