package com.example.demo;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class BtmScriptListener extends FileAlterationListenerAdaptor {
    @Autowired
    BtmProps props;

    @Override
    public void onFileCreate(File file) {
        reset();
    }

    // Is triggered when a file is deleted from the monitored folder
    @Override
    public void onFileDelete(File file) {
        reset();
    }

    @Override
    public void onFileChange(File file) {
        reset();
    }

    public void reset(){
        clearBTM();
        installAll();
    }

    public void installAll() {

        try {
            Stream<Path> list = Files.list(Paths.get(props.watchedDir));
            list.forEach(p -> {
                installBTM(p.toFile());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clearBTM() {
        try {
            org.jboss.byteman.agent.submit.Submit.main(new String[]{"-u"});
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void installBTM(File file) {
        try {
            String btm = file.getCanonicalPath();
            if (btm.endsWith(".btm")) {
                org.jboss.byteman.agent.submit.Submit.main(new String[]{"-l", btm});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
