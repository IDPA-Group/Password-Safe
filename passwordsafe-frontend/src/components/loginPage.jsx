import passwordSafe from "./passwordSafe"
import React, { useState } from 'react';
import PropTypes from 'prop-types';
import axios from 'axios';

const loginUser = async (credentials) => {
    try {
      const response = await axios.post('http://localhost:3005/users/login', credentials, {
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

function LoginPage(){

    const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const userData = await loginUser({
        username,
        password
      });

      console.log('User data:', userData);
      setUserToken(userData.token);

    } catch (error) {
      console.error('Login failed:', error);
    }
  };

    return(
        <>
        <h1>Log in</h1>
        <form onSubmit={handleSubmit}>
            <label>
                <p>Username</p>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            </label>
            <label>
                <p>Password</p>
                <input type="password" value={password} autoComplete="new-password" onChange={(e) => setPassword(e.target.value)} />
            </label>
            <div>
                <button type="submit">Submit</button>
            </div>
        </form>
        </>
    )
}
export default LoginPage;