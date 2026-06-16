package com.campusEvent.campus_event.service;

import com.campusEvent.campus_event.entity.User;
import com.campusEvent.campus_event.relations.Registration;
import com.campusEvent.campus_event.repository.RegisRepo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;


@Service
public class QRCodeService {

    @Autowired
    private RegisRepo regisrep;
    @Autowired
    private RegistrationService registrationService;

    @SneakyThrows
    public byte[] fetchTicket(long eventId) {
         User user = (User) registrationService.getAuthentication().getPrincipal();
         Registration reg = regisrep.findByUser_IdAndEvent_Eventid(user.getId(), eventId).orElse(null);
        if(reg == null){
            throw new IllegalStateException("Registration not found");
        }
         int width = 300, height =300;
        BitMatrix bitMatrix = new MultiFormatWriter()
                .encode(Long.toString(reg.getRegistrationId()), BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        return pngOutputStream.toByteArray();
    }
}
