package com.bachmanity.bchain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.sql.*;
import java.util.Arrays;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random ;
import java.io.InputStream.*;
import javax.imageio.*;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



import static javax.crypto.Cipher.getInstance;




@Controller
class Cont
{
    @RequestMapping("/reghome")
    public String reghome() throws Exception
    {
        return "firstpage";
    }


    @RequestMapping("/indexSample")
    public String sampLogin() throws Exception
    {
        return "index";
    }

    @RequestMapping("/loginSample")
    public String sampleLogin() throws Exception
    {
        return "login";
    }

    @RequestMapping("/registerSample")
    public String registerSample() throws Exception
    {
        return "register";
    }

    @PostMapping("/reghome2")
    public String reghome(@RequestParam("UserName") String userName,
                          @RequestParam("FullName") String fullName,
                          @RequestParam("email") String email,
                          @RequestParam("contact") String contact,
                          @RequestParam("p1") String password) throws Exception
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection res_con= DriverManager.getConnection("jdbc:mysql://C2.cnols8jopzvj.us-east-1.rds.amazonaws.com:3306/c2","c2","c2c2c2c2");
            String resultString="";
            PreparedStatement q= res_con.prepareStatement( "INSERT INTO register values(?,?,?,?,?)" ) ;

            File f=new File("G:\\Users\\PRANALI\\Desktop\\"+userName+".bmp");
            FileInputStream fin=new FileInputStream(f);


            byte[] fileContent=null;
            File fi = new File("G:\\Users\\PRANALI\\Desktop\\"+userName+".bmp");
            try {
                fileContent = Files.readAllBytes(fi.toPath());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


            String random=ResultController.randomAlphaNumeric(16);
            Cipher cipher = getInstance("Blowfish/ECB/PKCS5Padding");
            Key key = new SecretKeySpec(random.getBytes("UTF-8"), "Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] ebyte=cipher.doFinal(fileContent);
            fileContent=ebyte;

            File fff=new File("G:\\Users\\PRANALI\\Desktop\\"+userName+".bmp");
            FileInputStream in=new FileInputStream(fff);

            String hashtext="";
            try {


                MessageDigest md = MessageDigest.getInstance("SHA-256");


                byte[] messageDigest = md.digest(password.getBytes());


                BigInteger no = new BigInteger(1, messageDigest);


                hashtext = no.toString(16);

                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }


            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown"
                        + " for incorrect algorithm: " + e);
            }

            q.setString(1,userName);
            q.setBinaryStream(2,in);//where to store password
            q.setString(3,userName);
            q.setString(4,email);
            q.setString(5,hashtext);

            q.executeUpdate();

            int fileContentLenght=fileContent.length;

            byte[] slice1 = Arrays.copyOfRange(fileContent, 0, fileContentLenght/6);
            byte[] slice2 = Arrays.copyOfRange(fileContent, fileContentLenght/6, fileContentLenght/3);
            byte[] slice3 = Arrays.copyOfRange(fileContent, fileContentLenght/3, fileContentLenght);


            String POST_URL1="http://192.168.43.59:8000/fragmenting/recieveFragment/";
            String POST_URL2="http://192.168.43.68:8000/fragmenting/recieveFragment/";
            String POST_URL3="http://192.168.43.192:8000/fragmenting/recieveFragment/";

            String ff= Base64.getEncoder().encodeToString(slice1);
            String s= Base64.getEncoder().encodeToString(slice2);
            String t= Base64.getEncoder().encodeToString(slice3);


            PreparedStatement qq= res_con.prepareStatement( "INSERT INTO strings values(?,?,?,?)" );
            qq.setString(1,userName);
            qq.setString(2,ff);
            qq.setString(3,s);
            qq.setString(4,t);

            qq.executeUpdate();



            String POST_PARAMS1="username="+userName+"&"+"fragment="+ff;
            String POST_PARAMS2="username="+userName+"&"+"fragment="+s;
            String POST_PARAMS3="username="+userName+"&"+"fragment="+t;


            HttpSendVote send1=new HttpSendVote(POST_URL1,POST_PARAMS1);
            HttpSendVote send2=new HttpSendVote(POST_URL2,POST_PARAMS2);
            HttpSendVote send3=new HttpSendVote(POST_URL3,POST_PARAMS3);

            String s1="";
            String s2="";
            String s3="";

            try
            {
                s1=send1.sendPOST();
                s2=send2.sendPOST();
                s3=send3.sendPOST();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(f);
            System.out.println(s);
            System.out.println(t);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return "login";
    }

    @RequestMapping("/verhome")
    public String verhome() throws  Exception
    {
        return "login";
    }

    @PostMapping("/verhome2")
    public String verhome(@RequestParam("UserName") String UserName,
                          @RequestParam("password") String password
                          ) throws  Exception
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection res_con= DriverManager.getConnection("jdbc:mysql://C2.cnols8jopzvj.us-east-1.rds.amazonaws.com:3306/c2","c2","c2c2c2c2");
            String resultString="";
            PreparedStatement q= res_con.prepareStatement( "INSERT INTO c2 values(?,?)" );

            PreparedStatement qq= res_con.prepareStatement("SELECT * FROM register WHERE username=? AND pass=?");
            qq.setString(1,UserName);

            String hashtext="";
            try {


                MessageDigest md = MessageDigest.getInstance("SHA-256");


                byte[] messageDigest = md.digest(password.getBytes());


                BigInteger no = new BigInteger(1, messageDigest);


                hashtext = no.toString(16);

                while (hashtext.length() < 32) {
                    hashtext = "0" + hashtext;
                }


            }
            catch (NoSuchAlgorithmException e) {
                System.out.println("Exception thrown"
                        + " for incorrect algorithm: " + e);
            }

            qq.setString(2,hashtext);

            boolean res = qq.execute();
            if(!res)
            {
                return "false";
            }

            File f=new File("G:\\Users\\PRANALI\\Desktop\\"+UserName+".bmp");
            FileInputStream fin=new FileInputStream(f);

            q.setInt(1,1);
            q.setBinaryStream(2,fin);

            q.executeUpdate();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            String POST_URL="http://192.168.43.130:7000/sendEnc";
            String PARAMS= "username="+UserName;
            HttpSendVote tmp=new HttpSendVote(POST_URL,PARAMS);
            String res=tmp.sendPOST();

            if(res.equals("true"))
            {
                return ("voteConfirmation");//true page

            }
            else
                return ("false");//false page
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "anc";
    }

}




@RestController
public class ResultController {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String randomAlphaNumeric(int count)
    {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    @RequestMapping("/verify")
    public String verify() throws Exception
    {

//        byte[] fileContent=null;
//
//        fileContent = Files.readAllBytes(Paths.get("G:\\Users\\PRANALI\\Desktop\\sih\\1.bmp"));
//
//        int fileContentLenght=fileContent.length;
//
//        System.out.println(fileContentLenght);
//
//        byte[] slice1 = Arrays.copyOfRange(fileContent, 0, fileContentLenght/6);
//        byte[] slice2 = Arrays.copyOfRange(fileContent, fileContentLenght/6, fileContentLenght/3);
//        byte[] slice3 = Arrays.copyOfRange(fileContent, fileContentLenght/3, fileContentLenght);
//
//
//
//
//        String f=Base64.getEncoder().encodeToString(slice1.toString().getBytes("UTF-8"));
//        String s=Base64.getEncoder().encodeToString(slice2.toString().getBytes("UTF-8"));
//        String t=Base64.getEncoder().encodeToString(slice3.toString().getBytes("UTF-8"));
//
//
//        System.out.println(slice1.length+"\n\n"+slice2.length+"\n\n"+slice3.length);
//
//
//        //String s=Base64.getEncoder().encodeToString(slice2);
////        String t=Base64.getEncoder().encodeToString(slice3);
//
//
////        String random="AAAAAAAAAAAAAAAA";
////        Cipher cipher = getInstance("Blowfish/ECB/PKCS5Padding");
////        Key key = new SecretKeySpec(random.getBytes("UTF-8"), "Blowfish");
////        cipher.init(Cipher.ENCRYPT_MODE, key);
////
////        byte[] ebyte=cipher.doFinal(fileContent);
////        fileContent=ebyte;
////        String encStrings1=new String(fileContent);
////
////
////
////
////
////        cipher = getInstance("Blowfish/ECB/NoPadding");
////        key = new SecretKeySpec(random.getBytes("UTF-8"), "Blowfish");
////        cipher.init(Cipher.ENCRYPT_MODE, key);
////
////        ebyte=cipher.doFinal(fileContent);
////        fileContent=ebyte;
////        String encStrings2=new String(fileContent);
////
////
////
////
////
////        cipher = getInstance("Blowfish/ECB/NoPadding");
////        key= new SecretKeySpec(random.getBytes("UTF-8"), "Blowfish");
////        cipher.init(Cipher.ENCRYPT_MODE, key);
////
////        ebyte=cipher.doFinal(fileContent);
////        fileContent=ebyte;
////
////        String encStrings3=new String(fileContent);
////
////
////
//
//
//
//
//        String POST_URL1 = "http://192.168.43.130:7000/sendEnc";
//        String POST_PARAMS = "encString1="+f+"&encString2="+s+"&encString3="+t;    //"&username=user1";
//
//        HttpSendVote send1 = new HttpSendVote(POST_URL1,POST_PARAMS);
//        if(send1.sendPOST().equals("true"))
//            System.out.println("Matched");
//        else
//            System.out.println("Not Matched");

        return "tpo";
    }



    @RequestMapping("/recieve")
    public String recieve() throws Exception
    {

        byte[] fileContent=null;
        File fi = new File("G:\\Users\\PRANALI\\Desktop\\sih\\1.bmp");
        try {
            fileContent = Files.readAllBytes(fi.toPath());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        String random="AAAAAAAAAAAAAAAA";
        Cipher cipher = getInstance("Blowfish/ECB/PKCS5Padding");
        Key key = new SecretKeySpec(random.getBytes("UTF-8"), "Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] ebyte=cipher.doFinal(fileContent);
        fileContent=ebyte;



        int fileContentLenght=fileContent.length;

        byte[] slice1 = Arrays.copyOfRange(fileContent, 0, fileContentLenght/6);
        byte[] slice2 = Arrays.copyOfRange(fileContent, fileContentLenght/6, fileContentLenght/3);
        byte[] slice3 = Arrays.copyOfRange(fileContent, fileContentLenght/3, fileContentLenght);


        String POST_URL1="http://192.168.43.59:8000/fragmenting/recieveFragment/";
        String POST_URL2="http://192.168.43.68:8000/fragmenting/recieveFragment/";
        String POST_URL3="http://192.168.43.192:8000/fragmenting/recieveFragment/";

        String f= Base64.getEncoder().encodeToString(slice1);
        String s= Base64.getEncoder().encodeToString(slice2);
        String t= Base64.getEncoder().encodeToString(slice3);


        String POST_PARAMS1="username="+"user1&"+"fragment="+f;
        String POST_PARAMS2="username="+"user1&"+"fragment="+s;
        String POST_PARAMS3="username="+"user1&"+"fragment="+t;


       HttpSendVote send1=new HttpSendVote(POST_URL1,POST_PARAMS1);
        HttpSendVote send2=new HttpSendVote(POST_URL2,POST_PARAMS2);
        HttpSendVote send3=new HttpSendVote(POST_URL3,POST_PARAMS3);

        String s1="";
        String s2="";
        String s3="";

        try
        {
            s1=send1.sendPOST();
            s2=send2.sendPOST();
            s3=send3.sendPOST();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(f);
        System.out.println(s);
        System.out.println(t);
        return "hello";
    }
}