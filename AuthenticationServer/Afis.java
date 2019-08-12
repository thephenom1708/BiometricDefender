package com.bachmanity.bchain;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;

import java.math.BigInteger;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Afis {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(BchainApplication.class, args);
    }
}

@RestController
class Controller{



    @PostMapping("/getstring")
    public String getstring(@RequestParam("s") String s)
    {
        String ss= new String(AESCrypt.decrypt("AAAAAAAAAAAAAAAA", s));
        System.out.println(ss.equals("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"));
        return s;
    }



    @PostMapping("/sendEnc")
    public String sendEnc(@RequestParam(value="username") String username) {
        byte[] original = null;
        byte[] probe = null;
        String result = "false";

        //get username and encrypted biometric
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection res_con = DriverManager.getConnection("jdbc:mysql://c2.cnols8jopzvj.us-east-1.rds.amazonaws.com:3306/c2", "c2", "c2c2c2c2");
            PreparedStatement ps = res_con.prepareStatement("Select * from register where username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next();
            original = rs.getBytes(2);



            PreparedStatement ps1 = res_con.prepareStatement("SELECT * from c2 where id=?");
            ps1.setInt(1, 1);
            ResultSet rs1 = ps1.executeQuery();

            if (rs1.next()) {
                probe = rs1.getBytes(2);
            }

            PreparedStatement ps3 = res_con.prepareStatement("DELETE from c2");
            ps3.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }
        //fetch key from username
        String key = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://keydb.cnols8jopzvj.us-east-1.rds.amazonaws.com:3306/keydb", "keydb", "keydb123");
            PreparedStatement query = conn.prepareStatement("SELECT * from keydb where username = ?");
            query.setString(1, username);
            ResultSet rs = query.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //fetch distributed stored encrypted images
        String s1 = "";
        String s2 = "";
        String s3 = "";


        String POST_URL1 = "http://192.168.43.59:8000/fragmenting/fragmentRequest/";
        String POST_URL2 = "http://192.168.43.68:8000/fragmenting/fragmentRequest/";
        String POST_URL3 = "http://192.168.43.192:8000/fragmenting/fragmentRequest/";

        String POST_PARAMS = "username="+username;

        HttpSendVote send1 = new HttpSendVote(POST_URL1, POST_PARAMS);
        HttpSendVote send2 = new HttpSendVote(POST_URL2, POST_PARAMS);
        HttpSendVote send3 = new HttpSendVote(POST_URL3, POST_PARAMS);

        try {
            s1 = send1.sendPOST();
            System.out.println(s1);
            s2 = send2.sendPOST();
            System.out.println(s2);
            s3 = send3.sendPOST();
            System.out.println(s3);
        } catch (Exception e) {
            System.out.println("HTTP SEND Error");
            e.printStackTrace();
        }
//        //algorithm to match all together
        if(s1.equals("false") || s2.equals("false") || s3.equals("false"))
        {
            result="false";
            return result;
        }

        byte[] s11 = s1.getBytes();
        byte[] s12 = s2.getBytes();
        byte[] s13 = s3.getBytes();

        byte[] data1 = new byte[s11.length + s12.length + s13.length];

        String s111=null;
        String s112=null;
        String s113=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://c2.cnols8jopzvj.us-east-1.rds.amazonaws.com:3306/c2", "c2", "c2c2c2c2");
            PreparedStatement ps=conn.prepareStatement("Select * from strings where username=?");
            ps.setString(1,username);

            ResultSet rs1=ps.executeQuery();
            rs1.next();
            s111=rs1.getString(2);
            s112=rs1.getString(3);
            s113=rs1.getString(4);

            //PreparedStatement ps2=conn.prepareStatement("Delete from strings where username=?");
            //ps2.setString(1,username);

            //ps2.executeUpdate();


        }catch(Exception e)
        {
            e.printStackTrace();
        }



        int k = 0;

        for (int i = 0; i < s11.length; i++, k++)
            data1[k] = s11[i];
        for (int i = 0; i < s12.length; i++, k++)
            data1[k] = s12[i];
        for (int i = 0; i < s13.length; i++, k++)
            data1[k] = s13[i];

        int difference=0;

        for(int i=0,j=0; i<s111.length() && i<s1.length();i++,j++)
        {
            if(s111.charAt(i)!=s1.charAt(i))
                difference++;
        }
        difference += Math.abs(s111.length()-s1.length());

        for(int i=0,j=0; i<s112.length() && i<s2.length();i++,j++)
        {
            if(s112.charAt(i)!=s2.charAt(i))
                difference++;
        }
        difference += Math.abs(s111.length()-s1.length());

        for(int i=0,j=0; i<s113.length() && i<s3.length();i++,j++)
        {
            if(s113.charAt(i)!=s3.charAt(i))
                difference++;
        }
        difference += Math.abs(s111.length()-s1.length());

        boolean diff=true;

        float percentage= (difference*100)/(s1.length()+s2.length()+s3.length());
        if(difference>=40)
        {
            diff=false;
        }


        try {

//            byte[] encStringDecoded1 = Base64.getDecoder().decode(s11);
//            byte[] encStringDecoded2 = Base64.getDecoder().decode(s12);
//            byte[] encStringDecoded3 = Base64.getDecoder().decode(s13);
////
//            String ss1 = new String(encStringDecoded1, "UTF-8");
//            String ss2 = new String(encStringDecoded2, "UTF-8");
//            String ss3 = new String(encStringDecoded3, "UTF-8");
//
//            byte[] encStringDecoded11 = ss1.getBytes("UTF-8");
//            byte[] encStringDecoded12 = ss2.getBytes("UTF-8");
//            byte[] encStringDecoded13 = ss3.getBytes("UTF-8");
//
//            byte[] totEnc = new byte[encStringDecoded11.length + encStringDecoded12.length + encStringDecoded13.length];
//
//            int j = 0;
//            for (int i = 0; i < encStringDecoded11.length; j++, i++)
//                totEnc[j] = encStringDecoded11[i];
//            for (int i = 0; i < encStringDecoded12.length; j++, i++)
//                totEnc[j] = encStringDecoded12[i];
//            for (int i = 0; i < encStringDecoded13.length; j++, i++)
//                totEnc[j] = encStringDecoded13[i];
//
//            System.out.println("length " + totEnc.length);
//
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            Key keyer = new SecretKeySpec(("AAAAAAAAAAAAAAAA").getBytes("UTF-8"), "Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, keyer);
            //            data2 = cipher.doFinal(totEnc);
            //run afis to match encString && added strings


            int threshold=40;
            FingerprintTemplate probe2 = new FingerprintTemplate().dpi(500).create(probe);
            FingerprintTemplate candidate = new FingerprintTemplate().dpi(500).create(original);
            double score = new FingerprintMatcher().index(probe2).match(candidate);
            boolean matches = (score >= threshold) ;
            System.out.println(matches);
            if (matches) {
                result = "true";
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
            return result;


    }



    @PostMapping("/getresult")
    public String returnResult(@RequestParam("data1") String data1, @RequestParam("data2") String data2 ){

        try {
            //byte[] probeImage = Files.readAllBytes(Paths.get("/home/swapnil/Downloads/afis/src/main/java/resultJET11.bmp"));

            //byte[] candidateImage = Files.readAllBytes(Paths.get("/home/swapnil/Downloads/afis/src/main/java/1.bmp"));
            Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            Key key = new SecretKeySpec(("AAAAAAAAAAA").getBytes("UTF-8"), "Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, key);
            System.out.println(data1.getBytes("UTF-8").length);
            System.out.println(data2.getBytes("UTF-8").length);
            byte[] dec_bytes1 = cipher.doFinal(data1.getBytes("UTF-8"));
            //System.out.printf("%x%n", new BigInteger(1, dec_bytes1));
            byte[] data11=dec_bytes1;

            byte[] dec_bytes2=cipher.doFinal(data2.getBytes("UTF-8"));
            byte[] data12=dec_bytes2;

            String sdata1=new String(data11);
            String sdata2=new String(data12);

            byte[] fdata11= Base64.getDecoder().decode(sdata1.getBytes("UTF-8"));
            byte[] fdata12= Base64.getDecoder().decode(sdata2.getBytes("UTF-8"));

            FingerprintTemplate probe = new FingerprintTemplate()
                    .dpi(500)
                    .create(fdata11);
            FingerprintTemplate candidate = new FingerprintTemplate()
                    .dpi(500)
                    .create(fdata12);
            double score = new FingerprintMatcher()
                    .index(probe)
                    .match(candidate);

            int threshold=40;
            boolean matches = score >= threshold;
            System.out.println(matches);
            if(matches==true) return "Fingerprint Matched";

            return "Fingerprint not matched";
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "Something went wrong.";
        }



    }

}
