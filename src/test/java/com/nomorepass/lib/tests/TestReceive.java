package com.nomorepass.lib.tests;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nomorepass.lib.Nomorepass;

import junit.framework.TestCase;

public class TestReceive extends TestCase {

	public void testGetQrText() {
		Nomorepass nmp = new Nomorepass();
        nmp.init();
        String res = "";
        // Getting the url to show as QR
		try {
			res = nmp.getQrText("gmail.com");
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
		String url = res.replaceAll("nomorepass://", "https://nomorepass.com/getshared/");
		try {
		  Desktop desktop = java.awt.Desktop.getDesktop();
		  URI oURL = new URI(url);
		  desktop.browse(oURL);
		} catch (Exception e) {
		  e.printStackTrace();
		}
        // Waiting for the QR to be read
        Thread thread = new Thread(){
            public void run(){
            	try {
                    nmp.start();
                } catch (Exception ex) {
                    Logger.getLogger(TestReceive.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          };
        thread.start();
        // Waiting just 20 seconds to receive the pass (could be 2 hours)
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        nmp.stop();
        
        System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("user:" + nmp.getUser() + ", password:" + nmp.getPassword() + " extra:" + nmp.getExtra());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");

	}

}
