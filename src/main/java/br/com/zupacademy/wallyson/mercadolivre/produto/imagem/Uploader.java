package br.com.zupacademy.wallyson.mercadolivre.produto.imagem;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class Uploader {

    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map(this::generateLink).collect(Collectors.toSet());
    }

    private String generateLink(MultipartFile imagem) {
        String formatoArquivo = imagem.getContentType().split("/")[1];
        int posicaoExtensao = imagem.getOriginalFilename().lastIndexOf(".");
        String nomeArquivo = imagem.getOriginalFilename().substring(0, posicaoExtensao);
        String idUnico = UUID.randomUUID().toString();
        return String.format("http://hospedagem-imagem.com/%s-%s.%s", nomeArquivo, idUnico, formatoArquivo);
    }
}
