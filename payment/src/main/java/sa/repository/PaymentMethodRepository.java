package sa.repository;

public interface PaymentMethodRepository<T> {

    public T save(T paymentMethod);
    public boolean exists(T paymentMethod);
    public T findByUserId(Long userId);
    public T delete(T paymentMethod);
}
