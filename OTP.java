import java.util.ArrayList;
import java.util.Random;
import java.lang.Thread;

public class OTP {
    public static void main(String[] args) {
        OTP otp = new OTP();
        String p1 = otp.generatePassword(6);
        String p2 = otp.generatePassword(100);
        otp.usePassword();

        p1 = otp.generatePassword(10);

        p2 = otp.generatePassword(5);
    }

    public String generatePassword(int n) {
        ArrayList<String> oldPw = new ArrayList<>();

        String pwCharas = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()";

        char[] pw = new char[n];

        Random randChara = new Random();
        for (int i = 0; i < n; i++) {
            pw[i] = pwCharas.charAt(randChara.nextInt(pwCharas.length()));
        }
        String password = new String(pw);

        try {
            Thread.sleep(2500);
            oldPw.add(password);
            return password;
        }
        catch (Exception e) {
            return oldPw.get(0);
        }
    }

    public void usePassword(){
        try{
            Thread.sleep(6000);
        }
        catch (Exception e) {
            System.out.println("");
        }
    }
}
