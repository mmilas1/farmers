import React, { useState } from 'react'; 
import axios from 'axios';
import '../styles/FormStyles.css'

const FarmerFormComp = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const credentials = { username: 'admin@example.com', password: 'adminpassword' };
            // Sending the POST request to the backend with Basic Auth credentials
            await axios.post('http://localhost:8080/api/farmers', 
                { name, email, password }, 
                {
                    auth: {
                        username: credentials.username,
                        password: credentials.password
                    }
                }
            );
            alert('Farmer registered successfully!');
            // Optionally reset the form after a successful submission
            setName('');
            setEmail('');
            setPassword('');
        } catch (error) {
            console.error('Error registering farmer', error);
            alert('Failed to register farmer. Please check your credentials.');
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
            <button type="submit">Register Farmer</button>
        </form>
    );
};

export default FarmerFormComp;
