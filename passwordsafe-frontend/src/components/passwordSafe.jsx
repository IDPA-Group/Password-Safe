import React, { useState } from 'react';
import LoginPage from './loginPage';
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom"



function PasswordSafe() {
  const [blocks, setBlocks] = useState([
    
  ]);


  const handleAddBlock = () => {
    const newBlock = {
      title: document.getElementById('titleInput').value,
      username: document.getElementById('usernameInput').value,
      password: document.getElementById('passwordInput').value,
    };

    setBlocks([...blocks, newBlock]);   

    document.getElementById('titleInput').value = '';
    document.getElementById('usernameInput').value = '';
    document.getElementById('passwordInput').value = '';
  };
  const history = useNavigate();

  const handleLogout = () => {
    history('/');

  };

  const listItems = blocks.map((block, index) => (
    <li key={index}>
      Plattform: {block.title}, Username: {block.username}, Password: {block.password}
    </li>
  )
  )
  return (

    <>
    
      <Link ></Link>
      <button className='LogoutButton' onClick={handleLogout}>Log Out</button>
      <ul>{listItems}</ul>
      <div className="Block">
        <p>
          Platform <input type="text" id="titleInput" />
        </p>
        <p>
          Username <input type="text" id="usernameInput" />
        </p>
        <p>
          Password <input type="password" id="passwordInput" />
        </p>
      </div>
      <button className="AddButton" onClick={handleAddBlock}>
        Add
    </button>
    </>
  );

}
export default PasswordSafe;