import apiPath from "./apiService";

type LoginData = {
    senha:string,
    email:string
}



const loginService = async(loginData:LoginData,setErrors: React.Dispatch<React.SetStateAction<{ senha: string, email: string }>>) =>{
    try{
        const response = await fetch(`${apiPath}/login`,{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
              },
            body: JSON.stringify(loginData),
        }); 

       
        if(!response.ok){
            const errorData = await response.json()
            setErrors(prevErrors => ({
                ...prevErrors,
                senha: errorData.errors.find((msg: string) => msg.toLowerCase().includes('senha')) || '',
                email: errorData.errors.find((msg: string) => msg.toLowerCase().includes('email')) || '',
            }));
            return false;
        }
        const responseData = await response.json(); 
        const token = responseData.token;
        const idUser = responseData.idUser;
        
        localStorage.setItem('token', token); 
        localStorage.setItem('idUser', idUser);
        return true;
        
        
    }
    catch (error) {
        console.error('Erro ao realizar login:', error);
        return false;;
    }   
}


// const checkUserType = async(idUser:number) => {

//         const response = await fetch(`http://localhost:8080/api/users/${idUser}`);

//         const responseData = await response.json();
//         console.log(responseData);

            
// }

export default loginService;