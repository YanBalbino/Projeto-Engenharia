export interface Movie {
    title: string;
    description: string;
    releaseYear: string;
    duration: string;
    pictureUrl: string;
}

export interface CatalogRowProps {
    movies: Movie[];
    gender: string;
}

export interface CardProps{
    title: string; 
    description: string; 
    releaseYear: string;
    duration:string;
    pictureUrl:string;
    last:boolean;
    first:boolean;
}
