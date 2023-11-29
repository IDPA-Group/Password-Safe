import axios from 'axios';
import React, { useState } from 'react';
import '../styling/RegisterStyle.css'

const Register = () => {
  const [mastername, setMastername] = useState('');
  const [masterpassword, setMasterPassword] = useState('');
  const [error, setError] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();

    if (!mastername || !masterpassword) {
      setError('Username and password are required.');
      return;
    }

    try {
      const registerData = {
        mastername,
        masterpassword,
      };
      console.log(registerData)
      const response = await axios.post('http://localhost:8080/createLogin', registerData, {
        headers: {
            'Content-Type': 'application/json'
          }
      })
      
      console.log(response);
      setError('');
    } catch (error) {
      console.error(error);
      setError('Registration failed. Please try again.'); 
    }
    setMastername('')
    setMasterPassword('')

  };

  return (
    <div>
      <h1 className='titleRegister'>Register</h1>
      <form onSubmit={handleRegister} className='formRegister'>
        <p>
          Username{' '}
          <br />

          <input type="text" value={mastername} onChange={(e) => setMastername(e.target.value)} />
        </p>
        <p>
          Password{' '}
          <br />
          <input type="password" value={masterpassword} onChange={(e) => setMasterPassword(e.target.value)} />
        </p>
        
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button className= "RegisterButton" type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
