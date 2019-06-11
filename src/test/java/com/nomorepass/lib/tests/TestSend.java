package com.nomorepass.lib.tests;

import java.awt.Desktop;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nomorepass.lib.Nomorepass;

import junit.framework.TestCase;

public class TestSend extends TestCase {

	public void testGetQrSend() {
		String sitio = "awesome.com";
		String username = "myuser";
		String password = "mypass";
		String extra = "{\"type\":\"pwd\"}";
		Nomorepass nmp = new Nomorepass();
        nmp.init();
        String res="";
		try {
			res = nmp.getQrSend(sitio, username, password,extra );
			System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	        System.out.println(res);
	        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
	        
		} catch (Exception e) {
			e.printStackTrace();
			fail (e.getLocalizedMessage());
		}
		String url = res.replaceAll("nomorepass://SENDPASS", "https://nomorepass.com/getshared/");
		try {
		  Desktop desktop = java.awt.Desktop.getDesktop();
		  URI oURL = new URI(url);
		  desktop.browse(oURL);
		} catch (Exception e) {
		  e.printStackTrace();
		}

		// waiting for password to be retrieved
        Thread t = new Thread(() -> {
            try {
                nmp.ping();
                nmp.stop();
                System.out.println("\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    	        System.out.println(" Password received");
    	        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
            } catch (Exception ex) {
                Logger.getLogger(TestSend.class.getName()).log(Level.SEVERE, null, ex);
                fail (ex.getLocalizedMessage());
            }
        });
        t.start(); 
        try {
			Thread.sleep(60000);
			nmp.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        
	}

}
