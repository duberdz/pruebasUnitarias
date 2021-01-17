package medicalconsultation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PosologyTest {

    private Posology posology1 = new Posology(1f, 8f, FqUnit.MONTH);
    private Posology posology2 = new Posology(18f, 35f, FqUnit.DAY);

    @Test
    public void PosoloyDose(){
        assertNotEquals(posology1.getDose(), 9f);
        assertNotEquals(posology2.getDose(), 1f);

        assertNotEquals(posology1.getDose(), posology2.getDose());
    }

    @Test
    public void PosologyFreq(){
        assertEquals(posology1.getFreq(), 8f);
        assertNotEquals(posology2.getFreq(), 8f);

        posology1.setFreq(35f);
        assertEquals(posology1.getFreq(), posology2.getFreq());
    }

    @Test
    public void PosologyFqUnit(){
        assertNotEquals(posology1.getFreqUnit(), FqUnit.DAY);
        assertNotEquals(posology2.getFreqUnit(), FqUnit.MONTH);

        posology2.setFreqUnit(FqUnit.MONTH);
        assertEquals(posology1.getFreqUnit(), posology2.getFreqUnit());
    }
}
