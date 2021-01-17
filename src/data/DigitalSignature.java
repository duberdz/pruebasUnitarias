package data;

import java.util.Arrays;

final public class DigitalSignature {
    private final byte[] eSignature;

    public DigitalSignature(byte[] eSignature){
        this.eSignature = eSignature;
    }

    public byte[] geteSignature() {
        return eSignature;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass()!= o.getClass()){
            return false;
        }
        DigitalSignature dSignature = (DigitalSignature) o;
        return Arrays.equals(eSignature, dSignature.eSignature);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(eSignature);
    }

    @Override
    public String toString() {
        return "Signature{" + "data.Siganture = '" + Arrays.toString(eSignature) + '\'' + '}';
    }
}
