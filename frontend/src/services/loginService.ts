import { useNavigate } from "react-router-dom"


type LoginData = {
    senha:string,
    email:string
}



const loginService = async(data:LoginData) =>{
    try{
        const response = await fetch('http://localhost:8080/api/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
              },
            body: JSON.stringify(data),
        }); 

        return response;
     
    }
    catch (error) {
        console.error('Erro ao realizar login:', error);
        return undefined;
    }   
}

export default loginService;