package util;

import org.apache.commons.mail.*;
import play.Play;
import play.Configuration;
import javax.inject.Inject;

public class SendEmail {

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
            email.setHostName(Play.application().configuration().getString("smtp.host"));
            email.setSmtpPort(Integer.parseInt(Play.application().configuration().getString("smtp.port")));

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
