import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;

public class AESCipher {

    private static SecretKeySpec gerarChave(String senha) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] chave = sha.digest(senha.getBytes("UTF-8"));
        return new SecretKeySpec(chave, "AES");
    }

    public static String pad(String texto) {
        int padding = 16 - texto.length() % 16;
        return texto + String.valueOf((char)padding).repeat(padding);
    }

    public static String unpad(String texto) {
        int lastChar = texto.charAt(texto.length() - 1);
        return texto.substring(0, texto.length() - lastChar);
    }

    public static String criptografar(String texto, String senha) throws Exception {
        SecretKeySpec chave = gerarChave(senha);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        cipher.init(Cipher.ENCRYPT_MODE, chave, new IvParameterSpec(iv));
        byte[] criptografado = cipher.doFinal(pad(texto).getBytes("UTF-8"));
        byte[] resultado = new byte[iv.length + criptografado.length];
        System.arraycopy(iv, 0, resultado, 0, iv.length);
        System.arraycopy(criptografado, 0, resultado, iv.length, criptografado.length);
        return Base64.getEncoder().encodeToString(resultado);
    }

    public static String descriptografar(String base64Texto, String senha) throws Exception {
        SecretKeySpec chave = gerarChave(senha);
        byte[] dados = Base64.getDecoder().decode(base64Texto);
        byte[] iv = Arrays.copyOfRange(dados, 0, 16);
        byte[] criptografado = Arrays.copyOfRange(dados, 16, dados.length);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, chave, new IvParameterSpec(iv));
        String texto = new String(cipher.doFinal(criptografado), "UTF-8");
        return unpad(texto);
    }

    public static void main(String[] args) throws Exception {
        String mensagem = "Mensagem Original";
        String senha = "password";

        String cifrado = criptografar(mensagem, senha);
        System.out.println("Texto criptografado: " + cifrado);

        String claro = descriptografar(cifrado, senha);
        System.out.println("Texto descriptografado: " + claro);
    }
}
