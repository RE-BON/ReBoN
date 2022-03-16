import React from 'react';
import { Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Review from './components/pages/Review';

function App() {
  return (
    <Routes>
      <Route path="/review" element={<Review />} />
    </Routes>
  );
}

export default App;
