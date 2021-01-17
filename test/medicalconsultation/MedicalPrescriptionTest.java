package medicalconsultation;

import data.HealthCardID;
import exceptions.*;
import service.HealthNationalService;

import java.util.List;

public class MedicalPrescriptionTest {
    public static class NationalHealthServiceTestDoble implements HealthNationalService{

        @Override
        public MedicalPrescription getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            return null;
        }

        @Override
        public List<ProductSpecification> getProductsByKW(String keyWord) throws AnyKeyWordMedicineException, ConnectException {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(int opt) throws AnyMedicineSearchException, ConnectException {
            return null;
        }

        @Override
        public MedicalPrescription sendePrescription(MedicalPrescription ePresc) throws ConnectException, NotValidePrescription, eSignatureException, NotCompletedMedicalPrescription {
            return null;
        }
    }
}
