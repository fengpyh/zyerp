package com.cryptomind.trading.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.TransactionRolledbackException;

public class TransactionUtil {

    private static final Logger log = LoggerFactory.getLogger(TransactionUtil.class);

    @FunctionalInterface
    public interface TransactionSupplier<T> {
        T get() throws Exception;
    }

    public static <T> T transaction(
            PlatformTransactionManager transactionManager,
            TransactionSupplier<T> supplier)
            throws TransactionRolledbackException {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            T result = supplier.get();
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            transactionManager.rollback(status);
            throw new TransactionRolledbackException(ex.getMessage());
        }
    }
}