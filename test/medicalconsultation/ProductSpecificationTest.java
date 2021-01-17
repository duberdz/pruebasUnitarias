package medicalconsultation;

import data.ProductID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

public class ProductSpecificationTest {

    ProductID prodID = new ProductID("prod1234");
    BigDecimal decimal = new BigDecimal(3);
    ProductSpecification product = new ProductSpecification(prodID, "Paracetamol", decimal);

    @Test
    public void UpCode(){
        assertEquals(product.getUPCcode(), prodID);
        assertNotEquals(product.getUPCcode(), new ProductID("prod2453"));
        product.setUPCcode(new ProductID("prod2453"));
        assertEquals(product.getUPCcode(), new ProductID("prod2453"));
    }

    @Test
    public void Description(){
        assertNotEquals(product.getDescripcion(), "Caramelo de menta");
        assertEquals(product.getDescripcion(), "Paracetamol");
        product.setDescripcion("Caramelo de menta");
        assertNotEquals(product.getDescripcion(), "Paracetamol");
    }

    @Test
    public void Price(){
        assertEquals(product.getPrice(), new BigDecimal(3));
        product.setPrice(new BigDecimal(35));
        assertNotEquals(product.getPrice(), new BigDecimal(3));
    }
}
