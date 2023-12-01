import axios from 'axios';
import React, { useState } from 'react';
import '../styling/PasswordSafeStyle.css'

function PasswordSafe() {
  const [title, setTitle] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [blocks, setBlocks] = useState([]);
  const [editBlockId, setEditBlockId] = useState(null);

  const handleSignOut = () => {
    sessionStorage.removeItem('token');
    window.location.reload();
  };

  const handleAddBlock = async (e) => {
    e.preventDefault();

    const owner = sessionStorage.getItem('token').replace(/['"]+/g, '');

    const newBlock = {
      title,
      username,
      password,
      owner,
    };

    try {
      const response = await axios.post('http://localhost:8080/createBlock', newBlock);
      
    } catch (error) {
      console.error(error);
    }

    setTitle('');
    setUsername('');
    setPassword('');
  };

  const handleGetAllBlocks = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/Blocks/${sessionStorage.getItem('token').replace(/['"]+/g, '')}`);
      setBlocks(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDeleteBlock = async (id) => {
    try {
      await axios.delete(`http://localhost:8080/deleteBlock/${id}`);
      setBlocks(blocks.filter((block) => block.id !== id)); 
    } catch (error) {
      console.error(error);
    }
  };

  const handleEditBlock = (id) => {
    setEditBlockId(id);
  };

  const handleUpdateBlock = async (id) => {
    try {
      const updatedBlock = {
        title,
        username,
        password,
      };

      await axios.put(`http://localhost:8080/updateBlock/${id}`, updatedBlock);
      
      setEditBlockId(null); 
    } catch (error) {
      console.error(error);
    }
  };

  const listItems = blocks.map((block, index) => (
    <li key={index}>
      {editBlockId === block.id ? (
        
        <form className='editFunction'>
          <p>
            Platform{' '}
            <input type='text' value={title} onChange={(e) => setTitle(e.target.value)} />
          </p>
          <p>
            Username{' '}
            <input type='text' value={username} onChange={(e) => setUsername(e.target.value)} />
          </p>
          <p>
            Password{' '}
            <input type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
          </p>
          <button type='button' onClick={() => handleUpdateBlock(block.id)}>
            Update
          </button>
        </form>
      ) : (
        // Display block details and buttons
        <>
          Plattform: {block.title}, Username: {block.username}, Password: {block.password}
          <button className='del-Button' onClick={() => handleDeleteBlock(block.id)}>
            delete
          </button>
          <button className='ed-Button' onClick={() => handleEditBlock(block.id)}>
            edit
          </button>
        </>
      )}
    </li>
  ));

  return (
    <>
      <div className='passwordSafe-Container'>
        <div>
          <button id='SignOut-Button' className="LogOutButton" onClick={handleSignOut}>
            Logout
          </button>
          <form onSubmit={handleAddBlock} className='Block'>
            <p>
              Platform{' '}
              <br />
              <input type='text' className="InputBlock" value={title} onChange={(e) => setTitle(e.target.value)} />
            </p>
            <p>
              Username{' '}
              <br />
              <input type='text' value={username} className="InputBlock" onChange={(e) => setUsername(e.target.value)} />
            </p>
            <p>
              Password{' '}
              <br />
              <input type='password' value={password} className="InputBlock" onChange={(e) => setPassword(e.target.value)} />
            </p>
            <button type='submit' className='AddButton'>
              Add
            </button>
          </form>

        </div>
        
        <div className='Blocks'>
          <button onClick={handleGetAllBlocks} className='getBlocksButton'>Get All Blocks</button>
          <ul className='Blocklist'>{listItems}</ul>
        </div>
        
      </div>
    </>
  );
}



export default PasswordSafe;
