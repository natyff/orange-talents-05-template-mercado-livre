package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagensRequest {

    @Size(min = 1)
    @NotBlank
    private List<MultipartFile> imagens = new ArrayList<>();


    public ImagensRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public ImagensRequest() {
    }

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

}

