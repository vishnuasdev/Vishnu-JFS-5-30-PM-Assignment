import React, { useState, useEffect } from 'react';

const API_URL = "http://localhost:8080/books";

function RestAssThree() {
    const [books, setBooks] = useState([]);
    const [formData, setFormData] = useState({ title: '', author: '', category: '' });

    const [searchId, setSearchId] = useState('');
    const [searchedBook, setSearchedBook] = useState(null);
    const [searchError, setSearchError] = useState('');

    useEffect(() => {
        fetchAllBooks();
    }, []);

    const fetchAllBooks = async () => {
        try {
            const response = await fetch(API_URL);
            if (response.ok) {
                const data = await response.json();
                setBooks(data);
            }
        } catch (error) {
            console.error("Error fetching books:", error);
        }
    };

    const handleSearchBook = async (e) => {
        e.preventDefault();
        setSearchError('');
        setSearchedBook(null);

        if (!searchId) return;

        try {
            const response = await fetch(`${API_URL}/${searchId}`);
            if (response.ok) {
                const data = await response.json();
                setSearchedBook(data);
            } else {
                setSearchError(`Book with ID ${searchId} not found.`);
            }
        } catch (error) {
            console.error("Error searching book:", error);
            setSearchError("Failed to fetch book details.");
        }
    };

    const handleInputChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleCreateBook = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    title: formData.title,
                    author: formData.author,
                    category: formData.category
                })
            });

            if (response.ok) {
                setFormData({ title: '', author: '', category: '' });
                fetchAllBooks();
            }
        } catch (error) {
            console.error("Error adding book:", error);
        }
    };

    return (
        <div style={{ padding: '24px', fontFamily: 'sans-serif', maxWidth: '800px', margin: '0 auto' }}>
            <h2>Library Book Management API (Assignment 3)</h2>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Add New Book</h3>
                <form onSubmit={handleCreateBook} style={{ display: 'flex', gap: '10px', flexWrap: 'wrap' }}>
                    <input
                        type="text"
                        name="title"
                        placeholder="Book Title (e.g. Spring Boot Mastery)"
                        value={formData.title}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="text"
                        name="author"
                        placeholder="Author (e.g. James)"
                        value={formData.author}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <input
                        type="text"
                        name="category"
                        placeholder="Category (e.g. Programming)"
                        value={formData.category}
                        onChange={handleInputChange}
                        required
                        style={{ padding: '8px', flex: '1' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Submit
                    </button>
                </form>
            </div>

            <div style={{ marginBottom: '30px', padding: '16px', border: '1px solid #ddd', borderRadius: '8px' }}>
                <h3>Search Book By ID</h3>
                <form onSubmit={handleSearchBook} style={{ display: 'flex', gap: '10px', marginBottom: '12px' }}>
                    <input
                        type="number"
                        placeholder="Enter Book ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                        required
                        style={{ padding: '8px', width: '180px' }}
                    />
                    <button type="submit" style={{ padding: '8px 16px', cursor: 'pointer' }}>
                        Search
                    </button>
                </form>

                {searchedBook && (
                    <div style={{ background: '#f4f4f4', padding: '12px', borderRadius: '4px' }}>
                        <p style={{ margin: '4px 0' }}><strong>Book ID:</strong> {searchedBook.bookId}</p>
                        <p style={{ margin: '4px 0' }}><strong>Title:</strong> {searchedBook.title}</p>
                        <p style={{ margin: '4px 0' }}><strong>Author:</strong> {searchedBook.author}</p>
                        <p style={{ margin: '4px 0' }}><strong>Category:</strong> {searchedBook.category}</p>
                    </div>
                )}

                {searchError && <p style={{ color: 'red', margin: 0 }}>{searchError}</p>}
            </div>

            <div>
                <h3>All Library Books</h3>
                <table border="1" cellPadding="10" cellSpacing="0" style={{ width: '100%', borderCollapse: 'collapse' }}>
                    <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th>Book ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Category</th>
                    </tr>
                    </thead>
                    <tbody>
                    {books.length > 0 ? (
                        books.map((book) => (
                            <tr key={book.bookId}>
                                <td>{book.bookId}</td>
                                <td>{book.title}</td>
                                <td>{book.author}</td>
                                <td>{book.category}</td>
                            </tr>
                        ))
                    ) : (
                        <tr>
                            <td colSpan="4" style={{ textAlign: 'center' }}>No books found</td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default RestAssThree;