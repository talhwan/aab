package com.thc.sprboot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@Component
public class SendEmail {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final KeysProperties keysProperties;
	public SendEmail(
			KeysProperties keysProperties
	) {
		this.keysProperties = keysProperties;
	}
	public String host_name = "http://localhost:8080";
	public String from_name = "fym";

	public void send(String to, String title, Map<String, Object> content, String type) throws Exception {
		//String host = "smtp.gmail.com";
		String host = "smtp.naver.com";
		String from = keysProperties.getEmailUsername(); // 보내는 사람의 이메일 주소
		String password = keysProperties.getEmailPassword(); // 보내는 사람의 이메일 계정 비밀번호

		// SMTP 프로토콜 설정
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");
		//
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

		// 보내는 사람 계정 정보 설정
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		// 메일 내용 작성
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(from));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

		String domain = "http://localhost:8080";
		String newContent = "";
		if("send".equals(type)) {
			newContent = "<div style=\"margin:0;padding:0;background-color:#ffffff\">\n" +
					"        <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;Margin:0 auto;background-color:#ffffff\">\n" +
					"            <tbody>\n" +
					"                <tr>\n" +
					"                    <td style=\"font-size:0\">&nbsp;</td>\n" +
					"                    <td align=\"center\" valign=\"top\" style=\"width:400px\">\n" +
					"                        <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%\">\n" +
					"                            <tbody>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\" font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-weight:bold;font-size:24px;line-height:36px;color:#191919;text-align:left\">\n" +
					"\t\t\t\t인증번호를 확인해주세요\n" +
					"\t\t\t\t</td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"center\" style=\"padding-top: 32px;\">\n" +
					"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 100%;\">\n" +


					"											<tbody>\n" +
					"                                                <tr>\n" +
					"                                                    <td style=\"border-radius:16px;background-color:#3F36F4\">\n" +
					"                                                        <div style=\"display:block;font-size:15px; color:#ffffff;text-decoration:none;border-radius:16px;padding:15px 0;width:100%;font-weight:500;text-align:center\">"+content.get("num")+"</div>\n" +
					"                                                    </td>\n" +
					"                                                </tr>\n" +
					"                                            </tbody>" +

					"                                        </table>\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top:48px; font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-weight:500;font-size:16px;line-height:28px;color:#555555;text-align:left\">\n" +
					"\t\t\t\t인증번호를 요청한 사람이 본인이 아닌 경우,<br>보안을 위해 고객센터로 연락해주시기 바랍니다\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top: 15px;\">\n" +
					"                                        <a href=\""+domain + "/customer"+"\"><img src=\""+domain+"/resources/frontuser/assets/images/mail_form/btn_center.png\" alt=\"고객센터\" style=\"margin: 0;padding: 0;vertical-align: top; width:83px;\"></a>\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                            </tbody>\n" +
					"                        </table>\n" +
					"                    </td>\n" +
					"                    <td style=\"font-size:0\">&nbsp;</td>\n" +
					"                </tr>\n" +
					"            </tbody>\n" +
					"        </table>\n" +
					"    </div>";

		} else if("pw".equals(type)) {
			newContent = "<div style=\"margin:0;padding:0;background-color:#ffffff\">\n" +
					"        <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;Margin:0 auto;background-color:#ffffff\">\n" +
					"            <tbody>\n" +
					"                <tr>\n" +
					"                    <td style=\"font-size:0\">&nbsp;</td>\n" +
					"                    <td align=\"center\" valign=\"top\" style=\"width:400px\">\n" +
					"                        <table align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%\">\n" +
					"                            <tbody>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding: 60px 0 48px;\">\n" +
					"                                        <a href=\"\" target=\"_blank\">\n" +
					"                                            <img src=\""+domain+"/resources/frontuser/assets/images/mail_form/logo.png\" alt=\"fym\" style=\"margin: 0;padding: 0;vertical-align: top; width:120px;\">\n" +
					"                                        </a>\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\" font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue', 'Segoe UI', 'Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-weight:bold;font-size:24px;line-height:36px;color:#191919;text-align:left\">\n" +
					"\t\t\t\t비밀번호를 변경해주세요\n" +
					"\t\t\t\t</td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top:3px; font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-weight:500;font-size:16px;line-height:28px;color:#555555;text-align:left\">\n" +
					"\t\t\t\t아래 버튼을 누르면 비밀번호 변경 페이지로 변경돼요\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"center\" style=\"padding-top: 32px;\">\n" +
					"                                        <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width: 100%;\">\n" +
					"                                            <tbody>\n" +
					"                                                <tr>\n" +
					"                                                    <td style=\"border-radius:16px;background-color:#3F36F4\">\n" +
					"                                                        <a href=\""+domain + "/tbuser/resetpw?email="+content.get("email") + "&num="+ content.get("num")+"\" target=\"_blank\" style=\"display:block;font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-size:15px; color:#ffffff;text-decoration:none;border-radius:16px;padding:15px 0;width:100%;font-weight:500;text-align:center\">비밀번호 변경</a>\n" +
					"                                                    </td>\n" +
					"                                                </tr>\n" +
					"                                            </tbody>\n" +
					"                                        </table>\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top:48px; font-family:'Pretendard', Pretendard, -apple-system, BlinkMacSystemFont, system-ui, Roboto, 'Helvetica Neue','Apple SD Gothic Neo', 'Noto Sans KR', 'Malgun Gothic', 'Apple Color Emoji', sans-serif; font-weight:500;font-size:16px;line-height:28px;color:#555555;text-align:left\">\n" +
					"\t\t\t\t비밀번호 변경을 요청한 사람이 본인이 아닌 경우,<br>보안을 위해 고객센터로 연락해주시기 바랍니다\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top: 15px;\">\n" +
					"                                        <a href=\""+domain + "/customer"+"\"><img src=\""+domain+"/resources/frontuser/assets/images/mail_form/btn_center.png\" alt=\"고객센터\" style=\"margin: 0;padding: 0;vertical-align: top; width:83px;\"></a>\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                                <tr>\n" +
					"                                    <td align=\"left\" style=\"padding-top:80px\">\n" +
					"                                        <img src=\""+domain+"/resources/frontuser/assets/images/mail_form/footer.png\" alt=\"find your media, fym\" style=\"margin: 0;padding: 0;vertical-align: top; width:213px;\">\n" +
					"                                    </td>\n" +
					"                                </tr>\n" +
					"                            </tbody>\n" +
					"                        </table>\n" +
					"                    </td>\n" +
					"                    <td style=\"font-size:0\">&nbsp;</td>\n" +
					"                </tr>\n" +
					"            </tbody>\n" +
					"        </table>\n" +
					"    </div>";
		}


		msg.setSubject(title);
		//msg.setText(newContent);
		/*msg.setContent(content, "text/html;charset=utf-8");*/
		msg.setContent(newContent, "text/html;charset=utf-8");



		// 메일 보내기
		Transport.send(msg);
	}
}
