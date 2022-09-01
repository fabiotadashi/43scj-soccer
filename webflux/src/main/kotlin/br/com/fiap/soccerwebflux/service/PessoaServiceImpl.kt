package br.com.fiap.soccerwebflux.service

import br.com.fiap.soccerwebflux.document.PessoaDocument
import br.com.fiap.soccerwebflux.dto.CreatePessoaDTO
import br.com.fiap.soccerwebflux.dto.PessoaDTO
import br.com.fiap.soccerwebflux.repository.PessoaRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class PessoaServiceImpl(
    val pessoaRepository: PessoaRepository
) : PessoaService {

    override fun create(createPessoaDTO: CreatePessoaDTO) = Mono.just(createPessoaDTO)
        .map { PessoaDocument(name = it.nome) }
        .flatMap { pessoaRepository.save(it) }
        .map {
            PessoaDTO(
                id = it.id ?:  throw Exception(),
                nome = it.name
            )
        }

    override fun list() = pessoaRepository.findAll()
        .map { PessoaDTO(
            id = it.id ?:  throw Exception(),
            nome = it.name
        ) }
        .delayElements(Duration.ofSeconds(1))
}