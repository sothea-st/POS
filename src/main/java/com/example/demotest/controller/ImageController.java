// package com.example.demotest.controller;

// import com.example.demotest.ImageUtils;
// import com.example.demotest.service.ImageService;
// import jakarta.servlet.ServletContext;
// import jakarta.servlet.http.HttpServletResponse;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.io.InputStreamResource;
// import org.springframework.http.*;
// import org.springframework.util.StreamUtils;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Base64;
// import java.util.zip.DataFormatException;

// import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

// @RestController
// @RequestMapping("/image")
// public class ImageController {

//     @Autowired
//     private  ImageService imageService;


//     @GetMapping("/read")
//     public ResponseEntity<byte[]> read() {
//         System.out.println("hello");
//         String str = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/7AARRHVja3kAAQAEAAAAPAAA/+EAGEV4aWYAAElJKgAIAAAAAAAAAAAAAAD/4QNvaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29yZSA1LjMtYzAxMSA2Ni4xNDU2NjEsIDIwMTIvMDIvMDYtMTQ6NTY6MjcgICAgICAgICI+IDxyZGY6UkRGIHhtbG5zOnJkZj0iaHR0cDovL3d3dy53My5vcmcvMTk5OS8wMi8yMi1yZGYtc3ludGF4LW5zIyI+IDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiIHhtbG5zOnhtcE1NPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvbW0vIiB4bWxuczpzdFJlZj0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL3NUeXBlL1Jlc291cmNlUmVmIyIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9InhtcC5kaWQ6MjI2QkZCMDY1OEFERUExMUJEQTBFRjBCMTc0NDMxQkQiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6NEUzNDI1MEFDMzJDMTFFQUE3MzJCMzc0QjYzM0NGNEEiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6NEUzNDI1MDlDMzJDMTFFQUE3MzJCMzc0QjYzM0NGNEEiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDowQ0QyRDBBODgyQzJFQTExQjM2ODlGREI2RTg3NjJENCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDoyMjZCRkIwNjU4QURFQTExQkRBMEVGMEIxNzQ0MzFCRCIvPiA8L3JkZjpEZXNjcmlwdGlv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";
//         return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf(MediaType.IMAGE_JPEG_VALUE)).body(str.getBytes());
//     }


//     @GetMapping(value = "/pixels/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
//     @ResponseBody
//     public byte[] pixelTracking(@PathVariable("fileName") String fileName) throws IOException {

//         byte[] bytes = Files.readAllBytes(Paths.get("img\\"+fileName));
//         String base = Base64.getEncoder().encodeToString(bytes);
//         System.out.println("3333333Hello " + base);
//         System.out.println("3333333Hello ");
//         // return png image

//         String content = new String(Files.readAllBytes(Paths.get("img\\hello.txt")));

//         return Base64.getDecoder().decode(base);
//     }


//     @PostMapping
//     public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//         String uploadImage = imageService.uploadImage(file);
//         return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
//     }

//     @GetMapping("/{fileName}")
//     public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
//         byte[] imageData = imageService.downloadImage(fileName);
//         return ResponseEntity.status(HttpStatus.OK)
//                 .contentType(MediaType.valueOf(IMAGE_PNG_VALUE))
//                 .body(imageData);
//     }

//     @GetMapping("/get-text")
//     public @ResponseBody String getText() {
//         return "Hello world";
//     }



// }
