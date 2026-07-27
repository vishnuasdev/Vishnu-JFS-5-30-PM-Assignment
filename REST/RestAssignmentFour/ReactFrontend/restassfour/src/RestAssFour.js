    import React, { useState, useEffect } from 'react';

    const API_URL = "http://localhost:8080/customers";

    function RestAssFour() {
        const [customers, setCustomers] = useState([]);
        const [formData, setFormData] = useState({ customerName: '', city: '', mobileNumber: '' });
        const [editingId, setEditingId] = useState(null);
        const [statusMessage, setStatusMessage] = useState('');

        // --- Search state ---
        const [searchId, setSearchId] = useState('');
        const [searchedCustomer, setSearchedCustomer] = useState(null);
        const [searchError, setSearchError] = useState('');

        useEffect(() => {
            fetchAllCustomers();
        }, []);

        const fetchAllCustomers = async () => {
            try {
                const response = await fetch(API_URL);
                if (response.ok) {
                    const data = await response.json();
                    setCustomers(data);
                }
            } catch (error) {
                console.error("Error fetching customers:", error);
            }
        };

        const handleSearchCustomer = async (e) => {
            e.preventDefault();
            setSearchError('');
            setSearchedCustomer(null);

            if (!searchId) return;

            try {
                const response = await fetch(`${API_URL}/${searchId}`);
                if (response.ok) {
                    const data = await response.json();
                    setSearchedCustomer(data);
                } else {
                    setSearchError(`Customer with ID ${searchId} not found.`);
                }
            } catch (error) {
                console.error("Error searching customer:", error);
                setSearchError("Failed to fetch customer details.");
            }
        };

        const handleInputChange = (e) => {
            setFormData({ ...formData, [e.target.name]: e.target.value });
        };

        const handleSubmit = async (e) => {
            e.preventDefault();
            setStatusMessage('');

            const method = editingId ? 'PUT' : 'POST';
            const url = editingId ? `${API_URL}/${editingId}` : API_URL;

            try {
                const response = await fetch(url, {
                    method: method,
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(formData)
                });

                if (response.ok) {
                    const data = await response.json();
                    setStatusMessage(data.message);
                    setFormData({ customerName: '', city: '', mobileNumber: '' });
                    setEditingId(null);
                    fetchAllCustomers();
                }
            } catch (error) {
                console.error("Error submitting customer form:", error);
                setStatusMessage("Operation failed. Check server log.");
            }
        };

        const handleEdit = (customer) => {
            setEditingId(customer.customerId);
            setFormData({
                customerName: customer.customerName,
                city: customer.city,
                mobileNumber: customer.mobileNumber
            });
        };

        const handleCancelEdit = () => {
            setEditingId(null);
            setFormData({ customerName: '', city: '', mobileNumber: '' });
        };

        const handleDelete = async (id) => {
            if (!window.confirm(`Are you sure you want to delete customer ID ${id}?`)) return;

            try {
                const response = await fetch(`${API_URL}/${id}`, {
                    method: 'DELETE'
                });

                if (response.ok) {
                    const data = await response.json();
                    setStatusMessage(data.message);
                    fetchAllCustomers();
                }
            } catch (error) {
                console.error("Error deleting customer:", error);
                setStatusMessage("Failed to delete customer.");
            }
        };

        return (
            <div style={{ padding: '24px', fontFamily: 'sans-serif', maxWidth: '850px', margin: '0 auto' }}>
                <h2>REST API (Assignment 4)</h2>

                {statusMessage && (
                    <div style={{ padding: '10px 16px', backgroundColor: '#e2f0d9', color: '#385723', border: '1px solid #c5e0b4', borderRadius: '4px', marginBottom: '20px' }}>
                        {statusMessage}
                    </div>
                )}

                <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                    <h3>{editingId ? `Update Customer (ID: ${editingId})` : 'Add New Customer'}</h3>
                    <form onSubmit={handleSubmit} style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
                        <input
                            type="text"
                            name="customerName"
                            placeholder="Customer Name (e.g. Vishnu)"
                            value={formData.customerName}
                            onChange={handleInputChange}
                            required
                            style={{ padding: '8px', flex: '1' }}
                        />
                        <input
                            type="text"
                            name="city"
                            placeholder="City (e.g. Tindivanam)"
                            value={formData.city}
                            onChange={handleInputChange}
                            required
                            style={{ padding: '8px', flex: '1' }}
                        />
                        <input
                            type="text"
                            name="mobileNumber"
                            placeholder="Mobile (e.g. 9876543210)"
                            value={formData.mobileNumber}
                            onChange={handleInputChange}
                            required
                            style={{ padding: '8px', flex: '1' }}
                        />
                        <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer', backgroundColor: editingId ? '#2196F3' : '#4CAF50', color: '#fff', border: 'none', borderRadius: '4px' }}>
                            {editingId ? 'Update' : 'Submit'}
                        </button>
                        {editingId && (
                            <button type="button" onClick={handleCancelEdit} style={{ padding: '8px 16px', cursor: 'pointer', backgroundColor: '#9e9e9e', color: '#fff', border: 'none', borderRadius: '4px' }}>
                                Cancel
                            </button>
                        )}
                    </form>
                </div>

                {/* --- Find By ID Section --- */}
                <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                    <h3>Search Customer By ID</h3>
                    <form onSubmit={handleSearchCustomer} style={{ display: 'flex', gap: '10px', marginBottom: '12px' }}>
                        <input
                            type="number"
                            placeholder="Enter Customer ID"
                            value={searchId}
                            onChange={(e) => setSearchId(e.target.value)}
                            required
                            style={{ padding: '8px', width: '180px' }}
                        />
                        <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                            Search
                        </button>
                    </form>

                    {searchedCustomer && (
                        <div style={{ background: '#f4f4f4', padding: '12px', borderRadius: '4px' }}>
                            <p style={{ margin: '4px 0' }}><strong>ID:</strong> {searchedCustomer.customerId}</p>
                            <p style={{ margin: '4px 0' }}><strong>Name:</strong> {searchedCustomer.customerName}</p>
                            <p style={{ margin: '4px 0' }}><strong>City:</strong> {searchedCustomer.city}</p>
                            <p style={{ margin: '4px 0' }}><strong>Mobile:</strong> {searchedCustomer.mobileNumber}</p>
                        </div>
                    )}

                    {searchError && <p style={{ color: 'red', margin: 0 }}>{searchError}</p>}
                </div>

                <div>
                    <h3>Customer Directory</h3>
                    <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', borderCollapse: 'collapse' }}>
                        <thead>
                        <tr style={{ backgroundColor: '#f2f2f2' }}>
                            <th>ID</th>
                            <th>Name</th>
                            <th>City</th>
                            <th>Mobile Number</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {customers.length > 0 ? (
                            customers.map((cust) => (
                                <tr key={cust.customerId}>
                                    <td>{cust.customerId}</td>
                                    <td>{cust.customerName}</td>
                                    <td>{cust.city}</td>
                                    <td>{cust.mobileNumber}</td>
                                    <td style={{ textAlign: 'center' }}>
                                        <button onClick={() => handleEdit(cust)} style={{ marginRight: '8px', padding: '4px 8px', cursor: 'pointer' }}>
                                            Edit
                                        </button>
                                        <button onClick={() => handleDelete(cust.customerId)} style={{ padding: '4px 8px', cursor: 'pointer', color: 'red' }}>
                                            Delete
                                        </button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="5" style={{ textAlign: 'center' }}>No customers found</td>
                            </tr>
                        )}
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }

    export default RestAssFour;