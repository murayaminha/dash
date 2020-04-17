package br.com.rd.ecommerce.Controller;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class Criptografar {

    static String IV = "AAAAAAAAAAAAAAAA";
    static String textopuro = "teste texto 12345678\0\0\0";
    static String chaveencriptacao = "0123456789abcdef";

    public static void criotografar(String card){
        try {

            System.out.println("Texto Puro: " + textopuro);

            byte[] textoencriptado = encrypt(textopuro, chaveencriptacao);

            System.out.print("Texto Encriptado: ");

            for (byte b : textoencriptado) System.out.print((int) b + " ");

            System.out.println("");

            String textodecriptado = decrypt(textoencriptado, chaveencriptacao);

            System.out.println("Texto Decriptado: " + textodecriptado);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(String textopuro, String chaveencriptacao) throws Exception {
        Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes(StandardCharsets.UTF_8), "AES");
        encripta.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        return encripta.doFinal(textopuro.getBytes(StandardCharsets.UTF_8));
    }

    public static String decrypt(byte[] textoencriptado, String chaveencriptacao) throws Exception{
        Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes(StandardCharsets.UTF_8), "AES");
        decripta.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8)));
        return new String(decripta.doFinal(textoencriptado), StandardCharsets.UTF_8);
    }

}
