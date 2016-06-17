package lastochkin.streamTV.helpers;

import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by karhamint on 16.06.16.
 */
public class FileComparationUtils {
    public boolean imageIsEqual(String expectedFile, String actualFile) throws IOException {

        BufferedImage bufImExpected = ImageIO.read(new File(expectedFile));
        DataBuffer datBufExpected = bufImExpected.getData().getDataBuffer();
        int sizefileExp = datBufExpected.getSize();

        BufferedImage bufImActual = ImageIO.read(new File(actualFile));
        DataBuffer datBufActual = bufImActual.getData().getDataBuffer();
        int sizeFileActual = datBufActual.getSize();

        Boolean isEqual = true;
        if (sizefileExp == sizeFileActual) {
            for (int j = 0; j < sizefileExp; j++) {
                if (datBufExpected.getElem(j) != datBufActual.getElem(j)) {
                    isEqual = false;
                    break;
                }
            }
        } else {
            isEqual = false;
        }
        return isEqual;

    }
/*
    public void checkThatCorrectAttachmentWasUploaded() {
        assertThat("Uploaded file has unexpected name!", attachmentName.equals(uploadedAttachment.getText()));
        System.out.println("Correct file was uploaded.");
        waitWhenClickableAndClick(closeProfilePage, waitWebElem);
    }
 */
    public static boolean comparePdfs(String fileActual, String fileAbsolutePath) throws IOException {
        long fileSizeActual = FileUtils.checksumCRC32(new File(fileActual));
        long fileSizeExpected = FileUtils.checksumCRC32(new File(fileAbsolutePath));
        if (fileSizeActual == fileSizeExpected) {
            return true;
        } else {
            return false;
        }
    }
}
