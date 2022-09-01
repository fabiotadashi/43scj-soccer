package br.com.fiap.soccerwebflux.repository

import br.com.fiap.soccerwebflux.document.PessoaDocument
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface PessoaRepository: ReactiveMongoRepository<PessoaDocument, String>