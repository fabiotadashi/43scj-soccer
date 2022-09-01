package br.com.fiap.soccerwebflux.service

import br.com.fiap.soccerwebflux.dto.CreatePessoaDTO
import br.com.fiap.soccerwebflux.dto.PessoaDTO
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PessoaService {

    fun create(createPessoaDTO: CreatePessoaDTO): Mono<PessoaDTO>
    fun list(): Flux<PessoaDTO>

}