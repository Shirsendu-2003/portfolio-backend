package com.portfolio.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.time.Year;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendReply(String to, String subject, String body) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);

            int year = Year.now().getValue();

            // 🔐 (Optional but recommended)
            String safeBody = body
                    .replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");

            String html = """
            <div style="font-family: Arial; background:#f4f6f8; padding:20px;">
                
                <div style="max-width:600px; margin:auto; background:white; border-radius:10px; box-shadow:0 4px 10px rgba(0,0,0,0.1);">
                    
                    <!-- HEADER -->
                    <div style="background:#4f46e5; color:white; padding:15px; text-align:center;">
                        <h2>Shirsendu Message</h2>
                    </div>

                    <!-- BODY -->
                    <div style="padding:20px; color:#333;">
                        <p>Hello,</p>
                        <p>%s</p>

                        <!-- BUTTON -->
                        <div style="text-align:center; margin-top:20px;">
                            <a href="https://shirsendu-pramanik.vercel.app"
                               style="background:#4f46e5;
                                      color:white;
                                      padding:12px 20px;
                                      text-decoration:none;
                                      border-radius:5px;
                                      font-weight:bold;
                                      display:inline-block;">
                                🚀 View Dashboard
                            </a>
                        </div>
                    </div>

                    <!-- FOOTER -->
                    <div style="background:#f1f1f1; padding:10px; text-align:center; font-size:12px;">
                        © %d Shirsendu Pramanik Portfolio
                    </div>

                </div>

            </div>
            """.formatted(safeBody, year);

            helper.setText(html, true);

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Email sending failed: " + e.getMessage(), e);
        }
    }
}
