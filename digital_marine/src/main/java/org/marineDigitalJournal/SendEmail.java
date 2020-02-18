package org.marineDigitalJournal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jasypt.util.text.BasicTextEncryptor;

import javax.activation.DataSource;

public class SendEmail {
	final String username = "bluedigitalmaritimejournal@gmail.com";
	final String EMAIL_PASS = "iTHRIr52EFWlx5HvRX3FvvZCm2Gy8RkJ";

	private String decrypt(String encodedText) {
		String decodedText;
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("digital_marine");

		decodedText = textEncryptor.decrypt(encodedText);
		return decodedText;
	}

	public void send(String email) {
		// Put recipient’s address
		String to = email;

		String from = "bluedigitalmaritimejournal@gmail.com";

		// Paste host address from the SMTP settings tab in your Mailtrap Inbox
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");

		String password = decrypt(EMAIL_PASS);

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
		session.getProperties().put("mail.smtp.starttle.enable", "true");

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field
			message.setFrom(new InternetAddress(from));

			// Set To: header field
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Harmful Algal Blooms Alert!");

			// Put the content of your message
			message.setText(
					"Dear user, this is an Harmful Algal Blooms alert from BlueDigitalMaritimeJournal. It is recommended to test the water in the map specified area.");

			// This mail has 2 part, the BODY and the embedded image
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>Dear user, this is an Harmful Algal Blooms alert from BlueDigitalMaritimeJournal. It is recommended to test the water in the map specified area.</H1><img src=/application/pi/eclipse-workspace/blueMaritimeDigitalJournal/src/main/java/org/marineDigitalJournal/presentation/swing/CHL_predictions_next_day.png>";
			messageBodyPart.setContent(htmlText, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// second part (the image)
			messageBodyPart = new MimeBodyPart();

			String tempDir = System.getProperty("user.dir").toString();
			System.out.println("here");
			File file = new File(
					this.getClass().getClassLoader().getResource("CHL_predictions_next_day.png").getFile());

			System.out.println("1" + file.exists());
			System.out.println("4" + file.canRead());
			System.out.println("2" + file.getAbsolutePath());
			String tempFile = "CHL_predictions_next_day.png";
			InputStream in = this.getClass().getClassLoader().getSystemResourceAsStream(tempFile);

			File file1 = new File(System.getProperty("user.dir") + File.separator + tempFile);

			try {
				FileOutputStream out = new FileOutputStream(file1);

				int read;

				byte[] bytes = new byte[1024];

				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);

					System.out.println("6" + file1.canRead());
					System.out.println("5" + file1.getAbsolutePath());

				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			DataSource fds = (DataSource) new FileDataSource(file1.getAbsolutePath().toString());

			messageBodyPart.setDataHandler(new DataHandler((javax.activation.DataSource) fds));
			messageBodyPart.setHeader("Content-ID", "image");

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
			// Send message

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
