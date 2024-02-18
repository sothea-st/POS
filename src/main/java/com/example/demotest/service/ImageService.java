// package com.example.demotest.service;

// import com.example.demotest.ImageUtils;
// import com.example.demotest.entity.Image;
// import com.example.demotest.repository.ImageRepository;
// import org.apache.commons.lang3.exception.ContextedRuntimeException;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.Base64;
// import java.util.Optional;
// import java.util.zip.DataFormatException;

// @Service
// public class ImageService {

//     @Autowired
//     private ImageRepository imageRepository;


//     public void saveImage(String fileName,String base64){

//         String base64String = "data:image/jpeg;base64,"+base64;

//         String[] strings = base64String.split(",");
//         String extension;
//         switch (strings[0]) {//check image's extension
//             case "data:image/jpeg;base64":
//                 extension = "jpeg";
//                 break;
//             case "data:image/png;base64":
//                 extension = "png";
//                 break;
//             default://should write cases for more images types
//                 extension = "jpg";
//                 break;
//         }
//         //convert base64 string to binary data
//         byte[] data =  Base64.getDecoder().decode(strings[1]);
//         String path = "img\\"+fileName;
//         File file = new File(path);
//         try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//             outputStream.write(data);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public String uploadImage(MultipartFile imageFile) throws IOException {
//         String fileName = imageFile.getOriginalFilename();
//         byte[] bytes = imageFile.getBytes();
//         String base64 = Base64.getEncoder().encodeToString(bytes);
//         System.out.println(base64);
//         saveImage(fileName,base64);
//         var imageToSave = Image.builder()
//                 .name(fileName)
//                 .type(imageFile.getContentType())
//                 .imageData(ImageUtils.compressImage(imageFile.getBytes()))
//                 .build();

//         imageRepository.save(imageToSave);
//         return "file uploaded successfully : " + imageFile.getOriginalFilename();
//     }



//     public byte[] downloadImage(String imageName) {
//         Optional<Image> dbImage = imageRepository.findByName(imageName);
//         return dbImage.map(image -> {
//             try {
//                 return ImageUtils.decompressImage(image.getImageData());
//             } catch (DataFormatException | IOException exception) {

//                 throw new ContextedRuntimeException("Error downloading an image", exception)
//                         .addContextValue("Image ID",  image.getId())
//                         .addContextValue("Image name", imageName);
//             }
//         }).orElse(null);
//     }
// }
