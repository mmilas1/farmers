import React, { useState } from 'react';
import axios from 'axios';
import '../styles/FormStyles.css'

const PetitionFormComp = () => {
    const [description, setDescription] = useState('');
    const [location, setLocation] = useState('');
    const [farmerId, setFarmerId] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Validation (optional)
        if (!description || !location || !farmerId) {
            setErrorMessage('All fields are required.');
            return;
        }

        try {
            const credentials = { username: 'admin@example.com', password: 'adminpassword' };
            
            await axios.post(
                'http://localhost:8080/api/petitions',
                { description, location, farmerId },
                {
                    auth: {
                        username: credentials.username,
                        password: credentials.password
                    }
                }
            );
            
            alert('Petition submitted successfully!');
            // Clear form
            setDescription('');
            setLocation('');
            setFarmerId('');
            setErrorMessage('');  // Clear any previous error
        } catch (error) {
            console.error('Error submitting petition', error);
            setErrorMessage('Failed to submit petition. Please try again.');
        }
    };

    return (
        <div>
            <h2>Submit a Petition</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Description:
                    <input type="text" value={description} onChange={(e) => setDescription(e.target.value)} />
                </label>
                <label>
                    Location:
                    <input type="text" value={location} onChange={(e) => setLocation(e.target.value)} />
                </label>
                <label>
                    Farmer ID:
                    <input type="number" value={farmerId} onChange={(e) => setFarmerId(e.target.value)} />
                </label>
                <button type="submit">Submit Petition</button>
            </form>

            {/* Display error message if submission fails */}
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}
        </div>
    );
};

export default PetitionFormComp;
