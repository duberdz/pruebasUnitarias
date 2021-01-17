package medicalconsultation;

import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {

    private ProductID UPCcode;
    private String descripcion;
    private BigDecimal price;

    public ProductSpecification(ProductID UPCcode, String descripcion, BigDecimal price){
        this.UPCcode = UPCcode;
        this.descripcion = descripcion;
        this.price = price;
    }

    public ProductID getUPCcode() {
        return UPCcode;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setUPCcode(ProductID UPCcode) {
        this.UPCcode = UPCcode;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
