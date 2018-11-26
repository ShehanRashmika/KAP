package classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class EXTCLASS {

    public static boolean isLogin = false;
    public static boolean isAdmin = false;

    public static void FrameAdd(Object obj, JPanel pname) {

        pname.removeAll();
        pname.repaint();
        pname.revalidate();
        pname.add((Component) obj);
    }

    static Date date;
    static SimpleDateFormat sdf;

    public static void setDateTime(JLabel label, String format) {

        Timer t = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                date = new Date();
                sdf = new SimpleDateFormat(format);
                label.setText(sdf.format(date));

            }
        });
        t.start();

    }

    public static void setDateTime_2(JTextField label, String format) {

        Timer t = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                date = new Date();
                sdf = new SimpleDateFormat(format);
                label.setText(sdf.format(date));

            }
        });
        t.start();

    }


    public static void sendMail(String too, String sub, String msgg) {

        try {
            String host = "smtp.gmail.com";
            String user = "hotelroyalsapphire@gmail.com";
            String pass = "hotel123#";//12713336
            String to = too;//nisankaakash@gmail.com
            String from = "hotelroyalsapphire@gmail.com";
            String subject = sub;
            String messageText = "W E L C O M E" + "\n" + "\n" + "\n" + msgg + "\n" + "\n" + "Contact us for any problem," + "\n" + "0119877678" + "\n" + "0776765678" + "\n" + "0756765456" + "\n" + "\n" + "Thanks & Best Regards," + "\n" + "The Management of the Hotel Royal Sapphire!";
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(messageText);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();

        } catch (Exception ex) {

            System.out.println(ex);
        }

    }

    public static void loadTable(JTable tb, String query01, String query02) throws Exception {
        DefaultTableModel dtm = (DefaultTableModel) tb.getModel();
        dtm.setRowCount(0);
        ResultSet rs = DB.search(query01 + query02);
        while (rs.next()) {
            Vector v = new Vector();
            for (int i = 1; i < tb.getColumnCount() + 1; i++) {
                v.add(rs.getString(i));
            }
            dtm.addRow(v);

        }

    }

    public static String diffrentBetweenDates(Date d1, Date d2) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
            long diff = d2.getTime() - d1.getTime();
            if (d2.getTime() < d1.getTime()) {
                return "error";
            } else {
                long sec = diff / 1000 % 60;
                long min = diff / (60 * 1000) % 60;
                long hours = diff / (60 * 60 * 1000);
                long days = hours / 24;
                int dates = (int) days;

                return String.valueOf(dates);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final String str = "abcdefghijklmnopqrstuvwxyzk_";

    public static String encryption(String plaint, int key) {
        plaint = plaint.toLowerCase();
        String cipher = "";
        for (int i = 0; i < plaint.length(); i++) {
            int charpose = str.indexOf(plaint.charAt(i));
            int keyval = (charpose + key) % 26;
            char replaceval = str.charAt(keyval);
            cipher = cipher + replaceval;
        }
        return cipher;
    }

    public static String decrypt(String ciper, int key) {
        ciper = ciper.toLowerCase();
        String paint = "";
        for (int i = 0; i < ciper.length(); i++) {
            int charpose = str.indexOf(ciper.charAt(i));
            int keyval = (charpose - key) % 26;
            if (keyval < 0) {
                keyval = str.length() + keyval;
            }
            char replaceval = str.charAt(keyval);
            paint = paint + replaceval;
            paint = paint.replace("_", " ");
        }
        return paint;
    }

}
