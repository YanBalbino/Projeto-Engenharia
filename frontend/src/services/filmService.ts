import apiPath from "./apiService";



const fetchFilmsByGenre = async (genre: string, page: number = 0, size: number = 15) => {
    const profileId = localStorage.getItem('currentProfile');
    const token = localStorage.getItem('token');

    try {
      const response = await fetch(`${apiPath}/films/genre/${profileId}?genre=${encodeURIComponent(genre)}&page=${page}&size=${size}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`, 
            'Content-Type': 'application/json',
        },
      });
  
      if (!response.ok) {
        console.log(await response.json())
        return undefined;
      }
      else{
        return await response.json();            
      }

    } catch (error) {
      console.error('Error fetching films by genre:', error);
      return undefined;
    }
  };

export default fetchFilmsByGenre;