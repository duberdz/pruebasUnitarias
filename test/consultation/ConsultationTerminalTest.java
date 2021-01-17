package consultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.MedicalPrescriptionLine;
import medicalconsultation.ProductSpecification;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import service.HealthNationalService;
import service.ScheduledVisitAgenda;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ConsultationTerminalTest {
    ConsultationTerminal consultation;
    private String eSignature = "5555";
    private DigitalSignature signature = new DigitalSignature(eSignature.getBytes());
    ProductID product1;
    ProductID product2;
    ProductID product3;
    ProductID product4;
    String[] instruc1;
    String[] instruc2;
    String[] instruc3;
    String[] instruc4;
    HealthCardID cardID;

    public static class HealthNationalServiceTestDoble implements HealthNationalService {
        HashMap<HealthCardID, MedicalPrescription> pacientes = new HashMap<>();
        HashMap<String, ProductSpecification> catalogo = new HashMap<>();
        List<ProductSpecification> products = new ArrayList<>();

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            System.out.println("Holaaaaaaaaa");
            if(!pacientes.containsKey(hcID)){
                throw new HealthCardException("HealthCardID no valida");
            }
            if(pacientes.get(hcID).getPrescCode() == 0){
                throw new NotValidePrescriptionException("Prescripcion no valida");
            }
            return pacientes.get(hcID);
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            if(!catalogo.containsKey(keyWord)){
                throw new AnyKeyWordMedicineException("Ningun producto coincide");
            }
            return products;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            if(!products.contains(opt)){
                throw new AnyMedicineSearchException("Ningun producto encontrado");
            }
            return products.get(opt);
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }

    public static class ScheduledVisitAgendaTestDoble implements ScheduledVisitAgenda{
        HashMap<HealthCardID, MedicalPrescription> pacientes = new HashMap<>();
        HashMap<String, ProductSpecification> catalogo = new HashMap<>();
        List<ProductSpecification> products = new ArrayList<>();
        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            if(pacientes.isEmpty()){
                throw new HealthCardException("Visita no agendada");
            }
            return null;
        }
    }

    @Before
    public void setUp(){

        consultation = new ConsultationTerminal(signature);
        consultation.setHNS(new HealthNationalServiceTestDoble());
        consultation.setSVA(new ScheduledVisitAgendaTestDoble());

        cardID = new HealthCardID("card1234");
        HashMap<ProductID, MedicalPrescriptionLine> lineas = new HashMap<ProductID, MedicalPrescriptionLine>();
        MedicalPrescription prescripcion = new MedicalPrescription(1234, new Date(2019, 10, 5), new Date(2020, 10, 10), cardID, signature, lineas);
        prescripcion.setHcID(cardID);
        consultation.setCard(cardID);
        product1 = new ProductID("product123");
        product2 = new ProductID("product222");
        product3 = new ProductID("product321");
        product4 = new ProductID("product333");
        instruc1 = new String[]{"DURINGBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "WEEK"};
        instruc2 = new String[]{"AFTERMEALS","16", "tomar despues de la comida", "2", "8", "MONTH"};
        instruc3 = new String[]{"BEFOEMEALS","7", "Tomar antes de la comida", "6", "2", "DAY"};
        instruc4 = new String[]{"BEFOREBREAKFAST","10", "Tomar durante el desayuno", "1", "4", "DAY"};
    }

    @Test
    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException{
        consultation.initRevision();
        assertEquals(cardID, consultation.getCard());
    }
}
