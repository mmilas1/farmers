import React, { useState } from 'react'; 
import axios from 'axios';
import '../styles/FormStyles.css'

const FarmerFormComp = ({ onAddFarmer }) => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        if (!name || !email || !password) {
            setError('All fields are required');
            return;
        }
        try {
            const response = await axios.post('http://localhost:8080/api/farmers',
                { name, email, password }
            );
            alert('Farmer registered successfully!');
            onAddFarmer(response.data); // Passing the newly added farmer to the parent component
            setName('');
            setEmail('');
            setPassword('');
            setError('');
        } catch (error) {
            console.error('Error registering farmer', error);
            setError('Failed to register farmer.');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                Name:
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
            </label>
            <label>
                Email:
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} />
            </label>
            <label>
                Password:
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </label>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <button type="submit">Register Farmer</button>
        </form>
    );
};

export default FarmerFormComp;
