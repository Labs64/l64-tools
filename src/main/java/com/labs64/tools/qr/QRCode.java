package com.labs64.tools.qr;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * QRCode generator.
 */
public final class QRCode {

    private String content;
    private String encoding = "UTF-8";
    private int width = 300;
    private int height = 300;
    private String imageFormat = "png";
    private Integer margin = Integer.valueOf(2);
    private ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;

    /**
     * Set content (chl).
     * 
     * @param content
     *            the data to encode. Data can be digits (0-9), alphanumeric characters, binary bytes of data, or Kanji.
     *            You cannot mix data types within a QR code.
     * @return QRCode instance
     */
    public QRCode content(final String content) {
        this.content = content;
        return this;
    }

    /**
     * Set encoding (choe).
     * 
     * @param encoding
     *            how to encode the data in the QR code. Here are the available values: UTF-8 [Default]; Shift_JIS;
     *            ISO-8859-1
     * @return QRCode instance
     */
    public QRCode encoding(final String encoding) {
        this.encoding = encoding;
        return this;
    }

    /**
     * Define image size (chs).
     * 
     * @param width
     *            image width; default = 300
     * @param height
     *            image height; default = 300
     * @return QRCode instance
     */
    public QRCode size(final int width, final int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Set image format (choe).
     * 
     * @param imageFormat
     *            image format e.g. "png", "gif", "bmp", "jp(e)g"; default = png
     * @return QRCode instance
     */
    public QRCode imageFormat(final String imageFormat) {
        this.imageFormat = imageFormat;
        return this;
    }

    /**
     * Set margin (chld).
     * 
     * @param margin
     *            the width of the white border around the data portion of the code. This is in rows, not in pixels;
     *            default = 2
     * @return QRCode instance
     */
    public QRCode margin(final Integer margin) {
        this.margin = margin;
        return this;
    }

    /**
     * Set error correction level (chld).
     * 
     * @param errorCorrectionLevel
     *            <p>
     *            QR codes support four levels of error correction to enable recovery of missing, misread, or obscured
     *            data. Greater redundancy is achieved at the cost of being able to store less data.
     *            <p>
     *            Supported values:
     *            <ul>
     *            <li>L - [Default] Allows recovery of up to 7% data loss</li>
     *            <li>M - Allows recovery of up to 15% data loss</li>
     *            <li>Q - Allows recovery of up to 25% data loss</li>
     *            <li>H - Allows recovery of up to 30% data loss</li>
     *            </ul>
     *            default = ErrorCorrectionLevel.L
     * @return QRCode instance
     */
    public QRCode errorCorrectionLevel(final ErrorCorrectionLevel errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
        return this;
    }

    private BitMatrix createBitMatrix() throws WriterException {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);
        hints.put(EncodeHintType.MARGIN, margin);
        hints.put(EncodeHintType.CHARACTER_SET, encoding);

        return new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }

    /**
     * Generate QRCode.
     * 
     * @param outputStream
     *            stream
     * @throws Exception
     */
    public void generate(final OutputStream outputStream) throws Exception {
        BitMatrix bitMatrix = createBitMatrix();
        MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, outputStream);
    }

    /**
     * Generate QRCode.
     * 
     * @param file
     *            file
     * @throws Exception
     */
    public void generate(final File file) throws Exception {
        BitMatrix bitMatrix = createBitMatrix();
        MatrixToImageWriter.writeToFile(bitMatrix, imageFormat, file);
    }

}
