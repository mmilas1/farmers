import React, { useState } from 'react';
import axios from 'axios';
import '../styles/FormStyles.css'

const InspectorRegistration = () => {
  const [inspector, setInspector] = useState({
    name: '',
    email: '',
    password: ''
  });
  const [message, setMessage] = useState('');

  // Handle input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setInspector({
      ...inspector,
      [name]: value
    });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('/api/inspectors', inspector);
      setMessage('Inspector registered successfully!');
    } catch (error) {
      setMessage('Error registering inspector.');
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Register Inspector</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={inspector.name}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={inspector.email}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={inspector.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit">Register</button>
      </form>
      {message && <p>{message}</p>}
    </div>
  );
};

export default InspectorRegistration;
