type inputProps = {
    name:string;
    type: 'text'| 'password';
}

const RegisterInput: React.FC<inputProps>  = ({name,type}) =>{
    return(
        <div className=" w-11/12 flex flex-col">
            <label htmlFor="" className=" ml-2">{name}</label>
            <input type={type} name="" id="" className=" h-10 rounded-lg text-black p-2"/>
         </div>
    )
}


export default RegisterInput;