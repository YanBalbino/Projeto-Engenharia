import React, { useState,useRef,useEffect } from 'react';
import ReactPlayer from 'react-player';
import { Slider, Tooltip } from 'antd';
import { PlayCircleOutlined,PauseCircleOutlined } from '@ant-design/icons';
import Icon from '@ant-design/icons';
import screenfull from 'screenfull';
import Hls from 'hls.js';


interface VideoPlayerProps {
  src: string;
}

interface Caption {
  label: string
  kind: 'subtitles';
  src: string;
  srcLang: string;
  default: boolean;
}


const VolumeIconFull = () =>(
  <svg width="50px" height="50px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M16.0004 9.00009C16.6281 9.83575 17 10.8745 17 12.0001C17 13.1257 16.6281 14.1644 16.0004 15.0001M18 5.29177C19.8412 6.93973 21 9.33459 21 12.0001C21 14.6656 19.8412 17.0604 18 18.7084M4.6 9.00009H5.5012C6.05213 9.00009 6.32759 9.00009 6.58285 8.93141C6.80903 8.87056 7.02275 8.77046 7.21429 8.63566C7.43047 8.48353 7.60681 8.27191 7.95951 7.84868L10.5854 4.69758C11.0211 4.17476 11.2389 3.91335 11.4292 3.88614C11.594 3.86258 11.7597 3.92258 11.8712 4.04617C12 4.18889 12 4.52917 12 5.20973V18.7904C12 19.471 12 19.8113 11.8712 19.954C11.7597 20.0776 11.594 20.1376 11.4292 20.114C11.239 20.0868 11.0211 19.8254 10.5854 19.3026L7.95951 16.1515C7.60681 15.7283 7.43047 15.5166 7.21429 15.3645C7.02275 15.2297 6.80903 15.1296 6.58285 15.0688C6.32759 15.0001 6.05213 15.0001 5.5012 15.0001H4.6C4.03995 15.0001 3.75992 15.0001 3.54601 14.8911C3.35785 14.7952 3.20487 14.6422 3.10899 14.4541C3 14.2402 3 13.9601 3 13.4001V10.6001C3 10.04 3 9.76001 3.10899 9.54609C3.20487 9.35793 3.35785 9.20495 3.54601 9.10908C3.75992 9.00009 4.03995 9.00009 4.6 9.00009Z" 
    stroke="#FFFFFF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
);

const VolumeIconHalf = () =>(
  <svg width="50px" height="50px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M18 9.00009C18.6277 9.83575 18.9996 10.8745 18.9996 12.0001C18.9996 13.1257 18.6277 14.1644 18 15.0001M6.6 9.00009H7.5012C8.05213 9.00009 8.32759 9.00009 8.58285 8.93141C8.80903 8.87056 9.02275 8.77046 9.21429 8.63566C9.43047 8.48353 9.60681 8.27191 9.95951 7.84868L12.5854 4.69758C13.0211 4.17476 13.2389 3.91335 13.4292 3.88614C13.594 3.86258 13.7597 3.92258 13.8712 4.04617C14 4.18889 14 4.52917 14 5.20973V18.7904C14 19.471 14 19.8113 13.8712 19.954C13.7597 20.0776 13.594 20.1376 13.4292 20.114C13.239 20.0868 13.0211 19.8254 12.5854 19.3026L9.95951 16.1515C9.60681 15.7283 9.43047 15.5166 9.21429 15.3645C9.02275 15.2297 8.80903 15.1296 8.58285 15.0688C8.32759 15.0001 8.05213 15.0001 7.5012 15.0001H6.6C6.03995 15.0001 5.75992 15.0001 5.54601 14.8911C5.35785 14.7952 5.20487 14.6422 5.10899 14.4541C5 14.2402 5 13.9601 5 13.4001V10.6001C5 10.04 5 9.76001 5.10899 9.54609C5.20487 9.35793 5.35785 9.20495 5.54601 9.10908C5.75992 9.00009 6.03995 9.00009 6.6 9.00009Z" 
    stroke="#FFFFFF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
)

const VolumeIconMute = () =>(
  <svg width="50px" height="50px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M16 9.50009L21 14.5001M21 9.50009L16 14.5001M4.6 9.00009H5.5012C6.05213 9.00009 6.32759 9.00009 6.58285 8.93141C6.80903 8.87056 7.02275 8.77046 7.21429 8.63566C7.43047 8.48353 7.60681 8.27191 7.95951 7.84868L10.5854 4.69758C11.0211 4.17476 11.2389 3.91335 11.4292 3.88614C11.594 3.86258 11.7597 3.92258 11.8712 4.04617C12 4.18889 12 4.52917 12 5.20973V18.7904C12 19.471 12 19.8113 11.8712 19.954C11.7597 20.0776 11.594 20.1376 11.4292 20.114C11.239 20.0868 11.0211 19.8254 10.5854 19.3026L7.95951 16.1515C7.60681 15.7283 7.43047 15.5166 7.21429 15.3645C7.02275 15.2297 6.80903 15.1296 6.58285 15.0688C6.32759 15.0001 6.05213 15.0001 5.5012 15.0001H4.6C4.03995 15.0001 3.75992 15.0001 3.54601 14.8911C3.35785 14.7952 3.20487 14.6422 3.10899 14.4541C3 14.2402 3 13.9601 3 13.4001V10.6001C3 10.04 3 9.76001 3.10899 9.54609C3.20487 9.35793 3.35785 9.20495 3.54601 9.10908C3.75992 9.00009 4.03995 9.00009 4.6 9.00009Z" 
    stroke="#FFFFFF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
)

const SubtitleIcon = () =>( 
  <svg width="50px" height="50px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M2 12C2 8.22876 2 6.34315 3.17157 5.17157C4.34315 4 6.22876 4 10 4H14C17.7712 4 19.6569 4 20.8284 5.17157C22 6.34315 22 8.22876 22 12C22 15.7712 22 17.6569 20.8284 18.8284C19.6569 20 17.7712 20 14 20H10C6.22876 20 4.34315 20 3.17157 18.8284C2 17.6569 2 15.7712 2 12Z" stroke="#FFFFFF" strokeWidth="1.5"/>
    <path d="M10 16H6" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M14 13H18" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M14 16H12.5" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M9.5 13H11.5" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M18 16H16.5" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
    <path d="M6 13H7" stroke="#FFFFFF" strokeWidth="1.5" strokeLinecap="round"/>
  </svg>
)

const FullScreenIcon = () =>(
  <svg width="50px" height="50px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
    <path d="M21 9V8C21 5.79086 18.9853 4 16.5 4H15.25M21 15V16C21 18.2091 18.9853 20 16.5 20H15.25M3 15V16C3 18.2091 5.01472 20 7.5 20H8.75M3 9V8C3 5.79086 5.01472 4 7.5 4H8.75" 
    stroke="#FFFFFF" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round"/>
  </svg>
)


const VideoPlayer: React.FC<VideoPlayerProps> = ({ src }) => {
  const [isPlaying, setIsPlaying] = useState(false);
  const [volume, setVolume] = useState(1);
  const [currentTime, setCurrentTime] = useState(0);
  const [duration, setDuration] = useState(0);
  const [subtititlesModal,setSubtitlesModal] = useState(false);
  const playerRef = useRef<ReactPlayer>(null);
  const playerContainerRef = useRef(null);
  const [controlsVisible, setControlsVisible] = useState(false);
  const [mouseOverControls,setMouseOverControls] = useState(false);
  const controlsTimeoutRef = useRef<NodeJS.Timeout | null>(null); 
  const subtitles = ['Português']
  const [selectedSub,setSelectedSub] = useState<number>(1);
  const [previousTime, setPreviousTime] = useState<number | null>(null);
  const captions:Caption[] = [
    {label: '', kind: 'subtitles', src: '', srcLang: '',default: true},
    {label: "Portuguese" , kind: 'subtitles', src:'/videos/playlists_rickandmorty/RickAndMortyS01E01_legenda.vtt'	, srcLang: 'pt', default: true},
  ]
  

  
  useEffect(() => {
    const interval = setInterval(() => {
      if (playerRef.current) {
        setCurrentTime(playerRef.current.getCurrentTime());
      }
    }, 1000); 

    return () => clearInterval(interval);
  }, [isPlaying]);

  const handleDuration = (duration: number) => {
    setDuration(duration);
  };

  const handleSubtitlesModal = () =>{
   setSubtitlesModal(!subtititlesModal)
    
  }

  const handleSliderChange = (value: number) => {
    setCurrentTime(value);
    if (playerRef.current) {
      playerRef.current.seekTo(value, 'seconds');
    }
  };

  const handleMouseMove = () => {
    setControlsVisible(true);
    if (controlsTimeoutRef.current) {
      clearTimeout(controlsTimeoutRef.current); 
    }
    
    controlsTimeoutRef.current = setTimeout(() => {
      if(!mouseOverControls){
        setControlsVisible(false);
      }
    }, 2000);
  };


  const togglePlayPause = () => {
    setIsPlaying(!isPlaying);
  };

  const handleVolumeChange = (value: number) => {
    setVolume(value / 100); 
  };

  const handleSubtitleChange = (index: number) => {
    setPreviousTime(currentTime);
    setSelectedSub(index); 
  };

  const volumeIcon = () => {
    if (volume < 0.50 && volume > 0) {
      return <VolumeIconHalf />;
    }
    if (volume > 0.50) {
      return <VolumeIconFull />;
    }
    return <VolumeIconMute />;
  };

  const toggleFullScreen = () => {
    if (playerContainerRef.current) {
      screenfull.toggle(playerContainerRef.current);
    }
  };

  const formatTime = (seconds: number): string => {
    const minutes = Math.floor(seconds / 60);
    const secs = Math.floor(seconds % 60);
    return `${String(minutes).padStart(2, '0')}:${String(secs).padStart(2, '0')}`;
  };

  useEffect(() => {
    if (Hls.isSupported() && src.includes('.m3u8')) {
      const player = playerRef.current?.getInternalPlayer() as HTMLMediaElement | undefined;
      if (player) {
        const hls = new Hls();
        hls.loadSource(src);
        hls.attachMedia(player);
      }
    }
  }, [src]);

  useEffect(() => {
    console.log(selectedSub)
    if (previousTime !== null && playerRef.current) {
    
      playerRef.current.seekTo(previousTime, 'seconds');
    }
  }, [selectedSub]);
  

  
  return (
    <div onMouseMove={handleMouseMove}  ref={playerContainerRef} className="flex flex-col bg-black items-center justify-center w-screen h-screen">
      <div onClick={togglePlayPause} onDoubleClick={toggleFullScreen} className='w-full h-full'>
      <ReactPlayer
          key={selectedSub}
          ref={playerRef}
          url={src}
          playing={isPlaying}
          controls={false}
          volume={volume}
          width="100%"
          height="100%"
          onDuration={handleDuration}
          onProgress={({ playedSeconds }) => setCurrentTime(playedSeconds)}
          config={{ file: {
            // attributes:{
            //   crossOrigin: 'anonymus'
            // },
            tracks: [captions[selectedSub]],
              
            
          }}}
       />
      
      </div>
      
      
      
     
      <div onMouseEnter={() => setMouseOverControls(true)} onMouseLeave={() => setMouseOverControls(false)} className={`${controlsVisible ? 'opacity-100' : 'opacity-0'} transition-opacity duration-300 flex flex-col absolute bottom-0 w-[95%]`}>
      {subtititlesModal && (
        <div onMouseLeave={handleSubtitlesModal}  className={"flex flex-col gap-2  absolute bottom-28 mr-6 right-0 bg-slate-600 w-40 h-60"}>
          <h1 className=' text-white font-bold text-center'>Legendas</h1>
          <ul className='text-white '>
            <li onClick={() => handleSubtitleChange(0)} className={`${selectedSub == 0  ? 'bg-slate-500' : ''}  pl-5 hover:cursor-pointer hover:bg-slate-300 `}>Desligadas</li>
            {subtitles.map((subtitle,index)=>{
              return(
                <li onClick={() => handleSubtitleChange(index+1)} key={index+1} className={`${selectedSub == index+1  ? 'bg-slate-500' : ''}  pl-5 hover:cursor-pointer hover:bg-slate-300 `}>{subtitle}</li>
              )
            })}
          </ul>
        </div>
      )}

        <div className='flex flex-row gap-5'>
          <Slider value={currentTime} onChange={handleSliderChange} min={0} max={duration} step={0.1}  tooltip={{formatter: null}}  styles={{
            track:{
              backgroundColor: 'rgb(14 116 144)'
            }
          }} className="w-11/12"/>
          <p className=' text-white'>{formatTime(currentTime)}</p> 
        </div>
        <div className="flex flex-row w-full m-4 justify-between">
          <div className="flex flex-row gap-10">
            {isPlaying ? (
              <PauseCircleOutlined onClick={togglePlayPause} className="text-5xl text-white hover:cursor-pointer" />
            ) : (
              <PlayCircleOutlined onClick={togglePlayPause} className="text-5xl text-white hover:cursor-pointer" />
            )}
            <div className="flex items-center space-x-4">
              <label htmlFor="volume" className="text-sm font-medium">
                <Icon component={volumeIcon} className='text-4xl text-white hover:cursor-pointer'/>
              </label>
              <Slider onChange={handleVolumeChange} defaultValue={100} min={0} max={100} step={1}styles={{
            track:{
              backgroundColor: 'rgb(14 116 144)'
            }
          }} className='w-24 hover:cursor-pointer'/>
            </div>
          </div>
          <div className='flex flex-row gap-10'>
            <Icon component={SubtitleIcon} onClick={handleSubtitlesModal}  className='text-4xl text-white hover:cursor-pointer'/>
            <Icon component={FullScreenIcon} onClick={toggleFullScreen} className='text-4xl text-white hover:cursor-pointer'/>
          </div>
        </div>
      </div>
    </div>
  );
};


export default VideoPlayer;
