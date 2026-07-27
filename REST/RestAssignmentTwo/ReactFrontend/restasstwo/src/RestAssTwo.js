import React, { useState, useEffect } from 'react';

const API_URL = "http://localhost:8080/employees";

function RestAssTwo() {
    const [employees, setEmployees] = useState([]);
    const [formData, setFormData] = useState({ empName: '', department: '', salary: '' });

    const [searchId, setSearchId] = useState('');
    const [searchedEmployee, setSearchedEmployee] = useState(null);
    const [searchError, setSearchError] = useState('');

    useEffect(() => {
        fetchAllEmployees();
    }, []);

    const fetchAllEmployees = async () => {
        try {
            const response = await fetch(API_URL);
            if (response.ok) {
                const data = await response.json();
                setEmployees(data);
            }
        } catch (error) {
            console.error("Error fetching employees:", error);
        }
    };

    const handleSearchEmployee = async (e) => {
        e.preventDefault();
        setSearchError('');
        setSearchedEmployee(null);

        if (!searchId) return;

        try {
            const response = await fetch(`${API_URL}/${searchId}`);
            if (response.ok) {
                const data = await response.json();
                setSearchedEmployee(data);
            } else {
                setSearchError(`Employee with ID ${searchId} not found.`);
            }
        } catch (error) {
            console.error("Error searching employee:", error);
            setSearchError("Failed to fetch employee details.");
        }
    };

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCreateEmployee = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    empName: formData.empName,
                    department: formData.department,
                    salary: parseFloat(formData.salary)
                })
            });

            if (response.ok) {
                setFormData({ empName: '', department: '', salary: '' });
                fetchAllEmployees();
            }
        } catch (error) {
            console.error("Error creating employee:", error);
        }
    };

    return (
        <div style={{ padding: '24px', fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto' }}>
            <h2>REST API (Assignment 2)</h2>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Add New Employee</h3>
                <form onSubmit={handleCreateEmployee} style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
                    <input
                        type="text"
                        name="empName"
                        placeholder="Employee Name (e.g. John)"
                        value={formData.empName}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="text"
                        name="department"
                        placeholder="Department (e.g. IT)"
                        value={formData.department}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="number"
                        name="salary"
                        placeholder="Salary (e.g. 50000)"
                        value={formData.salary}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', width: '120px' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Submit
                    </button>
                </form>
            </div>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Search Employee By ID</h3>
                <form onSubmit={handleSearchEmployee} style={{ display: 'flex', gap: '10px', marginBottom: '12px' }}>
                    <input
                        type="number"
                        placeholder="Enter Employee ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                        required
                        style={{ padding: '8px', width: '180px' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Search
                    </button>
                </form>

                {searchedEmployee && (
                    <div style={{ background: '#f4f4f4', padding: '12px', borderRadius: '4px' }}>
                        <p style={{ margin: '4px 0' }}><strong>Emp ID:</strong> {searchedEmployee.empId}</p>
                        <p style={{ margin: '4px 0' }}><strong>Name:</strong> {searchedEmployee.empName}</p>
                        <p style={{ margin: '4px 0' }}><strong>Department:</strong> {searchedEmployee.department}</p>
                        <p style={{ margin: '4px 0' }}><strong>Salary:</strong> ₹{searchedEmployee.salary}</p>
                    </div>
                )}

                {searchError && <p style={{ color: 'red', margin: 0 }}>{searchError}</p>}
            </div>

            <div>
                <h3>All Employees</h3>
                <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', borderCollapse: 'collapse' }}>
                    <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th>Emp ID</th>
                        <th>Name</th>
                        <th>Department</th>
                        <th>Salary</th>
                    </tr>
                    </thead>
                    <tbody>
                    {employees.length > 0 ? (
                        employees.map((emp) => (
                            <tr key={emp.empId}>
                                <td>{emp.empId}</td>
                                <td>{emp.empName}</td>
                                <td>{emp.department}</td>
                                <td>₹{emp.salary}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: 'center' }}>No employees found</td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default RestAssTwo;