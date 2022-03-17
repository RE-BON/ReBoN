import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Detail from './pages/Detail';
import Header from './components/Header';
function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/detail" element={<Detail />} />
        <Route path="/header" element={<Header />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
