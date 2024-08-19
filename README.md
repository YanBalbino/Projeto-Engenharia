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
    class UserModel {
        -UUID id
        -String nome
        -String email
        -String senha
        -String metodoPagamento
        -LocalDate dataCadastro
    }

    class ProfileModel {
        -UUID id
        -String nome
        -boolean perfilInfantil
        -List<String> generosPreferidos
    }

    class SubscriptionModel {
        -UUID id
        -LocalDate dataInicio
        -LocalDate dataTermino
        -boolean statusAtivo
    }

    class MediaModel {
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

    class SeriesModel {
        -int numeroTemporadas
        -int numeroEpisodios
    }

    class FilmModel

    class CatalogModel {
        -UUID id
    }

    UserModel "1" -- "1..*" ProfileModel : "has"
    UserModel "1" -- "1..1" SubscriptionModel : "has"
    SubscriptionModel "1" -- "1" UserModel : "belongs to"

    CatalogModel "1" -- "0..*" FilmModel : "includes"
    CatalogModel "1" -- "0..*" SeriesModel : "includes"

    MediaModel "1" -- "1" CatalogModel : "belongs to"

    SeriesModel "1" -- "1" MediaModel : "is a"
    FilmModel "1" -- "1" MediaModel : "is a"
