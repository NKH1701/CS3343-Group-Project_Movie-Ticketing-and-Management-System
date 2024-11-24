package test.payment_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.externalAPI.AlipayAPIFactory;
import release.externalAPI.ExternalAPI;
import release.payment.*;

import java.util.Random;

/**
 * AlipayPaymentTest class<br>
 * Unit test for AlipayPayment
 */
public class AlipayPaymentTest {
    AlipayPaymentFactory alipayPaymentFactory;
    AlipayAPIFactory alipayAPIFactory;

    /**
     * Set up the test environment.
     */
    @BeforeEach
    public void setUp() {
        alipayPaymentFactory = new AlipayPaymentFactory();
        alipayAPIFactory = new AlipayAPIFactory();
    }

    /**
     * Test if the AlipayPaymentFactory can create an instance of AlipayPayment.
     */
    @Test
    public void testAlipayPaymentFactory() {
        Payment payment = alipayPaymentFactory.createPaymentMethod();
        Assertions.assertInstanceOf(AlipayPayment.class, payment);
    }

    /**
     * Test if the AlipayPaymentFactory can create an instance of AlipayPayment with an ExternalAPI object.
     */
    @Test
    public void testAlipayPaymentFactory_ExternalAPI() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI();
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertInstanceOf(AlipayPayment.class, payment);
    }

    /**
     * Test if the AlipayPaymentFactory can create an instance of AlipayPayment with a null ExternalAPI object.<br>
     * The method should not throw NullPointerException.
     */
    @Test
    public void testAlipayPaymentFactory_ExternalAPI_Null() {
        Payment payment = alipayPaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertInstanceOf(AlipayPayment.class, payment);
    }

    /**
     * Test if the AlipayPayment can do payment successfully.<br>
     * The payment should succeed, and the payment status should be SUCCESS.
     */
    @Test
    public void testDoPayment() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(PaymentStatus.SUCCESS, payment.getPaymentStatus());
    }

    /**
     * Test if the AlipayPayment can fail to do the payment.<br>
     * The payment should fail, and the payment status should be FAIL.
     */
    @Test
    public void testDoPayment_False() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(PaymentStatus.FAIL, payment.getPaymentStatus());
    }

    /**
     * Test getPaymentStatus method of AlipayPayment.<br>
     * The payment status should be NOT_PROCEED.
     */
    @Test
    public void testGetPaymentStatus() {
        ExternalAPI alipayAPI = alipayAPIFactory.getExternalAPI(new Random(10));
        Payment payment = alipayPaymentFactory.createPaymentMethod(alipayAPI);
        Assertions.assertEquals(PaymentStatus.NOT_PROCEED, payment.getPaymentStatus());
    }

    /**
     * Test getPaymentType method of AlipayPayment.<br>
     * The payment type should be ALIPAY.
     */
    @Test
    public void testGetPaymentType() {
        Payment alipayPayment = alipayPaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.ALIPAY, alipayPayment.getPaymentType());
    }
}
