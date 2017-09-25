package com.example.demo;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class BtmScriptMonitor extends FileAlterationListenerAdaptor {
    @Autowired
    BtmProps props;
    @Override
    public void onFileCreate(File file) {
        try {
            String btm = file.getCanonicalPath();
            if(btm.endsWith(".btm")){
                org.jboss.byteman.agent.submit.Submit.main(new String[]{"-l", btm});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Is triggered when a file is deleted from the monitored folder
    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onFileChange(File file) {

        try {
            String btm = file.getCanonicalPath();
            if(btm.endsWith("delete.btm")){
                ByteManRunner.SystemExitControl.forbidSystemExitCall();
                org.jboss.byteman.agent.submit.Submit.main(new String[]{"-u", btm});
                ByteManRunner.SystemExitControl.enableSystemExitCall();
            }else {
                org.jboss.byteman.agent.submit.Submit.main(new String[]{"-l", btm});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
