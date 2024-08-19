# StreamIt
StreamIt é uma aplicação completa para gerenciar e distribuir conteúdo audiovisual, incluindo filmes e séries. O sistema oferece uma solução robusta para usuários que desejam acessar uma vasta biblioteca de mídias. O projeto integra funcionalidades essenciais de gerenciamento de usuários, assinaturas, perfis e conteúdos.

## Alunos
- Erick Patrick de Paula Freitas
- João Emanoel Soares Silva 
- Rafael Lucas de Azevedo Nunes
- Samuel Rógenes Carvalho Freire
- Yan Balbino Nogueira
  
# Diagrama de Classes (Domínio da API)
```mermaid
classDiagram
    class User {
        -UUID id
        -String nome
        -String email
        -String senha
        -String metodoPagamento
        -LocalDate dataCadastro
    }

    class Profile {
        -UUID id
        -String nome
        -boolean perfilInfantil
        -List<String> generosPreferidos
    }

    class Subscription {
        -UUID id
        -LocalDate dataInicio
        -LocalDate dataTermino
        -boolean statusAtivo
    }

    class Media {
        -UUID id
        -String titulo
        -int anoProducao
        -String genero
        -int duracao
        -List<String> legendasDisponiveis
        -List<String> audiosDisponiveis
        -String descricao
        -List<String> atores
        -String diretor
        -String videoUrl
    }

    class Series {
        -int numeroTemporadas
        -int numeroEpisodios
    }

    class Film

    class Catalog {
        -UUID id
    }

    User "1" -- "1..*" Profile : "has"
    User "1" -- "1..1" Subscription : "has"
    Subscription "1" -- "1" User : "belongs to"

    Catalog "1" -- "0..*" Film : "includes"
    Catalog "1" -- "0..*" Series : "includes"

    Media "1" -- "1" Catalog : "belongs to"

    Series "1" -- "1" Media : "is a"
    Film "1" -- "1" Media : "is a"
