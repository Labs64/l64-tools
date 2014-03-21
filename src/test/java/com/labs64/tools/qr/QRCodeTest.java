package com.labs64.tools.qr;

import java.io.ByteArrayOutputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * QRCode generation tests.
 */
public class QRCodeTest {

    private QRCode underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new QRCode();
    }

    @Test
    public void testCustomParameters() throws Exception {
        // File outFile = new File("qr.jpg");
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        underTest.content("Hello Labs64!").encoding("ISO-8859-1").size(500, 500).margin(1).imageFormat("jpg")
                .errorCorrectionLevel(ErrorCorrectionLevel.H).generate(outStream);
        Assert.assertNotNull(outStream);
        Assert.assertTrue(outStream.size() > 0);
    }

}
