export interface Movie {
    title: string;
    description: string;
    releaseYear: string;
    duration: string;
    pictureUrl: string;
}

export interface CatalogRowProps {
    genre: string;
    genre_movie:string
}

export interface CardProps{
    title: string; 
    description: string; 
    releaseYear: string;
    duration:string;
    pictureUrl:string;
    last?:boolean;
    first?:boolean;
}


interface Audio {
    id: string;
    idioma: string;
    url: string;
  }
  
interface Subtitle {
    id: string;
    idioma: string;
    url: string;
  }
  
interface Actor {
    id: string;
    imagemUrl: string;
    nome: string;
  }
  
export interface Media {
    anoProducao: string;
    atores: Actor[];
    descricao: string;
    diretor: string;
    faixaEtaria: string;
    genero: string;
    id: string;
    imgUrl: string;
    titulo: string;
    videoUrl: string;
  }
  
export interface ReturnFilmDTO {
    id: string;
    duracao: string;
    audiosDisponiveis: Audio[];
    legendasDisponiveis: Subtitle[];
    media: Media;
  }
  
  export interface Pageable<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    number: number;  
    size: number;    
    first: boolean;
    last: boolean;
  }
