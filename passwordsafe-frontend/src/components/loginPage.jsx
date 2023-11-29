import passwordSafe from "./passwordSafe"
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';
import Register from "./Register";
import '../styling/LoginStyle.css'

const loginUser = async (credentials) => {
    try {
      const response = await axios.post('http://localhost:8080/login', credentials, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
  
      return response.data;
    } catch (error) {
      console.error('Login failed:', error);
      throw error;
    }
};

function LoginPage({setUserToken}){

    const [mastername, setMastername] = useState('');
    const [masterpassword, setMasterpassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const userData = await loginUser({
        mastername,
        masterpassword
      });

      console.log('User data:', userData);
      setUserToken(userData);

    } catch (error) {
      console.error('Login failed:', error);
    }
  };

    return(
        <>

          
    
        <h1 className="LoginPageTitle">Login</h1>
        <div className="login-Container">
          <form className= "formLogin" onSubmit={handleSubmit}>
              <label className="labelLogin">
                  <p>Username</p>
                  <input type="text" value={mastername} onChange={(e) => setMastername(e.target.value)} />
              </label>
              <label className="labelLogin">
                  <p>Password</p>
                  <input type="password" value={masterpassword} autoComplete="new-password" onChange={(e) => setMasterpassword(e.target.value)} />
              </label>
              <br />
              <div>
                  <button className= "LoginButton" type="submit">Login</button>
              </div>
          </form>
          <Register />
        </div>
        
     
        </>
    )
}
export default LoginPage;