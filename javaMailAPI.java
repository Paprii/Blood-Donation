package com.example.bloodforlife;

import android.content.Context;
import android.media.MediaCasException;
import android.provider.SyncStateContract;
import android.se.omapi.Session;

import androidx.loader.content.AsyncTaskLoader;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javaMailAPI extends AsyncTask<void, void, void> {
    private Context context;

    private Session session;
    private String email,subject,message;

    /**
     * This function points all the variable to current variable */

    public javaMailAPI(Context context, String main, String subject, String message) {
        this.context = context;
        this.session = session;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override

    /**
     * this function handle all the background activities of email such as mail host,authority and port
     *
     * */

    protected void doInBackground(void... voids){
        Properties properties=new Properties();
        properties.put("mail.smtp.host" ,"smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port" ,"465");
        properties.put("mail.smtp.socketFactory.class" ,"javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth" ,"true");
        properties.put("mail.smtp.port" ,"465");
        /**
         * set session value from PasswordAuthentication
         */
        session =Session.getDefaultInstance(properties, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(utils.EMAIL,utils.PASSWORD);
            }
    });
        MimeMessage mimeMessage = new MimeMessage(session);
        /**
         * This part is for exception handling
         */
        try {
            mimeMessage.setFrom(new InternetAddress(utils.EMAIL));
            mimeMessage.addRecipients(Message.RecipientType.TO, String.valueOf(new InternetAddress(email)));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);
            Transport.send(mimeMessage);
        }catch (MediaCasException e) {
            e.printStackTrace();

        }

    }

}
