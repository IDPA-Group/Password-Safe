import axios from 'axios';
import React, { useState } from 'react';

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
      <h2>Register</h2>
      <form onSubmit={handleRegister}>
        <p>
          Username{' '}
          <input type="text" value={mastername} onChange={(e) => setMastername(e.target.value)} />
        </p>
        <p>
          Password{' '}
          <input type="password" value={masterpassword} onChange={(e) => setMasterPassword(e.target.value)} />
        </p>
        {error && <p style={{ color: 'red' }}>{error}</p>}
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
