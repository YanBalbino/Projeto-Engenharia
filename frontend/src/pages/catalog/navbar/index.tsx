import React, { useState } from 'react';
import { SearchOutlined, MenuOutlined,UserOutlined} from "@ant-design/icons";
const Navbar = () => {
    const [navbarSelect,setNavbarSelect] = useState(1);

    const handleNavbar = (n:number) => {
        setNavbarSelect(n);
    }
    return(
        <div className="mt-5 ml-5 flex flex-row justify-between">
            <ul className="flex flex-row gap-5 text-xl">
            {['StreamIt!', 'Catálogo', 'Filmes', 'Séries', 'Esportes'].map((item, index) => (
                <li key={index} className={`relative hover:cursor-pointer ${navbarSelect === index + 1 ? 'underline decoration-1 underline-offset-4' : ''}`} onClick={() => handleNavbar(index + 1)}>{item}</li>
                ))}
            </ul>
            <ul className='mr-5 flex flex-row gap-10 text-xl '>
            <SearchOutlined className='w-8 h-8 p-1 rounded-full hover:cursor-pointer hover:bg-white hover:text-black' />
            <MenuOutlined className='w-8 h-8 p-1 rounded-full hover:cursor-pointer hover:bg-white hover:text-black' />
            <UserOutlined className='w-8 h-8 p-1 rounded-full hover:cursor-pointer hover:bg-white hover:text-black' />
            </ul>
        </div>
    )
}

export default Navbar;