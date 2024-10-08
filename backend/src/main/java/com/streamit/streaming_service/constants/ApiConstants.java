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
    public static final String PATH_USERS_RENEW = "api/users/{userId}/subscriptions/{subscriptionId}/payments/{paymentId}/renew";
    public static final String PATH_USER_BY_ID = "/api/users/{id}";
    
    public static final String PATH_PROFILES = "/api/profiles";
    public static final String PATH_PROFILE_BY_ID = "/api/profiles/{id}";
    
    public static final String PATH_FILMS = "/api/films";
    public static final String PATH_FILMS_ID = "/api/films/{id}";
    public static final String PATH_FILMS_ID_AUDIO = "/api/films/{id}/audio";
    public static final String PATH_FILMS_ID_SUBTITLE = "/api/films/{id}/subtitle";
    public static final String PATH_FILMS_ID_ACTOR = "/api/films/{id}/actor";
    
    public static final String PATH_SERIES = "/api/series";
    public static final String PATH_SERIES_ID = "/api/series/{id}";
    public static final String PATH_SERIES_ID_SEASON = "/api/series/{id}/season";
    public static final String PATH_SERIES_ID_ACTOR = "/api/series/{id}/actor";
    
    public static final String PATH_CATALOGS = "/api/catalogs";
    public static final String PATH_CATALOGS_ID = "/api/catalogs/{id}";
    
    public static final String PATH_MEDIAS = "/api/medias";
    public static final String PATH_MEDIAS_ID = "/api/medias/{id}";
    
    public static final String PATH_SUBTITLES = "/api/subtitles";
    public static final String PATH_SUBTITLES_ID = "/api/subtitles/{id}";
    
    public static final String PATH_AUDIOS = "/api/audios";
    public static final String PATH_AUDIOS_ID = "/api/audios/{id}";
    
    public static final String PATH_ACTORS = "/api/actors";
    public static final String PATH_ACTORS_ID = "/api/actors/{id}";
    
    public static final String PATH_SEASONS = "/api/seasons";
    public static final String PATH_SEASONS_ID = "/api/seasons/{id}";
    public static final String PATH_SEASONS_ID_EPISODE = "/api/seasons/{id}/episode";
    
    public static final String PATH_EPISODES = "/api/episodes";
    public static final String PATH_EPISODES_ID = "/api/episodes/{id}";
    public static final String PATH_EPISODES_ID_AUDIO = "/api/episodes/{id}/audio";
    public static final String PATH_EPISODES_ID_SUBTITLE = "/api/episodes/{id}/subtitle";
    
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
