package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import org.junit.jupiter.api.Test;
import service.HealthNationalService;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MedicalPrescriptionTest {
    private HealthCardID cardID = new HealthCardID("card1234");
    private DigitalSignature signature;
    private HashMap<ProductID, MedicalPrescriptionLine> lineas = new HashMap<ProductID, MedicalPrescriptionLine>();
    private MedicalPrescription prescripcion = new MedicalPrescription(1234, new Date(2019, 10, 5), new Date(2020, 10, 10), cardID, signature, lineas);
    private TakingGuideline taking = new TakingGuideline(dayMoment.BEFOREBREAKFAST, 10f, "Tomar durante el desayuno", 1f, 4f, FqUnit.DAY);

    @Test
    public void presCode(){
        assertEquals(prescripcion.getPrescCode(), 1234);
        assertNotEquals(prescripcion.getPrescCode(), 4321);
    }

    @Test
    public void setPresCode(){
        prescripcion.setPrescCode(4321);
        assertEquals(prescripcion.getPrescCode(), 4321);
    }

    @Test
    public void EndDate(){
        assertEquals(prescripcion.getEndDate(), new Date(2020, 10, 10));
        prescripcion.setEndDate(new Date(2021, 1, 10));
        assertNotEquals(prescripcion.getEndDate(), new Date(2020, 10, 10));
    }

    @Test
    public void addLine() throws IncorrectTakingGuidelinesException{
        ProductID product1 = new ProductID("product123");
        ProductID product2 = new ProductID("product222");
        ProductID product3 = new ProductID("product321");
        ProductID none = new ProductID("false");
        String[] instruc1 = new String[]{"DURINGBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "WEEK"};
        String[] instruc2 = new String[]{"AFTERMEALS","16", "tomar despues de la comida", "2", "8", "MONTH"};
        String[] instruc3 = new String[]{"BEFOEMEALS","7", "Tomar antes de la comida", "6", "2", "DAY"};

        prescripcion.addLine(product1, instruc1);
        prescripcion.addLine(product2, instruc2);
        prescripcion.addLine(product3, instruc3);
        assertTrue(prescripcion.getPrescripcion().containsKey(product1));
        assertFalse(prescripcion.getPrescripcion().containsKey(none));
    }

    @Test
    public void modifyLine() throws IncorrectTakingGuidelinesException, ProductNotInPrescription{
        ProductID product1 = new ProductID("product123");
        ProductID product2 = new ProductID("product222");
        ProductID product3 = new ProductID("product321");
        ProductID product4 = new ProductID("product333");
        String[] instruc1 = new String[]{"DURINGBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "WEEK"};
        String[] instruc2 = new String[]{"AFTERMEALS","16", "tomar despues de la comida", "2", "8", "MONTH"};
        String[] instruc3 = new String[]{"BEFOEMEALS","7", "Tomar antes de la comida", "6", "2", "DAY"};
        String[] instruc4 = new String[]{"BEFOREBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "DAY"};
        MedicalPrescriptionLine other = new MedicalPrescriptionLine(taking);

        prescripcion.addLine(product1, instruc1);
        prescripcion.addLine(product2, instruc2);
        prescripcion.addLine(product3, instruc3);
        prescripcion.addLine(product4, instruc1);

        prescripcion.modifyLine(product1, instruc4);
        assertNotEquals(prescripcion.getPrescripcion().get(product1), prescripcion.getPrescripcion().get(product4));
    }

    @Test
    public void removeLine() throws IncorrectTakingGuidelinesException, ProductNotInPrescription{
        ProductID product1 = new ProductID("product123");
        ProductID product2 = new ProductID("product222");
        ProductID product3 = new ProductID("product321");
        String[] instruc1 = new String[]{"DURINGBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "WEEK"};
        String[] instruc2 = new String[]{"AFTERMEALS","16", "tomar despues de la comida", "2", "8", "MONTH"};
        String[] instruc3 = new String[]{"BEFOEMEALS","7", "Tomar antes de la comida", "6", "2", "DAY"};

        prescripcion.addLine(product1, instruc1);
        prescripcion.addLine(product2, instruc2);
        prescripcion.addLine(product3, instruc3);

        prescripcion.removeLine(product3);
        assertFalse(prescripcion.getPrescripcion().containsKey(product3));
    }
}
