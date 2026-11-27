package id.pasarkita;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Validasi parameter halaman untuk semua endpoint berhalaman.
 * Batas 200 mengikuti kontrak mitra logistik v1.3 (size=200) dan notulen 11 Nov 2026.
 */
public final class Pagination {

    public static final int MAX_SIZE = 200;

    private Pagination() {}

    public static PageRequest of(int page, int size) {
        validate(page, size);
        return PageRequest.of(page, size);
    }

    public static PageRequest of(int page, int size, Sort sort) {
        validate(page, size);
        return PageRequest.of(page, size, sort);
    }

    private static void validate(int page, int size) {
        if (page < 0) {
            throw new InvalidPagingException("Parameter 'page' tidak boleh negatif (diterima: " + page + ")");
        }
        if (size < 1 || size > MAX_SIZE) {
            throw new InvalidPagingException("Parameter 'size' harus antara 1 dan " + MAX_SIZE + " (diterima: " + size + ")");
        }
    }

    public static final class InvalidPagingException extends RuntimeException {
        public InvalidPagingException(String message) {
            super(message);
        }
    }
}