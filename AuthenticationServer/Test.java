package com.bachmanity.bchain;


import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.security.SecureRandom;
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

class ImageDemo
{

    public ImageDemo(final String filename) throws Exception
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                JFrame editorFrame = new JFrame("Image Demo");
                editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                BufferedImage image = null;
                try
                {
                    image = ImageIO.read(new File(filename));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    System.exit(1);
                }
                ImageIcon imageIcon = new ImageIcon(image);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });
    }
}



class Blowfish {

    String inputMessage,encryptedData,decryptedMessage;

    public Blowfish(String imgPathx, String imgPathy) {
        try {

            byte[] ibyte = Files.readAllBytes(Paths.get("/home/swapnil/Desktop/1.bmp"));
            byte[] ibyte1 = Files.readAllBytes(Paths.get("/home/swapnil/Desktop/3.bmp"));

            Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
            Key key = new SecretKeySpec(("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA").getBytes("UTF-8"), "Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, key);






            byte[] ebyte=cipher.doFinal(ibyte);
            String encryptedData = new String(ebyte);
            byte[] ebyte1=cipher.doFinal(ibyte1);
            String encryptedData1 = new String(ebyte1);
            //System.out.println("Encrypted message "+encryptedData);
            //JOptionPane.showMessageDialog(null,"Encrypted Data "+"\n"+encryptedData);
            int x=ebyte.length;
            int y=ebyte1.length;


            //System.out.println("Decrypted message "+decryptedMessage);

            System.out.println("After Divison");

            //ByteBuffer buf = ByteBuffer.wrap(myArray);
            byte[] slice1 = Arrays.copyOfRange(ebyte, 0, x/6);
            byte[] slice2 = Arrays.copyOfRange(ebyte, x/6, x/3);
            byte[] slice3 = Arrays.copyOfRange(ebyte, x/3, x);
            byte[] finalx=new byte[slice1.length+slice2.length+slice3.length];
            byte[] slice11 = Arrays.copyOfRange(ebyte1, 0, y/6);
            byte[] slice12 = Arrays.copyOfRange(ebyte1, y/6, y/3);
            byte[] slice13 = Arrays.copyOfRange(ebyte1, y/3, y);
            byte[] finaly=new byte[slice11.length+slice12.length+slice13.length];

            System.arraycopy(slice1,0,finalx,0,slice1.length);
            System.arraycopy(slice2,0,finalx,slice1.length,slice2.length);
            System.arraycopy(slice3,0,finalx,slice1.length+slice2.length,slice3.length);


            System.arraycopy(slice11,0,finaly,0,slice11.length);
            System.arraycopy(slice12,0,finaly,slice11.length,slice12.length);
            System.arraycopy(slice13,0,finaly,slice11.length+slice12.length,slice13.length);
            if(Arrays.equals(finalx,ebyte))
                System.out.println("done0");
            else
                System.out.println("donew");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] dbyte= cipher.doFinal(finalx);
            String decryptedMessage = new String(dbyte);
            byte[] dbyte1= cipher.doFinal(finaly);
            String decryptedMessage1 = new String(dbyte1);




            System.out.println("Finger print matching");
            FingerprintTemplate probe = new FingerprintTemplate().dpi(500).create(dbyte);
            FingerprintTemplate candidate = new FingerprintTemplate().dpi(500).create(dbyte1);
            double score = new FingerprintMatcher().index(probe).match(candidate);
            boolean matches = score >= 40;
            System.out.println(matches);
            if(matches==true)
                System.out.println("Fingerprint Matched");
            else
            System.out.println("Fingerprint not matched");

            //JOptionPane.showMessageDialog(null,"Decrypted Data "+"\n"+decryptedMessage);
        }
        catch(Exception e) {
            System.out.println(e);
        }

    }





    public static void main(String args[]) throws Exception {
        String img = "/home/swapnil/Desktop/1.bmp";
        String img2 ="/home/swapnil/Desktop/3.bmp";
        new ImageDemo(img);
        new ImageDemo(img2);
        Blowfish bf = new Blowfish(img, img2);
        //Blowfish bf2 = new Blowfish(img2);
    }
}



/*byte[] fdata11= Base64.getDecoder().decode(sdata1.getBytes("UTF-8"));
byte[] fdata12= Base64.getDecoder().decode(sdata2.getBytes("UTF-8"));*/

