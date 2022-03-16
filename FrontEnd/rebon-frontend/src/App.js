import React from 'react';
import { Route, Routes } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Detail from './components/pages/Detail';

function App() {
  return (
    <Routes>
      <Route path="/review" element={<Detail />} />
    </Routes>
  );
}

export default App;
