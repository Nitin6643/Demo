package com.mail.main;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.mail.util.EmailUtil;

public class SendEmail {
	private static final Logger LOGGER = LogManager.getLogger(SendEmail.class);

	public static void main(String[] args) {
		String[] to = { "abhishekjainronu68@gmail.com", "niks110889@gmail.com" };
		String subject = "TEST1";
		String msg = "This is TEST email1";
		final String from = "nitinsharma1108@gmail.com";
		final String password = "lhlwmcrfudpuolhb";

		Boolean bool;
		try {
			bool = EmailUtil.sendEmail(from, password, subject, msg, to);

			if (bool) {
				LOGGER.info("Email Sent successfully!!!");
				LOGGER.info("Test Message from branch!!!");
			} else {
				LOGGER.error("Email sending failed!!!");
			}

		} catch (Exception e) {
			LOGGER.error("Exception occored while sending Email!!!");
			LOGGER.error(e.fillInStackTrace());
			e.printStackTrace();
		}

	}
}
