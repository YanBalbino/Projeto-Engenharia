import CatalogCard from '../catalog/catalogCard'

const Search = () => {

    const cardInfos = [
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: true
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: false,
            first: false
        },
        {
            title: "Batman",
            description: "Filme de ação",
            releaseYear: "2021",
            duration: "120",
            pictureUrl: "batPost.jpg",
            last: true,
            first: false
        }];


    return (
        <div id="content" className="pl-9 pt-6 flex flex-col gap-8">
            <div id="searchContent" className="flex flex-col gap-4">
                <h1 className="text-5xl">Pesquisar no catálogo</h1>
                <input 
                    type="text" 
                    autoFocus
                    placeholder="Pesquisar" 
                    className="rounded-3xl bg-gray-300 w-3/4 h-12 pl-3 text-black text-lg"/>
            </div>
            
                <div id="searchCards" className="flex flex-row gap-10">
                    <CatalogCard 
                    title={cardInfos[0].title} 
                    description={cardInfos[0].description} 
                    releaseYear={cardInfos[0].releaseYear} 
                    duration={cardInfos[0].duration} 
                    pictureUrl={cardInfos[0].pictureUrl} 
                    last={cardInfos[0].last} 
                    first={cardInfos[0].first}/>
                    <CatalogCard 
                    title={cardInfos[1].title} 
                    description={cardInfos[1].description} 
                    releaseYear={cardInfos[1].releaseYear} 
                    duration={cardInfos[1].duration} 
                    pictureUrl={cardInfos[1].pictureUrl} 
                    last={cardInfos[1].last} 
                    first={cardInfos[1].first}/>
                    <CatalogCard 
                    title={cardInfos[2].title} 
                    description={cardInfos[2].description} 
                    releaseYear={cardInfos[2].releaseYear} 
                    duration={cardInfos[2].duration} 
                    pictureUrl={cardInfos[2].pictureUrl} 
                    last={cardInfos[2].last} 
                    first={cardInfos[2].first}/>
                    <CatalogCard 
                    title={cardInfos[3].title} 
                    description={cardInfos[3].description} 
                    releaseYear={cardInfos[3].releaseYear} 
                    duration={cardInfos[3].duration} 
                    pictureUrl={cardInfos[3].pictureUrl} 
                    last={cardInfos[3].last} 
                    first={cardInfos[3].first}/>
                    <CatalogCard 
                    title={cardInfos[4].title} 
                    description={cardInfos[4].description} 
                    releaseYear={cardInfos[4].releaseYear} 
                    duration={cardInfos[4].duration} 
                    pictureUrl={cardInfos[4].pictureUrl} 
                    last={cardInfos[4].last} 
                    first={cardInfos[4].first}/>
                </div>
        </div>
    )
}
export default Search;