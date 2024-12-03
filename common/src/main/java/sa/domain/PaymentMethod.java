package sa.domain;

public interface PaymentMethod {
    public int pay(int amount);
    public int refund(int amount);

    public PaymentMethodType getPaymentMethodType();
}
