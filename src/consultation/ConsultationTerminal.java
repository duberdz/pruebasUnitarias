package consultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;
import exceptions.*;
import medicalconsultation.MedicalPrescription;
import medicalconsultation.ProductSpecification;
import service.HealthNationalService;
import service.ScheduledVisitAgenda;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultationTerminal {

    private MedicalPrescription prescripcion;
    private List<ProductSpecification> productos;
    private ProductID product;
    private ProductSpecification productEspecifico;
    private DigitalSignature signature;
    private HealthCardID card;
    private HealthNationalService HNS;
    private ScheduledVisitAgenda SVA;

    public ConsultationTerminal(DigitalSignature signature){
        this.signature = signature;
        this.productos = new ArrayList<>();
    }

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
        productEspecifico = HNS.getProductSpecific(option);
        product = productEspecifico.getUPCcode();
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
        prescripcion.seteSign(signature);
        HNS.sendePrescription(prescripcion);
    }

    public void printePresc() throws printingException { }

    public void setHNS(HealthNationalService HNS) {
        this.HNS = HNS;
    }

    public void setSVA(ScheduledVisitAgenda SVA) {
        this.SVA = SVA;
    }

    public HealthCardID getCard() {
        return card;
    }

    public void setCard(HealthCardID card) {
        this.card = card;
    }
}
