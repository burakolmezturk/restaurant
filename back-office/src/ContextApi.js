import React,{useState} from 'react';
const users = {  
      username: "burak",
      password: "1234"  
  };
  
  const AppContext = React.createContext(users.users);
  
  export { users, AppContext };
  
