package com.bachmanity.bchain;

import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;


import java.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            boolean matches = score >= 40;
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
