package model;

public class Receipt {
    private String oId;
    private String receiptId;

    @Override
    public String toString() {
        return "Receipt{" +
                "oId='" + oId + '\'' +
                ", receiptId='" + receiptId + '\'' +
                '}';
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
    public Receipt(){
    }

    public Receipt(String oId, String receiptId) {
        this.oId = oId;
        this.receiptId = receiptId;
    }
}

