import VideoPlayer from "./videoPlayer"
const Player  = () =>{
    return(
        <VideoPlayer src={`${process.env.PUBLIC_URL}/videos/RickAndMortyS01E01.mp4`} />
    )
}

export default Player;