package medicalconsultation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TakingGuidelineTest {

    private TakingGuideline taking1 = new TakingGuideline(dayMoment.AFTERDINNER, 8f, "Paracetamol", 2f, 4f, FqUnit.MONTH);
    private TakingGuideline taking2 = new TakingGuideline(dayMoment.AFTERLUNCH, 9f, "Antibiotico", 3f, 6f, FqUnit.MONTH);
    private Posology posology1 = new Posology(1f, 8f, FqUnit.MONTH);
    private Posology posology2 = new Posology(18f, 35f, FqUnit.DAY);

    @Test
    public void dayMoment(){
        assertEquals(taking1.getdMoment(), dayMoment.AFTERDINNER);
        assertNotEquals(taking2.getdMoment(), dayMoment.AFTERDINNER);

        taking2.setdMoment(dayMoment.AFTERBREAKFAST);
        assertNotEquals(taking1.getdMoment(), taking2.getdMoment());
    }

    @Test
    public void duration(){
        assertEquals(taking1.getDuration(), 8f);

        taking1.setDuration(9f);
        assertEquals(taking1.getDuration(), taking2.getDuration());

        taking1.setDuration(8f);
        assertNotEquals(taking1.getDuration(), taking2.getDuration());
    }

    @Test
    public void instruction(){
        taking1.setInstructions("Caramelo de menta");
        assertNotEquals(taking1.getInstructions(), "Paracetamol");
        assertNotEquals(taking1.getInstructions(), taking2.getInstructions());
    }

    @Test
    public void Posology(){
        assertNotEquals(taking1.getPosology(), posology1);
        taking1.setPosology(posology1);
        taking2.setPosology(posology2);
        assertEquals(taking1.getPosology(), posology1);
        assertEquals(taking2.getPosology(), posology2);
    }
}
