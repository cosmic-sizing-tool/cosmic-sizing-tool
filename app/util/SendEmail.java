package util;

//import play.libs.Mail.*;
import org.apache.commons.mail.*;

public class SendEmail {

    //final private String sender = "cosmic@do-not-respond.com";
    private String  from,
                    to,
                    subject,
                    content;


    public SendEmail(String from, String to, String subject, String content) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public boolean send() {
        Email email = new SimpleEmail();
        try {
            email.setHostName("mail.uqam.ca");
            email.setSmtpPort(25);

            email.setFrom(from);
            email.addTo(to);
            email.setSubject(subject);
            email.setMsg(content);
            email.send();
            return true;
        }
        catch (org.apache.commons.mail.EmailException e) {

        }
        return false;
    }

}
