package br.com.fiap.soccerwebflux.controller

import br.com.fiap.soccerwebflux.dto.CreatePessoaDTO
import br.com.fiap.soccerwebflux.service.PessoaService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("pessoas")
class PessoaController(
    val pessoaService: PessoaService
) {

    @GetMapping(produces = [MediaType.APPLICATION_STREAM_JSON_VALUE])
    fun list() = pessoaService.list()

    @PostMapping
    fun create(@RequestBody createPessoaDTO: CreatePessoaDTO) =
        pessoaService.create(createPessoaDTO)

}