package test.payment_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import release.externalAPI.PayMeAPIFactory;
import release.payment.*;

import java.util.Random;

/**
 * PayMePaymentTest class<br>
 * It is used to test the PayMePayment class and PayMePaymentFactory class
 */
public class PayMePaymentTest {
    PayMePaymentFactory payMePaymentFactory;
    PayMeAPIFactory payMeAPIFactory;

    /**
     * Set up the test environment
     */
    @BeforeEach
    public void setUp() {
        payMePaymentFactory = new PayMePaymentFactory();
        payMeAPIFactory = new PayMeAPIFactory();
    }

    /**
     * Test if the PayMePaymentFactory can create an instance of PayMePayment
     */
    @Test
    public void testPayMePaymentFactory() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertInstanceOf(PayMePayment.class, payment);
    }

    /**
     * Test if the PayMePaymentFactory can create an instance of PayMePayment with an ExternalAPI object
     */
    @Test
    public void testPayMePaymentFactory_ExternalAPI() {
        Payment payment = payMePaymentFactory.createPaymentMethod(payMeAPIFactory.getExternalAPI());
        Assertions.assertInstanceOf(PayMePayment.class, payment);
    }

    /**
     * Test if the PayMePaymentFactory can create an instance of PayMePayment with a null ExternalAPI object<br>
     * The method should not throw NullPointerException
     */
    @Test
    public void testPayMePaymentFactory_ExternalAPI_Null() {
        Payment payment = payMePaymentFactory.createPaymentMethod(null);
        // Test if there is no NullPointerException
        Assertions.assertDoesNotThrow(() -> payment.doPayment(1));
        Assertions.assertInstanceOf(PayMePayment.class, payment);
    }

    /**
     * Test the doPayment method of PayMePayment class<br>
     * The payment should be successful, and the payment status should be SUCCESS
     */
    @Test
    public void testDoPayment() {
        Payment payment = payMePaymentFactory.createPaymentMethod(
                payMeAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertTrue(payment.doPayment(100));
        Assertions.assertEquals(PaymentStatus.SUCCESS, payment.getPaymentStatus());
    }

    /**
     * Test the doPayment method of PayMePayment class with a false payment<br>
     * The payment should fail, and the payment status should be FAIL
     */
    @Test
    public void testDoPayment_False() {
        Payment payment = payMePaymentFactory.createPaymentMethod(
                payMeAPIFactory.getExternalAPI(new Random(10)));
        Assertions.assertFalse(payment.doPayment(15));
        Assertions.assertEquals(PaymentStatus.FAIL, payment.getPaymentStatus());
    }

    /**
     * Test if the PayMePayment can get the payment type<br>
     * The payment type should be PAYME
     */
    @Test
    public void testGetPaymentType() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentType.PAYME, payment.getPaymentType());
    }

    /**
     * Test if the PayMePayment can get the payment status<br>
     * The payment status should be NOT_PROCEED
     */
    @Test
    public void testGetPaymentStatus() {
        Payment payment = payMePaymentFactory.createPaymentMethod();
        Assertions.assertEquals(PaymentStatus.NOT_PROCEED, payment.getPaymentStatus());
    }

}

