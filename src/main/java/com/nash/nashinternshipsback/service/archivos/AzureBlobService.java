package com.nash.nashinternshipsback.service.archivos;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.nash.nashinternshipsback.model.Archivo;
import com.nash.nashinternshipsback.model.Carrera;
import com.nash.nashinternshipsback.model.enums.Etapas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;

@Component
public class AzureBlobService {

    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;

    @Autowired
    ArchivoService archivoService;

    public String subirArchivo(MultipartFile multipartFile) throws IOException {

        // Todo UUID
        BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
        blob.upload(multipartFile.getInputStream(), multipartFile.getSize(), true);

        //      TEST CREACION DE DOCUMENTO
        Archivo archivo = new Archivo();
        //archivo.setCarrera(new Carrera());
        archivo.setEtapa(Etapas.ETAPA_I);
        String url = blob.getBlobUrl();
        archivo.setUrl(url);
        archivo.setNombre(blob.getBlobName());
        archivoService.guardar(archivo);
        return multipartFile.getOriginalFilename();
    }

    public byte[] getFile(String fileName) throws URISyntaxException {

        BlobClient blob = blobContainerClient.getBlobClient(fileName);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.download(outputStream);
        final byte[] bytes = outputStream.toByteArray();
        return bytes;

    }

    public List<Object> listarArchivos() {

        PagedIterable<BlobItem> items = blobContainerClient.listBlobs();
        List<Object> names = new ArrayList<Object>();
        for (BlobItem item : items) {
            BlobClient blob = blobContainerClient.getBlobClient(item.getName());
            names.add(blob.getBlobUrl());

        }
        return names;

    }

    public Boolean eliminarArchivo(String blobName) {

        BlobClient blob = blobContainerClient.getBlobClient(blobName);
        blob.delete();
        return true;
    }

}