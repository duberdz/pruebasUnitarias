package consultation;

import data.HealthCardID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import service.HealthNationalService;
import service.ScheduledVisitAgenda;

import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    private MedicalPrescription prescripcion;
    List<ProductSpecification> productos;
    ProductSpecification productEspecifico;
    private HealthCardID card;
    HealthNationalService HNS;
    ScheduledVisitAgenda SVA;

    public void initRevision() throws HealthCardException, NotValidePrescriptionException, ConnectException {
        card = SVA.getHealthCardID();
        prescripcion = HNS.getePrescription(card);
    }

    public void initPrescriptionEdition() throws AnyCurrentPrescriptionException, NotFinishedTreatmentException {
        //FALTA!!!!
    }

    public void searchForProducts(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
        productos = HNS.getProductsByKW(keyWord);
    }

    public void selectProduct(int option) throws AnyMedicineSearchException, ConnectException {
        productEspecifico = productos.get(option);
    }

    public void enterMedicineGuidelines(String[] instruc) throws AnySelectedMedicineException, IncorrectTakingGuidelinesException
    {
        prescripcion.addLine(productEspecifico.getUPCcode(), instruc);
    }

    public void enterTreatmentEndingDate(Date date) throws IncorrectEndingDateException{
        prescripcion.setEndDate(date);
    }

    public void sendePrescription()
    throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription{
        HNS.sendePrescription(prescripcion);
    }

    public void printePresc() throws printingException { }
}
