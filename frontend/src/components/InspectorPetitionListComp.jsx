import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../styles/FormStyles.css';

const InspectorPetitionListComp = () => {
    const [petitions, setPetitions] = useState([]);
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        const fetchPetitions = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/petitions')
                setPetitions(response.data);
            } catch (error) {
                console.error('Error fetching petitions', error);
                setErrorMessage('Failed to load petitions. Please try again.');
            }
        };
        fetchPetitions();
    }, []);

    // Approve Petition
    const approvePetition = async (petitionId) => {
        try {
            await axios.put(`http://localhost:8080/api/petitions/${petitionId}/approve`, {}, {
                auth: { username: 'admin@example.com', password: 'adminpassword' }
            });
            setPetitions((prev) => prev.filter(p => p.id !== petitionId)); // Remove from list after approval
        } catch (error) {
            console.error('Error approving petition', error);
        }
    };

    // Reject Petition
    const rejectPetition = async (petitionId) => {
        try {
            await axios.put(`http://localhost:8080/api/petitions/${petitionId}/reject`, {}, {
                auth: { username: 'admin@example.com', password: 'adminpassword' }
            });
            setPetitions((prev) => prev.filter(p => p.id !== petitionId)); // Remove from list after rejection
        } catch (error) {
            console.error('Error rejecting petition', error);
        }
    };

    return (
        <div>
            <h2>Petition List for Approval</h2>
            {/* Display error message if fetching fails */}
            {errorMessage && <p style={{ color: 'red' }}>{errorMessage}</p>}

            <ul>
                {petitions.map((petition) => (
                    <li key={petition.id}>
                        <p><strong>Description:</strong> {petition.description}</p>
                        <p><strong>Location:</strong> {petition.location}</p>
                        <p><strong>Status:</strong> {petition.status}</p>

                        {petition.status === 'Pending' && (
                            <>
                                <button onClick={() => approvePetition(petition.id)}>Approve</button>
                                <button onClick={() => rejectPetition(petition.id)}>Reject</button>
                            </>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default InspectorPetitionListComp;
