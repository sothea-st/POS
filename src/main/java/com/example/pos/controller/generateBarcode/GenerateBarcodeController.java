package com.example.pos.controller.generateBarcode;

import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import java.awt.Font;
import java.awt.image.BufferedImage;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/generateBarcode")
public class GenerateBarcodeController {
    @Autowired
    BarcodeGenerator barcodeGenerator;

    @GetMapping(value = "/barcodes/{barcode}", produces = IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generate(
            @PathVariable("barcode") final String barcodeText) throws Exception {

        return ResponseEntity.ok().body(barcodeGenerator.generateUSPSBarcodeImage(barcodeText));
        
    }
}
