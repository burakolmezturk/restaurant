import React,{useState} from 'react';
const users = {  
      username: "burak",
      password: "1234"  
  };
  
 const loading=false; 
  const AppContext = React.createContext(loading);
  
  export { loading, AppContext };
  
