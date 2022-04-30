package az.ingressunibank.bookstoreapp.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s not found via %s: %s", resource, field, value));
    }
}
