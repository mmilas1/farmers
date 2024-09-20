import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../styles/FormStyles.css';

const InspectorPage = () => {
  // States for registering an inspector
  const [inspector, setInspector] = useState({
    name: '',
    email: '',
    password: ''
  });
  const [registrationMessage, setRegistrationMessage] = useState('');

  // States for finding an inspector by email
  const [email, setEmail] = useState('');
  const [foundInspector, setFoundInspector] = useState(null);
  const [searchMessage, setSearchMessage] = useState('');

  // Petition list state
  const [petitions, setPetitions] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');

  // Fetch petitions on component mount
  useEffect(() => {
    const fetchPetitions = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/petitions');
        setPetitions(response.data);
      } catch (error) {
        console.error('Error fetching petitions:', error);
        setErrorMessage('Failed to load petitions. Please try again.');
      }
    };
    fetchPetitions();
  }, []);

  // Handle input changes for registration
  const handleRegistrationChange = (e) => {
    const { name, value } = e.target;
    setInspector({
      ...inspector,
      [name]: value
    });
  };

  // Handle form submission for registering an inspector
  const handleRegistrationSubmit = async (e) => {
    e.preventDefault();

    try {
      await axios.post('/api/inspectors', inspector);
      setRegistrationMessage('Inspector registered successfully!');
      setInspector({
        name: '',
        email: '',
        password: ''
      });
    } catch (error) {
      setRegistrationMessage('Error registering inspector.');
      console.error(error);
    }
  };

  // Handle input change for email search
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  // Fetch inspector by email
  const fetchInspector = async () => {
    try {
      const response = await axios.get(`/api/inspectors/${email}`);
      setFoundInspector(response.data);
      setSearchMessage('');
    } catch (error) {
      setSearchMessage('Inspector not found.');
      setFoundInspector(null);
      console.error(error);
    }
  };

  // Approve Petition
  const approvePetition = async (petitionId) => {
    try {
      await axios.put(`http://localhost:8080/api/petitions/${petitionId}/approve`);
      setPetitions((prev) => prev.filter(p => p.id !== petitionId)); // Remove from list after approval
    } catch (error) {
      console.error('Error approving petition', error);
    }
  };

  // Reject Petition
  const rejectPetition = async (petitionId) => {
    try {
      await axios.put(`http://localhost:8080/api/petitions/${petitionId}/reject`);
      setPetitions((prev) => prev.filter(p => p.id !== petitionId)); // Remove from list after rejection
    } catch (error) {
      console.error('Error rejecting petition', error);
    }
  };

  return (
    <div>
      <h1>Inspector Dashboard</h1>

      {/* Section for registering a new inspector */}
      <div>
        <h2>Register Inspector</h2>
        <form onSubmit={handleRegistrationSubmit}>
          <div>
            <label>Name:</label>
            <input
              type="text"
              name="name"
              value={inspector.name}
              onChange={handleRegistrationChange}
              required
            />
          </div>
          <div>
            <label>Email:</label>
            <input
              type="email"
              name="email"
              value={inspector.email}
              onChange={handleRegistrationChange}
              required
            />
          </div>
          <div>
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={inspector.password}
              onChange={handleRegistrationChange}
              required
            />
          </div>
          <button type="submit">Register Inspector</button>
        </form>
        {registrationMessage && <p>{registrationMessage}</p>}
      </div>

      {/* Section for finding an inspector by email */}
      <div style={{ marginTop: '2rem' }}>
        <h2>Find Inspector by Email</h2>
        <input
          type="email"
          placeholder="Enter inspector email"
          value={email}
          onChange={handleEmailChange}
        />
        <button onClick={fetchInspector}>Find Inspector</button>

        {searchMessage && <p>{searchMessage}</p>}

        {foundInspector && (
          <div>
            <h3>Inspector Details</h3>
            <p><strong>Name:</strong> {foundInspector.name}</p>
            <p><strong>Email:</strong> {foundInspector.email}</p>
          </div>
        )}
      </div>

      {/* Section for managing petitions */}
      <div style={{ marginTop: '2rem' }}>
        <h2>Petition List for Approval</h2>
        {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        <ul>
          {petitions.map((petition) => (
            <li key={petition.id}>
              <p><strong>Description:</strong> {petition.description}</p>
              <p><strong>Location:</strong> {petition.location}</p>
              <p><strong>Status:</strong> {petition.status}</p>

              {petition.status === 'pending' && (
                <>
                  <button onClick={() => approvePetition(petition.id)}>Approve</button>
                  <button onClick={() => rejectPetition(petition.id)}>Reject</button>
                </>
              )}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default InspectorPage;
