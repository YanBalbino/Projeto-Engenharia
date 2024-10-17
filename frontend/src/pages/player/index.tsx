import VideoPlayer from "./videoPlayer"
import React, { useState, useEffect } from "react";
const Player  = () =>{
    const [playlistUrl, setPlaylistUrl] = useState<string>("");

    
    const fetchPlaylist = async (language: string, mediaName: string, subtitleLanguage?: string) => {
      try {
        const queryParams = new URLSearchParams({
          language,
          mediaName,
          ...(subtitleLanguage && { subtitleLanguage }),
        }).toString();
  
        const response = await fetch(`/api/streaming/playlists?${queryParams}`);
  
        if (response.ok) {
          const data = await response.text(); 
          console.log(data)
        } else {
          console.error("Failed to fetch playlist");
        }
      } catch (error) {
        console.error("Error fetching playlist", error);
      }
    };
  
    
    useEffect(() => {
      
      fetchPlaylist("en", "rickandmorty", "pt");
    }, []);
    
    return(
        <VideoPlayer src={`${process.env.PUBLIC_URL}/videos/playlists_rickandmorty/playlist_en.m3u8`} />
    )
}

export default Player;