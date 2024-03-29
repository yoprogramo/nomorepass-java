# nomorepass-java
Java Libraries for NoMorePass

You can use this library in combination with nomorepass app (nomorepass.com) to send or receive passwords in a safe an easy way.

## How to use

You can import the project and make the .jar using maven (mvn install) and then include as dependency:

```
<dependency>
	<groupId>com.nomorepass.lib</groupId>
	<artifactId>nomorepass</artifactId>
	<version>1.0</version>
</dependency>
```

### To send passwords

```
String sitio = "awesome.com";
String username = "myuser";
String password = "mypass";
String extra = "{\"type\":\"pwd\"}";
Nomorepass nmp = new Nomorepass();
nmp.init();
String res=nmp.getQrSend(sitio, username, password,extra );;

// res contains now the text you should display in QR form
// when the user scans this QR with nomorepass the password
// is transferred to the phone

// If you want to wait until the user receive the password:
nmp.ping(); 

```

### To receive a password

```
Nomorepass nmp = new Nomorepass();
nmp.init();
String res = nmp.getQrText("mypassword");
System.out.println(res);
// res contains now the text you should display in QR form
// the user should select a password in nomorepass app and
// scan the qr to transfer the password to the app

// Wait until the user scan the QR code
nmp.start();
System.out.println("user:" + nmp.getUser() + ", password:" + nmp.getPassword() + " extra:" + nmp.getExtra());
```

### Examples

You can execute the examples in the src/test/java folder as JUnit tests.
This examples uses the system browser to show the QR code generated

* TestSend.java : example to send a password to mobile phone
* TestReceive.java : example to receive a password sent from mobile phone

## More info

visit https://nomorepass.com or open here an issue

## Other libraries

* node/js: https://github.com/yoprogramo/nomorepass
* python: https://github.com/yoprogramo/nomorepass-py
* php: https://github.com/yoprogramo/nomorepass-php
* Dart/Flutter: https://github.com/yoprogramo/nomorepass-dart

## How to use NoMorePass

1. Download and install the mobile app

* [android] https://play.google.com/store/apps/details?id=com.biblioeteca.apps.NoMorePass
* [ios] https://itunes.apple.com/us/app/no-more-pass/id1199780162?l=es&ls=1&mt=8

3. Open it and create a new password (or use some of yours)
4. Then you can scan the qrcode generated by the library to send securely this password to your app or send/update passwords from your code to the app.

(c) 2019 Nomorepass.com



