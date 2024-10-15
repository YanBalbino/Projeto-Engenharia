package com.streamit.streaming_service.constants;

public final class ApiConstants {

    // Códigos de Status HTTP
    public static final int HTTP_STATUS_OK = 200;
    public static final int HTTP_STATUS_CREATED = 201;
    public static final int HTTP_STATUS_NO_CONTENT = 204;
    public static final int HTTP_STATUS_BAD_REQUEST = 400;
    public static final int HTTP_STATUS_UNAUTHORIZED = 401;
    public static final int HTTP_STATUS_FORBIDDEN = 403;
    public static final int HTTP_STATUS_NOT_FOUND = 404;
    public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

    // Paths de API
    public static final String PATH_USERS = "/api/users";
    public static final String PATH_USERS_REGISTER_CREDIT_CARD = "/api/users/register/credit-card";
    public static final String PATH_USERS_REGISTER_BANK_SLIP = "/api/users/register/bank-slip";
    public static final String PATH_USERS_UPDATE_RENEW = "/api/users/update/renew";
    public static final String PATH_USERS_UPDATE = "/api/users/update";
    public static final String PATH_USERS_DELETE_ID = "/api/users/delete/{id}";

    public static final String PATH_PROFILES = "/api/profiles";
    public static final String PATH_PROFILES_USER_ID = "/api/profiles/{userId}";
    public static final String PATH_PROFILES_UPDATE = "/api/profiles/update";
    public static final String PATH_PROFILES_DELETE_ID = "/api/profiles/delete/{id}";

    public static final String PATH_FILMS = "/api/films";
    public static final String PATH_FILMS_ID = "/api/films/{id}";
    public static final String PATH_FILMS_TITULO = "/api/films/{titulo}";
    public static final String PATH_FILMS_UPDATE_ID = "/api/films/update/{id}";
    public static final String PATH_FILMS_UPDATE_ID_AUDIO = "/api/films/update/{id}/audio";
    public static final String PATH_FILMS_UPDATE_ID_SUBTITLE = "/api/films/update/{id}/subtitle";
    public static final String PATH_FILMS_UPDATE_ID_ACTOR = "/api/films/update/{id}/actor";
    public static final String PATH_FILMS_DELETE_ID = "/api/films/delete/{id}";

    public static final String PATH_SERIES = "/api/series";
    public static final String PATH_SERIES_TITULO = "/api/series/{titulo}";
    public static final String PATH_SERIES_UPDATE = "/api/series/update";
    public static final String PATH_SERIES_UPDATE_ID_SEASON = "/api/series/update/{id}/season";
    public static final String PATH_SERIES_UPDATE_ID_ACTOR = "/api/series/update/{id}/actor";
    public static final String PATH_SERIES_DELETE_ID = "/api/series/delete/{id}";

    public static final String PATH_MEDIAS = "/api/medias";
    public static final String PATH_MEDIAS_UPDATE = "/api/medias/update";

    public static final String PATH_SUBTITLES = "/api/subtitles";
    public static final String PATH_SUBTITLES_UPDATE = "/api/subtitles/update";
    public static final String PATH_SUBTITLES_DELETE_ID = "/api/subtitles/delete/{id}";

    public static final String PATH_AUDIOS = "/api/audios";
    public static final String PATH_AUDIOS_ID = "/api/audios/{id}";
    public static final String PATH_AUDIOS_DELETE_ID = "/api/audios/delete/{id}";
    public static final String PATH_AUDIOS_UPDATE = "/api/audios/update";

    public static final String PATH_ACTORS = "/api/actors";
    public static final String PATH_ACTORS_ID = "/api/actors/{id}";
    public static final String PATH_ACTORS_DELETE_ID = "/api/actors/delete/{id}";
    public static final String PATH_ACTORS_UPDATE = "/api/actors/update";

    public static final String PATH_SEASONS = "/api/seasons";
    public static final String PATH_SEASONS_SERIES_ID = "/api/seasons/{seriesId}";
    public static final String PATH_SEASONS_UPDATE = "/api/seasons/update";
    public static final String PATH_SEASONS_UPDATE_ID_EPISODE = "/api/seasons/update/{id}/episode";
    public static final String PATH_SEASONS_DELETE_ID = "/api/seasons/delete/{id}";

    public static final String PATH_EPISODES = "/api/episodes";
    public static final String PATH_EPISODES_ID = "/api/episodes/{id}";
    public static final String PATH_EPISODES_ID_AUDIO = "/api/episodes/{id}/audio";
    public static final String PATH_EPISODES_ID_SUBTITLE = "/api/episodes/{id}/subtitle";
    public static final String PATH_EPISODES_DELETE_ID = "/api/episodes/delete/{id}";
    public static final String PATH_EPISODES_UPDATE_ID = "/api/episodes/update/{id}";
    public static final String PATH_EPISODES_UPDATE_ID_AUDIO = "/api/episodes/update/{id}/audio";
    public static final String PATH_EPISODES_UPDATE_ID_SUBTITLE = "/api/episodes/update/{id}/subtitle";
    
    // Mensagens padrão
    public static final String MESSAGE_SUCCESS = "Request processed successfully";
    public static final String MESSAGE_RESOURCE_CREATED = "Resource successfully created";
    public static final String MESSAGE_RESOURCE_UPDATED = "Resource successfully updated";
    public static final String MESSAGE_RESOURCE_DELETED = "Resource successfully deleted";
    public static final String MESSAGE_RESOURCE_NOT_FOUND = "Resource not found";
    public static final String MESSAGE_RESOURCE_ALREADY_EXISTS = "Resource already exists";
    public static final String MESSAGE_MAX_PROFILES_LIMIT_REACHED = "Max profiles limit reached";
    public static final String MESSAGE_RESOURCE_ADDED = "Resource successfully added";
    public static final String MESSAGE_BAD_REQUEST = "Invalid request parameters";
    public static final String MESSAGE_INTERNAL_ERROR = "Internal server error";

    private ApiConstants() {
        throw new AssertionError("Instantiating utility class.");
    }
}
