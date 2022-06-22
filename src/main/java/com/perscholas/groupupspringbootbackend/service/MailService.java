package com.perscholas.groupupspringbootbackend.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.perscholas.groupupspringbootbackend.exception.GroupUpException;
import com.perscholas.groupupspringbootbackend.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
	private final JavaMailSender mailSender;
	private final MailContentBuilder mailContentBuilder;
	
	@Async
	void sendMail(NotificationEmail notificationEmail) {
		MimeMessagePreparator messagePreparator = mimeMessage->{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("groupup@email.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(mailContentBuilder.build(notificationEmail.getBody()));
		};
		try {
			mailSender.send(messagePreparator);
			log.info("Activation email sent!!");
		}catch(MailException e){
			throw new GroupUpException("Exception occured when sending mail to " + notificationEmail.getRecipient());
		}
	}
	
	@Async
	void sendAdminVerification(String userEmail, String token) {
		MimeMessagePreparator messagePreparator = mimeMessage->{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(userEmail);
			messageHelper.setTo("a1ccormier@gmail.com");
			messageHelper.setSubject(userEmail + " is requesting Admin access to Group Up");
			messageHelper.setText("click link to grant access \n to register their account click the linke below \nhttp://localhost:8080/api/auth/accountVerification/" + token);
		};
		try {
			mailSender.send(messagePreparator);
			log.info("Admin request sent");
		}catch(MailException e) {
			throw new GroupUpException("Exception occured when sending mail");
		}
	}

}
