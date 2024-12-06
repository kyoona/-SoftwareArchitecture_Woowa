package sa.repository;

public interface PaymentMethodRepository<T> {

    public T save(T paymentMethod);

    public T findByUserId(Long userId);
}
