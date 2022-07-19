import React, { useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Router from './Router';
import './styles/common.css';

function App() {
  const { Kakao } = window;

  useEffect(() => {
    Kakao.init('58024163a4e32fd01f0150a8e3667109');
  }, []);

  return (
    <>
      <Router />
    </>
  );
}

export default App;
