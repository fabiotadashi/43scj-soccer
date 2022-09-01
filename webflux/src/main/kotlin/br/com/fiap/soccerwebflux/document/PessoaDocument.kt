package br.com.fiap.soccerwebflux.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PessoaDocument(

    @Id
    val id: String? = null,

    val name: String

)