package data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitalSignatureTest {

    @Test
    public void signature() {
        String signature24 = "Doctor24";
        String signature37 = "Doctor37";
        String signature17 = "Doctor17";

        byte[] byte1 = signature24.getBytes();
        byte[] byte2 = signature37.getBytes();
        byte[] byte3 = signature17.getBytes();

        DigitalSignature eSignature1 = new DigitalSignature(byte1);
        DigitalSignature eSignature2 = new DigitalSignature(byte2);
        DigitalSignature eSignature3 = new DigitalSignature(byte3);
        DigitalSignature eSignature4 = new DigitalSignature(byte3);


        assertNotEquals(eSignature1, eSignature2);
        assertEquals(eSignature3, eSignature4);
    }

    @Test
    public void hasCode(){
        String signature17 = "Doctor17";
        byte[] byte3 = signature17.getBytes();

        DigitalSignature eSignature3 = new DigitalSignature(byte3);
        DigitalSignature eSignature4 = new DigitalSignature(byte3);

        assertEquals(eSignature3.hashCode(), eSignature4.hashCode());
    }
}
