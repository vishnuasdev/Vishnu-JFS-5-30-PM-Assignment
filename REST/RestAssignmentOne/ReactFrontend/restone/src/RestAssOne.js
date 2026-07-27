import React, { useState, useEffect } from 'react';

const API_URL = "http://localhost:8080/students";

function RestAssOne() {
    const [students, setStudents] = useState([]);
    const [formData, setFormData] = useState({ name: '', course: '', marks: '' });

    const [searchId, setSearchId] = useState('');
    const [searchedStudent, setSearchedStudent] = useState(null);
    const [searchError, setSearchError] = useState('');

    useEffect(() => {
        fetchAllStudents();
    }, []);

    const fetchAllStudents = async () => {
        try {
            const response = await fetch(API_URL);
            if (response.ok) {
                const data = await response.json();
                setStudents(data);
            }
        } catch (error) {
            console.error("Error fetching students:", error);
        }
    };

    const handleSearchStudent = async (e) => {
        e.preventDefault();
        setSearchError('');
        setSearchedStudent(null);

        if (!searchId) return;

        try {
            const response = await fetch(`${API_URL}/${searchId}`);
            if (response.ok) {
                const data = await response.json();
                setSearchedStudent(data);
            } else {
                setSearchError(`Student with ID ${searchId} not found.`);
            }
        } catch (error) {
            console.error("Error searching student:", error);
            console.error("Error searching student:", error);
            setSearchError("Failed to fetch student details.");
        }
    };

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCreateStudent = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    name: formData.name,
                    course: formData.course,
                    marks: parseInt(formData.marks, 10)
                })
            });

            if (response.ok) {
                setFormData({ name: '', course: '', marks: '' });
                fetchAllStudents();
            }
        } catch (error) {
            console.error("Error creating student:", error);
        }
    };

    return (
        <div style={{ padding: '24px', fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto' }}>
            <h2>REST Assignment One</h2>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Add New Student</h3>
                <form onSubmit={handleCreateStudent} style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
                    <input
                        type="text"
                        name="name"
                        placeholder="Name (e.g. Arun)"
                        value={formData.name}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="text"
                        name="course"
                        placeholder="Course (e.g. Java)"
                        value={formData.course}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="number"
                        name="marks"
                        placeholder="Marks (e.g. 85)"
                        value={formData.marks}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', width: '100px' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Submit
                    </button>
                </form>
            </div>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Search Student By ID</h3>
                <form onSubmit={handleSearchStudent} style={{ display: 'flex', gap: '10px', marginBottom: '12px' }}>
                    <input
                        type="number"
                        placeholder="Enter Student ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                        required
                        style={{ padding: '8px', width: '180px' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Search
                    </button>
                </form>

                {searchedStudent && (
                    <div style={{ background: '#f4f4f4', padding: '12px', borderRadius: '4px' }}>
                        <p style={{ margin: '4px 0' }}><strong>ID:</strong> {searchedStudent.id}</p>
                        <p style={{ margin: '4px 0' }}><strong>Name:</strong> {searchedStudent.name}</p>
                        <p style={{ margin: '4px 0' }}><strong>Course:</strong> {searchedStudent.course}</p>
                        <p style={{ margin: '4px 0' }}><strong>Marks:</strong> {searchedStudent.marks}</p>
                    </div>
                )}

                {searchError && <p style={{ color: 'red', margin: 0 }}>{searchError}</p>}
            </div>

            <div>
                <h3>All Students</h3>
                <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', borderCollapse: 'collapse' }}>
                    <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Course</th>
                        <th>Marks</th>
                    </tr>
                    </thead>
                    <tbody>
                    {students.length > 0 ? (
                        students.map((student) => (
                            <tr key={student.id}>
                                <td>{student.id}</td>
                                <td>{student.name}</td>
                                <td>{student.course}</td>
                                <td>{student.marks}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: 'center' }}>No students found</td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default RestAssOne;