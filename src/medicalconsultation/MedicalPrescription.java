package medicalconsultation;

import data.DigitalSignature;
import data.HealthCardID;
import data.ProductID;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import exceptions.*;

public class MedicalPrescription {

    private int prescCode;
    private Date prescDate;
    private Date endDate;
    private HealthCardID hcID;
    private DigitalSignature eSign;
    private HashMap<ProductID, MedicalPrescriptionLine> prescripcion;


    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign){
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescripcion = new HashMap<>();
    }

    public MedicalPrescription (int prescCode, Date prescDate, Date endDate, HealthCardID hcID, DigitalSignature eSign, HashMap<ProductID, MedicalPrescriptionLine> lineas){
        this.prescCode = prescCode;
        this.prescDate = prescDate;
        this.endDate = endDate;
        this.hcID = hcID;
        this.eSign = eSign;
        this.prescripcion = lineas;
    }

    public void addLine(ProductID prodID, String[] instruc) throws IncorrectTakingGuidelinesException {
        /**
         * Estructure
         *
         *  instruc[0] = day moment
         *  instruc[1] = duration
         *  instruc[2] = instructions
         *  instruc[3] = dosis
         *  instruc[4] = frequency
         *  instruc[5] = FreqUnit
        **/
        genTupla(prodID, instruc);
    }

    public void modifyLine(ProductID prodID, String[] instruc) throws ProductNotInPrescription, IncorrectTakingGuidelinesException{
        /** Borramos linea a modificar **/
        prescripcion.remove(prodID);

        /** Generamos tupla con las nuevas instrucciones **/
        genTupla(prodID, instruc);
    }

    public void removeLine(ProductID prodID) throws ProductNotInPrescription{
        prescripcion.remove(prodID);
    }

    /** Generar tupla **/
    private void genTupla(ProductID prodID, String[] instruc) {
        MedicalPrescriptionLine newLine;
        TakingGuideline taking = new TakingGuideline();
        Posology posology = new Posology();

        taking.setdMoment(dayMoment.valueOf(instruc[0]));
        taking.setDuration(Float.valueOf(instruc[1]));
        taking.setInstructions(instruc[2]);
        posology.setDose(Float.valueOf(instruc[3]));
        posology.setFreq(Float.valueOf(instruc[4]));
        posology.setFreqUnit(FqUnit.valueOf(instruc[5]));
        taking.setPosology(posology);

        newLine = new MedicalPrescriptionLine(taking);
        prescripcion.put(prodID, newLine);
    }

    public HealthCardID getHcID() {
        return hcID;
    }

    public HashMap<ProductID, MedicalPrescriptionLine> getPrescripcion() {
        return prescripcion;
    }

    public int getPrescCode() {
        return prescCode;
    }

    public void setPrescCode(int prescCode) {
        this.prescCode = prescCode;
    }

    public DigitalSignature geteSign() {
        return eSign;
    }

    public void seteSign(DigitalSignature eSign) {
        this.eSign = eSign;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setHcID(HealthCardID hcID) {
        this.hcID = hcID;
    }
}
