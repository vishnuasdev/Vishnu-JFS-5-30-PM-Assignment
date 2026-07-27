import React, { useState, useEffect } from 'react';

const API_URL = "http://localhost:8080/api/employees";

function CapstoneProject() {
    const [employees, setEmployees] = useState([]);
    const [formData, setFormData] = useState({ name: '', department: '', salary: '' });
    const [editingId, setEditingId] = useState(null);

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            const response = await fetch(API_URL, {
                headers: { 'Accept': 'application/json' }
            });
            const data = await response.json();
            setEmployees(data);
        } catch (error) {
            console.error("Error fetching employees:", error);
        }
    };

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const payload = {
            name: formData.name,
            department: formData.department,
            salary: parseFloat(formData.salary)
        };

        try {
            if (editingId) {
                await fetch(`${API_URL}/${editingId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    body: JSON.stringify(payload)
                });
                setEditingId(null);
            } else {
                await fetch(API_URL, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    body: JSON.stringify(payload)
                });
            }

            setFormData({ name: '', department: '', salary: '' });
            fetchEmployees();
        } catch (error) {
            console.error("Error saving employee:", error);
        }
    };

    const handleEdit = (emp) => {
        setEditingId(emp.id);
        setFormData({ name: emp.name, department: emp.department, salary: emp.salary });
    };

    const handleDelete = async (id) => {
        try {
            await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
            fetchEmployees();
        } catch (error) {
            console.error("Error deleting employee:", error);
        }
    };

    return (
        <div style={{ maxWidth: '800px', margin: '30px auto', fontFamily: 'Arial, sans-serif' }}>
            <h2>Capstone: Employee Management System</h2>

            <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', marginBottom: '20px' }}>
                <input
                    type="text"
                    name="name"
                    placeholder="Employee Name"
                    value={formData.name}
                    onChange={handleInputChange}
                    required
                />
                <input
                    type="text"
                    name="department"
                    placeholder="Department"
                    value={formData.department}
                    onChange={handleInputChange}
                    required
                />
                <input
                    type="number"
                    name="salary"
                    placeholder="Salary"
                    value={formData.salary}
                    onChange={handleInputChange}
                    required
                />
                <button type="submit">{editingId ? 'Update' : 'Add'}</button>
            </form>

            <table border="1" cellPadding="8" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Department</th>
                    <th>Salary</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {employees.map((emp) => (
                    <tr key={emp.id}>
                        <td>{emp.id}</td>
                        <td>{emp.name}</td>
                        <td>{emp.department}</td>
                        <td>₹{emp.salary}</td>
                        <td>
                            <button onClick={() => handleEdit(emp)} style={{ marginRight: '5px' }}>Edit</button>
                            <button onClick={() => handleDelete(emp.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default CapstoneProject;