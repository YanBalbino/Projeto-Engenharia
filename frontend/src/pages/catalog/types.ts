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
